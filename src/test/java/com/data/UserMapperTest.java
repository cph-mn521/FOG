/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.data;

import com.entities.dto.Customer;
import com.entities.dto.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
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

    private static Connection testConnection;
    private static String USER = "testuser";
    private static String USERPW = "password123";
    private static String DBNAME = "fogcarport";
    private static String HOST = "localhost";

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
        try
        {
            // avoid making a new connection for each test
            if (testConnection == null)
            {
                String url = String.format("jdbc:mysql://%s:3306/%s?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8", HOST, DBNAME);
                Class.forName("com.mysql.cj.jdbc.Driver");

                testConnection = DriverManager.getConnection(url, USER, USERPW);
                // Make mappers use test 
                Connector.setConnection(testConnection);
            }
            // reset test database
            try (Statement stmt = testConnection.createStatement())
            {
                stmt.execute("drop table if exists Users");
                stmt.execute("create table Users like UsersTest");
                stmt.execute("insert into Users select * from UsersTest");
            }

        } catch (ClassNotFoundException | SQLException ex)
        {
            testConnection = null;
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void testSetUpOK()
    {
        // Just check that we have a connection.
        assertNotNull(testConnection);
    }

    @Test
    public void testGetCustomer() throws Exception
    {
        System.out.println("getCustomer");
        String email = "";
        String password = "";
        Customer expResult = new Customer(1, "bittie_bertha", "bertha@testmail.com", "1234", 26154895);
        Customer result = UserMapper.getCustomer(email, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
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
