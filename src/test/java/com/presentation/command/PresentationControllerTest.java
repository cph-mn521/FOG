package com.presentation.command;

import com.enumerations.DBURL;
import com.data.TestConnectorForward;
import com.entities.dto.BillOfMaterials;
import com.entities.dto.Carport;
import com.entities.dto.Case;
import com.entities.dto.Component;
import com.entities.dto.Customer;
import com.entities.dto.Employee;
import com.entities.dto.Message;
import com.entities.dto.Order;
import com.entities.dto.Roof;
import com.entities.dto.User;
import com.exceptions.DataException;
import com.presentation.command.PresentationController;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
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
public class PresentationControllerTest {

    public PresentationControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        {
            //Reset DB using Spring
            ResourceDatabasePopulator rdp = new ResourceDatabasePopulator();
            rdp.addScript(new ClassPathResource("mysql-scripts/carport_ddl.sql"));
            rdp.addScript(new ClassPathResource("mysql-scripts/carport_dml.sql"));

            TestConnectorForward testConnection = new TestConnectorForward();

            try {
                rdp.populate(testConnection.forwardConnection());
                System.out.println("");
            } catch (DataException ex) {
                ex.printStackTrace();
            }
        }
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

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////BOM TESTS/////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    @Test
    public void testGetBOM() throws Exception {
        System.out.println("\ngetBOM");
        int bomId = 1;
        Map<Integer, Integer> components = new HashMap();
        components.put(3, 2);
        components.put(4, 1);
        PresentationController instance = new PresentationController(DBURL.TEST);
        BillOfMaterials expResult = new BillOfMaterials(bomId, components);
        BillOfMaterials result = instance.getBOM(bomId);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateBOM() throws Exception {
        System.out.println("\ncreateBOM!");
        PresentationController instance = new PresentationController(DBURL.TEST);
        Map<Integer, Integer> components = new HashMap();
        components.put(2, 2);
        components.put(3, 2);
        components.put(4, 1);
        BillOfMaterials BOM = new BillOfMaterials(1, components);
        instance.createBOM(BOM);
        BillOfMaterials expResult = BOM;
        BillOfMaterials result = instance.getBOM(1);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateBOM() throws Exception {
        System.out.println("\nupdateBOM");
        Map<Integer, Integer> components = new HashMap();
        components.put(2, 1);
        components.put(3, 3);
        components.put(4, 1);
        BillOfMaterials newBOM = new BillOfMaterials(1, components);
        PresentationController instance = new PresentationController(DBURL.TEST);
        BillOfMaterials BOM = instance.getBOM(1);
        instance.updateBOM(BOM, newBOM);
        BillOfMaterials expResult = newBOM;
        BillOfMaterials result = instance.getBOM(1);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test(expected = DataException.class)
    public void testDeleteBOM() throws DataException {
        System.out.println("\ndeleteBOM");
        PresentationController instance = new PresentationController(DBURL.TEST);
        BillOfMaterials deletingBOM = instance.getBOM(2);
        instance.deleteBOM(deletingBOM);
        BillOfMaterials deletedBOM = instance.getBOM(2);
        System.out.println("Exception: " + DataException.class);
    }

    @Test
    public void testConvertBOMMap() throws Exception {
        System.out.println("convertBOMMap");
        BillOfMaterials bom = null;
        PresentationController instance = new PresentationController(DBURL.TEST);
        Map<Component, Integer> expResult = null;
        Map<Component, Integer> result = instance.convertBOMMap(bom);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testCalculatePriceOfBOM() throws Exception {
        System.out.println("calculatePriceOfBOM");
        BillOfMaterials bom = null;
        PresentationController instance = new PresentationController(DBURL.TEST);
        float expResult = 0.0F;
        float result = instance.calculatePriceOfBOM(bom);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////CARPORT TESTS/////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    @Test
    public void testGetCarport() throws Exception {
        System.out.println("getCarport");
        int orderId = 1;
        PresentationController instance = new PresentationController(DBURL.TEST);
        Carport expResult = new Carport(1, 1, 7000, 2500, 2000, 6000, 1500, 1000);
        Carport result = instance.getCarport(orderId);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetAllCarports() throws Exception {
        System.out.println("getAllCarports");
        PresentationController instance = new PresentationController(DBURL.TEST);
        int expResult = 1;
        int result = instance.getAllCarports().size();
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateCarport() throws Exception {
        System.out.println("createCarport");
        Carport expResult = new Carport(2, 1, 7000, 2500, 2000, 6000, 1500, 1000);
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.createCarport(expResult);
        Carport result = instance.getCarport(2);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testUpdateCarport() throws Exception {
        System.out.println("updateCarport");
        Carport carport = new Carport(2, 1, 7000, 2500, 2000, 6000, 1500, 1000);
        Carport newCarport = new Carport(2, 1, 8000, 2500, 4000, 6000, 1500, 1000);
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.updateCarport(carport, newCarport);
        Carport expResult = newCarport;
        Carport result = instance.getCarport(2);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test(expected = DataException.class)
    public void testDeleteCarport() throws Exception {
        System.out.println("deleteCarport");
        Carport carport = new Carport(2, 1, 8000, 2500, 4000, 6000, 1500, 1000);
        PresentationController instance = new PresentationController(DBURL.TEST);
        System.out.println(instance.getCarport(2));
        instance.deleteCarport(carport);
        Carport deletedCarport = instance.getCarport(2);
        System.out.println("Exception: " + DataException.class);
    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////CASE TESTS////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    @Test
    public void testLoginEmploye() throws Exception {
        System.out.println("LoginEmploye");
        String usn = "";
        String psw = "";
        HttpServletRequest request = null;
        PresentationController instance = new PresentationController(DBURL.TEST);
        Employee expResult = null;
        Employee result = instance.LoginEmploye(usn, psw, request);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetFreeCases() throws Exception {
        System.out.println("getFreeCases");
        String type = "";
        PresentationController instance = new PresentationController(DBURL.TEST);
        List<Case> expResult = null;
        List<Case> result = instance.getFreeCases(type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetMessages() throws Exception {
        System.out.println("getMessages");
        String rank = "";
        PresentationController instance = new PresentationController(DBURL.TEST);
        List<Message> expResult = null;
        List<Message> result = instance.getMessages(rank);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetMessage() throws Exception {
        System.out.println("getMessage");
        String ID = "";
        PresentationController instance = new PresentationController(DBURL.TEST);
        Message expResult = null;
        Message result = instance.getMessage(ID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetCase() throws Exception {
        System.out.println("getCase");
        String CaseNr = "";
        PresentationController instance = new PresentationController(DBURL.TEST);
        Case expResult = null;
        Case result = instance.getCase(CaseNr);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testTakeCase() throws Exception {
        System.out.println("TakeCase");
        int emplId = 0;
        int caseId = 0;
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.TakeCase(emplId, caseId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testCloseCase() throws Exception {
        System.out.println("closeCase");
        int caseID = 0;
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.closeCase(caseID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////COMPONENT TESTS///////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    @Test
    public void testGetComponent() throws Exception {
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
    public void testCreateComponent() throws Exception {
        System.out.println("\ncreateComponent");
        Component component = new Component(5, "PlastikSkruer", "Bruges i et legohus", 5, 1, 3, (float) 100.00);
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.createComponent(component);
        Component expResult = component;
        Component result = instance.getComponent(5);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
    }

    @Test
    public void testUpdateComponent() throws Exception {
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
    public void testDeleteComponent() throws DataException {
        System.out.println("\ndeleteComponent");
        PresentationController instance = new PresentationController(DBURL.TEST);
        Component deletingComponent = instance.getComponent(2);
        instance.deleteComponent(deletingComponent);
        Component deletedComponent = instance.getComponent(2);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test
    public void testGetAllComponents() throws Exception {
        System.out.println("getAllComponents");
        PresentationController instance = new PresentationController(DBURL.TEST);
        List<Component> expResult = null;
        List<Component> result = instance.getAllComponents();
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////CUSTOMER TESTS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    @Test
    public void testGetCustomerStrings() throws Exception {
        System.out.println("\ngetCustomerStrings");
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
    public void testGetCustomerFromID() throws Exception {
        System.out.println("getCustomerFromID");
        String ID = "";
        PresentationController instance = new PresentationController(DBURL.TEST);
        User expResult = null;
        User result = instance.getCustomerFromID(ID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetCustomerInt() throws Exception {
        System.out.println("\ngetCustomerINt");
        String email = "bertha@testmail.com";
        String password = "1234";
        Customer expResult = new Customer(1, "bittie_bertha", email, password, "26154895");
        PresentationController instance = new PresentationController(DBURL.TEST);
        Customer result = instance.getCustomer(1);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateCustomer() throws Exception {
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
    public void testUpdateCustomer() throws Exception {
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
    public void testDeleteCustomer() throws DataException {
        System.out.println("\ndeleteCustomer");
        PresentationController instance = new PresentationController(DBURL.TEST);
        Customer deletingCustomer = instance.getCustomer("boergensen@boergemail.com", "4512");
        instance.deleteCustomer(deletingCustomer);
        Customer deletedCustomer = instance.getCustomer("boergensen@boergemail.com", "4512");
        System.out.println("Exception: " + DataException.class);
    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////EMPLOYEE TESTS////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    @Test
    public void testGetEmployeeString() throws Exception {
        System.out.println("\ngetEmployee Strings");
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
    public void testGetEmployeeInt() throws Exception {
        System.out.println("\ngetEmployee Int");
        String email = "hall@testmail.com";
        String password = "4567";
        Employee expResult = new Employee(1, "halltheprotocol", "36459865", "hall@testmail.com", "4567", "admin");
        PresentationController instance = new PresentationController(DBURL.TEST);
        Employee result = instance.getEmployee(1);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateEmployee() throws Exception {
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
    public void testUpdateEmployee() throws Exception {
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
    public void testDeleteEmployee() throws DataException {
        System.out.println("\ndeleteEmployee");
        PresentationController instance = new PresentationController(DBURL.TEST);
        Employee deletingCustomer = instance.getEmployee("brandan@testmail.com", "7890");
        instance.deleteEmployee(deletingCustomer);
        Employee deletedCustomer = instance.getEmployee("brandan@testmail.com", "7890");
        System.out.println("Exception: " + DataException.class);
    }

    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////////ORDER TESTS////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    @Test
    public void testGetOrder() throws Exception {
        System.out.println("\ngetOrder");
        int orderId = 1;
        Order order = new Order(1, 1, Date.valueOf("2019-04-03"), Date.valueOf("2019-04-14"), "fantasivej 12 Lyngby", "sent", (float) 340.0);
        PresentationController instance = new PresentationController(DBURL.TEST);
        Order expResult = order;
        Order result = instance.getOrder(orderId);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testAllGetOrders() throws Exception {
        System.out.println("\ngetAllOrders");
        int orderId = 1;
        Order order = new Order(1, 1, Date.valueOf("2019-04-03"), Date.valueOf("2019-04-14"), "fantasivej 12 Lyngby", "sent", 0);
        Order order2 = new Order(2, 1, Date.valueOf("2019-04-25"), null, "fantasivej 12 Lyngby", "pending", 0);
        PresentationController instance = new PresentationController(DBURL.TEST);
        List<Order> expResult = new ArrayList<>();
        expResult.add(order);
        expResult.add(order2);
        List<Order> result = instance.getAllOrders();
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateOrder() throws Exception  {
        System.out.println("\ncreateOrder");
        
            PresentationController instance = new PresentationController(DBURL.TEST);
            Order order = new Order(7, 1, Date.valueOf("2019-04-03"), Date.valueOf("2019-04-14"), "fantasivej 12 Lyngby", "sent", 123);
            instance.createOrder(order);
            Order expResult = order;
            Order result = instance.getOrder(7);
            System.out.println("expResult: " + expResult);
            System.out.println("   result: " + result);
            assertEquals(expResult, result);
    }

    @Test
    public void testGetLastOrderID() throws Exception {
        System.out.println("getLastOrderID");
        PresentationController instance = new PresentationController(DBURL.TEST);
        int expResult = 6;
        int result = instance.getLastOrderID();
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateOrder_10args() throws Exception {
        System.out.println("createOrder");
        Customer customer = null;
        String customerAddress = "";
        int roofTypeId = 0;
        int carportLength = 0;
        int carportWidth = 0;
        int carportHeight = 0;
        int shedLength = 0;
        int shedWidth = 0;
        int shedHeight = 0;
        String filePath = "";
        PresentationController instance = new PresentationController(DBURL.TEST);
        Order expResult = null;
        Order result = instance.createOrder(customer, customerAddress, roofTypeId, carportLength, carportWidth, carportHeight, shedLength, shedWidth, shedHeight, filePath);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testCreateOrder_4args_1() throws Exception {
        System.out.println("createOrder");
        int customerId = 0;
        String customerAddress = "";
        Carport carport = null;
        String filePath = "";
        PresentationController instance = new PresentationController(DBURL.TEST);
        Order expResult = null;
        Order result = instance.createOrder(customerId, customerAddress, carport, filePath);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testCreateOrder_4args_2() throws Exception {
        System.out.println("createOrder");
        Customer customer = null;
        String customerAddress = "";
        Carport carport = null;
        String filePath = "";
        PresentationController instance = new PresentationController(DBURL.TEST);
        Order expResult = null;
        Order result = instance.createOrder(customer, customerAddress, carport, filePath);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateOrder() throws Exception {
        System.out.println("\nupdateOrder");
        Order order = new Order(3, 1, Date.valueOf("2019-04-03"), Date.valueOf("2019-04-14"), "fantasivej 12 Lyngby", "sent", (float) 123.0);
        Order newOrder = new Order(3, 1, Date.valueOf("2019-04-02"), Date.valueOf("2019-04-12"), "fantasivej 13 Lyngby", "sent", (float) 145.0);
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.updateOrder(order, newOrder);
        Order expResult = newOrder;
        Order result = instance.getOrder(3);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);

        assertEquals(expResult, result);
    }

    @Test(expected = DataException.class)
    public void testDeleteOrder() throws DataException {
        System.out.println("\ndeleteOrder");
        PresentationController instance = new PresentationController(DBURL.TEST);
        Order deletingOrder = instance.getOrder(2);
        instance.deleteOrder(deletingOrder);
        Order deletedOrder = instance.getOrder(2);
        System.out.println("Exception: " + DataException.class);
    }

    @Test
    public void testMarkOrderAsSent() throws Exception {
        System.out.println("markOrderAsSent");
        int orderId = 0;
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.markOrderAsSent(orderId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGeneratePDF() throws Exception {
        System.out.println("generatePDF");
        Order order = null;
        String filePath = "";
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.generatePDF(order, filePath);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////ROOF TESTS////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    @Test
    public void testGetRoof() throws Exception {
        System.out.println("getRoof");
        int roofTypeId = 3;
        PresentationController instance = new PresentationController(DBURL.TEST);
        Roof expResult = new Roof(3, 0, "Eternittag", "Grå", "B6");
        Roof result = instance.getRoof(roofTypeId);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateRoof() throws Exception {
        System.out.println("createRoof");
        Roof expResult = new Roof(5, 0, "Eternittag", "Blå", "B6");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.createRoof(expResult);
        Roof result = instance.getRoof(5);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateRoof() throws Exception {
        System.out.println("updateRoof");
        Roof roof = new Roof(5, 0, "Eternittag", "Blå", "B6");
        Roof newRoof = new Roof(5, 25, "Eternittag", "Rød", "B6");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.updateRoof(roof, newRoof);
        Roof result = instance.getRoof(5);
        Roof expResult = newRoof;
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test(expected = DataException.class)
    public void testDeleteRoof() throws Exception {
        System.out.println("deleteRoof");
        Roof roof = new Roof(5, 25, "Eternittag", "Rød", "B6");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.deleteRoof(roof);
        Roof result = instance.getRoof(5);
        System.out.println("Exception: " + DataException.class);
    }

    @Test
    public void testGetAllRoofs() throws Exception {
        System.out.println("getAllRoofs");
        PresentationController instance = new PresentationController(DBURL.TEST);
        int expResult = 4;
        int result = instance.getAllRoofs().size();
        assertEquals(expResult, result);
    }

}
