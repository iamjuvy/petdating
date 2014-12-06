/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author FrancisAerol
 */
public class GoogleGeocode {

    public Location geocodeParser(String geoCode) {
        String[] points = geoCode.split(",");
        return new Location(points[0], points[1]);
    }

    public enum Measurement {

        MILES,
        KILOMETER
    }

    private static final double EARTH_RADIUS_Mi = 3956.0;
    private static final double EARTH_RADIUS_KM = 6367.0;

    private static double ToRadian(double val) {
        return val * (Math.PI / 180);
    }

    private static double DiffRadian(double val1, double val2) {
        return ToRadian(val2) - ToRadian(val1);
    }

    public String calculateDistance(String geoCode1, String geoCode2, Measurement m) {
        String[] pointA = geoCode1.split(",");
        String[] pointB = geoCode2.split(",");
        double result = calculateDistance(Double.parseDouble(pointA[0]), Double.parseDouble(pointA[1]), Double.parseDouble(pointB[0]), Double.parseDouble(pointB[1]), m);
        return String.valueOf(result);

    }

    private double calculateDistance(double lat1, double lng1, double lat2, double lng2, Measurement m) {

        double radius = EARTH_RADIUS_Mi;
        if (m == Measurement.KILOMETER) {
            radius = EARTH_RADIUS_KM;
        }
        return radius * 2 * Math.asin(Math.min(1, Math.sqrt((Math.pow(Math.sin((DiffRadian(lat1, lat2)) / 2.0), 2.0)
                + Math.cos(ToRadian(lat1)) * Math.cos(ToRadian(lat2)) * Math.pow(Math.sin((DiffRadian(lng1, lng2)) / 2.0), 2.0)))));
    }

    private GoogleGeocode() {
        //singleton
    }

    private static GoogleGeocode _instance;

    public static GoogleGeocode getInstance() {
        if (_instance == null) {
            _instance = new GoogleGeocode();
        }
        return _instance;
    }

    private static final String URL = "http://maps.googleapis.com/maps/api/geocode/json";

    protected GoogleResponse convertToLatLong(String fullAddress) throws IOException {

        URL url = new URL(URL + "?address="
                + URLEncoder.encode(fullAddress, "UTF-8") + "&sensor=false");
        // Open the Connection
        URLConnection conn = url.openConnection();

        InputStream in = conn.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        GoogleResponse response = (GoogleResponse) mapper.readValue(in, GoogleResponse.class);
        in.close();
        return response;

    }

    protected GoogleResponse convertFromLatLong(String latlongString) throws IOException {

        URL url = new URL(URL + "?latlng="
                + URLEncoder.encode(latlongString, "UTF-8") + "&sensor=false");
        // Open the Connection
        URLConnection conn = url.openConnection();

        InputStream in = conn.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        GoogleResponse response = (GoogleResponse) mapper.readValue(in, GoogleResponse.class);
        in.close();
        return response;
    }

    public String getGeoCode(String address) {
        String geoCode = "";
        try {

            GoogleResponse res = new GoogleGeocode().convertToLatLong(address);
            if (res.getStatus().equals("OK")) {
                for (Result result : res.getResults()) {
                    System.out.println("Lattitude of address is :" + result.getGeometry().getLocation().getLat());
                    System.out.println("Longitude of address is :" + result.getGeometry().getLocation().getLng());
                    System.out.println("Location is " + result.getGeometry().getLocation_type());
                    geoCode = result.getGeometry().getLocation().getLat() + ", " + result.getGeometry().getLocation().getLng();
                }
            } else {
                geoCode = "0,0";
            }

        } catch (IOException ex) {
            Logger.getLogger(GoogleGeocode.class.getName()).log(Level.SEVERE, null, ex);
        }
        return geoCode;
    }
}
