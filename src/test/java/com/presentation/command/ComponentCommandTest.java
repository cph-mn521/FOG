/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentation.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author martin
 */
public class ComponentCommandTest {
    
    public ComponentCommandTest() {
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

    @Test
    public void testExecute() throws Exception {
        System.out.println("execute");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        ComponentCommand instance = new ComponentCommand();
        String expResult = "";
        String result = instance.execute(request, response);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testShowComponents() throws Exception {
        System.out.println("showComponents");
        PresentationController pc = null;
        HttpSession session = null;
        HttpServletRequest request = null;
        ComponentCommand instance = new ComponentCommand();
        instance.showComponents(pc, session, request);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testPrepareComponent() throws Exception {
        System.out.println("prepareComponent");
        PresentationController pc = null;
        HttpSession session = null;
        HttpServletRequest request = null;
        ComponentCommand instance = new ComponentCommand();
        instance.prepareComponent(pc, session, request);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testChangedComponent() throws Exception {
        System.out.println("changedComponent");
        PresentationController pc = null;
        HttpSession session = null;
        HttpServletRequest request = null;
        ComponentCommand instance = new ComponentCommand();
        instance.changedComponent(pc, session, request);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testPrepareFormComponent() {
        System.out.println("prepareFormComponent");
        PresentationController pc = null;
        HttpSession session = null;
        HttpServletRequest request = null;
        ComponentCommand instance = new ComponentCommand();
        instance.prepareFormComponent(pc, session, request);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testNewComponent() throws Exception {
        System.out.println("newComponent");
        PresentationController pc = null;
        HttpSession session = null;
        HttpServletRequest request = null;
        ComponentCommand instance = new ComponentCommand();
        instance.newComponent(pc, session, request);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testRemoveComponent() throws Exception {
        System.out.println("removeComponent");
        PresentationController pc = null;
        HttpSession session = null;
        HttpServletRequest request = null;
        ComponentCommand instance = new ComponentCommand();
        instance.removeComponent(pc, session, request);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
