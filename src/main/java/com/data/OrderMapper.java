/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.data;

import com.entities.dto.Order;
import com.exceptions.DataException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

/**
 *
 * @author Niels
 */
public class OrderMapper {

    /**
     * Method for fetching an order from the database.
     *
     * @param orderId The order number of the order.
     * @return Returns the requested order.
     * @throws DataException Thrown if no order with that Id is found.
     * @throws SQLException Thrown if method encounters a database error.
     */
    static Order getOrder(int orderId) throws DataException, SQLException {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM orders "
                    + "WHERE order_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int carportId = rs.getInt("carport_id");
                int customerId = rs.getInt("customer_id");
                int billId = rs.getInt("bill_id");
                Date orderDate = rs.getDate("order_recieve_date");
                Date sendDate = rs.getDate("order_send_date");
                String address = rs.getString("address");
                String status = rs.getString("status");
                Order order = new Order(orderId, carportId, customerId, billId, orderDate, sendDate, address, status);
                return order;
            } else {
                throw new DataException("Order not found");
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    /**
     * Method for adding a new Order entry to the database.
     *
     * Takes a user entity and converts it to a SQL statements
     *
     * @param order
     * @throws SQLException
     */
    static void createOrder(Order order) throws SQLException {
        try {
            // new Order(orderId, carportId, customerId, billId, order_recieve_date, order_send_date, customer_address, order_status);
            Connection con = Connector.connection();
            String SQL = "INSERT INTO orders(carport_id, customer_id, bill_id, order_recieve_date, order_send_date, customer_address, order_status)"
                    + "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, order.getCarport_id());
            ps.setInt(2, order.getCustomer_id());
            ps.setInt(3, order.getBill_id());
            ps.setDate(4, order.getOrder_recieve_date());
            ps.setDate(5, order.getOrder_send_date());
            ps.setString(6, order.getCustomer_address());
            ps.setString(7, order.getOrder_status());
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }

    /**
     *
     *
     * @param order
     * @param newOrder
     * @throws DataException
     * @throws SQLException
     */
    static void updateOrder(Order order, Order newOrder) throws DataException, SQLException {
        throw new UnsupportedOperationException("Not Supported yet.");
        /* try {
            Connection con = Connector.connection();
            String SQL = "UPDATE `orders` SET ..."
                    + "WHERE ...";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }*/
    }

    static void deleteOrder(Order order) throws DataException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        /*
        try {
            Connection con = Connector.connection();
            String SQL = "DELETE FROM `orders` WHERE  `orders`....";

        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }*/
    }

}
