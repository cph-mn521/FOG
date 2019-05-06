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
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

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

    public synchronized void createOrder(Customer customer, String customerAddress,
            int roofTypeId, int carportLength, int carportWidth, int carportHeight,
            int shedLength, int shedWidth, int shedHeight) throws DataException
    {
        Date currentDate = Date.valueOf(LocalDate.now());   // skal testes

        dao.createOrder(new Order(customer.getCustomer_id(), currentDate, null, customerAddress, "pending"));
        int orderId = dao.getLastOrder().getOrder_id();

        Carport carport = new Carport(orderId, roofTypeId, carportLength, carportWidth, carportHeight, shedLength, shedWidth, shedHeight);
        createCarport(carport);

        Roof roof = getRoof(roofTypeId);    //Den hjemmeside der er oppe nu har kun prefab tage. Skal man selv kunne sammensætte?

        generateBOM(orderId, carport, roof);

    }
    
    public void markOrderAsSent(int orderId) throws DataException
    {
        Order order = dao.getOrder(orderId);
        Order newOrder = order; //Hvad skal jeg bruge to objekter til? De har jo samme id
        
        Date currentDate = Date.valueOf(LocalDate.now());   // skal testes
        order.setOrder_send_date(currentDate);
        
        dao.updateOrder(order, newOrder);
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
     * @return the BillOfMaterial object that is also being generated in the DB
     * @throws DataException
     * @author Brandstrup
     */
    public BillOfMaterials generateBOM(int orderId, Carport carport, Roof roof) throws DataException
    {
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
    public float calculatePriceOfBOM(BillOfMaterials bom) throws DataException
    {
        PriceCalculator calc = new PriceCalculator();
        float price = 0;

        try
        {
            price = calc.calculateOrderPrice(bom, dao);
        }
        catch (SQLException ex)
        {
            throw new DataException("Fejl i calculatePriceOfBOM: " + ex.getMessage());
        }

        return price;
    }
    
    /**
     * Receives a bill of material object consisting of a HashMap containing the
     * IDs (key) of the Components it contains as well as the amount (value),
     * then converts these integers into a new HashMap containing actual DTOs
     * of these Components (as well as the amount).
     * 
     * @param bom the bill of material object to convert into a usable Map
     * @return a Map<Component, Integer> that is easier to use in presentation
     * @throws DataException 
     * @author Brandstrup
     */
    public Map<Component, Integer> convertBOMMap(BillOfMaterials bom) throws DataException
    {
        MappingLogic calc = new MappingLogic();
        try
        {
            return calc.convertBOMMap(bom, dao.getAllComponents());
        }
        catch (DataException ex)
        {
            throw new DataException("Fejl i convertBOMMap: " + ex.getMessage());
        }
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
