package com.data;

import com.enumerations.DBURL;
import com.exceptions.DataException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Forward connection to testclasses
 *
 * @author Martin Bøgh
 */
public class TestConnectorForward
{

    public Connection forwardConnection() throws DataException
    {
        Connection con;
        try
        {
            con = Connector.connection(DBURL.TEST);
            return con;
        } catch (SQLException | ClassNotFoundException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }
}
