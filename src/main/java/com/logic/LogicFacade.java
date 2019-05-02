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
public class LogicFacade
{

    DAOController dao;

    public LogicFacade(DBURL dburl) throws DataException
    {
        this.dao = new DAOController(dburl);
    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////CUSTOMER ACTIONS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Customer getCustomer(String email, String password) throws DataException
    {
        return dao.getCustomer(email, password);
    }

    public void createCustomer(Customer customer) throws DataException
    {
        dao.createCustomer(customer);
    }

    public void updateCustomer(Customer customer, Customer newCustomer) throws DataException
    {
        dao.updateCustomer(customer, newCustomer);
    }

    public void deleteCustomer(Customer customer) throws DataException
    {
        dao.deleteCustomer(customer);
    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////EMPLOYEE ACTIONS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Employee getEmployee(String email, String password) throws DataException
    {
        return dao.getEmployee(email, password);
    }

    public void createEmployee(Employee employee) throws DataException
    {
        dao.createEmployee(employee);
    }

    public void updateEmployee(Employee employee, Employee newEmployee) throws DataException
    {
        dao.updateEmployee(employee, newEmployee);
    }

    public void deleteEmployee(Employee employee) throws DataException
    {
        dao.deleteEmployee(employee);
    }

    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////ORDERMAPPING////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Order getOrder(int orderId) throws DataException
    {
        return dao.getOrder(orderId);
    }

    public void createOrder(Order order) throws DataException
    {
        dao.createOrder(order);
    }

    public void updateOrder(Order order, Order newOrder) throws DataException
    {
        dao.updateOrder(order, newOrder);
    }

    public void deleteOrder(Order order) throws DataException
    {
        dao.deleteOrder(order);
    }

    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////BILL OF MATERIALS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public BillOfMaterials getBOM(int bomId) throws DataException
    {
        return dao.getBOM(bomId);
    }

    public void createBOM(BillOfMaterials BOM) throws DataException
    {
        dao.createBOM(BOM);
    }

    public void updateBOM(BillOfMaterials BOM, BillOfMaterials newBOM) throws DataException
    {
        dao.updateBOM(BOM, newBOM);
    }

    public void deleteBOM(BillOfMaterials BOM) throws DataException
    {
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
    public void persistBOM(int orderId, Carport carport, Roof roof) throws DataException
    {
        BOMCalculator calc = new BOMCalculator();

//            Roof roof = dao.getRoof(roofId);
//            Carport carport = dao.getCarport(orderId);
        int roofId = carport.getRoofTypeId();
        BillOfMaterials bill = calc.calculateBOM(orderId, carport, roof);

        dao.createBOM(bill);

    }

    /**
     * Communicates with the Data layer to gather information about a bill of
     * materials in order to calculate the total cost of the entire carport.
     *
     * @param bom the BillOfMaterials object to calculate
     * @return a float value of the total cost of an entire bill
     * @author Brandstrup
     * @throws com.exceptions.DataException
     */
    public float calculatePriceOfBOM(BillOfMaterials bom) throws DataException
    {
        PriceCalculator calc = new PriceCalculator();
        float price = 0;

        try
        {
            price = calc.calculateOrderPrice(bom, dao);
        } catch (SQLException ex)
        {
            //??? Hvordan og hvor skal exceptionsne håndteres?
            throw new DataException("Fejl i calculatePriceOfBOM: " + ex.getMessage());
        }

        return price;
    }

    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////COMPONENTS//////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Component getComponent(int ComponentId) throws DataException
    {
        return dao.getComponent(ComponentId);
    }

    public void createComponent(Component Component) throws DataException
    {
        dao.createComponent(Component);
    }

    public void updateComponent(Component Component, Component newComponent) throws DataException
    {
        dao.updateComponent(Component, newComponent);
    }

    public void deleteComponent(Component Component) throws DataException
    {
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
