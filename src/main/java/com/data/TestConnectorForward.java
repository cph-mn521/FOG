package com.data;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Forward connection to testclasses
 * 
 * @author Martin Bøgh
 */
public class TestConnectorForward
{
    public Connection forwardConnection() throws ClassNotFoundException, SQLException
    {
        return TestConnector.connection();
    }
}
