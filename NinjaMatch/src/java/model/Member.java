/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 *
 * @author kulkin1
 */
@Entity
public class Member extends UserAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Transient
    private int age;
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "member_photo_fk")
    private List<Photo> photos;
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "member_preference_fk")
    private List<Preference> preferences = new ArrayList();
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "member_profileview_fk")
    private List<ProfileView> profileviews = new ArrayList();
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "member_message_fk")
    private List<Message> messages = new ArrayList();
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "VALUE")
    private List<Ethnicity> ethnicities = new ArrayList();

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the photos
     */
    public List<Photo> getPhotos() {
        return photos;
    }

    /**
     * @param photos the photos to set
     */
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    /**
     * @return the preferences
     */
    public List<Preference> getPreferences() {
        return preferences;
    }

    /**
     * @param preferences the preferences to set
     */
    public void setPreferences(List<Preference> preferences) {
        this.preferences = preferences;
    }

    /**
     * @return the profileviews
     */
    public List<ProfileView> getProfileviews() {
        return profileviews;
    }

    /**
     * @param profileviews the profileviews to set
     */
    public void setProfileviews(List<ProfileView> profileviews) {
        this.profileviews = profileviews;
    }

    /**
     * @return the messages
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * @param messages the messages to set
     */
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    /**
     * @return the ethnicities
     */
    public List<Ethnicity> getEthnicities() {
        return ethnicities;
    }

    /**
     * @param ethnicities the ethnicities to set
     */
    public void setEthnicities(List<Ethnicity> ethnicities) {
        this.ethnicities = ethnicities;
    }

}
