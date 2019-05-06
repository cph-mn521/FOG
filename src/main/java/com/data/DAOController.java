package com.data;

import com.enumerations.DBURL;
import com.entities.dto.Order;
import com.entities.dto.BillOfMaterials;
import com.entities.dto.Carport;
import com.entities.dto.Component;
import com.entities.dto.Customer;
import com.entities.dto.Employee;
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

    public DAOController(DBURL dburl) throws DataException
    {
        this.RM = new RoofMapper(dburl);
        this.CM = new ComponentMapper(dburl);
        this.CpM = new CarportMapper(dburl);
        this.BM = new BOMMapper(dburl);
        this.OM = new OrderMapper(dburl);
        this.UM = new UserMapper(dburl);
    }


    // Orders, Bill of Materials, Components
    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////CUSTOMER ACTIONS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Customer getCustomer(String email, String password) throws DataException {
        return UM.getCustomer(email, password);
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
    
    public List<Customer> getAllCustomers() throws DataException
    {
        return UM.getAllCustomers();
    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////EMPLOYEE ACTIONS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Employee getEmployee(String email, String password) throws DataException {
        return UM.getEmployee(email, password);
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
    
    public List<Employee> getAllEmployees() throws DataException
    {
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
    
    public List<Order> getAllOrders() throws DataException
    {
        return OM.getAllOrders();
    }
    
    public Order getLastOrder() throws DataException
    {
        List<Order> list = OM.getAllOrders();
        return list.get(list.size() - 1);
    }

    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////BILL OF MATERIALS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public BillOfMaterials getBOM(int bomId) throws DataException {
        return BM.getBOM(bomId);
    }

    public void createBOM(BillOfMaterials BOM) throws  DataException {
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

    public void createComponent(Component Component) throws DataException  {
        CM.createComponent(Component);
    }

    public void updateComponent(Component Component, Component newComponent) throws DataException {
        CM.updateComponent(Component, newComponent);
    }

    public void deleteComponent(Component Component) throws DataException{
        CM.deleteComponent(Component);
    }
    
    public List<Component> getAllComponents() throws DataException
    {
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
    
    public List<Carport> getAllCarports() throws DataException
    {
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
    
    public List<Roof> getAllRoofs() throws DataException
    {
        return RM.getAllRoofs();
    }
}
