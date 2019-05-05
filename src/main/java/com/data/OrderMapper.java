package com.data;

import com.enumerations.DBURL;
import com.entities.dto.Order;
import com.exceptions.DataException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Niels, Martin Bøgh
 */
public class OrderMapper
{

    private Connection con;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private DBURL dbURL;

    public OrderMapper(DBURL dbURL) throws DataException
    {
       this.dbURL = dbURL;
    }
    
    
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
    Order getOrder(int orderId) throws DataException {
        try {
            con = Connector.connection(dbURL);
            String SQL = "SELECT * FROM orders "
                    + "WHERE order_id = ?";
            ps = con.prepareStatement(SQL);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int customerId = rs.getInt("customer_id");
                Date orderDate = rs.getDate("order_receive_date");
                Date sendDate = rs.getDate("order_send_date");
                String address = rs.getString("customer_address");
                String status = rs.getString("order_status");
                Order order = new Order(orderId, customerId, orderDate, sendDate, address, status);
                return order;
            } else
            {
                throw new DataException("Order not found");
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new DataException(e.getMessage());
        } finally
        {
            Connector.CloseConnection(rs, ps, con);
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
    void createOrder(Order order) throws DataException {
        try {
            con = Connector.connection(dbURL);
            String SQL = "SET FOREIGN_KEY_CHECKS=0; INSERT INTO `orders`(`customer_id`, "
                    + "`customer_address`, `order_receive_date`, `order_status`, "
                    + "`order_send_date`) VALUES (?,?,?,?,?); SET FOREIGN_KEY_CHECKS=1;";
//            String SQL = "INSERT INTO `orders`(`customer_id`, `order_receive_date`, "
//                    + "`order_send_date`, `customer_address`, `order_status`) "
//                    + "VALUES(?,?,?,?,?);";
            ps = con.prepareStatement(SQL);
            ps.setInt(1, order.getCustomer_id());
            ps.setString(2, order.getCustomer_address());
            ps.setDate(3, order.getOrder_receive_date());
            ps.setString(4, order.getOrder_status());
            ps.setDate(5, order.getOrder_send_date());
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataException(e.getMessage());
        } finally
        {
            Connector.CloseConnection(ps, con);
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
    void updateOrder(Order order, Order newOrder) throws DataException {
        try {
            con = Connector.connection(dbURL);
            String SQL = "UPDATE `orders` SET  "
                    + "`customer_id` = ?, `order_receive_date` = ?,"
                    + " `order_send_date` = ?, `customer_adress` = ?, `order_status` = ? ,"
                    + "WHERE `order_id` = ?";
            ps = con.prepareStatement(SQL);
            ps.setInt(1, newOrder.getCustomer_id());
            ps.setDate(2, newOrder.getOrder_receive_date());
            ps.setDate(3, newOrder.getOrder_send_date());
            ps.setString(4, newOrder.getCustomer_address());
            ps.setString(5, newOrder.getOrder_status());
            ps.setInt(6, order.getOrder_id());
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataException(e.getMessage());
        } finally
        {
            Connector.CloseConnection(ps, con);
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
    void deleteOrder(Order order) throws DataException {
        try {
            Connection con = Connector.connection(dbURL);
            String SQL = "DELETE FROM `orders` WHERE  `orders`.`order_id` = ?";
            ps = con.prepareStatement(SQL);
            ps.setInt(1, order.getOrder_id());
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataException(e.getMessage());
        } finally
        {
            Connector.CloseConnection(ps, con);
        }
    }

    /**
     * 
     * @author Brandstrup
     * @return a List<Order> containing all the orders in the database
     * @throws DataException 
     */
    public List<Order> getAllOrders() throws DataException
    {
        try
        {
            con = Connector.connection(dbURL);
            String SQL
                    = "SELECT *"
                    + " FROM `fogcarport`.`orders`;";
            
            List<Order> list = new ArrayList();
            ps = con.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next())
            {
                int order_id = rs.getInt("order_id");
                int customer_id = rs.getInt("customer_id");
                Date order_receive_date = rs.getDate("order_receive_date");
                Date order_send_date = rs.getDate("order_send_date");
                String customer_address = rs.getString("customer_address");
                String order_status = rs.getString("order_status");
                
                list.add(new Order(order_id, customer_id, order_receive_date, order_send_date, customer_address, order_status));
            }
            
            return list;
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            throw new DataException(ex.getMessage());
        } finally
        {
            Connector.CloseConnection(rs, ps, con);
        }
    }
}
