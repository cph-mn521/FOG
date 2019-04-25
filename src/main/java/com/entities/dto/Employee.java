/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities.dto;

/**
 *
 * @author Niels, Martin Bøgh
 */
public class Employee extends User {

    private int employee_id;
    private String rank;

    public Employee(int employee_id, String name, int phone_number, String email_address, String password, String rank) {
        super(name, email_address, password, phone_number);
        this.employee_id = employee_id;
        this.rank = rank;
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
    public String toString()
    {
        return "Employee{" + "employee_id=" + employee_id + ", rank=" + rank + '}';
    }

    
}
