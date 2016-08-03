package dev.zelenin.film_finder.data.data_sets;

import dev.zelenin.film_finder.data.data_sets.users.Client;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by victor on 01.08.16.
 */
public class Message extends DataSet implements Serializable {
    private String value;
    private Date date;
    private Client client;

    public Message() {
    }

    public Message(int messageId, String value) {
        this.id = messageId;
        this.value = value;
    }

    public Message(String value, Date date, Client client) {
        this.value = value;
        this.date = date;
        this.client = client;
    }

    public Message(int messageId, String value, Date date, Client client) {
        this.id = messageId;
        this.value = value;
        this.date = date;
        this.client = client;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

        Message message = (Message) o;

        if (value != null ? !value.equals(message.value) : message.value != null) return false;
        if (date != null ? !date.equals(message.date) : message.date != null) return false;
        if (client != null ? !client.equals(message.client) : message.client != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + id +
                ", value='" + value + '\'' +
                ", date=" + date +
                '}';
    }
}
