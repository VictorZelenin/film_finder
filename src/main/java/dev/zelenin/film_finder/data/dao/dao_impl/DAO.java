package dev.zelenin.film_finder.data.dao.dao_impl;

import dev.zelenin.film_finder.data.dao.dao_interfaces.IDAO;
import dev.zelenin.film_finder.data.data_sets.DataSet;
import dev.zelenin.film_finder.data.database.executor.Executor;

import java.sql.Connection;

/**
 * Created by victor on 02.08.16.
 */
public abstract class DAO<T extends DataSet> implements IDAO<T> {
    protected final Connection connection;

    public DAO(Connection connection) {
        this.connection = connection;
    }

    public boolean exists(T obj) {
        return obj != null && this.find(obj.getId()) != null;
    }

    public int rowsCount(String tableName) {
        String query = "select count(*) from " + tableName;

        return Executor.executeQuery(connection, query, resultSet -> {
            resultSet.next();

            return resultSet.getInt(1);
        });
    }
}
