package com.entities.dto;

import java.util.Objects;

/**
 *
 * @author Niels, Martin Bøgh
 */
public class Employee extends User
{

    private int employee_id;
    private String rank, name, email_address, password, phone_number;;

    public Employee(int employee_id, String name, String phone_number, String email_address, String password, String rank)
    {
        super(name, email_address, password, phone_number);
        this.employee_id = employee_id;
        this.rank = rank;
    }

    public Employee(String name, String phone_number, String email_address, String password, String rank)
    {
        super(name, email_address, password, phone_number);
        this.rank = rank;
    }

    public int getEmployee_id()
    {
        return employee_id;
    }

    public void setEmployee_id(int employee_id)
    {
        this.employee_id = employee_id;
    }

    public String getRank()
    {
        return rank;
    }

    public void setRank(String rank)
    {
        this.rank = rank;
    }

    @Override
    public String toString()
    {
        return "Employee{" + "employee_id=" + employee_id + ", rank=" + rank + ", name=" + name + ", email_address=" + email_address + ", password=" + password + ", phone_number=" + phone_number + '}';
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 59 * hash + this.employee_id;
        hash = 59 * hash + Objects.hashCode(this.rank);
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.email_address);
        hash = 59 * hash + Objects.hashCode(this.password);
        hash = 59 * hash + Objects.hashCode(this.phone_number);
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
        final Employee other = (Employee) obj;
        if (this.employee_id != other.employee_id)
        {
            return false;
        }
        if (!Objects.equals(this.rank, other.rank))
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
        if (!Objects.equals(this.phone_number, other.phone_number))
        {
            return false;
        }
        return true;
    }


}
