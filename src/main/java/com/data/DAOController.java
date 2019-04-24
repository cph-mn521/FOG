package com.data;

import com.entities.dto.User;
import com.entities.dto.Order;
import com.entities.dto.BillOfMaterials;
import com.exceptions.DataException;
import java.sql.SQLException;

/**
 *
 * @author Martin
 */
public class DAOController {
    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////USER ACTIONS//////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    public User getUser(String email, String password) throws SQLException, DataException {
        User user = UserMapper.getUser(email, password);
        return user;
    }

    public void createUser(User user) throws SQLException {
        UserMapper.createUser(user);
    }

    public void updateUser(User user, User newUser) throws SQLException {
        UserMapper.updateUser(user, newUser);
    }

    public void deleteUser(User user) throws SQLException {
        UserMapper.deleteUser(user);
    }

    public Order getOrder(int orderId) throws SQLException {
        return OrderMapper.getOrder(orderId);
    }

    public void createOrder(Order order) throws SQLException {
        OrderMapper.createOrder(order);
    }

    public void updateOrder(Order order, Order newOrder) throws SQLException {
        OrderMapper.updateOrder(order, newOrder);
    }

    public void deleteOrder(Order order) throws SQLException {
        OrderMapper.deleteOrder(order);
    }

    public BillOfMaterials getBOM(int bomId) {
        BOMMapper.getBOM(bomId);
    }

}
