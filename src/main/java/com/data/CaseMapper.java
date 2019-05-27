package com.data;

import com.entities.dto.Case;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import java.sql.Connection;
import java.sql.Date;
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

    public CaseMapper(DBURL dbURL) throws DataException {
        this.dbURL = dbURL;
    }

    Case getCase(String CaseId) throws DataException {
        try {
            con = Connector.connection(dbURL);
            String SQL = "SELECT * FROM cases "
                    + "WHERE case_Id=?";
            ps = con.prepareStatement(SQL);
            int id = Integer.parseInt(CaseId);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                int orderId = rs.getInt("order_id");
                Date timestamp = rs.getDate("date");
                int customerId = rs.getInt("customer_id");
                int caseId = rs.getInt("case_id");
                String status = rs.getString("case_status");
                String msg_O = rs.getString("msg_owner");
                String msg_st = rs.getString("msg_status");
                int employeId = rs.getInt("employee_id");
                String type = rs.getString("case_type");
                Case C = new Case(caseId, timestamp, orderId, customerId,
                        employeId, status, msg_O, msg_st, type);
                return C;
            } else {
                throw new DataException("Case Not Found");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new DataException(ex.getMessage());
        } finally {
            Connector.CloseConnection(rs, ps, con);
        }
    }

    public List<Case> getUserCases(String userID) throws DataException {
        try {
            con = Connector.connection(dbURL);
            String SQL = "SELECT * FROM fogcarport.cases WHERE employee_id =? AND NOT case_status=\"closed\"";

            List<Case> list = new ArrayList();
            ps = con.prepareStatement(SQL);
            ps.setInt(1, Integer.parseInt(userID));
            rs = ps.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                Date timestamp = rs.getDate("date");
                int customerId = rs.getInt("customer_id");
                int caseId = rs.getInt("case_id");
                String status = rs.getString("case_status");
                String msg_O = rs.getString("msg_owner");
                String msg_st = rs.getString("msg_status");
                String type = rs.getString("case_type");
                int employeId = 0;

                list.add(new Case(caseId, timestamp, orderId, customerId,
                        employeId, status, msg_O, msg_st, type));
            }

            return list;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new DataException(ex.getMessage());
        } finally {
            Connector.CloseConnection(rs, ps, con);
        }
    }

    public List<Case> getCustomerCases(int ID) throws DataException {
        try {
            con = Connector.connection(dbURL);
            String SQL = "SELECT * FROM fogcarport.cases " +
                    "WHERE customer_id =? AND (case_type=\"salesperson\" OR case_type=\"storeworker\");";

            List<Case> list = new ArrayList();
            ps = con.prepareStatement(SQL);
            ps.setInt(1, ID);
            rs = ps.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                Date timestamp = rs.getDate("date");
                int customerId = rs.getInt("customer_id");
                int caseId = rs.getInt("case_id");
                String status = rs.getString("case_status");
                String msg_O = rs.getString("msg_owner");
                String msg_st = rs.getString("msg_status");
                String type = rs.getString("case_type");
                int employeId = 0;

                list.add(new Case(caseId, timestamp, orderId, customerId,
                        employeId, status, msg_O, msg_st, type));
            }

            return list;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new DataException(ex.getMessage());
        } finally {
            Connector.CloseConnection(rs, ps, con);
        }
    }

    public List<Case> getUserClosedCases(int userID) throws DataException {
        try {
            con = Connector.connection(dbURL);
            String SQL = "SELECT * FROM fogcarport.cases WHERE employee_id =? AND case_status=\"closed\"";

            List<Case> list = new ArrayList();
            ps = con.prepareStatement(SQL);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                Date timestamp = rs.getDate("date");
                int customerId = rs.getInt("customer_id");
                int caseId = rs.getInt("case_id");
                String status = rs.getString("case_status");
                String msg_O = rs.getString("msg_owner");
                String msg_st = rs.getString("msg_status");
                String type = rs.getString("case_type");
                int employeId = 0;

                list.add(new Case(caseId, timestamp, orderId, customerId,
                        employeId, status, msg_O, msg_st, type));
            }

            return list;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new DataException(ex.getMessage());
        } finally {
            Connector.CloseConnection(rs, ps, con);
        }
    }

    public List<Case> getFreeCases(String type) throws DataException {
        try {
            con = Connector.connection(dbURL);
            String SQL = "SELECT * FROM `fogcarport`.`cases` WHERE `employee_Id` IS NULL "
                    + "AND `case_type` =?";

            List<Case> list = new ArrayList();
            ps = con.prepareStatement(SQL);
            ps.setString(1, type);
            rs = ps.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                Date timestamp = rs.getDate("date");
                int customerId = rs.getInt("customer_id");
                int caseId = rs.getInt("case_id");
                String status = rs.getString("case_status");
                String msg_O = rs.getString("msg_owner");
                String msg_st = rs.getString("msg_status");
                int employeId = 0;

                list.add(new Case(caseId, timestamp, orderId, customerId,
                        employeId, status, msg_O, msg_st, type));
            }

            return list;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new DataException(ex.getMessage());
        } finally {
            Connector.CloseConnection(rs, ps, con);
        }
    }

    public void updCaseEmpl(int emplId, int caseId) throws DataException {

        try {
            con = Connector.connection(dbURL);
            String SQL = "UPDATE fogcarport.cases "
                    + "SET employee_id = ? WHERE case_id =? "
                    + "AND employee_id IS NULL;";
            ps = con.prepareStatement(SQL);
            ps.setInt(1, emplId);
            ps.setInt(2, caseId);
            int succes = ps.executeUpdate();
            if (succes != 1) {
                throw new DataException("Update Failed");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new DataException(ex.getMessage());
        } finally {
            Connector.CloseConnection(rs, ps, con);
        }

    }

    public void updCasefree(int caseId) throws DataException {

        try {
            con = Connector.connection(dbURL);
            String SQL = "UPDATE fogcarport.cases "
                    + "SET employee_id = NULL WHERE case_id =?;";
            ps = con.prepareStatement(SQL);
            ps.setInt(1, caseId);
            int succes = ps.executeUpdate();
            if (succes != 1) {
                throw new DataException("Update Failed");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new DataException(ex.getMessage());
        } finally {
            Connector.CloseConnection(rs, ps, con);
        }

    }

    public void updCaseStatus(int caseId, String stat) throws DataException {

        try {
            con = Connector.connection(dbURL);
            String SQL = "UPDATE fogcarport.cases "
                    + "SET case_status = ? WHERE case_id =? ;";
            ps = con.prepareStatement(SQL);
            ps.setString(1, stat);
            ps.setInt(2, caseId);
            int succes = ps.executeUpdate();
            if (succes != 1) {
                throw new DataException("Update Failed");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new DataException(ex.getMessage());
        } finally {
            Connector.CloseConnection(rs, ps, con);
        }

    }

    public void updCaseMsg(Case C) throws DataException {
        String SQL = "UPDATE fogcarport.cases "
                + "SET `msg_status`=? , `msg_owner`=? WHERE case_id =? ;";
        try {
            con = Connector.connection(dbURL);
            ps = con.prepareStatement(SQL);
            ps.setString(1, C.getMsg_status());
            ps.setString(2, C.getMsg_owner());
            ps.setInt(3, C.getCaseId());

            int succes = ps.executeUpdate();
            if (succes != 1) {
                throw new DataException("Update Failed");
            }
        } catch (SQLException ex) {
            throw new DataException(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CaseMapper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connector.CloseConnection(rs, ps, con);
        }

    }

    public void createCase(Case C) throws DataException {
        String SQL = "INSERT INTO `cases` ( `case_status`,`case_type`,`msg_status`,`msg_owner`) VALUES"
                + "('open',?,?,?)";
        try {
            con = Connector.connection(dbURL);
            ps = con.prepareStatement(SQL);
            ps.setString(1, C.getType());
            ps.setString(2, "");
            ps.setString(3, C.getMsg_owner());
            int succes = ps.executeUpdate();
            if (succes != 1) {
                throw new DataException("Update Failed");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new DataException(ex.getMessage());
        }

    }

    public void createCaseOrder(Case C) throws DataException {
        String SQL = "INSERT INTO `cases` ( `case_status`,`case_type`,`msg_status`,`msg_owner`,order_id, customer_id) VALUES"
                + "('open',?,\"\",?,?,?)";
        try {
            con = Connector.connection(dbURL);
            ps = con.prepareStatement(SQL);
            ps.setString(1, C.getType());
            ps.setString(2, C.getMsg_owner());
            ps.setInt(3, C.getOrderId());
            ps.setInt(4, C.getCustomerId());

            int succes = ps.executeUpdate();
            if (succes != 1) {
                throw new DataException("Update Failed");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new DataException(ex.getMessage());
        }

    }
}
