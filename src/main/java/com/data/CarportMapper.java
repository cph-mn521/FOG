package com.data;

import com.enumerations.DBURL;
import com.entities.dto.Carport;
import com.exceptions.DataException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Brandstrup, Martin Bøgh
 */
class CarportMapper {

    private Connection con;
    private PreparedStatement ps = null;
    private ResultSet rs;
    private DBURL dbURL;

    public CarportMapper(DBURL dbURL) throws DataException {
        this.dbURL = dbURL;
    }

    /**
     * Retrieves a Carport object with a given orderId from the database.
     *
     * @param orderId the orderId of the object you need to retrieve
     * @return a Carport object retrieved from the database
     * @throws DataException
     */
    Carport getCarport(int orderId) throws DataException {
        if (orderId <= 0) {
            throw new DataException("Carport blev ikke fundet. ID# ikke passende");
        }
        try {
            con = Connector.connection(dbURL);
            String SQL
                    = "SELECT *"
                    + " FROM `carports`"
                    + " WHERE `order_id` = ?;";

            ps = con.prepareStatement(SQL);
            ps.setInt(1, orderId);

            rs = ps.executeQuery();
            if (rs.next()) {
                int roofTypeId = rs.getInt("roof_type_id");
                int length = rs.getInt("length");
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                int shedLength = rs.getInt("shed_length");
                int shedWidth = rs.getInt("shed_width");
                int shedHeight = rs.getInt("shed_height");
                return new Carport(orderId, roofTypeId, length, width, height, shedLength, shedWidth, shedHeight);
            } else {
                throw new DataException("User (customer) not found");
            }

        } catch (NumberFormatException | SQLException ex) {
            throw new DataException(ex.getMessage());
        } finally {
            Connector.CloseConnection(rs, ps, con);
        }
    }

    /**
     * Persists a provided Carport object to the database.
     *
     * @param carport the Carport object you want to persist to the database
     * @throws DataException
     */
    void createCarport(Carport carport) throws DataException {
        try {
            con = Connector.connection(dbURL);
            String SQL
                    = "INSERT INTO `carports`"
                    + " (`order_id`, `roof_type_id`, `length`, `width`, `height`, `shed_length`, `shed_width`, `shed_height`)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

            ps = con.prepareStatement(SQL);
            ps.setInt(1, carport.getOrderId());
            ps.setInt(2, carport.getRoofTypeId());
            ps.setInt(3, carport.getLength());
            ps.setInt(4, carport.getWidth());
            ps.setInt(5, carport.getHeight());
            ps.setInt(6, carport.getShedLength());
            ps.setInt(7, carport.getShedWidth());
            ps.setInt(8, carport.getShedHeight());
            ps.executeUpdate();
        } catch (NullPointerException | SQLException ex) {
            throw new DataException(ex.getMessage());
        } finally {
            Connector.CloseConnection(ps, con);
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
    void updateCarport(Carport carport, Carport newCarport) throws DataException {
        try {
            con = Connector.connection(dbURL);
            String SQL
                    = "UPDATE `carports`"
                    + " SET `roof_type_id` = ?, `length` = ?, `width` = ?, `height` = ?"
                    + " `shed_length` = ?, `shed_width` = ?, `shed_height` = ?"
                    + " WHERE `order_id` = ?;";

            ps = con.prepareStatement(SQL);
            ps.setInt(1, newCarport.getRoofTypeId());
            ps.setInt(2, newCarport.getLength());
            ps.setInt(3, newCarport.getWidth());
            ps.setInt(4, newCarport.getHeight());
            ps.setInt(5, newCarport.getShedLength());
            ps.setInt(6, newCarport.getShedWidth());
            ps.setInt(7, newCarport.getShedHeight());
            ps.setInt(8, carport.getOrderId());
            ps.executeUpdate();

        } catch (NullPointerException | SQLException ex) {
            throw new DataException(ex.getMessage());
        } finally {
            Connector.CloseConnection(ps, con);
        }
    }

    /**
     * Deletes a Carport entry from the database.
     *
     * @param carport the object you need to replace; uses the orderId
     * @throws DataException
     */
    void deleteCarport(Carport carport) throws DataException {
        try {
            con = Connector.connection(dbURL);
            String SQL
                    = "DELETE *"
                    + " FROM `carports`"
                    + " WHERE  `order_id` = ?";

            ps = con.prepareStatement(SQL);
            ps.setInt(1, carport.getOrderId());
            int status = ps.executeUpdate();
            if (status == 0) {
                throw new DataException("Carport ikke fundet. Derfor ikke slettet");
            }

        } catch (NullPointerException | SQLException ex) {
            throw new DataException(ex.getMessage());
        } finally {
            Connector.CloseConnection(ps, con);
        }
    }

    /**
     *
     * @return a List<Carport> containing all the carports in the database
     * @throws DataException
     */
    public List<Carport> getAllCarports() throws DataException {
        try {
            con = Connector.connection(dbURL);
            String SQL
                    = "SELECT *"
                    + " FROM `carports`;";

            List<Carport> list = new ArrayList();
            ps = con.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                int roofTypeId = rs.getInt("roof_type_id");
                int length = rs.getInt("length");
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                int shedLength = rs.getInt("shed_length");
                int shedWidth = rs.getInt("shed_width");
                int shedHeight = rs.getInt("shed_height");

                list.add(new Carport(orderId, roofTypeId, length, width, height, shedLength, shedWidth, shedHeight));
            }

            return list;
        } catch (NumberFormatException | SQLException ex) {
            throw new DataException(ex.getMessage());
        } finally {
            Connector.CloseConnection(rs, ps, con);
        }
    }
}
