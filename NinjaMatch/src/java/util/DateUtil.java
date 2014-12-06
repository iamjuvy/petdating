/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FrancisAerol
 */
public class DateUtil {

    private DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");

    private DateUtil() {
        //singleton
    }

    private static DateUtil _instance;

    public static DateUtil getInstance() {
        if (_instance == null) {
            _instance = new DateUtil();
        }
        return _instance;
    }

    public Date getCurrentDate() {
        return new Date();
    }

    public String getCurrentDateString() {
        return formatter.format(new Date());
    }

    public Date convertBirthday(String month, String day, String year) {
        Date parsedDate = null;
        try {
            parsedDate = formatter.parse(month + "-" + day + "-" + year);
        } catch (ParseException ex) {
            Logger.getLogger(DateUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parsedDate;
    }

}
