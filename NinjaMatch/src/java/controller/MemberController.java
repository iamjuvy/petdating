/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.MemberFacade;
import ejb.UserFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import model.Address;
import model.Ethnicity;
import model.MemberAccount;
import util.DateUtil;
import util.GoogleGeocode;

/**
 *
 * @author FrancisAerol
 */
@ManagedBean(name = "memberBean")
@Stateless
public class MemberController implements Serializable {

    @EJB
    private MemberFacade ejbMemberFacade;
    @EJB
    private UserFacade ejbUserFacade;

    @EJB
    private DateUtil dateUtil;
    @EJB
    private GoogleGeocode geocoder;

    private MemberAccount member;
    private ArrayList<String> states;
    private List <Ethnicity> ethnicities = new ArrayList<Ethnicity>();

    private Address address;
    private String month;
    private String year;
    private String day;
  

    @PostConstruct
    public void init() {
        states = new ArrayList<>();
        states.add("Alabama");
        states.add("Alaska");
        states.add("Arizona");
        states.add("Arkansas");
        states.add("California");
        states.add("Colorado");
        states.add("Connecticut");
        states.add("Delaware");
        states.add("Florida");
        states.add("Georgia");
        states.add("Hawaii");
        states.add("Idaho");
        states.add("Illinois");
        states.add("Indiana");
        states.add("Iowa");
        states.add("Kansas");
        states.add("Kentucky");
        states.add("Louisiana");
        states.add("Maine");
        states.add("Maryland");
        states.add("Massachusetts");
        states.add("Michigan");
        states.add("Minnesota");
        states.add("Mississippi");
        states.add("Missouri");
        states.add("Montana");
        states.add("Nebraska");
        states.add("Nevada");
        states.add("New Hampshire");
        states.add("New Jersey");
        states.add("New Mexico");
        states.add("New York");
        states.add("North Carolina");
        states.add("North Dakota");
        states.add("Ohio");
        states.add("Oklahoma");
        states.add("Oregon");
        states.add("Pennsylvania");
        states.add("Rhode Island");
        states.add("South Carolina");
        states.add("South Dakota");
        states.add("Tennessee");
        states.add("Texas");
        states.add("Utah");
        states.add("Vermont");
        states.add("Virginia");
        states.add("Washington");
        states.add("West Virginia");
        states.add("Wisconsin");
        states.add("Wyoming");
    }

    public Address getAddress() {
        if (address == null) {
            address = new Address();
        }
        return address;
    }

    public MemberAccount getMember() {
        if (member == null) {
            member = new MemberAccount();
        }
        return member;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDay() {
        return day;
    }

    public List<Ethnicity> getEthnicities() {
        return ethnicities;
    }

    public void setEthnicities(List<Ethnicity> ethnicities) {
        this.ethnicities = ethnicities;
    }
    

    public void setDay(String day) {
        this.day = day;
    }

    public ArrayList<String> getStates() {
        return states;
    }

    public void save() {
        member = new MemberAccount(getMember().getUserName(),
                getMember().getPassword(),
                getMember().getFirstName(),
                getMember().getLastname(),
                getMember().getGender(),
                getMember().getBirthDate(),
                getMember().getEmail(),
                dateUtil.getCurrentDate());
       

        String geoCode = geocoder.getGeoCode(getAddress().getStreet() + " " + getAddress().getCity() + " " + getAddress().getState());
        address = new Address(getAddress().getStreet(),
                getAddress().getCity(),
                getAddress().getState(),
                getAddress().getZip(), geoCode);
       
        getMember().setEthnicities(getEthnicities());
        getMember().setAddress(address);
        ejbMemberFacade.create(member);
        
        System.out.println("Save Successfully!");

        List<MemberAccount> queryList = ejbUserFacade.validateUser(getMember().getUserName(), getMember().getPassword());
        if (!(queryList.isEmpty())) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.getSessionMap().put("user", queryList.get(0));
            try {
                ec.redirect(ec.getRequestContextPath() + "/faces/pages/customer/customer_home.xhtml");
            } catch (IOException ex) {

            }
        }
    }

    public void update() {
        
        ejbMemberFacade.edit(member);
        System.out.println("Update Successfully!");
    }

    public void validateUserId(FacesContext f, UIComponent c, Object obj) {
        String s = (String) obj;
        if (s.length() == 0) {
            throw new ValidatorException(new FacesMessage("UserID cannot be empty."));
        }
        if (ejbMemberFacade.isUserNameExist(s)) {
            throw new ValidatorException(new FacesMessage("UserID is already used."));
        }
    }
}
