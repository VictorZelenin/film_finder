package dev.zelenin.film_finder.data.dao.dao_factory;

import dev.zelenin.film_finder.data.dao.dao_interfaces.UserDAO;

/**
 * Created by victor on 02.08.16.
 */
public interface DAOFactoryInterface {
    UserDAO getUserDAO();

    // others DAO
}
