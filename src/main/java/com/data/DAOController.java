package com.data;

import com.entities.dto.User;
import com.entities.dto.Order;
import com.entities.dto.BillOfMaterials;
import com.entities.dto.Customer;
import com.entities.dto.Employee;
import com.exceptions.DataException;
import java.sql.SQLException;

/**
 *
 * @author Martin & Niels & Martin Bøgh
 */
public class DAOController {

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////CUSTOMER ACTIONS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Customer getCustomer(String email, String password) throws SQLException, DataException {
        return UserMapper.getCustomer(email, password);
    }

    public void createCustomer(Customer customer) throws SQLException {
        UserMapper.createCustomer(customer);
    }

    public void updateCustomer(Customer customer, Customer newCustomer) throws SQLException {
        UserMapper.updateCustomer(customer, newCustomer);
    }

    public void deleteCustomer(Customer customer) {
        UserMapper.deleteCustomer(customer);
    }

    public void deleteUser(User user) throws SQLException {
        UserMapper.deleteUser(user);
    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////EMPLOYEE ACTIONS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Employee getEmployee(String email, String password) throws SQLException, DataException {
        return UserMapper.getEmployee(email, password);
    }

    public void createEmployee(Employee employee) throws SQLException {
        UserMapper.createEmployee(employee);
    }

    public void updateEmployee(Employee employee, Employee newEmployee) throws SQLException {
        UserMapper.updateEmployee(employee, newEmployee);
    }

    public void deleteEmployee(Employee employee) {
        UserMapper.deleteEmployee(employee);
    }

    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////ORDERMAPPING////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Order getOrder(int orderId) throws SQLException, DataException {
        return OrderMapper.getOrder(orderId);
    }

    public void createOrder(Order order) throws SQLException, DataException {
        OrderMapper.createOrder(order);
    }

    public void updateOrder(Order order, Order newOrder) throws SQLException, DataException {
        OrderMapper.updateOrder(order, newOrder);
    }

    public void deleteOrder(Order order) throws SQLException, DataException {
        OrderMapper.deleteOrder(order);
    }

    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////BILL OF MATERIALS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public BillOfMaterials getBOM(int bomId) throws SQLException, DataException {
        return BOMMapper.getBOM(bomId);
    }

    public void createBOM(BillOfMaterials BOM) throws SQLException, DataException {
        BOMMapper.createBOM(BOM);
    }

    public void updateBOM(BillOfMaterials BOM, BillOfMaterials newBOM) throws SQLException, DataException {
        BOMMapper.updateBOM(BOM, newBOM);
    }

    public void deleteBOM(BillOfMaterials BOM) throws SQLException, DataException {
        BOMMapper.deleteBOM(BOM);
    }

    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////BILL OF MATERIALS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Component getComponent(int ComponentId) throws SQLException, DataException {
        return ComponentMapper.getComponent(ComponentId);
    }

    public void createComponent(Component Component) throws SQLException, DataException {
        ComponentMapper.createComponent(Component);
    }

    public void updateComponent(Component Component, Component newComponent) throws SQLException, DataException {
        ComponentMapper.updateComponent(Component, newComponent);
    }

    public void deleteComponent(Component Component) throws SQLException, DataException {
        ComponentMapper.deleteComponent(Component);
    }
}
