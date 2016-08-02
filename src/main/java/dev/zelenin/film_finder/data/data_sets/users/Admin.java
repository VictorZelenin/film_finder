package dev.zelenin.film_finder.data.data_sets.users;

import dev.zelenin.film_finder.data.data_sets.Message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// id-field should be unique value, provides by database and final field

/**
 * Created by victor on 01.08.16.
 */
// TODO rewrite hashcode and equals
public class Admin extends User implements Serializable {
    private List<Message> feedbackMessages; // all admins gets a all messages

    public Admin() {
        feedbackMessages = new ArrayList<>();
    }

    public Admin(int adminId, String name, String email, String password) {
        super(name, email, password);
        this.id = adminId;
        feedbackMessages = new ArrayList<>();
    }

    public Admin(int adminId, String name, String email, String password, List<Message> feedbackMessages) {
        super(name, email, password);
        this.id = adminId;
        this.feedbackMessages = feedbackMessages;
    }

    public List<Message> getFeedbackMessages() {
        return feedbackMessages;
    }

    public void setFeedbackMessages(List<Message> feedbackMessages) {
        this.feedbackMessages = feedbackMessages;
    }

    public void addMessage(Message message) {
        if (message == null) {
            System.err.println("Message is a null obj");
        } else {
            feedbackMessages.add(message);
        }
    }

    public boolean removeMessage(Message message) {
        return feedbackMessages.remove(message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin admin = (Admin) o;

        if (id != admin.id) return false;
        return feedbackMessages != null ?
                feedbackMessages.equals(admin.feedbackMessages) : admin.feedbackMessages == null;

    }

    @Override
    public int hashCode() {
        int result = (int)id;
        result = 31 * result + (feedbackMessages != null ? feedbackMessages.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + id +
                "name: " + name +
                " email: " + email;
    }
}
