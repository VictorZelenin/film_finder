package dev.zelenin.film_finder.data.data_sets;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by victor on 01.08.16.
 */
public class Message extends DataSet implements Serializable {
    private String value;
    private Date date;

    public Message(){
    }

    public Message(int messageId, String value) {
        this.id = messageId;
        this.value = value;
    }

    public Message(int messageId, String value, Date date) {
        this.id = messageId;
        this.value = value;
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (id != message.id) return false;
        if (value != null ? !value.equals(message.value) : message.value != null) return false;
        return date != null ? date.equals(message.date) : message.date == null;

    }

    @Override
    public int hashCode() {
        int result = (int)id;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
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
