/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.data;

import com.entities.dto.User;
import com.exceptions.DataException;
import java.sql.SQLException;

/**
 *
 * @author Martin
 */
public class DataMapper {
    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////USER ACTIONS//////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    public User getUser(String email, String password) throws SQLException, DataException {
        User user = UserMapper.getUser(email, password);
        return user;
    }

    public void createUser(User user) throws SQLException {
        UserMapper.createUser(user);
    }

    public void updateUser(String Username, User newUser) throws SQLException {
        UserMapper.updateUser(Username, newUser);
    }

}
