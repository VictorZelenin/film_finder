package dev.zelenin.film_finder.data.dao.dao_impl;

import dev.zelenin.film_finder.data.dao.dao_interfaces.IDAO;
import dev.zelenin.film_finder.data.data_sets.DataSet;

import java.sql.Connection;

/**
 * Created by victor on 02.08.16.
 */
public abstract class DAO<T extends DataSet> implements IDAO<T> {
    protected final Connection connection;

    public DAO(Connection connection) {
        this.connection = connection;
    }

    /*
    * общий функционал для всех дао + возможная реализация методов интерфейса
    *
    * */
}
