package dev.zelenin.film_finder.data.data_sets.users;

import dev.zelenin.film_finder.data.data_sets.users.util.Gender;

import java.io.Serializable;

/**
 * Created by victor on 01.08.16.
 */
public class Client extends User implements Serializable {
    private static int FIRST_NAME_INDEX = 0;
    private static int LAST_NAME_INDEX = 1;

    private String clientPhoto;
    private Gender gender;

    public Client() {
    }

    public Client(int clientId, String name, Gender gender, String email, String password) {
        super(name, email, password);
        this.id = clientId;
        this.gender = gender;
    }

    public Client(String name, Gender gender, String email, String password, String clientPhoto) {
        super(name, email, password);
        this.clientPhoto = clientPhoto;
        this.gender = gender;
    }

    public Client(long id, String name, Gender gender, String email, String password, String clientPhoto) {
        super(name, email, password);
        super.setId(id);
        this.clientPhoto = clientPhoto;
        this.gender = gender;
    }

    public String getClientPhoto() {
        return clientPhoto;
    }

    public void setClientPhoto(String clientPhoto) {
        this.clientPhoto = clientPhoto;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return splitName()[FIRST_NAME_INDEX];
    }

    public String getLastName() {
        return splitName()[LAST_NAME_INDEX];
    }

    private String[] splitName() {
        return name.split(" ");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return clientPhoto != null ? clientPhoto.equals(client.clientPhoto) : client.clientPhoto == null;

    }

    @Override
    public int hashCode() {
        return clientPhoto != null ? clientPhoto.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Client: " +
                "clientId=" + id +
                " name= " + name +
                " email= " + email;
    }
}
