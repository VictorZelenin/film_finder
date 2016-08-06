package dev.zelenin.film_finder.data.dao.dao_impl;

import dev.zelenin.film_finder.data.dao.dao_interfaces.IAdminDAO;
import dev.zelenin.film_finder.data.data_sets.users.Admin;
import dev.zelenin.film_finder.data.database.executor.Executor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 04.08.16.
 */
//TODO logging here
public class AdminDAO extends DAO<Admin> implements IAdminDAO {
    public AdminDAO(Connection connection) {
        super(connection);
    }

    @Override
    public int save(Admin object) {
        String saveQuery = "insert into admins(name, email, password) values(?, ?, ?)";

        try {
            return putAdmin(object, saveQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public Admin get(long id) {
        String getQuery = "select * from admins where id = " + id;

        try {
            return Executor.executeQuery(connection, getQuery, resultSet -> {
                resultSet.next();

                return new Admin(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4));
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Admin> getAll() {
        String getAllQuery = "select * from admins";
        List<Admin> admins = new ArrayList<>();

        try {
            Executor.executeQuery(connection, getAllQuery, resultSet -> {
                while (resultSet.next()) {
                    admins.add(new Admin(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                            resultSet.getString(4)));
                }

                return admins;
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admins;
    }

    // TODO check updating id
    @Override
    public int update(long id, Admin newObject) {
        String updateQuery = "update admins " +
                "set " +
                "name = ?, " +
                "email = ?, " +
                "password = ? " +
                "where id = " + id;

        try {
            return putAdmin(newObject, updateQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    private int putAdmin(Admin object, String query) throws SQLException {
        int updated;

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, object.getName());
        statement.setString(2, object.getEmail());
        statement.setString(3, object.getPassword());

        statement.execute();
        updated = statement.getUpdateCount();

        statement.close();
        return updated;
    }

    @Override
    public int remove(Admin object) {
        if (object == null) {
            throw new RuntimeException();
        }
        String removeQuery = "delete from admins where id = " + object.getId();
        int updated = 0;

        try {
            updated = Executor.executeUpdate(connection, removeQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    @Override
    public int removeAll() {
        String deleteAllQuery = "delete from admins";
        int updated = 0;

        try {
            updated = Executor.executeUpdate(connection, deleteAllQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }
}
