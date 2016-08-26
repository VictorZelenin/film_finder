package dev.zelenin.film_finder.data.database.executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by victor on 03.08.16.
 */
public class Executor {

    public static int executeUpdate(Connection connection, String query) throws SQLException {
        int updated;
        Statement statement = connection.createStatement();
        statement.execute(query);

        updated = statement.getUpdateCount();
        statement.close();

        return updated;
    }

    public static <T> T executeQuery(Connection connection, String query, ResultHandler<T> handler) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(query);
        ResultSet resultSet = statement.getResultSet();

        T value = handler.handle(resultSet);

        statement.close();

        return value;
    }
}
