package dev.zelenin.film_finder.data.dao.dao_interfaces;

import dev.zelenin.film_finder.data.data_sets.DataSet;

import java.util.List;

/**
 * Created by victor on 01.08.16.
 */
// TODO нужно ли добавить еще методов?
// TODO сохранять объект или ай-ди
public interface DAOInterface<T extends DataSet> {

    int save(T object);

    T get(int id);

    List<T> getAll();

    int update(T oldObject, T newObject);

    int remove(T object);

    int removeAll();
}
