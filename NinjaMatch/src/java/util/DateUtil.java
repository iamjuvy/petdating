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
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

/**
 *
 * @author FrancisAerol
 */
@Singleton
public class DateUtil {

    private DateUtil dateUtil;

    private final DateFormat FORMATTER = new SimpleDateFormat("MM-dd-yyyy");

    @PostConstruct
    void init() {
        dateUtil = new DateUtil();
    }

    public Date getCurrentDate() {
        return new Date();
    }

    public String getCurrentDateString() {
        return FORMATTER.format(new Date());
    }

    public Date convertBirthday(String month, String day, String year) {
        Date parsedDate = null;
        try {
            parsedDate = FORMATTER.parse(month + "-" + day + "-" + year);
        } catch (ParseException ex) {
            Logger.getLogger(DateUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parsedDate;
    }

}
