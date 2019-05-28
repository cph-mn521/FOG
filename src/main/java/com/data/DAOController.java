package com.data;

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
import java.util.List;

/**
 *
 * @author Martin & Niels & Martin Bøgh & Brandstrup
 */
public class DAOController {

    ComponentMapper CM;
    UserMapper UM;
    OrderMapper OM;
    BOMMapper BM;
    CarportMapper CpM;
    RoofMapper RM;
    CaseMapper CaM;
    messageMapper MM;

    public DAOController(DBURL dburl) throws DataException {

        this.RM = new RoofMapper(dburl);
        this.CM = new ComponentMapper(dburl);
        this.CpM = new CarportMapper(dburl);
        this.BM = new BOMMapper(dburl);
        this.OM = new OrderMapper(dburl);
        this.UM = new UserMapper(dburl);
        this.CaM = new CaseMapper(dburl);
        this.MM = new messageMapper(dburl);
    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////CUSTOMER ACTIONS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Customer getCustomer(String email, String password) throws DataException {
        return UM.getCustomer(email, password);
    }

    public Customer getCustomer(int id) throws DataException {
        return UM.getCustomer(id);
    }

    public void createCustomer(Customer customer) throws DataException {
        UM.createCustomer(customer);
    }

    public void updateCustomer(Customer customer, Customer newCustomer) throws DataException {
        UM.updateCustomer(customer, newCustomer);
    }

    public void deleteCustomer(Customer customer) throws DataException {
        UM.deleteCustomer(customer);
    }

    public List<Customer> getAllCustomers() throws DataException {
        return UM.getAllCustomers();
    }

    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////CASE ACTIONS////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Case getCase(int id) throws DataException {
        return CaM.getCase(id);
    }

    public List<Case> getUserCases(int id) throws DataException {
        return CaM.getUserCases(id);
    }

    public List<Case> getCustomerCases(int ID) throws DataException {
        return CaM.getCustomerCases(ID);
    }

    public List<Case> getFreeCase(String type) throws DataException {
        return CaM.getFreeCases(type);
    }

    public void updCaseEmpl(int emplId, int caseId) throws DataException {
        CaM.updCaseEmpl(emplId, caseId);
    }

    public List<Case> getUserClosedCases(int userID) throws DataException {
        return CaM.getUserClosedCases(userID);
    }

    public void updCaseStat(int caseID, String stat) throws DataException {
        CaM.updCaseStatus(caseID, stat);
    }

    public void updCaseMsg(Case C) throws DataException {
        CaM.updCaseMsg(C);
    }

    public void updCasefree(int CaseID) throws DataException {
        CaM.updCasefree(CaseID);
    }

    public void createCase(Case C) throws DataException {
        CaM.createCase(C);
    }

    public void createCaseOrder(Case C) throws DataException {
        CaM.createCaseOrder(C);
    }

    public List<Message> getMessages(String rank) throws DataException {
        return MM.getMessages(rank);
    }
    
    public Message getMessage(int ID) throws DataException{
        return MM.getMessage(ID);
    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////EMPLOYEE ACTIONS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Employee getEmployee(String email, String password) throws DataException {
        return UM.getEmployee(email, password);
    }

    public Employee getEmployee(int id) throws DataException {
        return UM.getEmployee(id);
    }

    public void createEmployee(Employee employee) throws DataException {
        UM.createEmployee(employee);
    }

    public void updateEmployee(Employee employee, Employee newEmployee) throws DataException {
        UM.updateEmployee(employee, newEmployee);
    }

    public void deleteEmployee(Employee employee) throws DataException {
        UM.deleteEmployee(employee);
    }

    public List<Employee> getAllEmployees() throws DataException {
        return UM.getAllEmployees();
    }

    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////ORDERMAPPING////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Order getOrder(int orderId) throws DataException {
        return OM.getOrder(orderId);
    }

    public void createOrder(Order order) throws DataException {
        OM.createOrder(order);
    }

    public void updateOrder(Order order, Order newOrder) throws DataException {
        OM.updateOrder(order, newOrder);
    }

    public void deleteOrder(Order order) throws DataException {
        OM.deleteOrder(order);
    }

    public List<Order> getAllOrders() throws DataException {
        return OM.getAllOrders();
    }

    public Order getLastOrder() throws DataException {
        return OM.getLastOrder();
    }

    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////BILL OF MATERIALS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public BillOfMaterials getBOM(int orderId) throws DataException {
        return BM.getBOM(orderId);
    }

    public void createBOM(BillOfMaterials BOM) throws DataException {
        BM.createBOM(BOM);
    }

    public void updateBOM(BillOfMaterials BOM, BillOfMaterials newBOM) throws DataException {
        BM.updateBOM(BOM, newBOM);
    }

    public void deleteBOM(BillOfMaterials BOM) throws DataException {
        BM.deleteBOM(BOM);
    }

    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////////COMPONENTS/////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Component getComponent(int ComponentId) throws DataException {
        return CM.getComponent(ComponentId);
    }

    public void createComponent(Component Component) throws DataException {
        CM.createComponent(Component);
    }

    public void updateComponent(Component Component, Component newComponent) throws DataException {
        CM.updateComponent(Component, newComponent);
    }

    public void deleteComponent(Component Component) throws DataException {
        CM.deleteComponent(Component);
    }

    public List<Component> getAllComponents() throws DataException {
        return CM.getAllComponents();
    }

    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////////CARPORT////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Carport getCarport(int orderId) throws DataException {
        return CpM.getCarport(orderId);
    }

    public void createCarport(Carport carport) throws DataException {
        CpM.createCarport(carport);
    }

    public void updateCarport(Carport carport, Carport newCarport) throws DataException {
        CpM.updateCarport(carport, newCarport);
    }

    public void deleteCarport(Carport carport) throws DataException {
        CpM.deleteCarport(carport);
    }

    public List<Carport> getAllCarports() throws DataException {
        return CpM.getAllCarports();
    }

    ///////////////////////////////////////////////////////////////////////////
    //////////////////////////////////ROOF/////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Roof getRoof(int roofTypeId) throws DataException {
        return RM.getRoof(roofTypeId);
    }

    public void createRoof(Roof roof) throws DataException {
        RM.createRoof(roof);
    }

    public void updateRoof(Roof roof, Roof newRoof) throws DataException {
        RM.updateRoof(roof, newRoof);
    }

    public void deleteRoof(Roof roof) throws DataException {
        RM.deleteRoof(roof);
    }

    public List<Roof> getAllRoofs() throws DataException {
        return RM.getAllRoofs();
    }

    public void createMsg(Message msg) throws DataException {
        MM.createMsg(msg);
    }
    
}
