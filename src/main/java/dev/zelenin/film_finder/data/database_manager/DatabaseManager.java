package dev.zelenin.film_finder.data.database_manager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by victor on 02.08.16.
 */
public class DatabaseManager implements PoolConnectionInterface {
    private Context initialContext;
    private DataSource dataSource;

    @Override
    public Connection getConnection() throws SQLException, NamingException {
        initialContext = new InitialContext();
        dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/film_finder_db");

        return dataSource.getConnection();
    }
}
