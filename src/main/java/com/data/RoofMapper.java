package com.data;

import com.entities.dto.Roof;
import com.exceptions.DataException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Brandstrup
 */
class RoofMapper
{

    /**
     * Retrieves a Roof object with a given orderId from the database.
     *
     * @param orderId the orderId of the object you need to retrieve
     * @return a Roof object retrieved from the database
     * @throws DataException
     */
    Roof getRoof(int roofTypeId) throws DataException
    {
        try
        {
            Connection con = Connector.connection();
            String SQL
                    = "SELECT *"
                    + " FROM `fogcarport`.`roof_types`"
                    + " WHERE `roof_types`.`roof_type_id` = ?";

            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, roofTypeId);

            ResultSet rs = ps.executeQuery();
            String type = rs.getString("type");
            String color = rs.getString("color");
            int slant = rs.getInt("slant");
            String version = rs.getString("version");

            return new Roof(roofTypeId, slant, type, color, version);
        }
        catch (SQLException | ClassNotFoundException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }

    /**
     * Persists a provided Roof object to the database.
     *
     * @param roof the Roof object you want to persist to the database
     * @throws DataException
     */
    void createRoof(Roof roof) throws DataException
    {
        try
        {
            Connection con = Connector.connection();
            String SQL
                    = "INSERT INTO `fogcarport`.`roof_types`"
                    + " (`type`, `color`, `slant`, `version`)"
                    + " VALUES (?, ?, ?, ?);";

            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, roof.getType());
            ps.setString(2, roof.getColor());
            ps.setInt(3, roof.getSlant());
            ps.setString(4, roof.getVersion());
            ps.executeUpdate();
        }
        catch (SQLException | ClassNotFoundException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }

    /**
     * Updates a Roof entry in the database with the data from a given Roof
     * object.
     *
     * @param roof the old object you need to replace; uses the roofTypeId
     * @param newRoof - the new Roof object with all the data you need to
     * persist to the database
     * @throws DataException
     */
    void updateRoof(Roof roof, Roof newRoof) throws DataException
    {
        try
        {
            Connection con = Connector.connection();
            String SQL
                    = "UPDATE `fogcarport`.`roof_types`"
                    + " SET `type` =?, `color` = `?, `slant` = ?, `version` = ?"
                    + " WHERE `roof_types`.`roof_type_id` = ?;";

            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, newRoof.getType());
            ps.setString(2, newRoof.getColor());
            ps.setInt(3, newRoof.getSlant());
            ps.setString(4, newRoof.getVersion());
            ps.setInt(5, roof.getRoofTypeId());
            ps.executeUpdate();

        }
        catch (SQLException | ClassNotFoundException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }

    /**
     * Deletes a Roof entry from the database.
     *
     * @param roof the object you need to replace; uses the roofTypeId
     * @throws DataException
     */
    void deleteRoof(Roof roof) throws DataException
    {
        try
        {
            Connection con = Connector.connection();
            String SQL
                    = "DELETE *"
                    + " FROM `fogcarport`.`roof_types`"
                    + " WHERE  `roof_types`.`roof_type_id` = ?";

            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, roof.getRoofTypeId());
            ps.executeUpdate();

        }
        catch (SQLException | ClassNotFoundException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }
}