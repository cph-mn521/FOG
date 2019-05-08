package com.data;

import com.entities.dto.Case;
import com.exceptions.DataException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Martin
 */
public class CaseMapper {
    /*
    Case getCase(int caseId) throws DataException {
        
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM case"
                    + "WHERE case_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, caseId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int orderId = rs.getInt("order_id");
                int customerId = rs.getInt("customer_id");
                int employeId = rs.getInt("employee_id");
                String status = rs.getString("status");
                Case returnCase = new Case(caseId, orderId, customerId, employeId, status);
                return returnCase;
            } else {
                throw new DataException("Order not found");
            }

        } catch (SQLException e) {
            throw new DataException("Order not found");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CaseMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    */
}
