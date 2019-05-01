package com.data;

import com.enumerations.DBURL;
import com.entities.dto.Component;
import com.exceptions.DataException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Niller
 */
public class ComponentMapper {

    /**
     * Method for adding components to the database identical to createComponent
     *
     * @param component the component to be added.
     * @throws SQLException if a database error occurs.
     */
    void addComponent(Component component) throws SQLException {
        try {
            Connection con = Connector.connection(DBURL.PRODUCTION);
            String SQL = "INSERT INTO `components`(`description`,`help_text`,`length`,`width`,`height`,`price`) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, component.getDescription());
            ps.setString(2, component.getHelpText());
            ps.setInt(3, component.getLength());
            ps.setInt(4, component.getWidth());
            ps.setInt(5, component.getHeight());
            ps.setFloat(6, component.getPrice());
            ps.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }

    /**
     * Method for adding components to the database.
     *
     * @deprecated Use addComponent.
     * @param component the component to be added.
     * @throws SQLException if a database error occurs.
     */
    void createComponent(Component component) throws SQLException {
        addComponent(component);
    }

    /**
     * Method for fetching a component from the database.
     *
     *
     * @param ComponentId the id-number of the component.
     * @return The requested component
     * @throws SQLException If a database error occurs.
     */
    Component getComponent(int ComponentId) throws SQLException {
        try {
            Connection con = Connector.connection(DBURL.PRODUCTION);
            String SQL = "SELECT * FROM `components` WHERE `components`.`component_id` = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, ComponentId);
            ResultSet rs = ps.executeQuery();
            String desc = rs.getString(1);
            String helptxt = rs.getString(2);
            int length = rs.getInt(3);
            int width = rs.getInt(4);
            int height = rs.getInt(5);
            float price = rs.getFloat(6);

            return new Component(desc, helptxt, length, width, height, price);
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }

    /**
     * Method for updating components.
     *
     *
     * @param comp The component to be changed.
     * @param newComp The component with the updated info.
     * @throws SQLException
     */
    void updateComponent(Component comp, Component newComp) throws SQLException {
        try {
            Connection con = Connector.connection(DBURL.PRODUCTION);
            String SQL = "UPDATE `components` SET "
                    + "`description` = ?, `help_text` = ?,"
                    + " `length` = ?, `width` = ?, `height` = ?, `price` = ?"
                    + "WHERE `component_id` = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, newComp.getDescription());
            ps.setString(2, newComp.getHelpText());
            ps.setInt(3, newComp.getLength());
            ps.setInt(4, newComp.getWidth());
            ps.setInt(5, newComp.getHeight());
            ps.setFloat(6, newComp.getPrice());
            ps.setInt(7, comp.getComponentId());

            ps.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }

    void deleteComponent(Component Component) throws SQLException {
        try {
            Connection con = Connector.connection(DBURL.PRODUCTION);
            String SQL = "DELETE * FROM `components` WHERE  `components`.`componentId` = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, Component.getComponentId());
            ps.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }

    }

}
