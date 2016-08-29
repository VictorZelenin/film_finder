package dev.zelenin.film_finder.data.dao.dao_interfaces;

import dev.zelenin.film_finder.data.data_sets.DataSet;

import java.util.List;

/**
 * Created by victor on 01.08.16.
 */
public interface IDAO<T extends DataSet> {

    int save(T object);

    T find(long id);

    List<T> findAll();

    int update(long id, T newObject);

    int remove(T object);

    int removeAll();

    boolean exists(T obj);

    int rowsCount();
}
