/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logic;

import com.data.DAOController;
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
 * @author Martin, Martin Bøgh & Brandstrup
 */
public class LogicFacade {

//    private static LogicFacade instance = null;
//
//    //Brug den her linje i alle classes der skal kende til LogigFacade
//    //Derefter kan man få adgang til metoderne ved at skrive 'Logic.??'
//    private final LogicFacade Logic = LogicFacade.getInstance();
//
//    public LogicFacade() {
//    }
//
//    public synchronized static LogicFacade getInstance() {
//        if (instance == null) {
//            instance = new LogicFacade();
//        }
//        return instance;
//    }
//
//    static DAOController DataCtrl = new DAOController();
//
//    public static User login(String email, String password) throws LoginException {
//        try {
//            return DataCtrl.getCustomer(email, password);
//        } catch (SQLException | DataException ex) {
//            throw new LoginException("User not found");
//        }
//    }
//
//    // Commented out because of imminent meating
////    public static User createUser(String email, String password) throws LoginException {
////        try {
////            User user = new User(email, password);
////            DataCtrl.createUser(user);
////            return user;
////        } catch (SQLException ex) {
////            throw new LoginException(ex.getMessage());
////        }
////    }
    DAOController dao = new DAOController();

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
    /////////////////////////////��CASE ACTIONS��//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    public void getCase(int id) {
        
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

    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////BILL OF MATERIALS//////////////////////////////
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

    /**
     * Communicates with the Data layer to gather information about an order in
     * order to calculate, create and persist a bill of materials to the DB.
     *
     * @param orderId
     * @author Brandstrup
     */
    public void persistBOM(int orderId) {
        BOMCalculator calc = new BOMCalculator();
        try {
            int roofId = dao.getCarport(orderId).getRoofTypeId();
            Carport carport = dao.getCarport(orderId);
            Roof roof = dao.getRoof(roofId);
            BillOfMaterials bill = calc.calculateBOM(orderId, carport, roof);

            dao.createBOM(bill);
        } catch (DataException | SQLException ex) {
            //??? Hvordan og hvor skal exceptionsne håndteres?
        }
    }

    /**
     *
     *
     * @param bom
     * @return
     * @author Brandstrup
     */
    public float calculatePriceOfBOM(BillOfMaterials bom) {
        PriceCalculator calc = new PriceCalculator();
        float price = 0;

        try {
            price = calc.calculateOrderPrice(bom, dao);
        } catch (DataException | SQLException ex) {
            //??? Hvordan og hvor skal exceptionsne håndteres?
        }

        return price;
    }
}
