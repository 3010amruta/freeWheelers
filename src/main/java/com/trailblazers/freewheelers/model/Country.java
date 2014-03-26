package com.trailblazers.freewheelers.model;

/**
 * Created by abhishep on 3/25/14.
 */
public class Country {

    private String country_name;

    public Country() {

        this.country_id = 0L;
    }

    private long country_id;


    public Country(String name) {
        this.country_id = 0L;
        this.country_name=name;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public long getCountry_id() {
        return country_id;
    }

    public void setCountry_id(long country_id) {
        this.country_id = country_id;
    }
}
