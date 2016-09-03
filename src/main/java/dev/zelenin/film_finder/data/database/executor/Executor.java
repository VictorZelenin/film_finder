package dev.zelenin.film_finder.data.database.executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by victor on 03.08.16.
 */
public class Executor {
    public static int executeUpdate(Connection connection, String query) {
        int updated = 0;

        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
            updated = statement.getUpdateCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    public static <T> T executeQuery(Connection connection, String query, ResultHandler<T> handler) {
        T value = null;

        try(Statement statement = connection.createStatement()) {
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();

            value = handler.handle(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return value;
    }
}
