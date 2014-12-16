/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ejb.AdminAccountFacade;
import ejb.MemberFacade;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import model.Address;
import model.AdminAccount;
import model.MemberAccount;

/**
 *
 * @author yyun
 */
@Singleton
@Startup
public class DataInitialization {

    @Inject
    private AdminAccountFacade adminFacade;
    @Inject
    private MemberFacade memberFacade;

    @PostConstruct
    @Interceptors(AdminLogger.class)
    public void init() {
        //initialize admin user,
        try {
            AdminAccount admin = adminFacade.findByUsername("admin");
            if (admin == null) {
                admin = new AdminAccount();
                admin.setUserName("admin");
                admin.setPassword("123");
                admin.setFirstName("Ninja");
                admin.setLastname("Admin");
                admin.setGender("Male");
                admin.setBirthDate(new Date());
                admin.setRegisteredDate(new Date());
                admin.setAddress(new Address("1000 4 Street", "Fairfield", "IA", "52257"));
                adminFacade.create(admin);
            }
            
            AdminAccount admin2 = adminFacade.findByUsername("admin2");
            if (admin2 == null) {
                admin2 = new AdminAccount();
                admin2.setUserName("admin2");
                admin2.setPassword("123");
                admin2.setFirstName("Ninja2");
                admin2.setLastname("Admin2");
                admin2.setGender("Male");
                admin2.setBirthDate(new Date());
                admin2.setRegisteredDate(new Date());
                admin2.setAddress(new Address("1000 4 Street", "Fairfield", "IA", "52257"));
                adminFacade.create(admin2);
            }
            
            MemberAccount m1 = memberFacade.findByUsername("m1");
            if (m1 == null) {
                m1 = new MemberAccount();
                m1.setUserName("m1");
                m1.setPassword("123");
                m1.setFirstName("Member 1");
                m1.setLastname("Ninja");
                m1.setGender("Male");
                m1.setBirthDate(new Date());
                m1.setRegisteredDate(new Date());
                m1.setAddress(new Address("1000 4 Street", "Fairfield", "IA", "52257"));
                memberFacade.create(m1);
            }
            MemberAccount m2 = memberFacade.findByUsername("m2");
            if (m2 == null) {
                m2 = new MemberAccount();
                m2.setUserName("m2");
                m2.setPassword("123");
                m2.setFirstName("Member 2");
                m2.setLastname("Ninja");
                m2.setGender("Female");
                m2.setBirthDate(new Date());
                m2.setRegisteredDate(new Date());
                m2.setAddress(new Address("1000 4 Street", "Fairfield", "IA", "52257"));
                memberFacade.create(m2);
            }
            MemberAccount m3 = memberFacade.findByUsername("m3");
            if (m3 == null) {
                m3 = new MemberAccount();
                m3.setUserName("m3");
                m3.setPassword("123");
                m3.setFirstName("Member 3");
                m3.setLastname("Ninja");
                m3.setGender("Male");
                m3.setBirthDate(new Date());
                m3.setRegisteredDate(new Date());
                m3.setAddress(new Address("1000 4 Street", "Fairfield", "IA", "52257"));
                memberFacade.create(m3);
            }
            MemberAccount m4 = memberFacade.findByUsername("m4");
            if (m4 == null) {
                m4 = new MemberAccount();
                m4.setUserName("m4");
                m4.setPassword("123");
                m4.setFirstName("Member 4");
                m4.setLastname("Ninja");
                m4.setGender("Male");
                m4.setBirthDate(new Date());
                m4.setRegisteredDate(new Date());
                m4.setAddress(new Address("1000 4 Street", "Fairfield", "IA", "52257"));
                memberFacade.create(m4);
            }
            MemberAccount m5 = memberFacade.findByUsername("m5");
            if (m5 == null) {
                m5 = new MemberAccount();
                m5.setUserName("m5");
                m5.setPassword("123");
                m5.setFirstName("Member 5");
                m5.setLastname("Ninja");
                m5.setGender("Female");
                m5.setBirthDate(new Date());
                m5.setRegisteredDate(new Date());
                m5.setAddress(new Address("1000 4 Street", "Fairfield", "IA", "52257"));
                memberFacade.create(m5);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
        }
    }
}
