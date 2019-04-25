/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.data;

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
public class UserMapperTest
{
    
    public UserMapperTest()
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
        User expResult = null;
        User result = UserMapper.getUser(email, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testCreateUser() throws Exception
    {
        System.out.println("createUser");
        User user = null;
        UserMapper.createUser(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateUser() throws Exception
    {
        System.out.println("updateUser");
        User user = null;
        User newUser = null;
        UserMapper.updateUser(user, newUser);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testDeleteUser() throws Exception
    {
        System.out.println("deleteUser");
        User user = null;
        UserMapper.deleteUser(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
