package com.presentation.command;

import com.enumerations.DBURL;
import com.entities.dto.BillOfMaterials;
import com.entities.dto.Component;
import com.entities.dto.Customer;
import com.entities.dto.Employee;
import com.entities.dto.Order;
import com.entities.dto.Roof;
import com.exceptions.DataException;
import com.logic.LogicFacade;
import java.util.List;
import java.util.Map;

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

    public List<Customer> getAllCustomers() throws DataException
    {
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

    public List<Employee> getAllEmployees() throws DataException
    {
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

    public void createOrder(Customer customer, String customerAddress,
            int roofTypeId, int carportLength, int carportWidth, int carportHeight,
            int shedLength, int shedWidth, int shedHeight) throws DataException {
        logic.createOrder(customer, customerAddress,
                roofTypeId, carportLength, carportWidth, carportHeight,
                shedLength, shedWidth, shedHeight);
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

    /// LOGIN FUNCTIONS
    public String[] LoginEmploye(String usn, String psw) {
        return logic.LoginEmployee(usn, psw);
    }

    public List<Component> getAllComponents() throws DataException {
        return logic.getAllComponents();
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
