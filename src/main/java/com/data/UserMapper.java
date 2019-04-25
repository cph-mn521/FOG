package com.data;

import com.entities.dto.Customer;
import com.entities.dto.User;
import com.exceptions.DataException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Martin, Martin BøghM, Niels
 */
public class UserMapper {

    /**
     * Method for fetching a user from the database, Requires both email and
     * password to match Sends an error to be interpreted in higher layer.
     *
     * * uses prepared statements to avoid SQL injects.
     *
     * @Author Martin, Martin Bøgh, Niels
     * @param email
     * @param password
     * @return User object containing the corresponding to the query.
     * @throws SQLException
     */
    static Customer getCustomer(String email, String password) throws SQLException, DataException {
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
                int phone_number = rs.getInt("phone_number");
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
     * @param user
     * @throws SQLException
     */
    static void createUser(User user) throws SQLException {
        try {
            Connection con = Connector.connection();

            String SQL = "INSERT INTO ? (name, email_address, password, phone_number) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            if (user instanceof Customer) {
                ps.setString(1, "`Customer`");
            } else {
                ps.setString(1, "`Employee`");
            }
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getPhone_number());

            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            throw new SQLException("Failed to create user. Error: " + ex.getMessage());
        }
    }

    /**
     * Method for updating a user in the database
     *
     * Takes user and a newUser entity and updates the old user to the newUser.
     *
     *
     * @param user
     * @param newUser
     * @throws SQLException
     */
    static void updateUser(User user, User newUser) throws SQLException {
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE `User` SET `email` = ?, `password`= ?, `role` = ?, `name`=? "
                    + "WHERE User.email = ? AND User.password = ?";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, newUser.getEmail());
            ps.setString(2, newUser.getPassword());
            ps.setString(3, newUser.get);
            ps.setString(4, user.email);
            ps.setString(5, user.password);
            ps.setString(6, user.role);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    /**
     * Deletes the passed user from the database.
     *
     * @param user
     * @throws SQLException
     */
    static void deleteUser(User user) throws SQLException {
        try {
            Connection con = Connector.connection();
            String SQL = "DELETE FROM `User` WHERE User.email = ?"
                    + " AND User.password = ?"
                    + " AND User.role = ?";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.email);
            ps.setString(2, user.password);
            ps.setString(3, user.role);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

}
