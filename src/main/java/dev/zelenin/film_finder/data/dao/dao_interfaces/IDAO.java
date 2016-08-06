package dev.zelenin.film_finder.data.dao.dao_interfaces;

import dev.zelenin.film_finder.data.data_sets.DataSet;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by victor on 01.08.16.
 */
public interface IDAO<T extends DataSet> {

    int save(T object) throws SQLException;

    T get(long id);

    List<T> getAll();

    int update(long id, T newObject);

    int remove(T object);

    int removeAll();

    boolean exists(T obj);
}
