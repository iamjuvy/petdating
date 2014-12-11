/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import model.UserAccount;

/**
 *
 * @author yyun
 */

public class AdminAccounntValidation {
    @PrePersist
    @PreUpdate
    public void validate(UserAccount user){
        if (user.getUserName()==null || "".equals(user.getUserName())){
            throw new IllegalArgumentException("Invalid user name");
        }else if (user.getPassword()==null || "".equals(user.getPassword())){
            throw new IllegalArgumentException("Invalid password");
        }
    }
}
