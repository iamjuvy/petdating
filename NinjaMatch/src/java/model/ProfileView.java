/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author kulkin1
 */
@Entity
public class ProfileView implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date visitedDate;
    @ManyToOne
    private MemberAccount member;
    @JoinColumn (name = "ID_MEMBER_VISITED")
    private MemberAccount memberVisited;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the visitedDate
     */
    public Date getVisitedDate() {
        return visitedDate;
    }

    /**
     * @param visitedDate the visitedDate to set
     */
    public void setVisitedDate(Date visitedDate) {
        this.visitedDate = visitedDate;
    }

    /**
     * @return the member
     */
    public MemberAccount getMember() {
        return member;
    }

    /**
     * @param member the member to set
     */
    public void setMember(MemberAccount member) {
        this.member = member;
    }

    /**
     * @return the memberVisited
     */
    public MemberAccount getMemberVisited() {
        return memberVisited;
    }

    /**
     * @param memberVisited the memberVisited to set
     */
    public void setMemberVisited(MemberAccount memberVisited) {
        this.memberVisited = memberVisited;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfileView)) {
            return false;
        }
        ProfileView other = (ProfileView) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ProfileView[ id=" + id + " ]";
    }
    
}
