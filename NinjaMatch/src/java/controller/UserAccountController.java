/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.UserFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.Query;
import model.Address;
import model.UserAccount;

/**
 *
 * @author jcabriana
 */
@ManagedBean(name = "userBean")
@SessionScoped
public class UserAccountController implements Serializable {

    private UserAccount user;
    private Address address;
    @EJB
    private UserFacade ejbUser;

    public UserAccount getUser() {
        if (user == null) {
            user = new UserAccount();
        }
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public Address getAddress() {
        if (address == null) {
            address = new Address();
        }
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public UserAccount getAccount(java.lang.Long id) {
        return ejbUser.find(id);
    }

    public void save() {
        user = new UserAccount(getUser().getUserName(), getUser().getPassword(), getUser().getFirstName(), getUser().getLastname(),
                getUser().getGender(), getUser().getBirthDate(), getUser().getEmail(), getUser().getRegisteredDate());
        address = new Address(getAddress().getStreet(), getAddress().getCity(), getAddress().getState(), getAddress().getZip());
        getUser().setAddress(address);
        ejbUser.create(user);
        System.out.println("Save Successfully!");
    }

    public void search() {
        String jpql = "SELECT c FROM UserAccount c WHERE c.userName LIKE :custName";
        Query query = ejbUser.getEntityManager().createQuery(jpql, UserAccount.class);
        query.setParameter("custName", getUser().getUserName());
        List<UserAccount> queryList = query.getResultList();
        for (UserAccount acct : queryList) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Test Selected " + acct.getFirstName(), ""));
        }
        
        ejbUser.edit(user);
    }
}
