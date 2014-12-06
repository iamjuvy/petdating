/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Member;

/**
 *
 * @author FrancisAerol
 */
public class TempStorage {

    private TempStorage() {
        //singleton
    }
    private static TempStorage temp;
    private Member member;

    public static TempStorage getInstance() {
        if (temp == null) {
            temp = new TempStorage();
        }
        return temp;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

}
