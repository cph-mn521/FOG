/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.data;

import com.entities.dto.Component;
import com.exceptions.DataException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Niller
 */
public class DataMapper {

    void createComponent(Component component) throws SQLException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO `components`(`description`,`help_text`,`length`,`width`,`height`,`price`) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, component.getDescription());
            ps.setString(2, component.getHelpText());
            ps.setInt(3, component.getLength());
            ps.setInt(4, component.getWidth());
            ps.setInt(5, component.getHeight());
            ps.setFloat(6, component.getPrice());

        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }

    //Components
    Component getComponent(int ComponentId) throws SQLException, DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void updateComponent(Component Component, Component newComponent) throws SQLException, DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void deleteComponent(Component Component) throws SQLException {
        try {
            Connection con = Connector.connection();
            String SQL = "DELETE FROM `components` WHERE  `components`.`componentId` = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, Component.getComponentId());
            ps.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }

    }

}
