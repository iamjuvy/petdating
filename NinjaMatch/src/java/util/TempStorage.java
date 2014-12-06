/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import model.MemberAccount;

/**
 *
 * @author FrancisAerol
 */
public class TempStorage {

    private TempStorage() {
        //singleton
    }
    private static TempStorage temp;
    private MemberAccount member;

    public static TempStorage getInstance() {
        if (temp == null) {
            temp = new TempStorage();
        }
        return temp;
    }

    public MemberAccount getMember() {
        return member;
    }

    public void setMember(MemberAccount member) {
        this.member = member;
    }

}
