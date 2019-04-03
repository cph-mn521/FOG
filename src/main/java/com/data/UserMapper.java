/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.data;

import com.entities.dto.User;
import com.exceptions.DataException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Martin
 */
public class UserMapper {

    /**
     * Method for fetching a user from the database, Requires both email and
     * password to match Sends an error to be intepretet in higher layer.
     *
     * * uses prepared statements to avoid SQL injects.
     *
     * @param email
     * @param password
     * @return User object containing the corrosponding to the query.
     * @throws SQLException
     */
    static User getUser(String email, String password) throws SQLException, DataException {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT id, role FROM user "
                    + "WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                int id = rs.getInt("id");
                User user = new User(email, password, role);
                //user.setId(id);
                return user;
            } else {
                throw new DataException("User not found");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    /**
     * Method for adding a new user entry to the database.
     *
     * Takes a user entety and then converts it to a SQL statements.
     *
     * * uses prepared statements to avoid SQL injects.
     *
     * @param user
     * @throws SQLException
     */
    static void createUser(User user) throws SQLException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO user (email, password, role) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.email);
            ps.setString(2, user.password);
            ps.setString(3, user.role);
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            //user.setId(id);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    static void updateUser(String userName, User newUser) {

    }

    static void deleteUser(User user) {

    }

}
