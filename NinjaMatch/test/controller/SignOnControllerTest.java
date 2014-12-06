/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author FrancisAerol
 */
public class SignOnControllerTest {
    
    public SignOnControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getUserName method, of class SignOnController.
     */
    @Test
    public void testGetUserName() {
        System.out.println("getUserName");
        SignOnController instance = new SignOnController();
        String expResult = "";
        String result = instance.getUserName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUserName method, of class SignOnController.
     */
    @Test
    public void testSetUserName() {
        System.out.println("setUserName");
        String userName = "";
        SignOnController instance = new SignOnController();
        instance.setUserName(userName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class SignOnController.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        SignOnController instance = new SignOnController();
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword method, of class SignOnController.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        SignOnController instance = new SignOnController();
        instance.setPassword(password);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getErrorMessage method, of class SignOnController.
     */
    @Test
    public void testGetErrorMessage() {
        System.out.println("getErrorMessage");
        SignOnController instance = new SignOnController();
        String expResult = "";
        String result = instance.getErrorMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setErrorMessage method, of class SignOnController.
     */
    @Test
    public void testSetErrorMessage() {
        System.out.println("setErrorMessage");
        String errorMessage = "";
        SignOnController instance = new SignOnController();
        instance.setErrorMessage(errorMessage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verifyPassword method, of class SignOnController.
     */
    @Test
    public void testVerifyPassword() {
        System.out.println("verifyPassword");
        SignOnController instance = new SignOnController();
        instance.verifyPassword();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
