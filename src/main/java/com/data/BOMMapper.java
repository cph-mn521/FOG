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

/**
 *
 * @author Niels
 */
class BOMMapper {

    static BillOfMaterials getBOM(int bomId) throws DataException, SQLException {
        try {
            Connection con = Connector.connection();

            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException | ClassNotFoundException e) {

        }
        return null;
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
