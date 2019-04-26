package com.logic;

import com.entities.dto.BillOfMaterials;
import com.entities.dto.Component;
import com.entities.dto.Customer;
import com.entities.dto.Employee;
import com.entities.dto.Order;
import java.sql.Date;
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
public class LogicFacadeTest
{

    private Order order = new Order(1, 1, 1, Date.valueOf("2019-04-03"), Date.valueOf("2019-04-14"), "fantasivej 12 Lyngby", "sent");

    public LogicFacadeTest()
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
    public void testGetCustomer() throws Exception
    {
        System.out.println("getCustomer");
        String email = "";
        String password = "";
        LogicFacade instance = new LogicFacade();
        Customer expResult = null;
        Customer result = instance.getCustomer(email, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testCreateCustomer() throws Exception
    {
        System.out.println("createCustomer");
        Customer customer = null;
        LogicFacade instance = new LogicFacade();
        instance.createCustomer(customer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateCustomer() throws Exception
    {
        System.out.println("updateCustomer");
        Customer customer = null;
        Customer newCustomer = null;
        LogicFacade instance = new LogicFacade();
        instance.updateCustomer(customer, newCustomer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testDeleteCustomer() throws Exception
    {
        System.out.println("deleteCustomer");
        Customer customer = null;
        LogicFacade instance = new LogicFacade();
        instance.deleteCustomer(customer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetEmployee() throws Exception
    {
        System.out.println("getEmployee");
        String email = "";
        String password = "";
        LogicFacade instance = new LogicFacade();
        Employee expResult = null;
        Employee result = instance.getEmployee(email, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testCreateEmployee() throws Exception
    {
        System.out.println("createEmployee");
        Employee employee = null;
        LogicFacade instance = new LogicFacade();
        instance.createEmployee(employee);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateEmployee() throws Exception
    {
        System.out.println("updateEmployee");
        Employee employee = null;
        Employee newEmployee = null;
        LogicFacade instance = new LogicFacade();
        instance.updateEmployee(employee, newEmployee);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testDeleteEmployee()
    {
        System.out.println("deleteEmployee");
        Employee employee = null;
        LogicFacade instance = new LogicFacade();
        instance.deleteEmployee(employee);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetOrder() throws Exception
    {
        System.out.println("getOrder");
        int orderId = 1;
        LogicFacade instance = new LogicFacade();
        Order expResult = order;
        Order result = instance.getOrder(orderId);
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateOrder() throws Exception
    {
        System.out.println("createOrder");
        Order order = null;
        LogicFacade instance = new LogicFacade();
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
        LogicFacade instance = new LogicFacade();
        instance.updateOrder(order, newOrder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testDeleteOrder() throws Exception
    {
        System.out.println("deleteOrder");
        Order order = null;
        LogicFacade instance = new LogicFacade();
        instance.deleteOrder(order);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetBOM() throws Exception
    {
        System.out.println("getBOM");
        int bomId = 0;
        LogicFacade instance = new LogicFacade();
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
        LogicFacade instance = new LogicFacade();
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
        LogicFacade instance = new LogicFacade();
        instance.updateBOM(BOM, newBOM);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testDeleteBOM() throws Exception
    {
        System.out.println("deleteBOM");
        BillOfMaterials BOM = null;
        LogicFacade instance = new LogicFacade();
        instance.deleteBOM(BOM);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetComponent() throws Exception
    {
        System.out.println("getComponent");
        int ComponentId = 0;
        LogicFacade instance = new LogicFacade();
        Component expResult = null;
        Component result = instance.getComponent(ComponentId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testCreateComponent() throws Exception
    {
        System.out.println("createComponent");
        Component Component = null;
        LogicFacade instance = new LogicFacade();
        instance.createComponent(Component);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateComponent() throws Exception
    {
        System.out.println("updateComponent");
        Component Component = null;
        Component newComponent = null;
        LogicFacade instance = new LogicFacade();
        instance.updateComponent(Component, newComponent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testDeleteComponent() throws Exception
    {
        System.out.println("deleteComponent");
        Component Component = null;
        LogicFacade instance = new LogicFacade();
        instance.deleteComponent(Component);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
