package com.presentation.command;

import com.enumerations.DBURL;
import com.data.TestConnectorForward;
import com.entities.dto.BillOfMaterials;
import com.entities.dto.Component;
import com.entities.dto.Customer;
import com.entities.dto.Employee;
import com.entities.dto.Order;
import com.exceptions.DataException;
import com.presentation.command.PresentationFacade;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
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
 * @author Martin Bøgh
 */
public class PresentationFacadeTest
{

    public PresentationFacadeTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
        {
            //Reset DB
            ResourceDatabasePopulator rdp = new ResourceDatabasePopulator();
            rdp.addScript(new ClassPathResource("mysql-scripts/carport_ddl.sql"));
            rdp.addScript(new ClassPathResource("mysql-scripts/carport_dml.sql"));

            TestConnectorForward testConnection = new TestConnectorForward();

            try
            {
                rdp.populate(testConnection.forwardConnection());
                System.out.println("");
            } catch (DataException ex)
            {
                ex.printStackTrace();
            }
        }
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
        System.out.println("getCustomer");
        String email = "bertha@testmail.com";
        String password = "1234";
        Customer expResult = new Customer(1, "bittie_bertha", "bertha@testmail.com", "1234", "26154895");
        PresentationFacade instance = new PresentationFacade(DBURL.TEST);
        Customer result = instance.getCustomer(email, password);
        System.out.println("expResult: " + expResult);
        System.out.println("result: " + result + "\n");
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateCustomer() throws Exception
    {
        System.out.println("createCustomer");
        PresentationFacade instance = new PresentationFacade(DBURL.TEST);
        Customer customerNew = new Customer(3, "Hans Hansen", "hans@hansenmail.com", "4321", "45859575");
        instance.createCustomer(customerNew);
        Customer result = instance.getCustomer("hans@hansenmail.com", "4321");
        Customer expResult = customerNew;
        System.out.println("expResult: " + expResult);
        System.out.println("result: " + result + "\n");
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateCustomer() throws Exception
    {
        System.out.println("updateCustomer");
        Customer customer = new Customer(2, "Børge Børgesen", "boerge@boergemail.com", "123", "54789565");
        Customer newCustomer = new Customer(2, "Børge Riis Børgesen", "boergensen@boergemail.com", "4512", "78457845");
        PresentationFacade instance = new PresentationFacade(DBURL.TEST);
        instance.updateCustomer(customer, newCustomer);
        Customer result = instance.getCustomer("boergensen@boergemail.com", "4512");
        Customer expResult = newCustomer;
        assertEquals(expResult, result);
    }

    @Test(expected = DataException.class)
    public void testDeleteCustomer() throws DataException, SQLException
    {
        System.out.println("deleteCustomer");
        PresentationFacade instance = new PresentationFacade(DBURL.TEST);
        Customer deletingCustomer = instance.getCustomer("boergensen@boergemail.com", "4512");
        instance.deleteCustomer(deletingCustomer);
        Customer deletedCustomer = instance.getCustomer("boergensen@boergemail.com", "4512");
        System.out.println("Exception: " + DataException.class);
        System.out.println("");
    }

    @Test
    public void testGetEmployee() throws Exception
    {
        System.out.println("getEmployee");
        String email = "hall@testmail.com";
        String password = "4567";
        Employee expResult = new Employee(1, "halltheprotocol", "36459865", "hall@testmail.com", "4567", "admin");
        PresentationFacade instance = new PresentationFacade(DBURL.TEST);
        Employee result = instance.getEmployee(email, password);
        System.out.println("expResult: " + expResult);
        System.out.println("result: " + result + "\n");
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateEmployee() throws Exception
    {
        System.out.println("createEmployee");
        Employee expResult = new Employee(4, "Niels Nielsen", "85457858", "niels@nielsmail.com", "6584", "admin");
        PresentationFacade instance = new PresentationFacade(DBURL.TEST);
        instance.createEmployee(expResult);
        Employee result = instance.getEmployee("niels@nielsmail.com", "6584");
        System.out.println("expResult: " + expResult);
        System.out.println("result: " + result + "\n");
        assertEquals(expResult, result);
    }

//    @Test
//    public void testUpdateEmployee() throws Exception
//    {
//        System.out.println("updateEmployee");
//        Employee newEmployee = null;
//        PresentationFacade instance = new PresentationFacade(DBURL.TEST);
////        instance.updateEmployee(employee, newEmployee);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testDeleteEmployee()
//    {
//        System.out.println("deleteEmployee");
//        PresentationFacade instance = new PresentationFacade(DBURL.TEST);
////        instance.deleteEmployee(employee);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    @Test
    public void testGetOrder() throws Exception
    {
        System.out.println("getOrder");
        int orderId = 1;
        Order order = new Order(1, 1, Date.valueOf("2019-04-03"), Date.valueOf("2019-04-14"), "fantasivej 12 Lyngby", "sent");
        PresentationFacade instance = new PresentationFacade(DBURL.TEST);
        Order expResult = order;
        Order result = instance.getOrder(orderId);
        System.out.println("expResult: " + expResult);
        System.out.println("result: " + result + "\n");
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateOrder() throws Exception
    {
        System.out.println("createOrder");
        PresentationFacade instance = new PresentationFacade(DBURL.TEST);
        Order order = new Order(3, 1, Date.valueOf("2019-04-03"), Date.valueOf("2019-04-14"), "fantasivej 12 Lyngby", "sent");
        instance.createOrder(order);
        Order expResult = order;
        Order result = instance.getOrder(3);
        System.out.println("expResult: " + expResult);
        System.out.println("result: " + result + "\n");
        assertEquals(expResult, result);
    }
    
//    @Test
//    public void testUpdateOrder() throws Exception
//    {
//        System.out.println("updateOrder");
//        Order newOrder = null;
//        PresentationFacade instance = new PresentationFacade(DBURL.TEST);
////        instance.updateOrder(order, newOrder);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testDeleteOrder() throws Exception
//    {
//        System.out.println("deleteOrder");
//        PresentationFacade instance = new PresentationFacade(DBURL.TEST);
////        instance.deleteOrder(order);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    @Test
    public void testGetBOM() throws Exception
    {
        System.out.println("getBOM");
        int bomId = 1;
        Map<Integer, Integer> components = new HashMap();
        components.put(2,2);
        components.put(3,2);
        components.put(4,1);
        PresentationFacade instance = new PresentationFacade(DBURL.TEST);
        BillOfMaterials expResult = new BillOfMaterials(bomId, components);
        BillOfMaterials result = instance.getBOM(bomId);
        System.out.println("expResult: " + expResult);
        System.out.println("result: " + result + "\n");
        assertEquals(expResult, result);
    }

//    @Test
//    public void testCreateBOM() throws Exception
//    {
//        System.out.println("createBOM");
//        BillOfMaterials BOM = null;
//        PresentationFacade instance = new PresentationFacade(DBURL.TEST);
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
//        PresentationFacade instance = new PresentationFacade(DBURL.TEST);
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
//        PresentationFacade instance = new PresentationFacade(DBURL.TEST);
////        instance.deleteBOM(BOM);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    @Test
    public void testGetComponent() throws Exception
    {
        System.out.println("getComponent");
        int ComponentId = 1;
        PresentationFacade instance = new PresentationFacade(DBURL.TEST);
        Component expResult = new Component(1, "38x57mm T1 Lægte Stemplet og godkendt til tag", "Max afstand 32cm.", 6600, 38, 57, (float) 100.0);
        Component result = instance.getComponent(ComponentId);
        System.out.println("expResult: " + expResult);
        System.out.println("result: " + result + "\n");
        assertEquals(expResult, result);
    }

//    @Test
//    public void testCreateComponent() throws Exception
//    {
//        System.out.println("createComponent");
//        Component Component = null;
//        PresentationFacade instance = new PresentationFacade(DBURL.TEST);
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
//        PresentationFacade instance = new PresentationFacade(DBURL.TEST);
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
//        PresentationFacade instance = new PresentationFacade(DBURL.TEST);
////        instance.deleteComponent(Component);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
