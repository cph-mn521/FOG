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

/**
 *
 * @author Niels
 */
class BOMMapper {

    static BillOfMaterials getBOM(int bomId) throws DataException, SQLException {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM `bills_of_materials` WHERE `bill_id` = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, bomId);
            ResultSet rs = ps.executeQuery();
            int billId = rs.getInt("case_Id");
            int componentId = rs.getInt("component_Id");
            int amount = rs.getInt("amount");
            BillOfMaterials BoM = new BillOfMaterials(billId, componentId, amount);
            return BoM;

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataException(e.getMessage());
        }
    }

    static void createBOM(BillOfMaterials BOM) throws DataException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void updateBOM(BillOfMaterials BOM, BillOfMaterials newBOM) throws DataException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void deleteBOM(BillOfMaterials BOM) throws DataException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
