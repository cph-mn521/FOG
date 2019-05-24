package com.entities.dto;

import java.util.Objects;

/**
 *
 * @author Niels, Martin Bøgh
 */
public class Employee extends User {

    int employee_id;
    String rank, name, email, password, phone_number;

    ;

    public Employee(int employee_id, String name, String phone_number, String email, String password, String rank) {
        super(name, email, password, phone_number);
        this.employee_id = employee_id;
        this.rank = rank;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
    }

    public Employee(String name, String phone_number, String email, String password, String rank) {
        super(name, email, password, phone_number);
        this.rank = rank;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPhone_number() {
        return phone_number;
    }

    @Override
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.employee_id;
        hash = 79 * hash + Objects.hashCode(this.rank);
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.email);
        hash = 79 * hash + Objects.hashCode(this.password);
        hash = 79 * hash + Objects.hashCode(this.phone_number);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (this.employee_id != other.employee_id) {
            return false;
        }
        if (!Objects.equals(this.rank, other.rank)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.phone_number, other.phone_number)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{\"employee_id\":" + employee_id + ", \"rank\":\"" + rank + "\", \"name\":\"" + name + "\", \"email\":\"" + email + "\", \"password\":\"" + password + "\", \"phone_number\":" + phone_number + '}';
    }

}
