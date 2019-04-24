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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        /*
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM orders "
                    + "WHERE order_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                //update when we know what an Order is
                return null;
            } else {
                throw new DataException("Order not found");
            }

        } catch (ClassNotFoundException | SQLException e) {

        }*/
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        /*  try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO orders ( )"
                    + "VALUES(?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(SQL);

            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }*/
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
