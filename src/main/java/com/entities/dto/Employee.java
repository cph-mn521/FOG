package com.entities.dto;

import java.util.Objects;

/**
 *
 * @author Niels, Martin Bøgh
 */
public class Employee extends User
{

    int employee_id;
    String rank, name, email, password, phone_number;

    ;

    public Employee(int employee_id, String name, String phone_number, String email, String password, String rank)
    {
        super(name, email, password, phone_number);
        this.employee_id = employee_id;
        this.rank = rank;
    }

    public Employee(String name, String phone_number, String email, String password, String rank)
    {
        super(name, email, password, phone_number);
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
        return "Employee{" + "employee_id=" + employee_id + ", rank=" + rank + ", name=" + super.name + ", email=" + super.email + ", password=" + super.password + ", phone_number=" + super.phone_number + '}';
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
        if (!Objects.equals(super.name, other.name))
        {
            return false;
        }
        if (!Objects.equals(super.email, other.email))
        {
            return false;
        }
        if (!Objects.equals(super.password, other.password))
        {
            return false;
        }
        if (!Objects.equals(super.phone_number, other.phone_number))
        {
            return false;
        }
        return true;
    }

}
