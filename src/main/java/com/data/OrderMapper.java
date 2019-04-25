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
     * @Author Niels
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
                Date orderDate = rs.getDate("order_receive_date"); //forket stavet i db
                Date sendDate = rs.getDate("order_send_date");
                String address = rs.getString("customer_address");
                String status = rs.getString("order_status");
                Order order = new Order(orderId, carportId, customerId, orderDate, sendDate, address, status);
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
     * @Author Niels
     *
     * @param order
     * @throws SQLException
     */
    static void createOrder(Order order) throws SQLException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO orders(carport_id, customer_id, order_recieve_date, order_send_date, customer_address, order_status)"
                    + "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, order.getCarport_id());
            ps.setInt(2, order.getCustomer_id());
            ps.setDate(3, order.getOrder_recieve_date());
            ps.setDate(4, order.getOrder_send_date());
            ps.setString(5, order.getCustomer_address());
            ps.setString(6, order.getOrder_status());
            ps.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }

    /**
     * Method for editing an existing order.
     *
     * Functions by replacing a order.
     *
     * @Author Niels
     *
     * @param order the order to be updated
     * @param newOrder the new data.
     * @throws DataException
     * @throws SQLException
     */
    static void updateOrder(Order order, Order newOrder) throws DataException, SQLException {
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE `orders` SET `carport_id` = ?, "
                    + "`customer_id` = ?, `order_recieve_date` = ?,"
                    + " `order_send_date` = ?, `customer_adress` = ?, `order_status` = ? ,"
                    + "WHERE `order_id` = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, newOrder.getCarport_id());
            ps.setInt(2, newOrder.getCustomer_id());
            ps.setDate(3, newOrder.getOrder_recieve_date());
            ps.setDate(4, newOrder.getOrder_send_date());
            ps.setString(5, newOrder.getCustomer_address());
            ps.setString(6, newOrder.getOrder_status());
            ps.setInt(7, order.getOrder_id());
            ps.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }

    /**
     * Method for removing an order from the database.
     *
     * @Author Niels
     * @param order
     * @throws DataException
     * @throws SQLException
     */
    static void deleteOrder(Order order) throws DataException, SQLException {
        try {
            Connection con = Connector.connection();
            String SQL = "DELETE FROM `orders` WHERE  `orders`.`order_id` = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, order.getOrder_id());
            ps.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            Order tets = getOrder(1);

            System.out.println(tets);

            createOrder(new Order(null, null, null, null, null, null));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
