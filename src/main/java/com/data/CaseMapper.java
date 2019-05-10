package com.data;

import com.entities.dto.Case;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    private Connection con;
    private PreparedStatement ps = null;
    private ResultSet rs;
    private DBURL dbURL;

    public CaseMapper(DBURL dbURL) throws DataException
    {
       this.dbURL = dbURL;
    }
    
    Case getCase(String CaseId) throws DataException
    {
        try
        {
            con = Connector.connection(dbURL);
            String SQL = "SELECT * FROM Cases "
                    + "WHERE CaseId=?";
            ps = con.prepareStatement(SQL);
            ps.setString(1, CaseId);
            rs = ps.executeQuery();
            if (rs.next())
            {
                int orderId = rs.getInt("order_id");
                int customerId = rs.getInt("customer_id");
                int employeId = rs.getInt("employee_id");
                String status = rs.getString("status");
                int caseId =rs.getInt("case_id");
                Case returnCase = new Case(caseId, orderId, customerId, employeId, status);
                return returnCase;
            } else
            {
                throw new DataException("Case Not Found");
            }
        } catch (ClassNotFoundException | SQLException ex)
        {
            throw new DataException(ex.getMessage());
        } finally
        {
            Connector.CloseConnection(rs, ps, con);
        }
    }
    
    public List<Case> getUserCases(String userID) throws DataException
    {
        try
        {
            con = Connector.connection(dbURL);
            String SQL
                    = "SELECT *"
                    + " FROM `fogcarport`.`cases`"
                    + "where `employeeId` = ?";
            
            List<Case> list = new ArrayList();
            ps = con.prepareStatement(SQL);
            ps.setString(1, userID);
            rs = ps.executeQuery();
            while (rs.next())
            {
                int orderId = rs.getInt("order_id");
                int customerId = rs.getInt("customer_id");
                int caseId = rs.getInt("case_id");
                String status = rs.getString("status");
                int employeId = rs.getInt("employee_id");
                
                list.add(new Case(caseId, orderId, customerId, employeId, status));
            }
            
            return list;
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            throw new DataException(ex.getMessage());
        } finally
        {
            Connector.CloseConnection(rs, ps, con);
        }
    }
    
    
    
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
