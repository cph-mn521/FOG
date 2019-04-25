/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.data;

import com.entities.dto.Order;
import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author martin
 */
public class OrderMapperTest
{

    private static Connection testConnection;
    private static String USER = "testuser";
    private static String USERPW = "password123";
    private static String DBNAME = "fogcarport";
    private static String HOST = "localhost";
    private Order order = new Order(1, 1, 1, Date.valueOf("2019-04-03"), Date.valueOf("2019-04-14"), "fantasivej 12 Lyngby", "sent");
    public OrderMapperTest()
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
    public void setUp() throws SQLException
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
    public void testGetOrder() throws Exception
    {
        System.out.println("getOrder");
        int orderId = 1;
        Order expResult = order;
        Order result = OrderMapper.getOrder(orderId);
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateOrder() throws Exception
    {
        System.out.println("createOrder");
        Order order = null;
        OrderMapper.createOrder(order);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateOrder() throws Exception
    {
        System.out.println("updateOrder");
        Order order = null;
        Order newOrder = null;
        OrderMapper.updateOrder(order, newOrder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testDeleteOrder() throws Exception
    {
        System.out.println("deleteOrder");
        Order order = null;
        OrderMapper.deleteOrder(order);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
