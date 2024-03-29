package com.entities.dto;

import java.sql.Date;

/**
 *
 * @author Martin
 */

public class Message {

    private String type;
    private int ID;
    private Date timestamp;
    private String title;
    private String msg;

    public Message(int ID, Date timestamp, String title, String msg) {
        this.ID = ID;
        this.msg = msg;
        this.timestamp = timestamp;
        this.title = title;
    }

    public Message(int ID,String type, Date timestamp, String title, String msg) {
        this.type=type;
        this.ID = ID;
        this.msg = msg;
        this.timestamp = timestamp;
        this.title = title;
    }

    public int getID() {
        return ID;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getTitle() {
        return title;
    }

    public String getMsg() {
        return msg;
    }

    public String getType(){
        return type;
    }
    
}
