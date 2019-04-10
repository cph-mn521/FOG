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

    public void updateUser(User user, User newUser) throws SQLException {
        UserMapper.updateUser(user, newUser);
    }

    public void deleteUser(User user) throws SQLException {
        UserMapper.deleteUser(user);
    }

}
