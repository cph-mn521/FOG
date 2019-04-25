/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities.dto;

/**
 *
 * @author Martin, Martin Bøgh
 */
public class User
{

    private int phone_number;
    private String name, email_address, password;

    public User(String name, String email_address, String password, int phone_number)
    {
        this.phone_number = phone_number;
        this.name = name;
        this.email_address = email_address;
        this.password = password;
    }
}
