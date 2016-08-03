package dev.zelenin.film_finder.data.data_sets.marks;

import dev.zelenin.film_finder.data.data_sets.acting_person.ActingPerson;
import dev.zelenin.film_finder.data.data_sets.users.Client;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by victor on 01.08.16.
 */
// TODO rewrite hashcode and equals
public class ActingPersonMark extends Mark implements Serializable {
    private ActingPerson actingPerson;
    private Client client;

    public ActingPersonMark() {
    }

    public ActingPersonMark(long id, int mark, Date date, String description, ActingPerson actingPerson,
                            Client client) {
        super(mark, date, description);
        this.id = id;
        this.actingPerson = actingPerson;
        this.client = client;
    }

    public ActingPerson getActingPerson() {
        return actingPerson;
    }

    public void setActingPerson(ActingPerson actingPerson) {
        this.actingPerson = actingPerson;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActingPersonMark that = (ActingPersonMark) o;

        if (id != that.id) return false;
        return actingPerson != null ? actingPerson.equals(that.actingPerson) : that.actingPerson == null;

    }

    @Override
    public int hashCode() {
        long result = id;
        result = 31 * result + (actingPerson != null ? actingPerson.hashCode() : 0);
        return (int) result;
    }

    @Override
    public String toString() {
        return "ActingPersonMark{" +
                "actingPersonMarkId=" + id +
                ", actingPerson=" + actingPerson +
                " value=" + mark +
                '}';
    }
}
