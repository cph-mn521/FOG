package com.data;

import com.enumerations.DBURL;
import com.entities.dto.Roof;
import com.exceptions.DataException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Brandstrup
 */
class RoofMapper {

    private Connection con;
    PreparedStatement ps = null;
    ResultSet rs;

    public RoofMapper(DBURL dbURL) throws DataException {
        con = Connector.connection(dbURL);
    }

    /**
     * Retrieves a Roof object with a given orderId from the database.
     *
     * @param orderId the orderId of the object you need to retrieve
     * @return a Roof object retrieved from the database
     * @throws DataException
     */
    Roof getRoof(int roofTypeId) throws DataException {
        if (roofTypeId <= 0) {
            throw new DataException("Tag-type blev ikke fundet. ID# ikke passende");
        }
        try {
            con = Connector.connection(DBURL.PRODUCTION);
            String SQL
                    = "SELECT *"
                    + " FROM `roof_types`"
                    + " WHERE `roof_type_id` = ?;";

            ps = con.prepareStatement(SQL);
            ps.setInt(1, roofTypeId);

            rs = ps.executeQuery();
            if (rs.next()) {
                String type = rs.getString("type");
                String color = rs.getString("color");
                int slant = rs.getInt("slant");
                String version = rs.getString("version");

                return new Roof(roofTypeId, slant, type, color, version);
            } else {
                throw new DataException("Roof was not found");
            }
        } catch (NumberFormatException | SQLException ex) {
            throw new DataException(ex.getMessage());
        } finally {
            Connector.CloseConnection(rs, ps, con);
        }
    }

    /**
     * Persists a provided Roof object to the database.
     *
     * @param roof the Roof object you want to persist to the database
     * @throws DataException
     */
    void createRoof(Roof roof) throws DataException {
        try {
            con = Connector.connection(DBURL.PRODUCTION);
            String SQL
                    = "INSERT INTO `roof_types`"
                    + " (`type`, `color`, `slant`, `version`)"
                    + " VALUES (?, ?, ?, ?);";

            ps = con.prepareStatement(SQL);
            ps.setString(1, roof.getType());
            ps.setString(2, roof.getColor());
            ps.setInt(3, roof.getSlant());
            ps.setString(4, roof.getVersion());
            ps.executeUpdate();
        } catch (NullPointerException | SQLException ex) {
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
    void updateRoof(Roof roof, Roof newRoof) throws DataException {
        try {
            con = Connector.connection(DBURL.PRODUCTION);
            String SQL
                    = "UPDATE `roof_types`"
                    + " SET `type` = ?, `color` = ?, `slant` = ?, `version` = ?"
                    + " WHERE `roof_type_id` = ?;";

            ps = con.prepareStatement(SQL);
            ps.setString(1, newRoof.getType());
            ps.setString(2, newRoof.getColor());
            ps.setInt(3, newRoof.getSlant());
            ps.setString(4, newRoof.getVersion());
            ps.setInt(5, roof.getRoofTypeId());
            ps.executeUpdate();

        } catch (NullPointerException | SQLException ex) {
            throw new DataException(ex.getMessage());
        }
    }

    /**
     * Deletes a Roof entry from the database.
     *
     * @param roof the object you need to replace; uses the roofTypeId
     * @throws DataException
     */
    void deleteRoof(Roof roof) throws DataException {
        try {
            con = Connector.connection(DBURL.PRODUCTION);
            String SQL
                    = "DELETE *"
                    + " FROM `roof_types`"
                    + " WHERE  `roof_type_id` = ?";

            ps = con.prepareStatement(SQL);
            ps.setInt(1, roof.getRoofTypeId());
            ps.executeUpdate();

        } catch (NullPointerException | SQLException ex) {
            throw new DataException(ex.getMessage());
        }
    }

    /**
     *
     * @return a List<Roof> containing all the roof types in the database
     * @throws DataException
     */
    public List<Roof> getAllRoofs() throws DataException {
        try {
            con = Connector.connection(DBURL.PRODUCTION);
            String SQL = "SELECT * FROM `roof_types`;";

            List<Roof> list = new ArrayList();
            ps = con.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                int roofTypeId = rs.getInt("roof_type_id");
                String type = rs.getString("type");
                String color = rs.getString("color");
                int slant = rs.getInt("slant");
                String version = rs.getString("version");

                list.add(new Roof(roofTypeId, slant, type, color, version));
            }

            return list;
        } catch (NumberFormatException | SQLException ex) {
            throw new DataException(ex.getMessage());
        } finally {
            Connector.CloseConnection(rs, ps, con);
        }
    }
}
