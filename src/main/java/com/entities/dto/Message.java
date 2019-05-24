/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities.dto;

import java.sql.Date;

/**
 *
 * @author Martin
 */
public class Message implements java.io.Serializable {

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

}
