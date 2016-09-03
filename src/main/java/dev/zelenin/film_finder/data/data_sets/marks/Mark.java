package dev.zelenin.film_finder.data.data_sets.marks;

import dev.zelenin.film_finder.data.data_sets.DataSet;

import java.util.Date;

/**
 * Created by victor on 01.08.16.
 */
public abstract class Mark extends DataSet {
    protected int mark; // 0...10
    protected Date date;

    protected String description;

    protected Mark() {
    }

    protected Mark(int mark, Date date, String description) {
        this.mark = mark;
        this.date = date;
        this.description = description;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
