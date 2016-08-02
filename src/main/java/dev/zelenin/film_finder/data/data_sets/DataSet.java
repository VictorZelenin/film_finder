package dev.zelenin.film_finder.data.data_sets;

/**
 * Created by victor on 01.08.16.
 */
public abstract class DataSet {
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
