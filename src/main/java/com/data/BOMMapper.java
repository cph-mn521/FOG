/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.data;

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
 * @author Niels
 */
class BOMMapper {

    /**
     *
     *
     * @param bomId
     * @return
     * @throws DataException
     * @throws SQLException
     */
    static BillOfMaterials getBOM(int bomId) throws DataException, SQLException {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM `bills_of_materials` WHERE `bill_id` = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, bomId);
            ResultSet rs = ps.executeQuery();
            int billId = rs.getInt("case_Id");
            HashMap<Integer, Integer> components = new HashMap();

            while (rs.next()) {
                components.put(rs.getInt("component_id"), rs.getInt("amount"));
            }

            BillOfMaterials BoM = new BillOfMaterials(billId, components);
            return BoM;

        } catch (SQLException | ClassNotFoundException e) {
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
    static void createBOM(BillOfMaterials BOM) throws DataException, SQLException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO `bills_of_materials` VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            int billId = BOM.getBillId();

            for (Map.Entry<Integer, Integer> entry : BOM.getComponents().entrySet()) {
                ps.setInt(1, billId);
                ps.setInt(2, entry.getKey());
                ps.setInt(3, entry.getValue());
                ps.executeUpdate();
            }
        } catch (ClassNotFoundException e) {
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
    static void updateBOM(BillOfMaterials BOM, BillOfMaterials newBOM) throws DataException, SQLException {
        deleteBOM(BOM);
        newBOM.setBillId(BOM.getBillId());
        createBOM(newBOM);
    }

    /**
     * Method for deleting a BoM in the database.
     *
     * @param BOM the Bill of materials to be deleted.
     * @throws DataException
     * @throws SQLException
     */
    static void deleteBOM(BillOfMaterials BOM) throws DataException, SQLException {
        try {
            Connection con = Connector.connection();
            String SQL = "DELETE * FROM `bills_of_materials` WHERE `bill_id` = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, BOM.getBillId());
            ps.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }

    }

}
