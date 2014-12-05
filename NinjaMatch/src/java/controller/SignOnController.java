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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Query;
import model.UserAccount;

/**
 *
 * @author atan
 */
@ManagedBean(name="signOnBean")
@SessionScoped
public class SignOnController implements Serializable {
    private String userName;
    private String password;
    private String errorMessage;
    @EJB
    private UserFacade ejbUserFacade;

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
    
    public String verifyPassword(){
       
        if(userName.equals("admin")&& password.equals("admin"))
            return "AdminWelcome";
        
        String jpql = "SELECT c FROM UserAccount c WHERE c.userName= : custName AND c.password= :pass";
        Query query = ejbUserFacade.getEntityManager().createQuery(jpql, UserAccount.class);
        query.setParameter("custName",getUserName());
        query.setParameter("pass",getPassword());
        List<UserAccount> queryList = query.getResultList();
        if(queryList.isEmpty()){
            errorMessage="Invalid user name and password ";
            return "login";
        }
         return "welcomeCustomer";
    }
    
    
    
    
}
