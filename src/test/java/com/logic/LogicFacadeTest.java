package com.logic;

import com.data.TestConnector;
import com.entities.dto.Customer;
import com.entities.dto.Employee;
import com.entities.dto.Order;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

/**
 *
 * @author martin
 */
public class LogicFacadeTest
{

    private Order order = new Order(1, 1, 1, Date.valueOf("2019-04-03"), Date.valueOf("2019-04-14"), "fantasivej 12 Lyngby", "sent");
    private Order order3 = new Order(3, 1, 1, Date.valueOf("2019-04-03"), Date.valueOf("2019-04-14"), "fantasivej 12 Lyngby", "sent");


    public LogicFacadeTest()
    {
//        //Reset DB
//        ResourceDatabasePopulator rdp = new ResourceDatabasePopulator();
//        rdp.addScript(new ClassPathResource("mysql-scripts/carport_ddl.sql"));
//        rdp.addScript(new ClassPathResource("mysql-scripts/carport_dml.sql"));
//
//        TestConnector connection = new TestConnector();
//
//        Connection conn;
//        try
//        {
//            conn = connection.forwardConnection();
//            rdp.populate(conn); // this starts the script execution, in the order as added
//        } catch (SQLException | ClassNotFoundException ex)
//        {
//            ex.printStackTrace();
//        }
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
    public void testGetCustomer() throws Exception
    {
//        System.out.println("getCustomer");
//        String email = "bertha@testmail.com";
//        String password = "1234";
//        Customer customerDB = new Customer(1, "bittie_bertha", "bertha@testmail.com", "1234", "26154895");
//        LogicFacade instance = new LogicFacade();
//        Customer expResult = customerDB;
//        Customer result = instance.getCustomer(email, password);
//        assertEquals(expResult, result);
    }

    @Test
    public void testCreateCustomer() throws Exception
    {
//        System.out.println("createCustomer");
//        LogicFacade instance = new LogicFacade();
//        Customer customerNew = new Customer(2, "Hans Hansen", "hans@hansenmail.com", "4321", "45859575");
//        instance.createCustomer(customerNew);
//        Customer result = instance.getCustomer("hans@hansenmail.com", "4321");
//        Customer expResult = customerNew;
//        assertEquals(expResult, result);
    }

//    @Test
//    public void testUpdateCustomer() throws Exception
//    {
//        System.out.println("updateCustomer");
//        Customer newCustomer = null;
//        LogicFacade instance = new LogicFacade();
////        instance.updateCustomer(customer, newCustomer);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testDeleteCustomer() throws Exception
//    {
//        System.out.println("deleteCustomer");
//        LogicFacade instance = new LogicFacade();
////        instance.deleteCustomer(customer);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    @Test
    public void testGetEmployee() throws Exception
    {
//        System.out.println("getEmployee");
//        String email = "hall@testmail.com";
//        String password = "4567";
//        LogicFacade instance = new LogicFacade();
//        Employee expResult = new Employee(1, "halltheprotocol", "36459865", "hall@testmail.com", "4567", "admin");
//        Employee result = instance.getEmployee(email, password);
//
//        assertEquals(expResult, result);
    }

//    @Test
//    public void testCreateEmployee() throws Exception
//    {
//        System.out.println("createEmployee");
//        LogicFacade instance = new LogicFacade();
////        instance.createEmployee(employee);
//     Employee employeeNew = new Employee(1, "Peder Sørensen", "45859575", "peder@sorensenmail.com", "8545", "admin");
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testUpdateEmployee() throws Exception
//    {
//        System.out.println("updateEmployee");
//        Employee newEmployee = null;
//        LogicFacade instance = new LogicFacade();
////        instance.updateEmployee(employee, newEmployee);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testDeleteEmployee()
//    {
//        System.out.println("deleteEmployee");
//        LogicFacade instance = new LogicFacade();
////        instance.deleteEmployee(employee);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetOrder() throws Exception
//    {
//        System.out.println("getOrder");
//        int orderId = 1;
//        LogicFacade instance = new LogicFacade();
//        Order expResult = order;
//        Order result = instance.getOrder(orderId);
//        assertEquals(expResult, result);
//    }
//
//    @Test
//    public void testCreateOrder() throws Exception
//    {
//        System.out.println("createOrder");
//        LogicFacade instance = new LogicFacade();
//        instance.createOrder(order);
//        Order expResult = order3;
//        Order result = instance.getOrder(3);
//
//        assertEquals(expResult, result);
//    }
//
//    @Test
//    public void testUpdateOrder() throws Exception
//    {
//        System.out.println("updateOrder");
//        Order newOrder = null;
//        LogicFacade instance = new LogicFacade();
////        instance.updateOrder(order, newOrder);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testDeleteOrder() throws Exception
//    {
//        System.out.println("deleteOrder");
//        LogicFacade instance = new LogicFacade();
////        instance.deleteOrder(order);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetBOM() throws Exception
//    {
//        System.out.println("getBOM");
//        int bomId = 0;
//        LogicFacade instance = new LogicFacade();
//        BillOfMaterials expResult = null;
//        BillOfMaterials result = instance.getBOM(bomId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testCreateBOM() throws Exception
//    {
//        System.out.println("createBOM");
//        BillOfMaterials BOM = null;
//        LogicFacade instance = new LogicFacade();
////        instance.createBOM(BOM);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testUpdateBOM() throws Exception
//    {
//        System.out.println("updateBOM");
//        BillOfMaterials BOM = null;
//        BillOfMaterials newBOM = null;
//        LogicFacade instance = new LogicFacade();
////        instance.updateBOM(BOM, newBOM);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testDeleteBOM() throws Exception
//    {
//        System.out.println("deleteBOM");
//        BillOfMaterials BOM = null;
//        LogicFacade instance = new LogicFacade();
////        instance.deleteBOM(BOM);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetComponent() throws Exception
//    {
//        System.out.println("getComponent");
//        int ComponentId = 0;
//        LogicFacade instance = new LogicFacade();
//        Component expResult = null;
//        Component result = instance.getComponent(ComponentId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testCreateComponent() throws Exception
//    {
//        System.out.println("createComponent");
//        Component Component = null;
//        LogicFacade instance = new LogicFacade();
////        instance.createComponent(Component);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testUpdateComponent() throws Exception
//    {
//        System.out.println("updateComponent");
//        Component Component = null;
//        Component newComponent = null;
//        LogicFacade instance = new LogicFacade();
////        instance.updateComponent(Component, newComponent);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testDeleteComponent() throws Exception
//    {
//        System.out.println("deleteComponent");
//        Component Component = null;
//        LogicFacade instance = new LogicFacade();
////        instance.deleteComponent(Component);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
