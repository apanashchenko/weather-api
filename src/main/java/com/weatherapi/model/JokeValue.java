package com.weatherapi.model;

import lombok.Data;

import java.util.List;

/**
 * Created by alpa on 2019-08-29
 */
@Data
public class JokeValue {

    private int id;
    private String joke;
    private List<String> categories;

}
