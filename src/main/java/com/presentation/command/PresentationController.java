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
import com.exceptions.PDFException;
import com.logic.LogicFacade;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author martin bøgh
 */
public class PresentationController {

    private final LogicFacade logic;

    public PresentationController(DBURL dbURL) throws DataException {
        logic = new LogicFacade(dbURL);
    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////CUSTOMER ACTIONS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Customer getCustomer(String email, String password) throws DataException {
        return logic.getCustomer(email, password);
    }
    
    public Customer getCustomer(int id) throws DataException {
        return logic.getCustomer(id);
    }

    public void createCustomer(Customer customer) throws DataException {
        logic.createCustomer(customer);
    }

    public void updateCustomer(Customer customer, Customer newCustomer) throws DataException {
        logic.updateCustomer(customer, newCustomer);
    }

    public void deleteCustomer(Customer customer) throws DataException {
        logic.deleteCustomer(customer);
    }

    public List<Customer> getAllCustomers() throws DataException {
        return logic.getAllCustomers();
    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////EMPLOYEE ACTIONS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Employee getEmployee(int id) throws DataException {
        return logic.getEmployee(id);
    }

    public void createEmployee(Employee employee) throws DataException {
        logic.createEmployee(employee);
    }

    public void updateEmployee(Employee employee, Employee newEmployee) throws DataException {
        logic.updateEmployee(employee, newEmployee);
    }

    public void deleteEmployee(Employee employee) throws DataException {
        logic.deleteEmployee(employee);
    }

    public List<Employee> getAllEmployees() throws DataException {
        return logic.getAllEmployees();
    }
    
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

    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////ORDERMAPPING////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Order getOrder(int orderId) throws DataException {
        return logic.getOrder(orderId);
    }

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
     * @throws com.exceptions.DataException
     * @throws com.exceptions.PDFException
     * @author Brandstrup
     */
    public void generatePDFFromOrder(Order order, String filePath) throws DataException, PDFException
    {
        logic.generatePDFFromOrder(order, filePath);
    }

    public Order createOrder(Customer customer, String customerAddress,
            int roofTypeId, int carportLength, int carportWidth, int carportHeight,
            int shedLength, int shedWidth, int shedHeight, String filePath,String msg) throws DataException, PDFException {
        return logic.createOrder(customer, customerAddress,
                roofTypeId, carportLength, carportWidth, carportHeight,
                shedLength, shedWidth, shedHeight, filePath,msg);
    }
    
    public void markOrderAsSent(int orderId) throws DataException
    {
        logic.markOrderAsSent(orderId);
    }
    
    public void updateOrder(Order order, Order newOrder) throws DataException {
        logic.updateOrder(order, newOrder);
    }

    public void deleteOrder(Order order) throws DataException {
        logic.deleteOrder(order);
    }

    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////BILL OF MATERIALS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public BillOfMaterials getBOM(int bomId) throws DataException {
        return logic.getBOM(bomId);
    }

//    public void updateBOM(BillOfMaterials BOM, BillOfMaterials newBOM) throws DataException {
//        logic.updateBOM(BOM, newBOM);
//    }

//    public void deleteBOM(BillOfMaterials BOM) throws DataException {
//        logic.deleteBOM(BOM);
//    }

//    public BillOfMaterials generateBOM(int orderId, Carport carport, Roof roof) throws DataException
//    {
//        return logic.generateBOM(orderId, carport, roof);
//    }
//    
//    public float calculatePriceOfBOM(BillOfMaterials bom) throws DataException
//    {
//        return logic.calculatePriceOfBOM(bom);
//    }
    
    public Map<Component, Integer> convertBOMMap(BillOfMaterials bom) throws DataException {
        return logic.convertBOMMap(bom);
    }
    
//    public List<String> convertBillToStringList(Map<Component, Integer> bom)
//    {
//        return logic.convertBillToStringList(bom);
//    }
//    
//    public List<String> convertBillToStringList(BillOfMaterials bom) throws DataException
//    {
//        return logic.convertBillToStringList(bom);
//    }

    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////COMPONENTS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Component getComponent(int ComponentId) throws DataException {
        return logic.getComponent(ComponentId);
    }

    public void createComponent(Component Component) throws DataException {
        logic.createComponent(Component);
    }

    public void updateComponent(Component Component, Component newComponent) throws DataException {
        logic.updateComponent(Component, newComponent);
    }

    public void deleteComponent(Component Component) throws DataException {
        logic.deleteComponent(Component);
    }

    public List<Component> getAllComponents() throws DataException {
        return logic.getAllComponents();
    }

    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////////CARPORT////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Carport getCarport(int orderId) throws DataException {
        return logic.getCarport(orderId);
    }

//    public void deleteCarport(Carport carport) throws DataException {
//        logic.deleteCarport(carport);
//    }

//    public List<Carport> getAllCarports() throws DataException {
//        return logic.getAllCarports();
//    }

    ///////////////////////////////////////////////////////////////////////////
    //////////////////////////////////Case/////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public List<Case> getFreeCases(String type) throws DataException {
        return logic.getFreeCases(type);
    }

    public List<Message> getMessages(String rank) throws DataException {
        return logic.getMessages(rank);
    }

    public Message getMessage(String ID) throws DataException {
        return logic.getMessage(ID);
    }

    public Case getCase(String CaseNr) throws DataException {
        return logic.getCase(CaseNr);
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
    
    ///////////////////////////////////////////////////////////////////////////
    //////////////////////////////////ROOF/////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Roof getRoof(int roofTypeId) throws DataException {
        return logic.getRoof(roofTypeId);
    }

//    public void createRoof(Roof roof) throws DataException {
//        logic.createRoof(roof);
//    }

//    public void updateRoof(Roof roof, Roof newRoof) throws DataException {
//        logic.updateRoof(roof, newRoof);
//    }

//    public void deleteRoof(Roof roof) throws DataException {
//        logic.deleteRoof(roof);
//    }

    public List<Roof> getAllRoofs() throws DataException {
        return logic.getAllRoofs();
    }

}
