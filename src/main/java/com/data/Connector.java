package com.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The purpose of Connector is to establish connection to the database.
 *
 * @author kasper & Niels
 */
class Connector {

    private static final String URL = "jdbc:mysql://207.154.240.48:3306/useradmin";
    private static final String USERNAME = "guest";
    private static final String PASSWORD = "1234";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static Connection singleton;

    /**
     * To be deleted????? Martin Bøgh
     *
     * @param con
     */
    static void setConnection(Connection con) {
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
    static Connection connection() throws ClassNotFoundException, SQLException {
        if (singleton == null || singleton.isClosed()) {
            Class.forName(DRIVER);

            singleton = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return singleton;
    }

}
