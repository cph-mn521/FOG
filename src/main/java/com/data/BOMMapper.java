package com.data;

import com.enumerations.DBURL;
import com.entities.dto.BillOfMaterials;
import com.exceptions.DataException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Niels & Brandstrup (refactoring), Martin Bøgh
 */
class BOMMapper {

    private Connection con;
    private PreparedStatement ps = null;
    private ResultSet rs;
    private DBURL dbURL;

    public BOMMapper(DBURL dbURL) throws DataException {
        this.dbURL = dbURL;
    }

    /**
     * Method for reading the BoMs connected to an order.
     *
     *
     * @param orderId the id of the order to be read.
     * @return The requested bill of materials.
     * @throws DataException
     */
    BillOfMaterials getBOM(int orderId) throws DataException {
        if (orderId <= 0) {
            throw new DataException("Stykliste blev ikke fundet. ID# ikke passende");
        }
        try {
            con = Connector.connection(dbURL);
            String SQL = "SELECT * FROM `bills_of_materials` WHERE `order_id` = ?";
            ps = con.prepareStatement(SQL);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            Map<Integer, Integer> components = new HashMap();

            while (rs.next()) {
                components.put(rs.getInt("component_id"), rs.getInt("amount"));
            }

            BillOfMaterials BoM = new BillOfMaterials(orderId, components);
            return BoM;

        } catch (NumberFormatException | SQLException e) {
            throw new DataException(e.getMessage());
        } finally {
            Connector.CloseConnection(rs, ps, con);
        }
    }

    /**
     * Method for adding a BoM to the database.
     *
     *
     * @param BOM The BoM to be added
     * @throws DataException
     */
    void createBOM(BillOfMaterials BOM) throws DataException {
        try {
            con = Connector.connection(dbURL);
            String SQL = "INSERT INTO `bills_of_materials` (`order_id`,`component_id`,`amount`) VALUES (?,?,?);";
            ps = con.prepareStatement(SQL);
            int orderId = BOM.getOrderId();

            for (Map.Entry<Integer, Integer> entry : BOM.getComponents().entrySet()) {
                ps.setInt(1, orderId);
                ps.setInt(2, entry.getKey());
                ps.setInt(3, entry.getValue());
                ps.executeUpdate();
            }
        } catch (NullPointerException | SQLException e) {
            throw new DataException(e.getMessage());
        } finally {
            Connector.CloseConnection(ps, con);
        }
    }

    /**
     * Method for updating a bill of materials
     *
     * Works by removing the old BoM and adding a new with the same Id.
     *
     * @param BOM Old Bill of Materials
     * @param newBOM New Bill of Materials
     * @throws SQLException
     */
    void updateBOM(BillOfMaterials BOM, BillOfMaterials newBOM) throws DataException {
        try {
            deleteBOM(BOM);
            newBOM.setOrderId(BOM.getOrderId());
            createBOM(newBOM);
        } catch (NullPointerException ex) {
            throw new DataException("Der skete en fejl i opdatering af stykliste. " + ex.getMessage());
        }
    }

    /**
     * Method for deleting a BoM in the database.
     *
     * @param BOM the Bill of materials to be deleted.
     * @throws DataException
     * @throws SQLException
     */
    void deleteBOM(BillOfMaterials BOM) throws DataException {
        try {
            con = Connector.connection(dbURL);
            String SQL = "SET FOREIGN_KEY_CHECKS=0; DELETE * FROM `bills_of_materials` "
                    + "WHERE `order_id` = ?; SET FOREIGN_KEY_CHECKS=1;";
            ps = con.prepareStatement(SQL);
            ps.setInt(1, BOM.getOrderId());
            int status = ps.executeUpdate();
            if (status == 0) {
                throw new DataException("Stykliste ikke fundet. Derfor ikke slettet");
            }

        } catch (NullPointerException | SQLException e) {
            throw new DataException(e.getMessage());
        } finally {
            Connector.CloseConnection(ps, con);
        }
    }
}
