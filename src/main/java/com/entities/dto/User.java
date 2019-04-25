/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities.dto;

/**
 *
 * @author Martin
 */
public class User
{

    private int customer_id, phone_number;
    private String name, email_address, password;

    public User(int customer_id, String name, String email_address, String password, int phone_number)
    {
        this.customer_id = customer_id;
        this.phone_number = phone_number;
        this.name = name;
        this.email_address = email_address;
        this.password = password;
    }

   


}
