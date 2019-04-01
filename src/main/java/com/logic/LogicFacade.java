/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logic;

import com.data.DataMapper;
import com.enteties.dto.User;
import com.exceptions.LoginException;
import java.sql.SQLException;

/**
 *
 * @author Martin
 */
public class LogicFacade {

    static DataMapper DataCtrl = new DataMapper();

    public static User login(String email, String password) throws LoginException {
        try {
            return DataCtrl.getUser(email, password);
        } catch (SQLException ex) {
            throw new LoginException("User not found");
        }
    }

    public static User createUser(String email, String password) throws LoginException {
        try {
            User user = new User(email, password, "0");
            DataCtrl.createUser(user);
            return user;
        } catch (SQLException ex) {
            throw new LoginException(ex.getMessage());
        }
    }

}
