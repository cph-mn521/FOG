/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities.dto;

/**
 *
 * @author Martin Bøgh
 */
public class Customer extends User
{

    private int customer_id, phone_number;
    private String name, email_address, password;

    public Customer(int customer_id, String name, String email_address, String password, int phone_number)
    {
        super(name, email_address, password, phone_number);
        this.customer_id = customer_id;
    }

    public int getCustomer_id()
    {
        return customer_id;
    }

    public void setCustomer_id(int customer_id)
    {
        this.customer_id = customer_id;
    }

    public int getPhone_number()
    {
        return phone_number;
    }

    public void setPhone_number(int phone_number)
    {
        this.phone_number = phone_number;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail_address()
    {
        return email_address;
    }

    public void setEmail_address(String email_address)
    {
        this.email_address = email_address;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "Customer{" + "customer_id=" + customer_id + ", phone_number=" + phone_number + ", name=" + name + ", email_address=" + email_address + ", password=" + password + '}';
    }

    
    
}
