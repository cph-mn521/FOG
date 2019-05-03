package com.data;

import com.enumerations.DBURL;
import com.entities.dto.Component;
import com.exceptions.DataException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Niller, Martin Bøgh
 */
public class ComponentMapper
{

    private Connection con;
    private PreparedStatement ps = null;
    private ResultSet rs;
    private DBURL dbURL;

    public ComponentMapper(DBURL dbURL) throws DataException
    {
        this.dbURL = dbURL;
    }

    /**
     * Method for adding components to the database identical to createComponent
     *
     * @param component the component to be added.
     * @throws SQLException if a database error occurs.
     */
    void addComponent(Component component) throws DataException
    {
        try
        {
            con = Connector.connection(dbURL);
            String SQL = "INSERT INTO `components`(`description`,`help_text`,`length`,`width`,`height`,`price`) VALUES (?,?,?,?,?,?)";
            ps = con.prepareStatement(SQL);
            ps.setString(1, component.getDescription());
            ps.setString(2, component.getHelpText());
            ps.setInt(3, component.getLength());
            ps.setInt(4, component.getWidth());
            ps.setInt(5, component.getHeight());
            ps.setFloat(6, component.getPrice());
            ps.executeUpdate();

        } catch (NullPointerException | SQLException | ClassNotFoundException e)
        {
            throw new DataException(e.getMessage());
        } finally
        {
            Connector.CloseConnection(ps, con);
        }
    }

    /**
     * Method for adding components to the database.
     *
     * @deprecated Use addComponent.
     * @param component the component to be added.
     * @throws SQLException if a database error occurs.
     */
    void createComponent(Component component) throws DataException
    {
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
    Component getComponent(int ComponentId) throws DataException
    {
        try
        {
            con = Connector.connection(dbURL);
            String SQL = "SELECT * FROM `components` WHERE `components`.`component_id` = ?";
            ps = con.prepareStatement(SQL);
            ps.setInt(1, ComponentId);
            rs = ps.executeQuery();
            if (rs.next())
            {
                String desc = rs.getString("description");
                String helptxt = rs.getString("help_text");
                int length = rs.getInt("length");
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                float price = rs.getFloat("price");
                return new Component(ComponentId, desc, helptxt, length, width, height, price);
            } else {
                throw new DataException("Component not found");
            }

        } catch (SQLException | ClassNotFoundException e)
        {
            throw new DataException(e.getMessage());
        } finally
        {
            Connector.CloseConnection(rs, ps, con);
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
    void updateComponent(Component comp, Component newComp) throws DataException
    {
        try
        {
            con = Connector.connection(dbURL);
            String SQL = "UPDATE `components` SET "
                    + "`description` = ?, `help_text` = ?,"
                    + " `length` = ?, `width` = ?, `height` = ?, `price` = ?"
                    + "WHERE `component_id` = ?";
            ps = con.prepareStatement(SQL);
            ps.setString(1, newComp.getDescription());
            ps.setString(2, newComp.getHelpText());
            ps.setInt(3, newComp.getLength());
            ps.setInt(4, newComp.getWidth());
            ps.setInt(5, newComp.getHeight());
            ps.setFloat(6, newComp.getPrice());
            ps.setInt(7, comp.getComponentId());

            ps.executeUpdate();

        } catch (NullPointerException | SQLException | ClassNotFoundException e)
        {
            throw new DataException(e.getMessage());
        } finally
        {
            Connector.CloseConnection(ps, con);
        }
    }

    void deleteComponent(Component Component) throws DataException
    {
        try
        {
            con = Connector.connection(dbURL);
            String SQL = "DELETE * FROM `components` WHERE  `components`.`componentId` = ?";
            ps = con.prepareStatement(SQL);
            ps.setInt(1, Component.getComponentId());
            ps.executeUpdate();

        } catch (NullPointerException | SQLException | ClassNotFoundException e)
        {
            throw new DataException(e.getMessage());
        } finally
        {
            Connector.CloseConnection(ps, con);
        }

    }

    /**
     * 
     * @author Brandstrup
     * @return a List<Component> containing all the components in the database
     * @throws DataException 
     */
    public List<Component> getAllEmployees() throws DataException
    {
        try
        {
            con = Connector.connection(dbURL);
            String SQL
                    = "SELECT *"
                    + " FROM `fogcarport`.`components`;";
            
            List<Component> list = new ArrayList();
            ps = con.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next())
            {
                int componentId = rs.getInt("component_id");
                String description = rs.getString("description");
                String helpText = rs.getString("help_text");
                int length = rs.getInt("length");
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                float price = rs.getFloat("price");
                
                list.add(new Component(componentId, description, helpText, length, width, height, price));
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
