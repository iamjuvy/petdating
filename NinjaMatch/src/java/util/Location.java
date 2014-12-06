/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author FrancisAerol
 */
public class Location {

    private String lat;

    private String lng;

    public Location() {
    }

    public Location(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getDLat() {
        return Double.parseDouble(lat);
    }

    public double getDLng() {
        return Double.parseDouble(lng);
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

}
