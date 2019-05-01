package com.data;

import com.enumerations.DBURL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The purpose of Connector is to establish connection to the database.
 *
 * @author kasper & Niels, Martin Bøgh
 */
class Connector
{

    private static final String URL_PRODUCTION = "jdbc:mysql://localhost:3306/fogcarport?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";
    private static final String URL_TEST = "jdbc:mysql://localhost:3306/fogcarport_TEST?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";
    private static String URL;
    private static final String USERNAME = "testuser";
    private static final String PASSWORD = "password123";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static Connection singleton;

    /**
     * To be deleted????? Martin Bøgh
     *
     * @param con
     */
    static void setConnection(Connection con)
    {
        singleton = con;
    }

    /**
     * Singleton. Only make connection if reference singleton object is null or
     * reopen existing connection.
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    static Connection connection(DBURL dbURL) throws ClassNotFoundException, SQLException
    {
        switch (dbURL)
        {
            case TEST:
                URL = URL_PRODUCTION;
                break;
            case PRODUCTION:
                URL = URL_TEST;
                break;
        }
        if (singleton == null || singleton.isClosed())
        {
            Class.forName(DRIVER);

            singleton = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return singleton;
    }

}
