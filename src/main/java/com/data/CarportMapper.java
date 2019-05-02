package com.data;

import com.enumerations.DBURL;
import com.entities.dto.Carport;
import com.exceptions.DataException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Brandstrup
 */
class CarportMapper
{

    private Connection con;
    private PreparedStatement ps = null;
    private ResultSet rs;

    public CarportMapper(DBURL dbURL) throws DataException
    {
        try
        {
            con = Connector.connection(dbURL);
        } catch (ClassNotFoundException | SQLException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }

    /**
     * Retrieves a Carport object with a given orderId from the database.
     *
     * @param orderId the orderId of the object you need to retrieve
     * @return a Carport object retrieved from the database
     * @throws DataException
     */
    Carport getCarport(int orderId) throws DataException
    {
        try
        {
            Connection con = Connector.connection(DBURL.PRODUCTION);
            String SQL
                    = "SELECT *"
                    + " FROM `fogcarport`.`carports`"
                    + " WHERE `carports`.`order_id` = ?";

            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();
            int roofTypeId = rs.getInt("roof_type_id");
            int length = rs.getInt("length");
            int width = rs.getInt("width");
            int height = rs.getInt("height");
            int shedLength = rs.getInt("shedLength");
            int shedWidth = rs.getInt("shedWidth");
            int shedHeight = rs.getInt("shedHeight");

            return new Carport(orderId, roofTypeId, length, width, height, shedLength, shedWidth, shedHeight);
        } catch (SQLException | ClassNotFoundException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }

    /**
     * Persists a provided Carport object to the database.
     *
     * @param carport the Carport object you want to persist to the database
     * @throws DataException
     */
    void createCarport(Carport carport) throws DataException
    {
        try
        {
            Connection con = Connector.connection(DBURL.PRODUCTION);
            String SQL
                    = "INSERT INTO `fogcarport`.`carports`"
                    + " (`order_id`, `roof_type_id`, `length`, `width`, `height`, `shedLength`, `shedWidth`, `shedHeight`)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, carport.getOrderId());
            ps.setInt(2, carport.getRoofTypeId());
            ps.setInt(3, carport.getLength());
            ps.setInt(4, carport.getWidth());
            ps.setInt(5, carport.getHeight());
            ps.setInt(6, carport.getShedLength());
            ps.setInt(7, carport.getShedWidth());
            ps.setInt(8, carport.getShedHeight());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }

    /**
     * Updates a Carport entry in the database with the data from a given
     * Carport object.
     *
     * @param carport the old object you need to replace; uses the orderId
     * @param newCarport the new Carport object with all the data you need to
     * persist to the database
     * @throws DataException
     */
    void updateCarport(Carport carport, Carport newCarport) throws DataException
    {
        try
        {
            Connection con = Connector.connection(DBURL.PRODUCTION);
            String SQL
                    = "UPDATE `fogcarport`.`carports`"
                    + " SET `roof_type_id` = ?, `length` = `?, `width` = ?, `height` = ?"
                    + " `shedLength` = ?, `shedWidth` = ?, `shedHeight` = ?"
                    + " WHERE `carports`.`order_id` = ?;";

            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, newCarport.getRoofTypeId());
            ps.setInt(2, newCarport.getLength());
            ps.setInt(3, newCarport.getWidth());
            ps.setInt(4, newCarport.getHeight());
            ps.setInt(5, newCarport.getShedLength());
            ps.setInt(6, newCarport.getShedWidth());
            ps.setInt(7, newCarport.getShedHeight());
            ps.setInt(8, carport.getOrderId());
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }

    /**
     * Deletes a Carport entry from the database.
     *
     * @param carport the object you need to replace; uses the orderId
     * @throws DataException
     */
    void deleteCarport(Carport carport) throws DataException
    {
        try
        {
            Connection con = Connector.connection(DBURL.PRODUCTION);
            String SQL
                    = "DELETE *"
                    + " FROM `fogcarport`.`carports`"
                    + " WHERE  `carports`.`order_id` = ?";

            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, carport.getOrderId());
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }
}
