/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.AdminAccountFacade;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import model.AdminAccount;

/**
 *
 * @author yyun
 */
@ManagedBean
@RequestScoped
public class AdminController {
    @Inject 
    private AdminAccountFacade adminFacade;
    
    private AdminAccount admin;
    
    @PostConstruct
    public void init(){
        admin = new AdminAccount();
    }
    
    public void save(){
        
    }

    /**
     * @return the admin
     */
    public AdminAccount getAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(AdminAccount admin) {
        this.admin = admin;
    }
    
}
