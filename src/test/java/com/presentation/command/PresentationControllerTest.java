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
import com.exceptions.DataException;
import com.exceptions.LogicException;
import java.sql.Date;
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

    @Test(expected = DataException.class)
    public void testDeleteBOM() throws DataException {
        System.out.println("\ndeleteBOM");
        PresentationController instance = new PresentationController(DBURL.TEST);
        BillOfMaterials deletingBOM = instance.getBOM(2);
        instance.deleteBOM(deletingBOM);
        BillOfMaterials deletedBOM = instance.getBOM(2);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test
    public void testConvertBOMMap() throws Exception {
        System.out.println("convertBOMMap");
        PresentationController instance = new PresentationController(DBURL.TEST);
        BillOfMaterials bom = instance.getBOM(1);
        Map<Component, Integer> expResult = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : bom.getComponents().entrySet()) {
            expResult.put(instance.getComponent(entry.getKey()), 
                    entry.getValue());
        }
        Map<Component, Integer> result = instance.convertBOMMap(bom);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testCalculatePriceOfBOM() throws Exception {
        System.out.println("calculatePriceOfBOM");
        PresentationController instance = new PresentationController(DBURL.TEST);
        BillOfMaterials bom = instance.getBOM(1);
        float expResult = 240F;
        float result = instance.calculatePriceOfBOM(bom);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result, 0.0);
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

    @Test(expected = DataException.class)
    public void testGetCarportNull() throws Exception {
        System.out.println("getCarportNull");
        int orderId = 0;
        PresentationController instance = new PresentationController(DBURL.TEST);
        Carport result = instance.getCarport(orderId);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test(expected = DataException.class)
    public void testGetCarportSQL() throws Exception {
        System.out.println("getCarportSQL");
        PresentationController instance = new PresentationController(DBURL.TEST);
        Carport result = instance.getCarport(1234);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test
    public void testGetAllCarports() throws Exception {
        System.out.println("getAllCarports");
        PresentationController instance = new PresentationController(DBURL.TEST);
        int expResult = 2;
        int result = instance.getAllCarports().size();
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateCarport() throws Exception {
        System.out.println("createCarport");
        Carport expResult = new Carport(4, 1, 7000, 2500, 2000, 6000, 1500, 1000);
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.createCarport(expResult);
        Carport result = instance.getCarport(4);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test(expected = DataException.class)
    public void testCreateCarportNull() throws Exception {
        System.out.println("createCarportNull");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.createCarport(null);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test(expected = DataException.class)
    public void testCreateCarportSQL() throws Exception {
        System.out.println("createCarportSQL");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.createCarport(new Carport(4000, 1, 7000, 2500, 2000, 6000, 1500,
                1000));
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    public void testDeleteCarport() throws Exception {
        System.out.println("deleteCarport");
        Carport carport = new Carport(2, 1, 8000, 2500, 4000, 6000, 1500, 1000);
        PresentationController instance = new PresentationController(DBURL.TEST);
        System.out.println(instance.getCarport(2));
        instance.deleteCarport(carport);
        Carport deletedCarport = instance.getCarport(2);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test(expected = DataException.class)
    public void testDeleteCarportNull() throws Exception {
        System.out.println("deleteCarportNull");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.deleteCarport(null);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test(expected = DataException.class)
    public void testDeleteCarportSQL() throws Exception {
        System.out.println("deleteCarportSQL");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.deleteCarport(new Carport(2049, 1, 8000, 2500, 4000, 6000, 1500, 1000));
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////CASE TESTS////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    @Test(expected = DataException.class)
    public void testGetMessages() throws Exception {
        System.out.println("getMessages");
        String rank = "";
        PresentationController instance = new PresentationController(DBURL.TEST);
        List<Message> result = instance.getMessages(rank);
    }

    @Test(expected = DataException.class)
    public void testGetMessageNul() throws Exception {
        System.out.println("getMessageNul");
        int ID = 0;
        PresentationController instance = new PresentationController(DBURL.TEST);
        Message result = instance.getMessage(ID);
    }

    @Test(expected = DataException.class)
    public void testGetCaseNull() throws Exception {
        System.out.println("getCaseNull");
        int CaseNr = 0;
        PresentationController instance = new PresentationController(DBURL.TEST);
        Case result = instance.getCase(CaseNr);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////COMPONENT TESTS///////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    @Test
    public void testGetAllComponents() throws Exception {
        System.out.println("getAllComponents");
        PresentationController instance = new PresentationController(DBURL.TEST);
        int expResult = 14;
        int result = instance.getAllComponents().size();
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetComponent() throws Exception {
        System.out.println("\ngetComponent");
        int ComponentId = 1;
        PresentationController instance = new PresentationController(DBURL.TEST);
        Component expResult = new Component(1, "38x73 mm. taglægte T1",
                "til montering på spær, 7 rækker lægter på hver skiftevis 1 hel"
                + " & 1 halv lægte", 5400, 38, 73, (float) 91.53);
        Component result = instance.getComponent(ComponentId);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test(expected = DataException.class)
    public void testGetComponentNul() throws Exception {
        System.out.println("\ngetComponentNul");
        PresentationController instance = new PresentationController(DBURL.TEST);
        Component result = instance.getComponent(0);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test(expected = DataException.class)
    public void testGetComponentSQL() throws Exception {
        System.out.println("\ngetComponentSQL");
        PresentationController instance = new PresentationController(DBURL.TEST);
        Component result = instance.getComponent(1111);
        System.out.println("Exception: " + DataException.class + "\n\n");
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

    @Test(expected = DataException.class)
    public void testCreateComponentNull() throws Exception {
        System.out.println("\ncreateComponentNull");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.createComponent(null);
        System.out.println("Exception: " + DataException.class + "\n\n");
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
    public void testUpdateComponentNull() throws Exception {
        System.out.println("\nupdateComponentNull");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.updateComponent(null, null);
        System.out.println("Exception: " + DataException.class + "\n\n");
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

    @Test(expected = DataException.class)
    public void testDeleteComponentNull() throws DataException {
        System.out.println("\ndeleteComponentNull");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.deleteComponent(null);
        System.out.println("Exception: " + DataException.class + "\n\n");
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

    @Test(expected = DataException.class)
    public void testGetCustomerStringsNull() throws Exception {
        System.out.println("\ngetCustomerStringsNull");
        String email = null;
        String password = null;
        PresentationController instance = new PresentationController(DBURL.TEST);
        Customer result = instance.getCustomer(email, password);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test(expected = DataException.class)
    public void testGetCustomerStringsEmpty() throws Exception {
        System.out.println("\ngetCustomerStringsEmpty");
        String email = "";
        String password = "";
        PresentationController instance = new PresentationController(DBURL.TEST);
        Customer result = instance.getCustomer(email, password);
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        System.out.println("getAllCustomers");
        PresentationController instance = new PresentationController(DBURL.TEST);
        int expResult = 2;
        int result = instance.getAllCustomers().size();
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
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

    @Test(expected = DataException.class)
    public void testGetCustomerIntNul() throws Exception {
        System.out.println("\ngetCustomerIntNul");
        PresentationController instance = new PresentationController(DBURL.TEST);
        Customer result = instance.getCustomer(0);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test(expected = DataException.class)
    public void testGetCustomerIntSQL() throws Exception {
        System.out.println("\ngetCustomerIntSQL");
        PresentationController instance = new PresentationController(DBURL.TEST);
        Customer result = instance.getCustomer(1110);
        System.out.println("Exception: " + DataException.class + "\n\n");
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

    @Test(expected = DataException.class)
    public void testCreateCustomerNull() throws Exception {
        System.out.println("\ncreateCustomerNull");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.createCustomer(null);
        System.out.println("Exception: " + DataException.class + "\n\n");
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
    public void testUpdateCustomerNull() throws Exception {
        System.out.println("\nupdateCustomerNull");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.updateCustomer(null, null);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test(expected = DataException.class)
    public void testDeleteCustomer() throws DataException {
        System.out.println("\ndeleteCustomer");
        PresentationController instance = new PresentationController(DBURL.TEST);
        Customer deletingCustomer = instance.getCustomer("boergensen@boergemail.com", "4512");
        instance.deleteCustomer(deletingCustomer);
        Customer deletedCustomer = instance.getCustomer("boergensen@boergemail.com", "4512");
        System.out.println("Exception: " + DataException.class);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test(expected = DataException.class)
    public void testDeleteCustomerNull() throws DataException {
        System.out.println("\ndeleteCustomerNull");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.deleteCustomer(null);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test(expected = DataException.class)
    public void testDeleteCustomerSQL() throws DataException {
        System.out.println("\ndeleteCustomerSQL");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.deleteCustomer(new Customer(2222, "Børge Børgesen", "boerge@boergemail.com", "123", "54789565"));
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////EMPLOYEE TESTS////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    @Test
    public void testGetAllEmployees() throws Exception {
        System.out.println("getAllEmployees");
        PresentationController instance = new PresentationController(DBURL.TEST);
        int expResult = 5;
        int result = instance.getAllEmployees().size();
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

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

    @Test(expected = DataException.class)
    public void testGetEmployeeStringNull() throws Exception {
        System.out.println("\ngetEmployee StringsNull");
        String email = null;
        String password = null;
        PresentationController instance = new PresentationController(DBURL.TEST);
        Employee result = instance.getEmployee(email, password);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test(expected = DataException.class)
    public void testGetEmployeeStringEmpty() throws Exception {
        System.out.println("\ngetEmployee StringsEmpty");
        String email = "";
        String password = "";
        PresentationController instance = new PresentationController(DBURL.TEST);
        Employee result = instance.getEmployee(email, password);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test(expected = DataException.class)
    public void testGetEmployeeStringSQL() throws Exception {
        System.out.println("\ngetEmployeeStringsSQL");
        String email = "Hans Borgeson";
        String password = "Holla";
        PresentationController instance = new PresentationController(DBURL.TEST);
        Employee result = instance.getEmployee(email, password);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test
    public void testGetEmployeeInt() throws Exception {
        System.out.println("\ngetEmployee Int");
        Employee expResult = new Employee(1, "halltheprotocol", "36459865", "hall@testmail.com", "4567", "admin");
        PresentationController instance = new PresentationController(DBURL.TEST);
        Employee result = instance.getEmployee(1);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test(expected = DataException.class)
    public void testGetEmployeeIntNul() throws Exception {
        System.out.println("\ngetEmployeeIntNul");
        PresentationController instance = new PresentationController(DBURL.TEST);
        Employee result = instance.getEmployee(0);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test(expected = DataException.class)
    public void testGetEmployeeIntSQL() throws Exception {
        System.out.println("\ngetEmployeeIntSQL");
        PresentationController instance = new PresentationController(DBURL.TEST);
        Employee result = instance.getEmployee(1110);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test
    public void testCreateEmployee() throws Exception {
        System.out.println("\ncreateEmployee");
        Employee expResult = new Employee(5, "Niels Nielsen", "85457858", "niels@nielsmail.com", "6584", "admin");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.createEmployee(expResult);
        Employee result = instance.getEmployee("niels@nielsmail.com", "6584");
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test(expected = DataException.class)
    public void testCreateEmployeeNull() throws Exception {
        System.out.println("\ncreateEmployeeNull");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.createEmployee(null);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        System.out.println("\nupdateEmployee");
        Employee employee = new Employee("Hans Hansen", "23456789", "hans@hansen.dk", "1111", "superadmin");
        Employee newEmployee = new Employee(4, "Peter Højer Nielsen", "45781512", "peter@nielsenmail.com", "4578", "superadmin");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.updateEmployee(employee, newEmployee);
        Employee result = instance.getEmployee("peter@nielsenmail.com", "4578");
        Employee expResult = newEmployee;
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test(expected = DataException.class)
    public void testUpdateEmployeeNull() throws Exception {
        System.out.println("\nupdateEmployeeNull");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.updateEmployee(null, null);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test(expected = DataException.class)
    public void testDeleteEmployee() throws DataException {
        System.out.println("\ndeleteEmployee");
        PresentationController instance = new PresentationController(DBURL.TEST);
        Employee deletingCustomer = instance.getEmployee("brandan@testmail.com", "7890");
        instance.deleteEmployee(deletingCustomer);
        Employee deletedCustomer = instance.getEmployee("brandan@testmail.com", "7890");
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test(expected = DataException.class)
    public void testDeleteEmployeeNull() throws DataException {
        System.out.println("\ndeleteEmployeeNull");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.deleteEmployee(null);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test(expected = DataException.class)
    public void testDeleteEmployeeSQL() throws DataException {
        System.out.println("\ndeleteEmployeeSQL");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.deleteEmployee(new Employee("SuperAdministrator", "37373737",
                "adminini@fogh.dk", "1337", "superadmin"));
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////////ORDER TESTS////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    @Test
    public void testGetOrder() throws Exception {
        System.out.println("\ngetOrder");
        int orderId = 1;
        Order order = new Order(1, 1, Date.valueOf("2019-04-03"),
                Date.valueOf("2019-04-14"), "fantasivej 12 Lyngby", "sent", (float) 240.0);
        PresentationController instance = new PresentationController(DBURL.TEST);
        Order expResult = order;
        Order result = instance.getOrder(orderId);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test(expected = DataException.class)
    public void testGetOrderNul() throws Exception {
        System.out.println("\ngetOrderNul");
        int orderId = 0;
        PresentationController instance = new PresentationController(DBURL.TEST);
        Order result = instance.getOrder(orderId);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test(expected = DataException.class)
    public void testGetOrderSQL() throws Exception {
        System.out.println("\ngetOrderSQL");
        int orderId = 1110;
        PresentationController instance = new PresentationController(DBURL.TEST);
        Order result = instance.getOrder(orderId);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test
    public void testAllGetOrders() throws Exception {
        System.out.println("\ngetAllOrders");
        PresentationController instance = new PresentationController(DBURL.TEST);
        int expResult = 5;
        int result = instance.getAllOrders().size();
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
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
    public void testUpdateOrderNull() throws Exception {
        System.out.println("\nupdateOrderNull");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.updateOrder(null, null);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test(expected = DataException.class)
    public void testDeleteOrder() throws DataException {
        System.out.println("\ndeleteOrder");
        PresentationController instance = new PresentationController(DBURL.TEST);
        Order deletingOrder = instance.getOrder(2);
        instance.deleteOrder(deletingOrder);
        Order deletedOrder = instance.getOrder(2);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test(expected = DataException.class)
    public void testDeleteOrderSQL() throws DataException {
        System.out.println("\ndeleteOrderSQL");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.deleteOrder(new Order(3000, 1, Date.valueOf("2019-04-03"),
                Date.valueOf("2019-04-14"), "fantasivej 12 Lyngby", "sent", (float) 123.0));
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test
    public void testMarkOrderAsSent() throws Exception {
        System.out.println("markOrderAsSent");
        int orderId = 4;
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.markOrderAsSent(orderId);
        String expResult = "sent";
        String result = instance.getOrder(3).getOrder_status();
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
    }

    @Test(expected = DataException.class)
    public void testMarkOrderAsSentNull() throws Exception {
        System.out.println("markOrderAsSentNull");
        int orderId = 0;
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.markOrderAsSent(orderId);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test(expected = LogicException.class)
    public void testGeneratePDFNull() throws Exception {
        System.out.println("generatePDFNull");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.generatePDFFromOrder(null, null);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////ROOF TESTS////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    @Test
    public void testGetRoof() throws Exception {
        System.out.println("getRoof");
        int roofTypeId = 2;
        Roof roof = new Roof(2, 15, "Betontagsten", "Sort", null);
        PresentationController instance = new PresentationController(DBURL.TEST);
        Roof expResult = roof;
        Roof result = instance.getRoof(roofTypeId);
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test(expected = DataException.class)
    public void testGetRoofNull() throws Exception {
        System.out.println("getRoofNull");
        int roofTypeId = 0;
        PresentationController instance = new PresentationController(DBURL.TEST);
        Roof result = instance.getRoof(roofTypeId);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test(expected = DataException.class)
    public void testGetRoofSQL() throws Exception {
        System.out.println("getRoofSQL");
        int roofTypeId = 1110;
        PresentationController instance = new PresentationController(DBURL.TEST);
        Roof result = instance.getRoof(roofTypeId);
        System.out.println("Exception: " + DataException.class + "\n\n");
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

    @Test(expected = DataException.class)
    public void testCreateRoofNull() throws Exception {
        System.out.println("createRoofNull");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.createRoof(null);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test
    public void testUpdateRoof() throws Exception {
        System.out.println("updateRoof");
        Roof roof = new Roof(3, 0, "Eternittag", "Grå", "B6");
        Roof newRoof = new Roof(3, 25, "Eternittag", "Rød", "B6");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.updateRoof(roof, newRoof);
        Roof result = instance.getRoof(3);
        Roof expResult = newRoof;
        System.out.println("expResult: " + expResult);
        System.out.println("   result: " + result);
        assertEquals(expResult, result);
    }

    @Test(expected = DataException.class)
    public void testUpdateRoofNull() throws Exception {
        System.out.println("updateRoofNull");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.updateRoof(null, null);
        System.out.println("Exception: " + DataException.class + "\n\n");
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

    @Test(expected = DataException.class)
    public void testDeleteRoofNull() throws Exception {
        System.out.println("deleteRoofNull");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.deleteRoof(null);
        System.out.println("Exception: " + DataException.class + "\n\n");
    }

    @Test(expected = DataException.class)
    public void testDeleteRoofSQL() throws Exception {
        System.out.println("deleteRoofSQL");
        PresentationController instance = new PresentationController(DBURL.TEST);
        instance.deleteRoof(new Roof(5222, 25, "Eternittag", "Rød", "B6"));
        System.out.println("Exception: " + DataException.class + "\n\n");
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
