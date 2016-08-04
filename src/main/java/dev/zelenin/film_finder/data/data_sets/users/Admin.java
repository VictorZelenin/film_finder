package dev.zelenin.film_finder.data.data_sets.users;

import java.io.Serializable;

// id-field should be unique value, provides by database and final field

/**
 * Created by victor on 01.08.16.
 */
// TODO rewrite hashcode and equals
public class Admin extends User implements Serializable {

    public Admin() {

    }

    public Admin(String name, String email, String password) {
        super(name, email, password);
    }

    public Admin(long adminId, String name, String email, String password) {
        super(name, email, password);
        this.id = adminId;

    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + id +
                "name: " + name +
                " email: " + email;
    }
}
