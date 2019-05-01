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
 * @author Niels & Brandstrup (refactoring)
 */
class BOMMapper {

    /**
     *
     *
     * @param orderId
     * @return
     * @throws DataException
     * @throws SQLException
     */
    BillOfMaterials getBOM(int orderId) throws DataException, SQLException {
        try {
            Connection con = Connector.connection(DBURL.PRODUCTION);
            String SQL = "SELECT * FROM `bills_of_materials` WHERE `order_id` = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
//            int orderId = rs.getInt("case_Id");
            HashMap<Integer, Integer> components = new HashMap();

            while (rs.next()) {
                components.put(rs.getInt("component_id"), rs.getInt("amount"));
            }

            BillOfMaterials BoM = new BillOfMaterials(orderId, components);
            return BoM;

        } catch (ClassNotFoundException | SQLException e) {
            throw new DataException(e.getMessage());
        }
    }

    /**
     *
     *
     * @param BOM
     * @throws DataException
     * @throws SQLException
     */
    void createBOM(BillOfMaterials BOM) throws DataException {
        try {
            Connection con = Connector.connection(DBURL.PRODUCTION);
            String SQL = "INSERT INTO `bills_of_materials` VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            int orderId = BOM.getOrderlId();

            for (Map.Entry<Integer, Integer> entry : BOM.getComponents().entrySet()) {
                ps.setInt(1, orderId);
                ps.setInt(2, entry.getKey());
                ps.setInt(3, entry.getValue());
                ps.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new DataException(e.getMessage());
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
    void updateBOM(BillOfMaterials BOM, BillOfMaterials newBOM) throws DataException, SQLException {
        deleteBOM(BOM);
        newBOM.setOrderId(BOM.getOrderlId());
        createBOM(newBOM);
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
            Connection con = Connector.connection(DBURL.PRODUCTION);
            String SQL = "DELETE * FROM `bills_of_materials` WHERE `order_id` = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, BOM.getOrderlId());
            ps.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            throw new DataException(e.getMessage());
        }

    }

}
