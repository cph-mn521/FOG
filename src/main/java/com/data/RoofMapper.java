/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
     * @param orderId - the orderId of the object you need to retrieve
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
            int slant = rs.getInt("slant");
            String type = rs.getString("type");
            String color = rs.getString("color");
            String version = rs.getString("version");

            return new Roof(roofTypeId, slant, type, color, version);
        }
        catch (SQLException | ClassNotFoundException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }

    void createRoof(Roof roof)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void updateRoof(Roof roof, Roof newRoof)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void deleteRoof(Roof roof)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
