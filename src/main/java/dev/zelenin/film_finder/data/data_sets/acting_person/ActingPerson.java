package dev.zelenin.film_finder.data.data_sets.acting_person;

import dev.zelenin.film_finder.data.data_sets.DataSet;
import dev.zelenin.film_finder.data.data_sets.users.util.Gender;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by victor on 01.08.16.
 */
// TODO rewrite hashcode and equals
// TODO implement in DAO getRoles and genres
public class ActingPerson extends DataSet implements Serializable {
    private String name;
    private Gender gender;
    private double height;
    private String country;
    private int age;
    private int totalMoviesNumber;
    private Date deathDate;
    private String photoURL;

    private double averageClientMark;

    public ActingPerson() {
    }

    public ActingPerson(String name, Gender gender, double height, String country,
                        int age, Date deathDate, int totalMoviesNumber, double averageClientMark,
                        String photoURL) {
        this.name = name;
        this.gender = gender;
        this.height = height;
        this.country = country;
        this.age = age;
        this.deathDate = deathDate;
        this.totalMoviesNumber = totalMoviesNumber;
        this.averageClientMark = averageClientMark;
        this.photoURL = photoURL;
    }

    public ActingPerson(String name, Gender gender, double height, String country,
                        int age, Date deathDate, int totalMoviesNumber,
                        String photoURL) {
        this.name = name;
        this.gender = gender;
        this.height = height;
        this.country = country;
        this.age = age;
        this.deathDate = deathDate;
        this.totalMoviesNumber = totalMoviesNumber;
        this.photoURL = photoURL;
    }

    public ActingPerson(long actingPersonId, String name, Gender gender, double height, String country,
                        int age, Date deathDate, int totalMoviesNumber, double averageClientMark,
                        String photoURL) {
        this.id = actingPersonId;
        this.name = name;
        this.gender = gender;
        this.height = height;
        this.country = country;
        this.age = age;
        this.deathDate = deathDate;
        this.totalMoviesNumber = totalMoviesNumber;
        this.averageClientMark = averageClientMark;
        this.photoURL = photoURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTotalMoviesNumber() {
        return totalMoviesNumber;
    }

    public void setTotalMoviesNumber(int totalMoviesNumber) {
        this.totalMoviesNumber = totalMoviesNumber;
    }

    public double getAverageClientMark() {
        return averageClientMark;
    }

    public void setAverageClientMark(double averageClientMark) {
        this.averageClientMark = averageClientMark;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActingPerson that = (ActingPerson) o;

        if (Double.compare(that.height, height) != 0) return false;
        if (age != that.age) return false;
        if (totalMoviesNumber != that.totalMoviesNumber) return false;
        if (Double.compare(that.averageClientMark, averageClientMark) != 0) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (deathDate != null ? !deathDate.equals(that.deathDate) : that.deathDate != null) return false;
        return photoURL != null ? photoURL.equals(that.photoURL) : that.photoURL == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(height);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + totalMoviesNumber;
        result = 31 * result + (deathDate != null ? deathDate.hashCode() : 0);
        result = 31 * result + (photoURL != null ? photoURL.hashCode() : 0);
        temp = Double.doubleToLongBits(averageClientMark);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ActingPerson{" +
                "actingPersonId=" + id +
                ", name='" + name + '\'' +
                ", totalMoviesNumber=" + totalMoviesNumber +
                ", averageClientMark=" + averageClientMark +
                '}';
    }
}
