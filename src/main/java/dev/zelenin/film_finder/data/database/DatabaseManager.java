package dev.zelenin.film_finder.data.database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by victor on 02.08.16.
 */
public class DatabaseManager {

    public static Connection getConnection() {
        Context initialContext;
        DataSource dataSource = null;
        try {
            initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/film_finder_db");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        // TODO create property file for DB

        try {
            assert dataSource != null;
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Connection getDebugConnection() {
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/film_finder_db" +
                "?useUnicode=true&characterEncoding=UTF8";
        final String USERNAME = "root";
        final String PASSWORD = "root";


        try {
            return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
