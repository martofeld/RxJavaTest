package com.mfeldsztejn.rxjavatest.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class People {
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("next")
    @Expose
    private String next;
    @SerializedName("previous")
    @Expose
    private String previous;
    @SerializedName("results")
    @Expose
    private List<Person> people = new ArrayList<>();

    /**
     * @return The count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count The count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return The next
     */
    public String getNext() {
        return next;
    }

    /**
     * @param next The next
     */
    public void setNext(String next) {
        this.next = next;
    }

    /**
     * @return The previous
     */
    public String getPrevious() {
        return previous;
    }

    /**
     * @param previous The previous
     */
    public void setPrevious(String previous) {
        this.previous = previous;
    }

    /**
     * @return The results
     */
    public List<Person> getPeople() {
        return people;
    }

    /**
     * @param results The results
     */
    public void setPeople(List<Person> results) {
        this.people = results;
    }

}
