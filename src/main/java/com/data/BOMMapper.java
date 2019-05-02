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

    public BOMMapper(DBURL dbURL) throws DataException
    {
       this.dbURL = dbURL;
    }
    
    /**
     *
     *
     * @param orderId
     * @return
     * @throws DataException
     * @throws SQLException
     */
    BillOfMaterials getBOM(int orderId) throws DataException {
        try {
            con = Connector.connection(DBURL.PRODUCTION);
            String SQL = "SELECT * FROM `bills_of_materials` WHERE `order_id` = ?";
            ps = con.prepareStatement(SQL);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
//            int orderId = rs.getInt("case_Id");
            Map<Integer, Integer> components = new HashMap();

            while (rs.next()) {
                components.put(rs.getInt("component_id"), rs.getInt("amount"));
            }

            BillOfMaterials BoM = new BillOfMaterials(orderId, components);
            return BoM;

        } catch (ClassNotFoundException | SQLException e) {
            throw new DataException(e.getMessage());
        } finally
        {
            Connector.CloseConnection(rs, ps, con);
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
            con = Connector.connection(DBURL.PRODUCTION);
            String SQL = "SET FOREIGN_KEY_CHECKS=0; INSERT INTO `bills_of_materials` "
                    + "VALUES (?,?,?); SET FOREIGN_KEY_CHECKS=1; ";
            ps = con.prepareStatement(SQL);
            int orderId = BOM.getOrderlId();

            for (Map.Entry<Integer, Integer> entry : BOM.getComponents().entrySet()) {
                ps.setInt(1, orderId);
                ps.setInt(2, entry.getKey());
                ps.setInt(3, entry.getValue());
                ps.executeUpdate();
            }
        } catch (NullPointerException | ClassNotFoundException | SQLException e) {
            throw new DataException(e.getMessage());
        } finally
        {
            Connector.CloseConnection(rs, ps, con);
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
            con = Connector.connection(DBURL.PRODUCTION);
            String SQL = "SET FOREIGN_KEY_CHECKS=0; DELETE * FROM `bills_of_materials` "
                    + "WHERE `order_id` = ?; SET FOREIGN_KEY_CHECKS=1;";
            ps = con.prepareStatement(SQL);
            ps.setInt(1, BOM.getOrderlId());
            ps.executeUpdate();

        } catch (NullPointerException | ClassNotFoundException | SQLException e) {
            throw new DataException(e.getMessage());
        } finally
        {
            Connector.CloseConnection(rs, ps, con);
        }

    }

}
