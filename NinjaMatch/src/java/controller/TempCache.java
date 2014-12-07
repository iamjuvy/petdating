/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import model.MemberAccount;

/**
 *
 * @author FrancisAerol
 */
@Singleton
public class TempCache {
    
    private MemberAccount member;

    @PostConstruct
    void init() {
        member = null;
    }

    public void setMember(MemberAccount member) {
        this.member = member;
    }

    public MemberAccount getMember() {
        return member;
    }
}
