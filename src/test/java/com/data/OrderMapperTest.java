/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.data;

import com.entities.dto.Order;
import com.presentation.command.FrontController;
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
public class OrderMapperTest {

    private static Connection testConnection;
    private static String USER = "testuser";
    private static String USERPW = "password123";
    private static String DBNAME = "fogcarport";
    private static String HOST = "localhost";
    private Order order = new Order(1, 1, 1, Date.valueOf("2019-04-03"), Date.valueOf("2019-04-14"), "fantasivej 12 Lyngby", "sent");
//    private FrontController fc = new FrontController();
    
    public OrderMapperTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            // avoid making a new connection for each test
            if (testConnection == null) {
                String url = String.format("jdbc:mysql://%s:3306/%s?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8", HOST, DBNAME);
                Class.forName("com.mysql.cj.jdbc.Driver");

                testConnection = DriverManager.getConnection(url, USER, USERPW);
                // Make mappers use test
                Connector.setConnection(testConnection);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            testConnection = null;
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetOrder() throws Exception {
//        System.out.println("getOrder");
//        int orderId = 1;
//        Order expResult = order;
//        Order result = fc.getOrder(orderId);
//        assertEquals(expResult, result);
    }


}
