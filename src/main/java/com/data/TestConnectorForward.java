package com.data;

import com.enumerations.DBURL;
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
        Connection con = Connector.connection(DBURL.PRODUCTION);
        Connector.setConnection(TestConnector.connection());
        return con;
    }
}
