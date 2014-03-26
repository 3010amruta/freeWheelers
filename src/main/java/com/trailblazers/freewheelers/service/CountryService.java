package com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.model.Country;

import java.util.List;

/**
 * Created by abhishep on 3/25/14.
 */
public interface CountryService {


    List<Country> findAll();

    Country get(long country_id);
}
