package com.data;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Forward connection to testclasses
 * 
 * @author Martin Bøgh
 */
public class TestConnector
{
    public Connection forwardConnection() throws ClassNotFoundException, SQLException
    {
        return Connector.connection();
    }
}
