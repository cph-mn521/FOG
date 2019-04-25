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

    private int customer_id;
    private String name, email_address, password, phone_number;

    
    public User(String email_address, String password)
    {
        this.email_address = email_address;
        this.password = password;
    }

    
    public User(int customer_id, String name, String email_address, String password, String phone_number)
    {
        this.customer_id = customer_id;
        this.name = name;
        this.email_address = email_address;
        this.password = password;
        this.phone_number = phone_number;
    }

    public int getCustomer_id()
    {
        return customer_id;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail_address()
    {
        return email_address;
    }

    public String getPassword()
    {
        return password;
    }

    public String getPhone_number()
    {
        return phone_number;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setEmail_address(String email_address)
    {
        this.email_address = email_address;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setPhone_number(String phone_number)
    {
        this.phone_number = phone_number;
    }


}
