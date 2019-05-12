package com.entities.dto;

import java.util.Objects;

/**
 *
 * @author Martin Bøgh
 */
public class Customer extends User {

    private int customer_id;
    private String name, email, password, phone_number;

    public Customer(int customer_id, String name, String email, String password, String phone_number) {
        super(name, email, password, phone_number);
        this.customer_id = customer_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
        
    }

    public Customer(String name, String email, String password, String phone_number) {
        super(name, email, password, phone_number);
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
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
    public String getName()
    {
        return name;
    }

    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String getEmail()
    {
        return email;
    }

    @Override
    public void setEmail(String email)
    {
        this.email = email;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String getPhone_number()
    {
        return phone_number;
    }

    @Override
    public void setPhone_number(String phone_number)
    {
        this.phone_number = phone_number;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 71 * hash + this.customer_id;
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.email);
        hash = 71 * hash + Objects.hashCode(this.password);
        hash = 71 * hash + Objects.hashCode(this.phone_number);
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
        if (!Objects.equals(this.name, other.name))
        {
            return false;
        }
        if (!Objects.equals(this.email, other.email))
        {
            return false;
        }
        if (!Objects.equals(this.password, other.password))
        {
            return false;
        }
        if (!Objects.equals(this.phone_number, other.phone_number))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Customer{" + "customer_id=" + customer_id + ", name=" + name + ", email=" + email + ", password=" + password + ", phone_number=" + phone_number + '}';
    }

    

}
