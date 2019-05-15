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
import com.entities.dto.User;
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
    public User getCustomerFromID(String ID) throws DataException {
        return logic.getCustomerFromId(ID);
    }

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
    public Employee getEmployee(String email, String password) throws DataException {
        return logic.getEmployee(email, password);
    }

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

    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////ORDERMAPPING////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Order getOrder(int orderId) throws DataException {
        return logic.getOrder(orderId);
    }

    public List<Order> getAllOrders() throws DataException {
        return logic.getAllOrders();
    }

    public Order createOrder(Customer customer, String customerAddress,
            int roofTypeId, int carportLength, int carportWidth, int carportHeight,
            int shedLength, int shedWidth, int shedHeight) throws DataException, PDFException {
        return logic.createOrder(customer, customerAddress,
                roofTypeId, carportLength, carportWidth, carportHeight,
                shedLength, shedWidth, shedHeight);
    }
    
    public Order createOrder(int customerId, String customerAddress, Carport carport) throws DataException, PDFException
    {
        return logic.createOrder(customerId, customerAddress, carport);
    }
    
    public Order createOrder(Customer customer, String customerAddress, Carport carport) throws DataException, PDFException
    {
        return logic.createOrder(customer, customerAddress, carport);
    }

    public void createOrder(Order order) throws DataException {
        logic.createOrder(order);
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

    public void createBOM(BillOfMaterials BOM) throws DataException {
        logic.createBOM(BOM);
    }

    public void updateBOM(BillOfMaterials BOM, BillOfMaterials newBOM) throws DataException {
        logic.updateBOM(BOM, newBOM);
    }

    public void deleteBOM(BillOfMaterials BOM) throws DataException {
        logic.deleteBOM(BOM);
    }

    public Map<Component, Integer> convertBOMMap(BillOfMaterials bom) throws DataException {
        return logic.convertBOMMap(bom);
    }

    public float calculatePriceOfBOM(BillOfMaterials bom) throws DataException {
        return logic.calculatePriceOfBOM(bom);
    }

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

    public void createCarport(Carport carport) throws DataException {
        logic.createCarport(carport);
    }

    public void updateCarport(Carport carport, Carport newCarport) throws DataException {
        logic.updateCarport(carport, newCarport);
    }

    public void deleteCarport(Carport carport) throws DataException {
        logic.deleteCarport(carport);
    }

    public List<Carport> getAllCarports() throws DataException {
        return logic.getAllCarports();
    }

    /// LOGIN FUNCTIONS
    public Employee LoginEmploye(String usn, String psw, HttpServletRequest request) throws DataException {
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

    public void closeCase(int caseID) throws DataException {
        logic.closeCase(caseID);
    }

    ///////////////////////////////////////////////////////////////////////////
    //////////////////////////////////ROOF/////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Roof getRoof(int roofTypeId) throws DataException {
        return logic.getRoof(roofTypeId);
    }

    public void createRoof(Roof roof) throws DataException {
        logic.createRoof(roof);
    }

    public void updateRoof(Roof roof, Roof newRoof) throws DataException {
        logic.updateRoof(roof, newRoof);
    }

    public void deleteRoof(Roof roof) throws DataException {
        logic.deleteRoof(roof);
    }

    public List<Roof> getAllRoofs() throws DataException {
        return logic.getAllRoofs();
    }

}
