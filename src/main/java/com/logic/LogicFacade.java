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
import com.entities.dto.User;
import com.exceptions.DataException;
import com.exceptions.PDFException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public User getCustomerFromId(String ID) throws DataException {
        return dao.getCustomerFromId(ID);
    }

    public Customer getCustomer(String email, String password) throws DataException {
        return dao.getCustomer(email, password);
    }

    public Customer getCustomer(int id) throws DataException {
        return dao.getCustomer(id);
    }

    public void createCustomer(Customer customer) throws DataException {
        dao.createCustomer(customer);
    }

    public void updateCustomer(Customer customer, Customer newCustomer) throws DataException {
        dao.updateCustomer(customer, newCustomer);
    }

    public void deleteCustomer(Customer customer) throws DataException {
        dao.deleteCustomer(customer);
    }

    public List<Customer> getAllCustomers() throws DataException {
        return dao.getAllCustomers();
    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////EMPLOYEE ACTIONS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Employee getEmployee(String email, String password) throws DataException {
        return dao.getEmployee(email, password);
    }

    public Employee getEmployee(int id) throws DataException {
        return dao.getEmployee(id);
    }

    public void createEmployee(Employee employee) throws DataException {
        dao.createEmployee(employee);
    }

    public void updateEmployee(Employee employee, Employee newEmployee) throws DataException {
        dao.updateEmployee(employee, newEmployee);
    }

    public void deleteEmployee(Employee employee) throws DataException {
        dao.deleteEmployee(employee);
    }

    public List<Employee> getAllEmployees() throws DataException {
        return dao.getAllEmployees();
    }

    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////ORDERMAPPING////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Order getOrder(int orderId) throws DataException {
        return dao.getOrder(orderId);
    }

    public List<Order> getAllOrders() throws DataException {
        return dao.getAllOrders();
    }

    public int getLastOrderID() throws DataException
    {
        return dao.getLastOrder().getOrder_id();
    }
    
    /**
     * Creates and persist an entire order as well as all objects related to
     * said order both as Java objects and as entries in the database. Requires
     * a Customer object, presumably from whomever is currently logged in. Also
     * generates and saves a PDF file containing the bill of materials to
     * 'src/main/webapp/pdf/'.
     * 
     * The entire list of entries getting persisted to the database:
     * Carport, Roof, BillOfMaterials (calculated and written to PDF),
     * Order (with totalPriceCalculation)
     *
     * @param customer the Customer to whom the order should be attached
     * @param customerAddress the address of said customer
     * @param roofTypeId the id of the type of roof selected
     * @param carportLength
     * @param carportWidth
     * @param carportHeight
     * @param shedLength
     * @param shedWidth
     * @param shedHeight
     * @return the Order object created
     * @throws DataException
     * @throws PDFException
     * @author Brandstrup
     */
    public synchronized Order createOrder(Customer customer, String customerAddress,
            int roofTypeId, int carportLength, int carportWidth, int carportHeight,
            int shedLength, int shedWidth, int shedHeight) throws DataException, PDFException {
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
        generatePDFFromBill(bomMap, "Fog", "Bill" + orderId);
        
        return order;
    }
    
    /**
     * Creates and persist an entire order as well as all objects related to
     * said order both as Java objects and as entries in the database. Requires
     * a Customer object, presumably from whomever is currently logged in.
     * Also generates and saves a PDF file containing the bill of materials to
     * 'src/main/webapp/pdf/'.
     * 
     * The entire list of entries getting persisted to the database:
     * Carport, Roof, BillOfMaterials (calculated and written to PDF),
     * Order (with totalPriceCalculation)
     *
     * @param customer the Customer to whom the order should be attached
     * @param customerAddress the address of said customer
     * @param carport the Carport object to use in the order
     * @return the Order object created
     * @throws DataException
     * @throws PDFException
     * @author Brandstrup
     */
    public synchronized Order createOrder(Customer customer, String customerAddress,
            Carport carport) throws DataException, PDFException {
        Date currentDate = Date.valueOf(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));

        Order order = new Order(customer.getCustomer_id(), currentDate, null, customerAddress, "pending", 0);
        dao.createOrder(order);
        int orderId = dao.getLastOrder().getOrder_id();
        order.setOrder_id(orderId);

        createCarport(carport);
        Roof roof = getRoof(carport.getRoofTypeId());

        BillOfMaterials bill = generateBOM(orderId, carport, roof);
        float totalPrice = calculatePriceOfBOM(bill);
        order.setTotal_price(totalPrice);
        
        Map<Component, Integer> bomMap = convertBOMMap(bill);
        generatePDFFromBill(bomMap, "Fog", "Bill" + orderId);
        
        return order;
    }
    
    public void createOrder(Order order) throws DataException {
        dao.createOrder(order);
    }

    /**
     * Creates and persist an entire order as well as all objects related to
     * said order both as Java objects and as entries in the database. Requires
     * a customerId, presumably from whomever is currently logged in.
     * Also generates and saves a PDF file containing the bill of materials to
     * 'src/main/webapp/pdf/'.
     * 
     * The entire list of entries getting persisted to the database:
     * Carport, Roof, BillOfMaterials (calculated and written to PDF),
     * Order (with totalPriceCalculation)
     *
     * @param customerId the id of the customer to be attached
     * @param customerAddress the address of said customer
     * @param carport the Carport object to use in the order
     * @return the Order object created
     * @throws DataException
     * @throws PDFException
     * @author Brandstrup
     */
    public synchronized Order createOrder(int customerId, String customerAddress,
            Carport carport) throws DataException, PDFException {
        Date currentDate = Date.valueOf(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));

        Order order = new Order(customerId, currentDate, null, customerAddress, "pending", 0);
        dao.createOrder(order);
        int orderId = dao.getLastOrder().getOrder_id();
        order.setOrder_id(orderId);

        createCarport(carport);
        Roof roof = getRoof(carport.getRoofTypeId());

        BillOfMaterials bill = generateBOM(orderId, carport, roof);
        float totalPrice = calculatePriceOfBOM(bill);
        order.setTotal_price(totalPrice);
        
        Map<Component, Integer> bomMap = convertBOMMap(bill);
        generatePDFFromBill(bomMap, "Fog", "Bill" + orderId);
        
        return order;
    }

    /**
     * Updates an Order instance in the database to be marked as sent. Also
     * provides the current date as the sending date.
     *
     * @param orderId the id of the order to update
     * @throws DataException
     * @author Brandstrup
     */
    public void markOrderAsSent(int orderId) throws DataException {
        Order order = dao.getOrder(orderId);
        Date currentDate = Date.valueOf(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));

        order.setOrder_status("sent");
        order.setOrder_send_date(currentDate);

        dao.updateOrder(order, order);
    }

    public void updateOrder(Order order, Order newOrder) throws DataException {
        dao.updateOrder(order, newOrder);
    }

    public void deleteOrder(Order order) throws DataException {
        dao.deleteOrder(order);
    }

    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////BILL OF MATERIALS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public BillOfMaterials getBOM(int bomId) throws DataException {
        return dao.getBOM(bomId);
    }

    public void createBOM(BillOfMaterials BOM) throws DataException {
        dao.createBOM(BOM);
    }

    public void updateBOM(BillOfMaterials BOM, BillOfMaterials newBOM) throws DataException {
        dao.updateBOM(BOM, newBOM);
    }

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
     * @throws DataException
     * @author Brandstrup
     */
    public BillOfMaterials generateBOM(int orderId, Carport carport, Roof roof) throws DataException {
        BOMCalculator calc = new BOMCalculator();

//            int roofId = carport.getRoofTypeId();
//            Roof roof = dao.getRoof(roofId);
//            Carport carport = dao.getCarport(orderId);
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
     * @throws DataException
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
     * @throws DataException
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
     *
     * @param bom the BillOfMaterials object to convert
     * @return an List of Strings formatted to be presented
     * @throws DataException
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
     * Saves a complete PDF file to the local folder 'src/main/webapp/pdf/'.
     *
     * @param bom the Bill of Materials Map containing the data required
     * @param author the author of the document; ie. the person generating it
     * @param fileName the name to save the file as
     * @throws PDFException
     * @author Brandstrup
     */
    public void generatePDFFromBill(Map<Component, Integer> bom, String author, String fileName) throws PDFException {
        PDFCalculator calc = new PDFCalculator();

        try {
            calc.generatePDF(bom, author, fileName);
        } catch (PDFException ex) {
            throw new PDFException("Fejl i generatePDFFromBill: " + ex.getMessage());
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

    public void updateCarport(Carport carport, Carport newCarport) throws DataException {
        dao.updateCarport(carport, newCarport);
    }

    public void deleteCarport(Carport carport) throws DataException {
        dao.deleteCarport(carport);
    }

    public List<Carport> getAllCarports() throws DataException {
        return dao.getAllCarports();
    }

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

    public void closeCase(int caseID) throws DataException {
        dao.closeCase(caseID);
    }
}
