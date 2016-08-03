package dev.zelenin.film_finder.data.database;

import java.sql.Connection;

/**
 * Created by victor on 02.08.16.
 */
public interface PoolConnectionInterface {
    Connection getConnection() throws Exception;
}
