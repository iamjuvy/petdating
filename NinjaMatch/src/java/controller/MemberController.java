/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.MemberFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.Query;
import model.Address;
import model.Member;
import model.UserAccount;

/**
 *
 * @author FrancisAerol
 */
@ManagedBean(name = "memberBean")
@SessionScoped
public class MemberController implements Serializable {

    @EJB
    private MemberFacade ejbMemberFacade;

    private Member member;
    private Address address;

    public Address getAddress() {
        if (address == null) {
            address = new Address();
        }
        return address;
    }

    public Member getMember() {
        if (member == null) {
            member = new Member();
        }
        return member;

    }

    public void save() {
        member = new Member(getMember().getUserName(), getMember().getPassword(), getMember().getFirstName(), getMember().getLastname(),
                getMember().getGender(), getMember().getBirthDate(), getMember().getEmail(), getMember().getRegisteredDate());
        address = new Address(getAddress().getStreet(), getAddress().getCity(), getAddress().getState(), getAddress().getZip());
        getMember().setAddress(address);
        ejbMemberFacade.create(member);
        System.out.println("Save Successfully!");

        String jpql = "SELECT c FROM UserAccount c WHERE c.userName = :custName AND c.password = :pass";
        Query query = ejbMemberFacade.getEntityManager().createQuery(jpql, UserAccount.class);
        query.setParameter("custName", getMember().getUserName());
        query.setParameter("pass", getMember().getPassword());
        List<Member> queryList = query.getResultList();
        if (!(queryList.isEmpty())) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            try {
                ec.redirect(ec.getRequestContextPath() + "/faces/pages/customer/customer_home.xhtml");
            } catch (IOException ex) {
//                Logger.getLogger(this.class.getName()).log(Level.SEVERE, null, ex);
            }
            TempStorage.getInstance().setMember(queryList.get(0));
        }
    }
}
