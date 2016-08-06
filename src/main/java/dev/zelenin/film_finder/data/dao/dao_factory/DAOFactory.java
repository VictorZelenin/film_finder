package dev.zelenin.film_finder.data.dao.dao_factory;

import dev.zelenin.film_finder.data.dao.dao_impl.*;
import dev.zelenin.film_finder.data.dao.dao_interfaces.*;

import java.sql.Connection;

/**
 * Created by victor on 06.08.16.
 */
public class DAOFactory implements IDAOFactory {
    private Connection connection;

    public DAOFactory(Connection connection) {
        this.connection = connection;
    }

    @Override
    public IActingPersonDAO getActingPersonDAO() {
        return new ActingPersonDAO(connection);
    }

    @Override
    public IActingPersonMarkDAO getActingPersonMarkDAO() {
        return new ActingPersonMarkDAO(connection);
    }

    @Override
    public IAdminDAO getAdminDAO() {
        return new AdminDAO(connection);
    }

    @Override
    public IClientDAO getClientDAO() {
        return new ClientDAO(connection);
    }

    @Override
    public IMessageDAO getMessageDAO() {
        return new MessageDAO(connection);
    }

    @Override
    public IMovieDAO getMovieDAO() {
        return new MovieDAO(connection);
    }

    @Override
    public IMovieMarkDAO getMovieMarkDAO() {
        return new MovieMarkDAO(connection);
    }
}
