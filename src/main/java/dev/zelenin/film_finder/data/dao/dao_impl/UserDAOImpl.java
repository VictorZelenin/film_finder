package dev.zelenin.film_finder.data.dao.dao_impl;

import dev.zelenin.film_finder.data.dao.dao_interfaces.UserDAO;
import dev.zelenin.film_finder.data.data_sets.users.User;

import java.util.List;

/**
 * Created by victor on 02.08.16.
 */
public class UserDAOImpl extends DAO<User> implements UserDAO {
    @Override
    public int save(User object) {
        return 0;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public int update(User oldObject, User newObject) {
        return 0;
    }

    @Override
    public int remove(User object) {
        return 0;
    }

    @Override
    public int removeAll() {
        return 0;
    }
}
