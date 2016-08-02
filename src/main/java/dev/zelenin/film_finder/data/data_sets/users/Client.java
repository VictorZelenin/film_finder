package dev.zelenin.film_finder.data.data_sets.users;

import dev.zelenin.film_finder.data.data_sets.Message;
import dev.zelenin.film_finder.data.data_sets.marks.ActingPersonMark;
import dev.zelenin.film_finder.data.data_sets.marks.Mark;
import dev.zelenin.film_finder.data.data_sets.marks.MovieMark;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 01.08.16.
 */
public class Client extends User implements Serializable {

    private String clientPhoto;
    private Message message; // for feedback, get from messages table

    private List<Movie> chosenMovies;
    private List<MovieMark> markListForMovies;
    private List<ActingPersonMark> markListForActingPerson;

    public Client() {
        chosenMovies = new ArrayList<>();
        markListForMovies = new ArrayList<>();
        markListForActingPerson = new ArrayList<>();
    }

    public Client(int clientId, String name, String email, String password) {
        super(name, email, password);
        this.id = clientId;
        chosenMovies = new ArrayList<>();
        markListForMovies = new ArrayList<>();
        markListForActingPerson = new ArrayList<>();
    }

    public Client(int clientId, List<Movie> chosenMovies, String clientPhoto, Message message) {
        this.id = clientId;
        this.chosenMovies = chosenMovies;
        this.clientPhoto = clientPhoto;
        this.message = message;
        markListForMovies = new ArrayList<>();
        markListForActingPerson = new ArrayList<>();
    }

    public Client(int clientId, List<Movie> chosenMovies, List<MovieMark> markList,
                  List<ActingPersonMark> mark, String clientPhoto, Message message) {
        this.id = clientId;
        this.chosenMovies = chosenMovies;
        this.markListForMovies = markList;
        this.markListForActingPerson = mark;
        this.clientPhoto = clientPhoto;
        this.message = message;
    }

    public List<Movie> getChosenMovies() {
        return chosenMovies;
    }

    public void setChosenMovies(List<Movie> chosenMovies) {
        this.chosenMovies = chosenMovies;
    }

    public String getClientPhoto() {
        return clientPhoto;
    }

    public void setClientPhoto(String clientPhoto) {
        this.clientPhoto = clientPhoto;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public List<MovieMark> getMarkListForMovies() {
        return markListForMovies;
    }

    public void setMarkListForMovies(List<MovieMark> markListForMovies) {
        this.markListForMovies = markListForMovies;
    }

    public List<ActingPersonMark> getMarkListForActingPerson() {
        return markListForActingPerson;
    }

    public void setMarkListForActingPerson(List<ActingPersonMark> markListForActingPerson) {
        this.markListForActingPerson = markListForActingPerson;
    }

    public void addMovie(Movie movie) {
        if (movie == null) {
            System.err.println("Movie shouldn't be a null ");
        } else {
            chosenMovies.add(movie);
        }

    }

    public boolean removeMovie(Movie movie) {
        return chosenMovies.remove(movie);
    }


    public void addMarkForMovie(MovieMark mark) {
        if (mark == null) {
            System.err.println("Mark is null obj");
        } else {
            markListForMovies.add(mark);
        }


    }

    public boolean removeMarkForMovie(Mark mark) {
        return markListForMovies.remove(mark);
    }


    public void addMarkForActingPerson(ActingPersonMark mark) {
        if (mark == null) {
            System.err.println("Mark is null obj");
        } else {
            markListForActingPerson.add(mark);
        }


    }

    public boolean removeMarkForActingPerson(ActingPersonMark mark) {
        return markListForActingPerson.remove(mark);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (id != client.id) return false;
        if (chosenMovies != null ? !chosenMovies.equals(client.chosenMovies) : client.chosenMovies != null)
            return false;
        if (clientPhoto != null ? !clientPhoto.equals(client.clientPhoto) : client.clientPhoto != null)
            return false;

        return message != null ? message.equals(client.message) : client.message == null;

    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (chosenMovies != null ? chosenMovies.hashCode() : 0);
        result = 31 * result + (clientPhoto != null ? clientPhoto.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Client: " +
                "clientId=" + id +
                " name= " + name +
                " email= + " + email;
    }
}
