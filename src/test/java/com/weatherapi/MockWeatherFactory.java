package com.weatherapi;

import com.weatherapi.model.WeatherResponse;

/**
 * Created by alpa on 2019-08-26
 */
public class MockWeatherFactory {


    public static WeatherResponse getOpenWeatherResponse() {
        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setCity("OWP City");
        weatherResponse.setDescription("OWP cool");
        weatherResponse.setTemp(-15.5);
        weatherResponse.setLat(55.11);
        weatherResponse.setLon(43.52);
        return weatherResponse;
    }

    public static WeatherResponse getWeatherBitResponse() {
        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setCity("WB City");
        weatherResponse.setDescription("WB greate");
        weatherResponse.setTemp(15.2);
        weatherResponse.setLat(15.21);
        weatherResponse.setLon(33.25);
        return weatherResponse;
    }

    public static WeatherResponse getWeatherStackResponse() {
        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setCity("Apixu City");
        weatherResponse.setDescription("Apixu cool");
        weatherResponse.setTemp(31.1);
        weatherResponse.setLat(11.21);
        weatherResponse.setLon(22.22);
        return weatherResponse;
    }


}
