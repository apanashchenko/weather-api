package com.weatherapi.model;


import lombok.Data;

@Data
public class WeatherResponse {

    private String city;
    private String description;
    private double temp;
    private double lat;
    private double lon;

}
