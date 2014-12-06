/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.UserFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import model.MemberAccount;

/**
 *
 * @author atan
 */
@ManagedBean(name = "signOnBean")
@SessionScoped
public class SignOnController implements Serializable {

    private String userName;
    private String password;
    private String errorMessage;
    @EJB
    private UserFacade ejbUserFacade;

    @EJB
    private TempCache t;

    private MemberAccount member;

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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public MemberAccount getMember() {
        return member;
    }

    public void verifyPassword() {

        if (userName.equals("admin") && password.equals("admin")) {
//            return "AdminWelcome";
        }

        List<MemberAccount> queryList = ejbUserFacade.validateUser(getUserName(), getPassword());
        if (!(queryList.isEmpty())) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            try {
                ec.redirect(ec.getRequestContextPath() + "/faces/pages/customer/customer_home.xhtml");
            } catch (IOException ex) {
//                Logger.getLogger(this.class.getName()).log(Level.SEVERE, null, ex);
            }
            t.setMember(queryList.get(0));
        }
    }

    public void register() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "/faces/pages/customer/customer_registration.xhtml");
        } catch (IOException ex) {
//                Logger.getLogger(this.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
