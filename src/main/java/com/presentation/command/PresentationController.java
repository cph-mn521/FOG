package com.presentation.command;

import com.enumerations.DBURL;
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
import com.exceptions.PDFException;
import com.logic.LogicController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author martin bøgh
 */
public class PresentationController {

    private final LogicController logic;

    public PresentationController(DBURL dbURL) throws DataException {
        logic = new LogicController(dbURL);
    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////CUSTOMER ACTIONS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    /**
     * Retrieves a Customer from the database consisting with the provided
     * email address and password. This overload is used specifically for login
     * functions.
     * 
     * @param email the email of the Customer logging in
     * @param password the password of the Customer logging in
     * @return a Customer DTO Java object
     * @throws DataException if an error occurs in the data layer
     */
    public Customer getCustomer(String email, String password) throws DataException {
        return logic.getCustomer(email, password);
    }
    
    /**
     * Retrieves a Customer from the database consisting with the provided id.
     * 
     * @param id the id of the Customer to retrieve
     * @return a Customer DTO Java object
     * @throws DataException if an error occurs in the data layer
     */
    public Customer getCustomer(int id) throws DataException {
        return logic.getCustomer(id);
    }

    /**
     * Persists a Customer DTO object to the database.
     * 
     * @param customer the Customer DTO Java object to persist
     * @throws DataException if an error occurs in the data layer
     */
    public void createCustomer(Customer customer) throws DataException {
        logic.createCustomer(customer);
    }

    /**
     * Updates a Customer in the database with a provided Customer DTO object.
     * This method basically overrides everything but the id column of an entry.
     * If the objects share an id the same object can be used as both parameters.
     * 
     * @param customer the old Customer entry that needs to be updated
     * @param newCustomer the new Customer DTO Java object to override with
     * @throws DataException if an error occurs in the data layer
     */
    public void updateCustomer(Customer customer, Customer newCustomer) throws DataException {
        logic.updateCustomer(customer, newCustomer);
    }

    /**
     * Deletes a provided Customer from the database.
     * 
     * @param customer the Customer DTO Java object equal to the entry to delete
     * in the database
     * @throws DataException if an error occurs in the data layer
     */
    public void deleteCustomer(Customer customer) throws DataException {
        logic.deleteCustomer(customer);
    }

    /**
     * Retrieves a list of Customer DTO objects from the database.
     * 
     * @return a list of Customer DTO Java objects
     * @throws DataException if an error occurs in the data layer
     */
    public List<Customer> getAllCustomers() throws DataException {
        return logic.getAllCustomers();
    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////EMPLOYEE ACTIONS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    /**
     * Retrieves an Employee from the database consisting with the provided
     * email address and password. This overload is used specifically for login
     * functions.
     * 
     * -- is only used for testing. Use LoginEmployee for actual login --
     * 
     * @param email the email of the Employee logging in
     * @param password the password of the Employee logging in
     * @return an Employee DTO Java object
     * @throws DataException if an error occurs in the data layer
     */
    public Employee getEmployee(String email, String password) throws DataException {
        return logic.getEmployee(email, password);
    }
    
    /**
     * 
     * 
     * @param usn the username of the Employee
     * @param psw the password of the Employee
     * @param request a HttpServletRequest
     * @return an Employee DTO Java object
     * @throws DataException if an error occurs in the data layer
     * @author Wulff
     */
    public Employee LoginEmployee(String usn, String psw, HttpServletRequest request) throws DataException {
        HttpSession ses = request.getSession();
        Employee emp = logic.getEmployee(usn, psw);
        ses.setAttribute("user", emp);
        ses.setAttribute("rank", emp.getRank());
        try {
            List<Case> cases = logic.getCases(emp.getEmployee_id());
            ses.setAttribute("Cases", cases);
            List<Case> Ccases = logic.getClosedCases(emp.getEmployee_id());
            ses.setAttribute("oldCases", Ccases);
        } catch (DataException e) {
            ses.setAttribute("Cases", null);
        }
        return emp;
    }

    /**
     * Retrieves an Employee from the database consisting with the provided id.
     * 
     * @param id the id of the Employee to retrieve
     * @return an Employee DTO Java object
     * @throws DataException if an error occurs in the data layer
     */
    public Employee getEmployee(int id) throws DataException {
        return logic.getEmployee(id);
    }
    
    /**
     * Persists an Employee DTO object to the database.
     * 
     * @param employee the Employee DTO Java object to persist
     * @throws DataException if an error occurs in the data layer
     */
    public void createEmployee(Employee employee) throws DataException {
        logic.createEmployee(employee);
    }

    /**
     * Updates an Employee in the database with a provided Employee DTO object.
     * This method basically overrides everything but the id column of an entry.
     * If the objects share an id the same object can be used as both parameters.
     * 
     * @param employee the old Employee entry that needs to be updated
     * @param newEmployee the new Employee DTO Java object to override with
     * @throws DataException if an error occurs in the data layer
     */
    public void updateEmployee(Employee employee, Employee newEmployee) throws DataException {
        logic.updateEmployee(employee, newEmployee);
    }

    /**
     * Deletes a provided Employee from the database.
     * 
     * @param employee the Employee DTO Java object equal to the entry to delete
     * in the database
     * @throws DataException if an error occurs in the data layer
     */
    public void deleteEmployee(Employee employee) throws DataException {
        logic.deleteEmployee(employee);
    }

    /**
     * Retrieves a list of Employee DTO objects from the database.
     * 
     * @return a list of Employee DTO Java objects
     * @throws DataException if an error occurs in the data layer
     */
    public List<Employee> getAllEmployees() throws DataException {
        return logic.getAllEmployees();
    }
    
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////ORDERMAPPING////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    /**
     * Retrieves an Order from the database consisting with the provided id.
     * 
     * @param orderId the id of the Order to retrieve
     * @return an Order DTO Java object
     * @throws DataException if an error occurs in the data layer
     */
    public Order getOrder(int orderId) throws DataException {
        return logic.getOrder(orderId);
    }
    
    /**
     * Updates an Order in the database with a provided Order DTO object.
     * This method basically overrides everything but the id column of an entry.
     * If the objects share an id the same object can be used as both parameters.
     * 
     * @param order the old Employee entry that needs to be updated
     * @param newOrder the new Employee DTO Java object to override with
     * @throws DataException if an error occurs in the data layer
     */
    public void updateOrder(Order order, Order newOrder) throws DataException {
        logic.updateOrder(order, newOrder);
    }

    /**
     * Deletes a provided Order from the database.
     * 
     * @param order the Order DTO Java object equal to the entry to delete
     * in the database
     * @throws DataException if an error occurs in the data layer
     */
    public void deleteOrder(Order order) throws DataException {
        logic.deleteOrder(order);
    }
    
    /**
     * Retrieves a list of Order DTO objects from the database.
     * 
     * @return a list of Order DTO Java objects
     * @throws DataException if an error occurs in the data layer
     */
    public List<Order> getAllOrders() throws DataException {
        return logic.getAllOrders();
    }
    
    /**
     * Formats a float from an order's total cost value into a string with the
     * format '$$.$$kr.'.
     * 
     * @param order the of which to format the cost
     * @return the String in the correct format
     * @author Brandstrup
     */
    public String formatTotalCostFloatToString(Order order)
    {
        return logic.formatTotalCostFloatToString(order);
    }
    
    /**
     * Saves a complete PDF file to a specified path.
     *
     * @param order the order to which the PDF is associated
     * @param filePath the path to save the PDF file
     * @throws DataException if an error occurs in the data layer
     * @throws LogicException if an error occurs in the logic layer
     * @author Brandstrup
     */
    public void generatePDFFromOrder(Order order, String filePath) throws DataException, LogicException
    {
        logic.generatePDFFromOrder(order, filePath);
    }

    /**
     * Creates and persist an entire order as well as all objects related to
     * said order both as Java objects and as entries in the database. Requires
     * a Customer object, presumably from whomever is currently logged in. Also
     * generates and saves a PDF file containing the bill of materials.
     * Finally this method creates a Case in the database to attach the order to.
     *
     * The entire list of entries getting persisted to the database: Carport,
     * Roof, BillOfMaterials (calculated and written to PDF), Order (with
     * totalPriceCalculation) and lastly case.
     *
     * @param customer the Customer to whom the order should be attached
     * @param customerAddress the address of said customer
     * @param roofTypeId the id of the type of roof selected
     * @param carportLength
     * @param carportWidth
     * @param carportHeight
     * @param shedLength 0 if no shed is chosen
     * @param shedWidth 0 if no shed is chosen
     * @param shedHeight 0 if no shed is chosen
     * @param filePath the path to the location the PDF is saved
     * @param caseMessage the message to attach to the case this order is
     * created for
     * @return the Order object created
     * @throws DataException if an error occurs in the data layer
     * @throws LogicException if an error occurs in the logic layer
     * @author Brandstrup
     */
    public Order createOrder(Customer customer, String customerAddress,
            int roofTypeId, int carportLength, int carportWidth, int carportHeight,
            int shedLength, int shedWidth, int shedHeight, String filePath, 
            String caseMessage) throws DataException, LogicException
    {
        return logic.createOrder(customer, customerAddress,
                roofTypeId, carportLength, carportWidth, carportHeight,
                shedLength, shedWidth, shedHeight, filePath, caseMessage);
    }
    
    /**
     * Updates an Order instance in the database to be marked as sent. Also
     * provides the current date as the sending date.
     *
     * @param orderId the id of the order to update
     * @throws DataException if an error occurs in the data layer
     * @author Brandstrup
     */
    public void markOrderAsSent(int orderId) throws DataException
    {
        logic.markOrderAsSent(orderId);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////BILL OF MATERIALS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    /**
     * Retrieves a Bill from the database consisting with the provided id.
     * 
     * @param bomId the id of the Bill to retrieve
     * @return a BillOfMaterials DTO Java object
     * @throws DataException if an error occurs in the data layer
     */
    public BillOfMaterials getBOM(int bomId) throws DataException {
        return logic.getBOM(bomId);
    }

    /**
     * Updates a Bill in the database with a provided BillOfMaterials DTO object.
     * This method basically overrides everything but the id column of an entry.
     * If the objects share an id the same object can be used as both parameters.
     * 
     * @param BOM the old Bill entry that needs to be updated
     * @param newBOM the new BillOfMaterials DTO Java object to override with
     * @throws DataException if an error occurs in the data layer
     */
    public void updateBOM(BillOfMaterials BOM, BillOfMaterials newBOM) throws DataException {
        logic.updateBOM(BOM, newBOM);
    }

    /**
     * Deletes a provided Bill from the database.
     * 
     * @param BOM the BillOfMaterials DTO Java object equal to the entry to
     * delete in the database
     * @throws DataException if an error occurs in the data layer
     */
    public void deleteBOM(BillOfMaterials BOM) throws DataException {
        logic.deleteBOM(BOM);
    }

    /**
     * Communicates with the Data layer to gather information about an order in
     * order to calculate, create and persist a bill of materials to the DB.
     *
     * @param orderId the id of the order whose bill needs to be persisted
     * @param carport
     * @param roof
     * @return the BillOfMaterial object that is also being generated in the DB
     * @throws DataException if an error occurs in the data layer
     * @author Brandstrup
     */
    public BillOfMaterials generateBOM(int orderId, Carport carport, Roof roof) throws DataException
    {
        return logic.generateBOM(orderId, carport, roof);
    }
    
    /**
     * Communicates with the Data layer to gather information about a bill of
     * materials in order to calculate the total cost of the entire carport.
     *
     * @param bom the BillOfMaterials object to calculate
     * @return a float value of the total cost of an entire bill
     * @throws DataException if an error occurs in the data layer
     * @throws LogicException if an error occurs in the logic layer
     * @author Brandstrup
     */
    public float calculatePriceOfBOM(BillOfMaterials bom) throws DataException, LogicException
    {
        return logic.calculatePriceOfBOM(bom);
    }

    /**
     * Receives a bill of material object consisting of a HashMap containing the
     * IDs (key) of the Components it contains as well as the amount (value),
     * then converts these integers into a new HashMap containing actual DTOs of
     * these Components (as well as the amount).
     *
     * @param bom the bill of material object to convert into a usable Map
     * @return a Map<Component, Integer> that is easier to use in presentation
     * @throws DataException if an error occurs in the data layer
     * @throws LogicException if an error occurs in the logic layer
     * @author Brandstrup
     */
    public Map<Component, Integer> convertBOMMap(BillOfMaterials bom) throws DataException, LogicException
    {
        return logic.convertBOMMap(bom);
    }

    /**
     * Receives a HashMap<Component, Integer> and formats it into usable Strings
     * that can be used for presentation.
     * Includes commas in float numbers and trimming zeroes as well as adding
     * m. and kr. where applicable.
     *
     * @param bom the Map from which to extract data
     * @return an List of Strings formatted to be presented
     * @author Brandstrup
     */
    public List<String> convertBillToStringList(Map<Component, Integer> bom) throws PDFException
    {
        return logic.convertBillToStringList(bom);
    }
    
    /**
     * Receives a bill of material object consisting of a HashMap containing the
     * IDs (key) of the Components it contains as well as the amount (value),
     * and formats them into usable Strings that can be used for presentation.
     * Includes commas in float numbers and trimming zeroes as well as adding
     * m/mm and kr. where applicable.
     *
     * @param bom the BillOfMaterials object to convert
     * @return an List of Strings formatted to be presented
     * @throws DataException if an error occurs in the data layer
     * @throws PDFException if an error occurs during the generation of the PDF
     * @throws LogicException if an error occurs in the logic layer
     * @author Brandstrup
     */
    public List<String> convertBillToStringList(BillOfMaterials bom) throws DataException, LogicException, PDFException
    {
        return logic.convertBillToStringList(bom);
    }

    /**
     * Saves a complete PDF file from a bill of materials map.
     *
     * @param bom the Bill of Materials Map containing the data required
     * @param author the author of the document; ie. the person generating it
     * @param fileName the name to save the file as
     * @param filePath the path to save the file to
     * @throws LogicException if an error occurs in the logic layer
     * @author Brandstrup
     */
    public void generatePDFFromBill(Map<Component, Integer> bom, String author, 
            String fileName, String filePath) throws LogicException
    {
        logic.generatePDFFromBill(bom, author, fileName, filePath);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////COMPONENTS//////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    /**
     * Retrieves a Component from the database consisting with the provided id.
     * 
     * @param ComponentId the id of the Component to retrieve
     * @return a Component DTO Java object
     * @throws DataException if an error occurs in the data layer
     */
    public Component getComponent(int ComponentId) throws DataException {
        return logic.getComponent(ComponentId);
    }

    /**
     * Persists a Component DTO object to the database.
     * 
     * @param Component the Component DTO Java object to persist
     * @throws DataException if an error occurs in the data layer
     */
    public void createComponent(Component Component) throws DataException {
        logic.createComponent(Component);
    }

    /**
     * Updates a Component in the database with a provided Component DTO object.
     * This method basically overrides everything but the id column of an entry.
     * If the objects share an id the same object can be used as both parameters.
     * 
     * @param Component the old Component entry that needs to be updated
     * @param newComponent the new Component DTO Java object to override with
     * @throws DataException if an error occurs in the data layer
     */
    public void updateComponent(Component Component, Component newComponent) throws DataException {
        logic.updateComponent(Component, newComponent);
    }

    /**
     * Deletes a provided Component from the database.
     * 
     * @param Component the Component DTO Java object equal to the entry to
     * delete in the database
     * @throws DataException if an error occurs in the data layer
     */
    public void deleteComponent(Component Component) throws DataException {
        logic.deleteComponent(Component);
    }

    /**
     * Retrieves a list of Component DTO objects from the database.
     * 
     * @return a list of Component DTO Java objects
     * @throws DataException if an error occurs in the data layer
     */
    public List<Component> getAllComponents() throws DataException {
        return logic.getAllComponents();
    }

    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////////CARPORT////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    /**
     * Retrieves a Carport from the database consisting with the provided id.
     * 
     * @param orderId the id of the Carport to retrieve
     * @return a Carport DTO Java object
     * @throws DataException if an error occurs in the data layer
     */
    public Carport getCarport(int orderId) throws DataException {
        return logic.getCarport(orderId);
    }

    /**
     * Persists a Carport DTO object to the database.
     * 
     * @param carport the Carport DTO Java object to persist
     * @throws DataException if an error occurs in the data layer
     */
    public void createCarport(Carport carport) throws DataException {
        logic.createCarport(carport);
    }

    /**
     * Deletes a provided Carport from the database.
     * 
     * @param carport the Carport DTO Java object equal to the entry to delete
     * in the database
     * @throws DataException if an error occurs in the data layer
     */
    public void deleteCarport(Carport carport) throws DataException {
        logic.deleteCarport(carport);
    }

    /**
     * Retrieves a list of Carport DTO objects from the database.
     * 
     * @return a list of Carport DTO Java objects
     * @throws DataException if an error occurs in the data layer
     */
    public List<Carport> getAllCarports() throws DataException {
        return logic.getAllCarports();
    }
    
    ///////////////////////////////////////////////////////////////////////////
    //////////////////////////////////ROOF/////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    /**
     * Retrieves a Roof from the database consisting with the provided id.
     * 
     * @param roofTypeId id of the Roof to retrieve
     * @return a Roof DTO Java object
     * @throws DataException if an error occurs in the data layer
     */
    public Roof getRoof(int roofTypeId) throws DataException {
        return logic.getRoof(roofTypeId);
    }

    /**
     * Persists a Roof DTO object to the database.
     * 
     * @param roof the Roof DTO Java object to persist
     * @throws DataException if an error occurs in the data layer
     */
    public void createRoof(Roof roof) throws DataException {
        logic.createRoof(roof);
    }

    /**
     * Updates a Roof in the database with a provided Customer DTO object.
     * This method basically overrides everything but the id column of an entry.
     * If the objects share an id the same object can be used as both parameters.
     * 
     * @param roof the old Roof entry that needs to be updated
     * @param newRoof the new Roof DTO Java object to override with
     * @throws DataException if an error occurs in the data layer
     */
    public void updateRoof(Roof roof, Roof newRoof) throws DataException {
        logic.updateRoof(roof, newRoof);
    }

    /**
     * Deletes a provided Roof from the database.
     * 
     * @param roof the Roof DTO Java object equal to the entry to delete
     * in the database
     * @throws DataException if an error occurs in the data layer
     */

    public void deleteRoof(Roof roof) throws DataException {
        logic.deleteRoof(roof);
    }

    /**
     * Retrieves a list of Roof DTO objects from the database.
     * 
     * @return a list of Roof DTO Java objects
     * @throws DataException if an error occurs in the data layer
     */
    public List<Roof> getAllRoofs() throws DataException {
        return logic.getAllRoofs();
    }

    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////CASE ACTIONS////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public List<Case> getFreeCases(String type) throws DataException {
        return logic.getFreeCases(type);
    }

    public List<Message> getMessages(String rank) throws DataException {
        return logic.getMessages(rank);
    }

    public Message getMessage(int ID) throws DataException {
        return logic.getMessage(ID);
    }

    public Case getCase(int CaseNr) throws DataException {
        return logic.getCase(CaseNr);
    }

    public void getCustomerCases(int ID, HttpServletRequest request) throws DataException{
        List<Case> Cases = logic.getCustomerCases(ID);
        List<Order> Orders = new ArrayList();        
        for (Case C : Cases) {
            Orders.add(logic.getOrder(C.getOrderId()));
        }
        request.setAttribute("Cases", Cases);
        request.setAttribute("Orders", Orders);
    }
    
    public void TakeCase(int emplId, int caseId) throws DataException {
        logic.TakeCase(emplId, caseId);
    }

    public void updCaseStat(int caseID,String stat) throws DataException {
        logic.updCaseStat(caseID,stat);
    }
    
    public void updCaseMsg(Case C) throws DataException{
        logic.updCaseMsg(C);
    }
    
    public void updCasefree(int CaseID) throws DataException{
        logic.updCasefree(CaseID);
    }
    
    public void createCase(Case C) throws DataException{
        logic.createCase(C);
    }

    void createMsg(Message msg) throws DataException {
        logic.createMsg(msg);
    }
}
