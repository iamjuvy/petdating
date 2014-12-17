/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.AdminAccountFacade;
import ejb.MemberFacade;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import model.AdminAccount;
import model.MemberAccount;

/**
 *
 * @author yyun
 */
@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    private String userName;
    private String password;
    private AdminAccount admin;
    private MemberAccount member;
    @Inject
    MemberFacade memberFacade;
    @Inject
    AdminAccountFacade adminFacade;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AdminAccount getAdmin() {
        return admin;
    }

    public void setAdmin(AdminAccount admin) {
        this.admin = admin;
    }

    public MemberAccount getMember() {
        return member;
    }

    public void setMember(MemberAccount member) {
        this.member = member;
    }

    public void register() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "/faces/pages/customer/customer_registration.xhtml");
        } catch (IOException ex) {

        }
    }

    public void login() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        admin = adminFacade.findByUsernamePassword(userName, password);
        if (admin != null) {
            ec.redirect(ec.getRequestContextPath() + "/faces/pages/admin/" + "home.xhtml");
        } else {
            member = memberFacade.findByUsernamePassword(userName, password);
            if (member == null) {
                return;
            }
            ec.getSessionMap().put("user", member);
            ec.redirect(ec.getRequestContextPath() + "/faces/pages/customer/customer_home.xhtml");
        }
    }

    public void logout() throws IOException {
        admin = null;
        member = null;
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        ec.redirect(ec.getRequestContextPath() + "/faces/" + "login.xhtml");

    }
}
