package com.presentation.command;

import com.enumerations.DBURL;
import com.data.TestConnectorForward;
import com.entities.dto.BillOfMaterials;
import com.entities.dto.Component;
import com.entities.dto.Customer;
import com.entities.dto.Employee;
import com.entities.dto.Order;
import com.exceptions.DataException;
import com.presentation.command.PresentationController;
import java.sql.Date;
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
public class PresentationControllerTest
{

    public PresentationControllerTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
        {
            //Reset DB using Spring
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
        System.out.println("\ngetCustomer");
        String email = "bertha@testmail.com";
        String password = "1234";
        Customer expResult = new Customer(1, "bittie_bertha", "bertha@testmail.com", "1234", "26154895");
        PresentationController instance = new PresentationController(DBURL.TEST);
        Customer result = instance.getCustomer(email, password);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateCustomer() throws Exception
    {
        System.out.println("\ncreateCustomer");
        PresentationController instance = new PresentationController(DBURL.TEST);
        Customer customerNew = new Customer(3, "Hans Hansen", "hans@hansenmail.com", "4321", "45859575");
        instance.createCustomer(customerNew);
        Customer result = instance.getCustomer("hans@hansenmail.com", "4321");
        Customer expResult = customerNew;
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateCustomer() throws Exception
    {
        System.out.println("\nupdateCustomer");
        Customer customer = new Customer(2, "Børge Børgesen", "boerge@boergemail.com", "123", "54789565");
        Customer newCustomer = new Customer(2, "Børge Riis Børgesen", "boergensen@boergemail.com", "4512", "78457845");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.updateCustomer(customer, newCustomer);
        Customer result = instance.getCustomer("boergensen@boergemail.com", "4512");
        Customer expResult = newCustomer;
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test(expected = DataException.class)
    public void testDeleteCustomer() throws DataException
    {
        System.out.println("\ndeleteCustomer");
        PresentationController instance = new PresentationController(DBURL.TEST);
        Customer deletingCustomer = instance.getCustomer("boergensen@boergemail.com", "4512");
        instance.deleteCustomer(deletingCustomer);
        Customer deletedCustomer = instance.getCustomer("boergensen@boergemail.com", "4512");
        System.out.println("Exception: " + DataException.class);
    }

    @Test
    public void testGetEmployee() throws Exception
    {
        System.out.println("\ngetEmployee");
        String email = "hall@testmail.com";
        String password = "4567";
        Employee expResult = new Employee(1, "halltheprotocol", "36459865", "hall@testmail.com", "4567", "admin");
        PresentationController instance = new PresentationController(DBURL.TEST);
        Employee result = instance.getEmployee(email, password);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateEmployee() throws Exception
    {
        System.out.println("\ncreateEmployee");
        Employee expResult = new Employee(4, "Niels Nielsen", "85457858", "niels@nielsmail.com", "6584", "admin");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.createEmployee(expResult);
        Employee result = instance.getEmployee("niels@nielsmail.com", "6584");
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateEmployee() throws Exception
    {
        System.out.println("\nupdateEmployee");
        Employee employee = new Employee(3, "SuperAdministrator", "37373737", "admin@fog.dk", "1337", "superadmin");
        Employee newEmployee = new Employee(3, "Peter Højer Nielsen", "45781512", "peter@nielsenmail.com", "4578", "superadmin");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.updateEmployee(employee, newEmployee);
        Employee result = instance.getEmployee("peter@nielsenmail.com", "4578");
        Employee expResult = newEmployee;
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test(expected = DataException.class)
    public void testDeleteEmployee() throws DataException
    {
        System.out.println("\ndeleteEmployee");
        PresentationController instance = new PresentationController(DBURL.TEST);
        Employee deletingCustomer = instance.getEmployee("brandan@testmail.com", "7890");
        instance.deleteEmployee(deletingCustomer);
        Employee deletedCustomer = instance.getEmployee("brandan@testmail.com", "7890");
        System.out.println("Exception: " + DataException.class);
    }

    @Test
    public void testGetOrder() throws Exception
    {
        System.out.println("\ngetOrder");
        int orderId = 1;
        Order order = new Order(1, 1, Date.valueOf("2019-04-03"), Date.valueOf("2019-04-14"), "fantasivej 12 Lyngby", "sent", 0);
        PresentationController instance = new PresentationController(DBURL.TEST);
        Order expResult = order;
        Order result = instance.getOrder(orderId);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
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

//    @Test
//    public void testCreateOrder() throws Exception
//    {
//        System.out.println("\ncreateOrder");
//        PresentationController instance = new PresentationController(DBURL.TEST);
//        Order order = new Order(3, 1, Date.valueOf("2019-04-03"), Date.valueOf("2019-04-14"), "fantasivej 12 Lyngby", "sent");
//        instance.createOrder(order);
//        Order expResult = order;
//        Order result = instance.getOrder(3);
//        System.out.println("expResult: " + expResult);
//        System.out.println("   result: " + result);
//        assertEquals(expResult, result);
//    }
    
//    @Test
//    public void testUpdateOrder() throws Exception
//    {
//        System.out.println("\nupdateOrder");
//        Order newOrder = null;
//        Order order = new Order(3, 1, Date.valueOf("2019-04-03"), Date.valueOf("2019-04-14"), "fantasivej 12 Lyngby", "sent");
//        PresentationController instance = new PresentationController(DBURL.TEST);
////        instance.updateOrder(order, newOrder);
//
//        System.out.println("expResult: " + expResult);
//        System.out.println("   result: " + result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
    @Test(expected = DataException.class)
    public void testDeleteOrder() throws DataException
    {
        System.out.println("\ndeleteOrder");
        PresentationController instance = new PresentationController(DBURL.TEST);
        Order deletingOrder = instance.getOrder(2);
        instance.deleteOrder(deletingOrder);
        Order deletedOrder = instance.getOrder(2);
        System.out.println("Exception: " + DataException.class);
    }

    @Test
    public void testGetBOM() throws Exception
    {
        System.out.println("\ngetBOM");
        int bomId = 1;
        Map<Integer, Integer> components = new HashMap();
        components.put(2, 2);
        components.put(3, 2);
        components.put(4, 1);
        PresentationController instance = new PresentationController(DBURL.TEST);
        BillOfMaterials expResult = new BillOfMaterials(bomId, components);
        BillOfMaterials result = instance.getBOM(bomId);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

//    @Test
//    public void testCreateBOM() throws Exception
//    {
//        System.out.println("\ncreateBOM!");
//        PresentationController instance = new PresentationController(DBURL.TEST);
//        Map<Integer, Integer> components = new HashMap();
//        components.put(2, 2);
//        components.put(3, 2);
//        components.put(4, 1);
//        BillOfMaterials BOM = new BillOfMaterials(1, components);
//        instance.createBOM(BOM);
//        BillOfMaterials expResult = BOM;
//        BillOfMaterials result = instance.getBOM(1);
//        System.out.println("expResult: " + expResult);
//        System.out.println("   result: " + result);
//        assertEquals(expResult, result);
//    }
    
//    @Test
//    public void testUpdateBOM() throws Exception
//    {
//        System.out.println("\nupdateBOM");
//        Map<Integer, Integer> components = new HashMap();
//        components.put(2, 1);
//        components.put(3, 3);
//        components.put(4, 1);
//        BillOfMaterials newBOM = new BillOfMaterials(1, components);
//        PresentationController instance = new PresentationController(DBURL.TEST);
//        BillOfMaterials BOM = instance.getBOM(1);
//        instance.updateBOM(BOM, newBOM);
//        BillOfMaterials expResult = newBOM;
//        BillOfMaterials result = instance.getBOM(1);
//        System.out.println("expResult: " + expResult);
//        System.out.println("   result: " + result);
//        assertEquals(expResult, result);
//    }

    @Test(expected = DataException.class)
    public void testDeleteBOM() throws DataException
    {
        System.out.println("\ndeleteBOM");
        PresentationController instance = new PresentationController(DBURL.TEST);
        BillOfMaterials deletingBOM = instance.getBOM(2);
        instance.deleteBOM(deletingBOM);
        BillOfMaterials deletedBOM = instance.getBOM(2);
        System.out.println("Exception: " + DataException.class);
    }

    @Test
    public void testGetComponent() throws Exception
    {
        System.out.println("\ngetComponent");
        int ComponentId = 1;
        PresentationController instance = new PresentationController(DBURL.TEST);
        Component expResult = new Component(1, "38x57mm T1 Lægte Stemplet og godkendt til tag",
                "Max afstand 32cm.", 6600, 38, 57, (float) 100.0);
        Component result = instance.getComponent(ComponentId);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateComponent() throws Exception
    {
        System.out.println("\ncreateComponent");
        Component component = new Component(5, "PlastikSkruer", "Bruges i et legohus", 5, 1, 3, (float)100.00);
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.createComponent(component);
        Component expResult = component;
        Component result = instance.getComponent(5);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
    }
    
    @Test
    public void testUpdateComponent() throws Exception
    {
        System.out.println("\nupdateComponent");
        Component component = new Component(1, "38x57mm T1 Lægte Stemplet og godkendt til tag", "Max afstand 32cm.", 6600, 38, 57, (float) 100.00);
        Component newComponent = new Component(1, "38x77mm T1 Lægte Stemplet og godkendt til tag", "Max afstand 39cm.", 6300, 32, 52, (float) 230.00);
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.updateComponent(component, newComponent);
        Component result = instance.getComponent(1);
        Component expResult = newComponent;
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test(expected = DataException.class)
    public void testDeleteComponent() throws DataException
    {
        System.out.println("\ndeleteComponent");
        PresentationController instance = new PresentationController(DBURL.TEST);
        Component deletingComponent = instance.getComponent(2);
        instance.deleteComponent(deletingComponent);
        Component deletedComponent = instance.getComponent(2);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }
}
