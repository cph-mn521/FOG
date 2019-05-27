package com.data;

import com.entities.dto.Message;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin
 */
public class messageMapper {

    private Connection con;
    private PreparedStatement ps = null;
    private ResultSet rs;
    private DBURL dbURL;

    public messageMapper(DBURL dbURL) throws DataException {
        this.dbURL = dbURL;
    }

    public Message getMessage(int ID) throws DataException {
        if (ID <= 0) {
            throw new DataException("Ansat blev ikke fundet. ID# ikke passende");
        }
        try {
            con = Connector.connection(dbURL);
            String SQL = "SELECT * FROM `messages` WHERE msg_id =?";
            ps = con.prepareStatement(SQL);
            ps.setInt(1, ID);
            rs = ps.executeQuery();
            if (rs.next()) {
                Date timestamp = rs.getDate("date");
                String type = rs.getString("type");
                String title = rs.getString("title");
                String content = rs.getString("content");

                int dbID = rs.getInt("msg_id");
                return new Message(dbID, timestamp, title, content);
            } else {
                throw new DataException("Message not Found");
            }
        } catch (NumberFormatException | SQLException ex) {
            throw new DataException(ex.getMessage());
        }
    }

    public List<Message> getMessages(String Rank) throws DataException {
        if (Rank.isEmpty()) {
            throw new DataException("Beskeder ikke fundet. Stilling ikke korrekt");
        }
        try {
            con = Connector.connection(dbURL);
            List<Message> list = new ArrayList();
            String SQL = "SELECT * FROM `messages` WHERE type =? OR type = ?";
            ps = con.prepareStatement(SQL);
            ps.setString(1, Rank);
            ps.setString(2, "all");
            rs = ps.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("msg_id");
                Date timestamp = rs.getDate("date");
                String type = rs.getString("type");
                String content = rs.getString("content");
                String title = rs.getString("title");
                list.add(new Message(ID, timestamp, title, content));
            }
            return list;

        } catch (NullPointerException | NumberFormatException | SQLException ex) {
            throw new DataException(ex.getMessage());
        }
    }

    public void createMsg(Message msg) throws DataException {
        String SQL = "INSERT INTO `fogcarport`.`messages` (`type`,`title` ,`content`) VALUES\n"
                + "(?,?,?);";
        try {
            con = Connector.connection(dbURL);            
            ps = con.prepareStatement(SQL);
            ps.setString(1, msg.getType());
            ps.setString(2, msg.getTitle());
            ps.setString(3, msg.getMsg());
            int succes = ps.executeUpdate();
            if (succes != 1) {
                throw new DataException("Update Failed");
            }
        } catch (SQLException ex) {
            throw new DataException(ex.getMessage());
        }
    }


}
