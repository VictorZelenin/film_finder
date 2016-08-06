package dev.zelenin.film_finder.data.dao.dao_factory;

import dev.zelenin.film_finder.data.dao.dao_interfaces.*;


/**
 * Created by victor on 02.08.16.
 */
public interface IDAOFactory {

    IActingPersonDAO getActingPersonDAO();

    IActingPersonMarkDAO getActingPersonMarkDAO();

    IAdminDAO getAdminDAO();

    IClientDAO getClientDAO();

    IMessageDAO getMessageDAO();

    IMovieDAO getMovieDAO();

    IMovieMarkDAO getMovieMarkDAO();
}
