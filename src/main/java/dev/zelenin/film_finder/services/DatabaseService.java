package dev.zelenin.film_finder.services;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by victor on 29.08.16.
 */
public abstract class DatabaseService {
    /* common functionality */

//    protected <T>

    protected static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
