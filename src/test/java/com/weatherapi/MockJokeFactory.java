package com.weatherapi;

import com.weatherapi.model.Joke;
import com.weatherapi.model.JokeValue;
import com.weatherapi.model.WeatherResponse;

import java.util.Arrays;

/**
 * Created by alpa on 2019-08-26
 */
public class MockJokeFactory {


    public static Joke getJokeResponse() {
        Joke joke = new Joke();
        joke.setType("success");

        JokeValue value = new JokeValue();
        value.setId(1);
        value.setCategories(Arrays.asList("hoho", "blabla"));
        value.setJoke("Best joke of the World!");

        joke.setValue(value);
        return joke;
    }



}
