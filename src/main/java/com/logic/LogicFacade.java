package com.logic;

import com.data.DAOController;
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
import java.net.URISyntaxException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Martin, Martin Bøgh & Brandstrup
 */
public class LogicFacade {

    DAOController dao;

    public LogicFacade(DBURL dburl) throws DataException {
        this.dao = new DAOController(dburl);
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
        return dao.getCustomer(email, password);
    }
    
    /**
     * Retrieves a Customer from the database consisting with the provided id.
     * 
     * @param id the id of the Customer to retrieve
     * @return a Customer DTO Java object
     * @throws DataException if an error occurs in the data layer
     */
    public Customer getCustomer(int id) throws DataException {
        return dao.getCustomer(id);
    }

    /**
     * Persist a Customer DTO object to the database.
     * 
     * @param customer the Customer DTO Java object to persist
     * @throws DataException if an error occurs in the data layer
     */
    public void createCustomer(Customer customer) throws DataException {
        dao.createCustomer(customer);
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
        dao.updateCustomer(customer, newCustomer);
    }

    /**
     * Deletes a provided Customer from the database.
     * 
     * @param customer the Customer DTO Java object equal to the entry to delete
     * in the database
     * @throws DataException if an error occurs in the data layer
     */
    public void deleteCustomer(Customer customer) throws DataException {
        dao.deleteCustomer(customer);
    }

    /**
     * Retrieves a list of Customer DTO objects from the database.
     * 
     * @return a list of Customer DTO Java objects
     * @throws DataException if an error occurs in the data layer
     */
    public List<Customer> getAllCustomers() throws DataException {
        return dao.getAllCustomers();
    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////EMPLOYEE ACTIONS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    /**
     * Retrieves an Employee from the database consisting with the provided
     * email address and password. This overload is used specifically for login
     * functions.
     * 
     * @param email the email of the Employee logging in
     * @param password the password of the Employee logging in
     * @return an Employee DTO Java object
     * @throws DataException if an error occurs in the data layer
     */
    public Employee getEmployee(String email, String password) throws DataException {
        return dao.getEmployee(email, password);
    }

    /**
     * Retrieves an Employee from the database consisting with the provided id.
     * 
     * @param id the id of the Employee to retrieve
     * @return an Employee DTO Java object
     * @throws DataException if an error occurs in the data layer
     */
    public Employee getEmployee(int id) throws DataException {
        return dao.getEmployee(id);
    }

    /**
     * Persist an Employee DTO object to the database.
     * 
     * @param employee the Employee DTO Java object to persist
     * @throws DataException if an error occurs in the data layer
     */
    public void createEmployee(Employee employee) throws DataException {
        dao.createEmployee(employee);
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
        dao.updateEmployee(employee, newEmployee);
    }

    /**
     * Deletes a provided Employee from the database.
     * 
     * @param employee the Employee DTO Java object equal to the entry to delete
     * in the database
     * @throws DataException if an error occurs in the data layer
     */
    public void deleteEmployee(Employee employee) throws DataException {
        dao.deleteEmployee(employee);
    }

    /**
     * Retrieves a list of Employee DTO objects from the database.
     * 
     * @return a list of Employee DTO Java objects
     * @throws DataException if an error occurs in the data layer
     */
    public List<Employee> getAllEmployees() throws DataException {
        return dao.getAllEmployees();
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
        return dao.getOrder(orderId);
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
        dao.updateOrder(order, newOrder);
    }

    /**
     * Deletes a provided Order from the database.
     * 
     * @param order the Order DTO Java object equal to the entry to delete
     * in the database
     * @throws DataException if an error occurs in the data layer
     */
    public void deleteOrder(Order order) throws DataException {
        dao.deleteOrder(order);
    }
    
    /**
     * Retrieves a list of Order DTO objects from the database.
     * 
     * @return a list of Order DTO Java objects
     * @throws DataException if an error occurs in the data layer
     */
    public List<Order> getAllOrders() throws DataException {
        return dao.getAllOrders();
    }

    /**
     * Formats a float from an order's total cost value into a string with the
     * format '$$.$$kr.'.
     *
     * @param order the of which to format the cost
     * @return the String in the correct format
     * @author Brandstrup
     */
    public String formatTotalCostFloatToString(Order order) {
        MappingLogic calc = new MappingLogic();
        Float totalCost = order.getTotal_price();

        return calc.formatTotalCostFloatToString(totalCost);
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
    public void generatePDFFromOrder(Order order, String filePath) throws DataException, LogicException {
        try {
            MappingLogic calc = new MappingLogic();

            int orderId = order.getOrder_id();
            Map<Component, Integer> bom = convertBOMMap(dao.getBOM(orderId));

            calc.generatePDFFromOrder(order, filePath, bom);
        } catch (NullPointerException | PDFException ex) {
            throw new LogicException(ex.getMessage());
        }
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
    public synchronized Order createOrder(Customer customer, String customerAddress,
            int roofTypeId, int carportLength, int carportWidth, int carportHeight,
            int shedLength, int shedWidth, int shedHeight, String filePath, String caseMessage) throws DataException, LogicException {
        Date currentDate = Date.valueOf(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));

        Order order = new Order(customer.getCustomer_id(), currentDate, null, customerAddress, "pending", 0);
        dao.createOrder(order);
        int orderId = dao.getLastOrder().getOrder_id();
        order.setOrder_id(orderId);

        Carport carport = new Carport(orderId, roofTypeId, carportLength, carportWidth, carportHeight, shedLength, shedWidth, shedHeight);
        createCarport(carport);
        Roof roof = getRoof(roofTypeId);

        BillOfMaterials bill = generateBOM(orderId, carport, roof);
        float totalPrice = calculatePriceOfBOM(bill);
        order.setTotal_price(totalPrice);

        Map<Component, Integer> bomMap = convertBOMMap(bill);

        generatePDFFromBill(bomMap, "Fog", "FOGCarportstykliste_" + orderId + "_" + currentDate.toString(), filePath);

        dao.updateOrder(order, order);
        Case c = new Case(orderId, customer.getCustomer_id(), 0, "", caseMessage);
        dao.createCaseOrder(c);
        return order;
    }

    /**
     * Updates an Order instance in the database to be marked as sent. Also
     * provides the current date as the sending date.
     *
     * @param orderId the id of the order to update
     * @throws DataException if an error occurs in the data layer
     * @author Brandstrup
     */
    public void markOrderAsSent(int orderId) throws DataException {
        Order order = dao.getOrder(orderId);
        Date currentDate = Date.valueOf(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));

        order.setOrder_status("sent");
        order.setOrder_send_date(currentDate);

        dao.updateOrder(order, order);
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
        return dao.getBOM(bomId);
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
        dao.updateBOM(BOM, newBOM);
    }

    /**
     * Deletes a provided Bill from the database.
     * 
     * @param BOM the BillOfMaterials DTO Java object equal to the entry to
     * delete in the database
     * @throws DataException if an error occurs in the data layer
     */
    public void deleteBOM(BillOfMaterials BOM) throws DataException {
        dao.deleteBOM(BOM);
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
    public BillOfMaterials generateBOM(int orderId, Carport carport, Roof roof) throws DataException {
        BOMCalculator calc = new BOMCalculator();

        BillOfMaterials bill = calc.calculateBOM(orderId, carport, roof);

        dao.createBOM(bill);
        return bill;
    }

    /**
     * Communicates with the Data layer to gather information about a bill of
     * materials in order to calculate the total cost of the entire carport.
     *
     * @param bom the BillOfMaterials object to calculate
     * @return a float value of the total cost of an entire bill
     * @throws DataException if an error occurs in the data layer
     * @author Brandstrup
     */
    public float calculatePriceOfBOM(BillOfMaterials bom) throws DataException {
        PriceCalculator calc = new PriceCalculator();

        float price = calc.calculateOrderPrice(bom, dao.getAllComponents());
        return price;
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
     * @author Brandstrup
     */
    public Map<Component, Integer> convertBOMMap(BillOfMaterials bom) throws DataException {
        MappingLogic calc = new MappingLogic();
        try {
            return calc.convertBOMMap(bom, dao.getAllComponents());
        } catch (DataException ex) {
            throw new DataException("Fejl i convertBOMMap: " + ex.getMessage());
        }
    }

    /**
     * Takes a HashMap<Component, Integer> and formats them into usable Strings
     * that can be used for presentation.
     * Includes commas in float numbers and trimming zeroes as well as adding
     * m. and kr. where applicable.
     *
     * @param bom the Map from which to extract data
     * @return an List of Strings formatted to be presented
     * @author Brandstrup
     */
    public List<String> convertBillToStringList(Map<Component, Integer> bom) {
        return new PDFCalculator().stringExtractor(bom);
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
     * @author Brandstrup
     */
    public List<String> convertBillToStringList(BillOfMaterials bom) throws DataException {
        MappingLogic mcalc = new MappingLogic();
        PDFCalculator pcalc = new PDFCalculator();
        Map<Component, Integer> bommap = null;

        try {
            bommap = mcalc.convertBOMMap(bom, dao.getAllComponents());
        } catch (DataException ex) {
            throw new DataException("Fejl i ConvertBillToStringList: " + ex.getMessage());
        }

        return pcalc.stringExtractor(bommap);
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
    public void generatePDFFromBill(Map<Component, Integer> bom, String author, String fileName, String filePath) throws LogicException {
        PDFCalculator calc = new PDFCalculator();

        try {
            calc.generatePDFFromBill(bom, author, fileName, filePath);
        } catch (PDFException | URISyntaxException ex) {
            throw new LogicException("Fejl i generatePDFFromBill: " + ex.getMessage());
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////COMPONENTS//////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Component getComponent(int ComponentId) throws DataException {
        return dao.getComponent(ComponentId);
    }

    public void createComponent(Component Component) throws DataException {
        dao.createComponent(Component);
    }

    public void updateComponent(Component Component, Component newComponent) throws DataException {
        dao.updateComponent(Component, newComponent);
    }

    public void deleteComponent(Component Component) throws DataException {
        dao.deleteComponent(Component);
    }

    public List<Component> getAllComponents() throws DataException {
        return dao.getAllComponents();
    }

    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////////CARPORT////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Carport getCarport(int orderId) throws DataException {
        return dao.getCarport(orderId);
    }

    public void createCarport(Carport carport) throws DataException {
        dao.createCarport(carport);
    }

//    public void deleteCarport(Carport carport) throws DataException {
//        dao.deleteCarport(carport);
//    }
//
//    public List<Carport> getAllCarports() throws DataException {
//        return dao.getAllCarports();
//    }

    ///////////////////////////////////////////////////////////////////////////
    //////////////////////////////////ROOF/////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Roof getRoof(int roofTypeId) throws DataException {
        return dao.getRoof(roofTypeId);
    }

    public void createRoof(Roof roof) throws DataException {
        dao.createRoof(roof);
    }

    public void updateRoof(Roof roof, Roof newRoof) throws DataException {
        dao.updateRoof(roof, newRoof);
    }

    public void deleteRoof(Roof roof) throws DataException {
        dao.deleteRoof(roof);
    }

    public List<Roof> getAllRoofs() throws DataException {
        return dao.getAllRoofs();
    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////CASE ACTIONS��//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Case getCase(String id) throws DataException {
        return dao.getCase(id);
    }

    public List<Case> getCases(int employeeid) throws DataException {
        return dao.getUserCases(employeeid + "");
    }

    public List<Case> getFreeCases(String type) throws DataException {
        return dao.getFreeCase(type);
    }
    //Login Logic:

    public Message getMessage(String ID) throws DataException {
        return dao.getMessage(ID);
    }

    public List<Message> getMessages(String rank) throws DataException {
        return dao.getMessages(rank);
    }

    public void TakeCase(int emplId, int caseId) throws DataException {
        dao.updCaseEmpl(emplId, caseId);
    }

    public List<Case> getClosedCases(int userID) throws DataException {
        return dao.getUserClosedCases(userID);
    }

    public void updCaseStat(int caseID, String stat) throws DataException {
        dao.updCaseStat(caseID, stat);
    }

    public void updCaseMsg(Case C) throws DataException {
        dao.updCaseMsg(C);
    }

    public void updCasefree(int CaseID) throws DataException {
        dao.updCasefree(CaseID);
    }
}
