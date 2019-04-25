/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities.dto;

import java.util.Objects;

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
        this.phone_number = phone_number;
        this.name = name;
        this.email_address = email_address;
        this.password = password;
    }
   

    public int getCustomer_id()
    {
        return customer_id;
    }

    public void setCustomer_id(int customer_id)
    {
        this.customer_id = customer_id;
    }


    @Override
    public String toString()
    {
        return "Customer{" + "customer_id=" + customer_id + ", phone_number=" + phone_number + ", name=" + name + ", email_address=" + email_address + ", password=" + password + '}';
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Customer other = (Customer) obj;
        if (this.customer_id != other.customer_id)
        {
            return false;
        }
        if (this.phone_number != other.phone_number)
        {
            return false;
        }
        if (!Objects.equals(this.name, other.name))
        {
            return false;
        }
        if (!Objects.equals(this.email_address, other.email_address))
        {
            return false;
        }
        if (!Objects.equals(this.password, other.password))
        {
            return false;
        }
        return true;
    }

    
    
}
