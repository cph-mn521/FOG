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
public class User {

    public final String email, password;
    public final String role = "Customer";

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
