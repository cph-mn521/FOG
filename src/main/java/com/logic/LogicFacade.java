package com.logic;

import com.data.DAOController;
import com.enumerations.DBURL;
import com.entities.dto.Carport;
import com.entities.dto.Roof;
import com.entities.dto.BillOfMaterials;
import com.entities.dto.Component;
import com.entities.dto.Customer;
import com.entities.dto.Employee;
import com.entities.dto.Order;
import com.exceptions.DataException;
import java.sql.SQLException;

/**
 *
 * @author Martin, Martin Bøgh & Brandstrup
 */
public class LogicFacade {
    
    DAOController dao;

    public LogicFacade(DBURL dburl) throws SQLException, DataException
    {
        this.dao = new DAOController(dburl);
    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////CUSTOMER ACTIONS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Customer getCustomer(String email, String password) throws SQLException, DataException {
        return dao.getCustomer(email, password);
    }

    public void createCustomer(Customer customer) throws SQLException {
        dao.createCustomer(customer);
    }

    public void updateCustomer(Customer customer, Customer newCustomer) throws SQLException {
        dao.updateCustomer(customer, newCustomer);
    }

    public void deleteCustomer(Customer customer) throws SQLException {
        dao.deleteCustomer(customer);
    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////EMPLOYEE ACTIONS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Employee getEmployee(String email, String password) throws SQLException, DataException {
        return dao.getEmployee(email, password);
    }

    public void createEmployee(Employee employee) throws SQLException {
        dao.createEmployee(employee);
    }

    public void updateEmployee(Employee employee, Employee newEmployee) throws SQLException {
        dao.updateEmployee(employee, newEmployee);
    }

    public void deleteEmployee(Employee employee) throws SQLException {
        dao.deleteEmployee(employee);
    }

    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////ORDERMAPPING////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Order getOrder(int orderId) throws SQLException, DataException {
        return dao.getOrder(orderId);
    }

    public void createOrder(Order order) throws SQLException, DataException {
        dao.createOrder(order);
    }

    public void updateOrder(Order order, Order newOrder) throws SQLException, DataException {
        dao.updateOrder(order, newOrder);
    }

    public void deleteOrder(Order order) throws SQLException, DataException {
        dao.deleteOrder(order);
    }

    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////BILL OF MATERIALS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public BillOfMaterials getBOM(int bomId) throws SQLException, DataException {
        return dao.getBOM(bomId);
    }

    public void createBOM(BillOfMaterials BOM) throws SQLException, DataException {
        dao.createBOM(BOM);
    }

    public void updateBOM(BillOfMaterials BOM, BillOfMaterials newBOM) throws SQLException, DataException {
        dao.updateBOM(BOM, newBOM);
    }

    public void deleteBOM(BillOfMaterials BOM) throws SQLException, DataException {
        dao.deleteBOM(BOM);
    }

    /**
     * Communicates with the Data layer to gather information about an order in
     * order to calculate, create and persist a bill of materials to the DB.
     *
     * @param orderId the id of the order whose bill needs to be persisted
     * @param carport
     * @param roof
     * @author Brandstrup
     */
    public void persistBOM(int orderId, Carport carport, Roof roof)
    {
        BOMCalculator calc = new BOMCalculator();
        try
        {
//            Roof roof = dao.getRoof(roofId);
//            Carport carport = dao.getCarport(orderId);
            int roofId = carport.getRoofTypeId();
            BillOfMaterials bill = calc.calculateBOM(orderId, carport, roof);
            
            dao.createBOM(bill);
        }
        catch (DataException | SQLException ex)
        {
            //??? Hvordan og hvor skal exceptionsne håndteres?
        }
    }
    
    /**
     * Communicates with the Data layer to gather information about a bill of
     * materials in order to calculate the total cost of the entire carport.
     * 
     * @param bom the BillOfMaterials object to calculate
     * @return a float value of the total cost of an entire bill
     * @author Brandstrup
     */
    public float calculatePriceOfBOM(BillOfMaterials bom)
    {
       PriceCalculator calc = new PriceCalculator();
       float price = 0;
       
       try
       {
           price = calc.calculateOrderPrice(bom, dao);
       }
       catch (DataException | SQLException ex)
       {
           //??? Hvordan og hvor skal exceptionsne håndteres?
       }
       
       return price;
    }
    
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////COMPONENTS//////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Component getComponent(int ComponentId) throws SQLException, DataException {
        return dao.getComponent(ComponentId);
    }

    public void createComponent(Component Component) throws SQLException, DataException {
        dao.createComponent(Component);
    }

    public void updateComponent(Component Component, Component newComponent) throws SQLException, DataException {
        dao.updateComponent(Component, newComponent);
    }

    public void deleteComponent(Component Component) throws SQLException, DataException {
        dao.deleteComponent(Component);
    }

    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////////CARPORT////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Carport getCarport(int orderId) throws DataException
    {
        return dao.getCarport(orderId);
    }

    public void createCarport(Carport carport) throws DataException
    {
        dao.createCarport(carport);
    }

    public void updateCarport(Carport carport, Carport newCarport) throws DataException
    {
        dao.updateCarport(carport, newCarport);
    }

    public void deleteCarport(Carport carport) throws DataException
    {
        dao.deleteCarport(carport);
    }

    ///////////////////////////////////////////////////////////////////////////
    //////////////////////////////////ROOF/////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Roof getRoof(int roofTypeId) throws DataException
    {
        return dao.getRoof(roofTypeId);
    }

    public void createRoof(Roof roof) throws DataException
    {
        dao.createRoof(roof);
    }

    public void updateRoof(Roof roof, Roof newRoof) throws DataException
    {
        dao.updateRoof(roof, newRoof);
    }

    public void deleteRoof(Roof roof) throws DataException
    {
        dao.deleteRoof(roof);
    }
}
