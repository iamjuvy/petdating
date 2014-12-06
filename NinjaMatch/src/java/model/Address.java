/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Embeddable;


/**
 *
 * @author atan
 */
@Embeddable
public class Address implements Serializable {

    private String street;
    private String city;
    private String state;
    private String zip;
    private String geoCode;

    public Address() {
    }

    public Address(String street, String city, String state, String zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public Address(String street, String city, String state, String zip, String geoCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.geoCode = geoCode;
    }
    
    
    
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getGeoCode() {
        return geoCode;
    }
    
    

}
