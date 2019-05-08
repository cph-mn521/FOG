package com.data;

import com.entities.dto.BillOfMaterials;
import com.entities.dto.Carport;
import com.entities.dto.Component;
import com.entities.dto.Customer;
import com.entities.dto.Employee;
import com.entities.dto.Order;
import com.entities.dto.Roof;
import com.exceptions.DataException;
import java.sql.SQLException;

/**
 *
 * @author Martin & Niels & Martin Bøgh & Brandstrup
 */
public class DAOController {

    CaseMapper CaM = new CaseMapper();
    ComponentMapper CM = new ComponentMapper();
    UserMapper UM = new UserMapper();
    OrderMapper OM = new OrderMapper();
    BOMMapper BM = new BOMMapper();
    CarportMapper CpM = new CarportMapper();
    RoofMapper RM = new RoofMapper();

    // Orders, Bill of Materials, Components
    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////CUSTOMER ACTIONS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Customer getCustomer(String email, String password) throws SQLException, DataException {
        return UM.getCustomer(email, password);
    }

    public void createCustomer(Customer customer) throws SQLException {
        UM.createCustomer(customer);
    }

    public void updateCustomer(Customer customer, Customer newCustomer) throws SQLException {
        UM.updateCustomer(customer, newCustomer);
    }

    public void deleteCustomer(Customer customer) throws SQLException {
        UM.deleteCustomer(customer);
    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////��CASE ACTIONS��//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public void getCase(int id) {
        
    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////EMPLOYEE ACTIONS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Employee getEmployee(String email, String password) throws SQLException, DataException {
        return UM.getEmployee(email, password);
    }

    public void createEmployee(Employee employee) throws SQLException {
        UM.createEmployee(employee);
    }

    public void updateEmployee(Employee employee, Employee newEmployee) throws SQLException {
        UM.updateEmployee(employee, newEmployee);
    }

    public void deleteEmployee(Employee employee) throws SQLException {
        UM.deleteEmployee(employee);
    }

    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////ORDERMAPPING////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Order getOrder(int orderId) throws SQLException, DataException {
        return OM.getOrder(orderId);
    }

    public void createOrder(Order order) throws SQLException, DataException {
        OM.createOrder(order);
    }

    public void updateOrder(Order order, Order newOrder) throws SQLException, DataException {
        OM.updateOrder(order, newOrder);
    }

    public void deleteOrder(Order order) throws SQLException, DataException {
        OM.deleteOrder(order);
    }

    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////BILL OF MATERIALS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public BillOfMaterials getBOM(int bomId) throws SQLException, DataException {
        return BM.getBOM(bomId);
    }

    public void createBOM(BillOfMaterials BOM) throws SQLException, DataException {
        BM.createBOM(BOM);
    }

    public void updateBOM(BillOfMaterials BOM, BillOfMaterials newBOM) throws SQLException, DataException {
        BM.updateBOM(BOM, newBOM);
    }

    public void deleteBOM(BillOfMaterials BOM) throws SQLException, DataException {
        BM.deleteBOM(BOM);
    }

    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////////COMPONENTS/////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Component getComponent(int ComponentId) throws SQLException, DataException {
        return CM.getComponent(ComponentId);
    }

    public void createComponent(Component Component) throws SQLException {
        CM.createComponent(Component);
    }

    public void updateComponent(Component Component, Component newComponent) throws SQLException {
        CM.updateComponent(Component, newComponent);
    }

    public void deleteComponent(Component Component) throws SQLException {
        CM.deleteComponent(Component);
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
}
