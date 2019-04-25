/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities.dto;

/**
 *
 * @author nille
 */
public class Employee extends User {

    private int employee_id, phone_number;
    private String rank;

    public Employee(int employee_id, int phone_number, String email, String password, String rank) {
        super(email, password);
        this.employee_id = employee_id;
        this.phone_number = phone_number;
        this.rank = rank;

    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

}
