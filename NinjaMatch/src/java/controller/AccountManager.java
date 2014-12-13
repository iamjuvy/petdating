/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.ejb.Singleton;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.AdminAccount;

/**
 *
 * @author YunYusin
 */
@Singleton
public class AccountManager {
    private AdminAccount selectedAmin;
    private AdminAccount admin;

    public AdminAccount getSelectedAmin() {
        return selectedAmin;
    }

    public void setSelectedAmin(AdminAccount selectedAmin) {
        this.selectedAmin = selectedAmin;
    }

    public AdminAccount getAdmin() {
        return admin;
    }

    public void setAdmin(AdminAccount admin) {
        this.admin = admin;
    }
}
