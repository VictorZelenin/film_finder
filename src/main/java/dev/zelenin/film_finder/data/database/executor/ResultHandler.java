package dev.zelenin.film_finder.data.database.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by victor on 03.08.16.
 */
@FunctionalInterface
public interface ResultHandler<T> {
    T handle(ResultSet resultSet) throws SQLException;
}
