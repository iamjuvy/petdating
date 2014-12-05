/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author atan
 */
@Entity
public class AdminAccount extends UserAccount implements Serializable {
    private String position;

    public AdminAccount() {
    }

    public AdminAccount(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
    
    
    
    
    
    
}
