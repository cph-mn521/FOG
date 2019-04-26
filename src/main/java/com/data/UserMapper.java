package com.data;

import com.entities.dto.Customer;
import com.entities.dto.Employee;
import com.entities.dto.User;
import com.exceptions.DataException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Martin, Martin Bøgh, Niels
 */
public class UserMapper {

    /**
     * Method for fetching a user from the database, Requires both email and
     * password to match Sends an error to be interpreted in higher layer.
     *
     * * uses prepared statements to avoid SQL injects.
     *
     * @Author Martin, Martin Bøgh
     * @param email
     * @param password
     * @return User object containing the corresponding to the query.
     * @throws SQLException
     */
    Customer getCustomer(String email, String password) throws SQLException, DataException {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT customer_id, name, phone_number FROM customers "
                    + "WHERE email_address=? AND password=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int customer_id = rs.getInt("customer_id");
                String name = rs.getString("name");
                String phone_number = rs.getString("phone_number");
                Customer cust = new Customer(customer_id, name, email, password, phone_number);
                return cust;
            } else {
                throw new DataException("User (customer) not found");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    /**
     * Method for adding a new user entry to the database.
     *
     * Takes a user entity and then converts it to a SQL statement.
     *
     * * uses prepared statements to avoid SQL injects.
     *
     * @Author Niels
     * @param user
     * @throws SQLException
     */
    void createCustomer(Customer customer) throws SQLException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO `Customers` (name, email_address, password, phone_number) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getPassword());
            ps.setString(4, customer.getPhone_number());
            ps.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }

    }

    /**
     * Method for updating a user in the database
     *
     * Takes user and a newUser entity and updates the old user to the newUser.
     *
     * @Author Niels
     * @param user
     * @param newUser
     * @throws SQLException
     */
    void updateUser(User user, User newUser) throws SQLException {
        try {
            Connection con = Connector.connection();
            String SQL, table;
            String ranked = "";
            int n = 0;
            if (newUser instanceof Customer) {
                table = "`Customers`";
            } else {
                table = "`Employees`";
                ranked = ", `rank` = ? ";
                n++;
            }
            SQL = "UPDATE " + table + " SET `email` = ?, `password`= ?,"
                    + " `name` = ? " + ranked
                    + "WHERE User.email = ? AND User.password = ?";

            //name, email, password, phone_number
            // name, email, password, phone_number, rank
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setString(1, newUser.getEmail());
            ps.setString(2, newUser.getPassword());
            ps.setString(3, newUser.getName());
            if (n > 0) {
                ps.setString(4, ((Employee) newUser).getRank());
            }
            ps.setString(4 + n, user.getEmail());
            ps.setString(5 + n, user.getPassword());
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    /**
     * Deletes the passed user from the database.
     *
     * @Author Niels
     * @param user
     * @throws SQLException
     */
    void deleteUser(User user) throws SQLException {
        try {
            Connection con = Connector.connection();
            String SQL = "DELETE FROM ? WHERE User.email = ?"
                    + " AND User.password = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            if (user instanceof Customer) {
                ps.setString(1, "Customers");
            } else {
                ps.setString(1, "Employees");
            }
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    void deleteCustomer(Customer customer) throws SQLException {
        try {
            Connection con = Connector.connection();
            String SQL = "DELETE FROM `customers` WHERE `customer`.`email` = ? "
                    + "AND `customer`.`password` = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, customer.getEmail());
            ps.setString(2, customer.getPassword());
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }

    void updateCustomer(Customer customer, Customer newCustomer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Employee getEmployee(String email, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void createEmployee(Employee emp) throws SQLException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO `employees` (name, email_address, password, phone_number, rank) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getPassword());
            ps.setString(4, emp.getPhone_number());
            ps.setString(5, emp.getRank());
            ps.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }

    void updateEmployee(Employee employee, Employee newEmployee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void deleteEmployee(Employee employee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
