/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.data;

import com.entities.dto.BillOfMaterials;
import com.entities.dto.Customer;
import com.entities.dto.Order;
import com.entities.dto.User;
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
public class DAOControllerTest
{
    
    public DAOControllerTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    @Test
    public void testGetUser() throws Exception
    {
        System.out.println("getUser");
        String email = "";
        String password = "";
        DAOController instance = new DAOController();
        Customer expResult = null;
        Customer result = instance.getCustomer(email, password);
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateUser() throws Exception
    {
        System.out.println("createUser");
        User user = null;
        DAOController instance = new DAOController();
        instance.createUser(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateUser() throws Exception
    {
        System.out.println("updateUser");
        User user = null;
        User newUser = null;
        DAOController instance = new DAOController();
        instance.updateUser(user, newUser);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testDeleteUser() throws Exception
    {
        System.out.println("deleteUser");
        User user = null;
        DAOController instance = new DAOController();
        instance.deleteUser(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetOrder() throws Exception
    {
        System.out.println("getOrder");
        int orderId = 0;
        DAOController instance = new DAOController();
        Order expResult = null;
        Order result = instance.getOrder(orderId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testCreateOrder() throws Exception
    {
        System.out.println("createOrder");
        Order order = null;
        DAOController instance = new DAOController();
        instance.createOrder(order);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateOrder() throws Exception
    {
        System.out.println("updateOrder");
        Order order = null;
        Order newOrder = null;
        DAOController instance = new DAOController();
        instance.updateOrder(order, newOrder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testDeleteOrder() throws Exception
    {
        System.out.println("deleteOrder");
        Order order = null;
        DAOController instance = new DAOController();
        instance.deleteOrder(order);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetBOM() throws Exception
    {
        System.out.println("getBOM");
        int bomId = 0;
        DAOController instance = new DAOController();
        BillOfMaterials expResult = null;
        BillOfMaterials result = instance.getBOM(bomId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testCreateBOM() throws Exception
    {
        System.out.println("createBOM");
        BillOfMaterials BOM = null;
        DAOController instance = new DAOController();
        instance.createBOM(BOM);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateBOM() throws Exception
    {
        System.out.println("updateBOM");
        BillOfMaterials BOM = null;
        BillOfMaterials newBOM = null;
        DAOController instance = new DAOController();
        instance.updateBOM(BOM, newBOM);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testDeleteBOM() throws Exception
    {
        System.out.println("deleteBOM");
        BillOfMaterials BOM = null;
        DAOController instance = new DAOController();
        instance.deleteBOM(BOM);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
