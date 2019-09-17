package com.weatherapi.utils;

import com.weatherapi.model.WeatherResponse;
import com.weatherapi.model.apixu.WeatherStackResponse;
import com.weatherapi.model.apixu.Current;
import com.weatherapi.model.apixu.Location;
import com.weatherapi.model.owm.Coord;
import com.weatherapi.model.owm.Main;
import com.weatherapi.model.owm.OpenWeatherMapResponse;
import com.weatherapi.model.owm.Weather;
import com.weatherapi.model.wb.Datum;
import com.weatherapi.model.wb.WeatherBitResponse;

public class ResponseConverter {


    public static WeatherResponse convertResponse(WeatherStackResponse weatherStackResponse) {
        WeatherResponse weatherResponse = new WeatherResponse();
        Location location = weatherStackResponse.getLocation();
        weatherResponse.setCity(location.getName());
        weatherResponse.setLat(location.getLat());
        weatherResponse.setLon(location.getLon());

        Current current = weatherStackResponse.getCurrent();
        weatherResponse.setDescription(current.getWeatherDescriptions().toString());
        weatherResponse.setTemp(current.getTemperature());

        return weatherResponse;
    }

    public static WeatherResponse convertResponse(OpenWeatherMapResponse openWeatherMapResponse) {
        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setCity(openWeatherMapResponse.getName());

        Weather weather = openWeatherMapResponse.getWeather().get(0);
        weatherResponse.setDescription(weather.getDescription());

        Main main = openWeatherMapResponse.getMain();
        weatherResponse.setTemp(main.getTemp());

        Coord coord = openWeatherMapResponse.getCoord();
        weatherResponse.setLat(coord.getLat());
        weatherResponse.setLon(coord.getLon());

        return weatherResponse;
    }

    public static WeatherResponse convertResponse(WeatherBitResponse weatherBitResponse) {
        WeatherResponse weatherResponse = new WeatherResponse();
        Datum datum = weatherBitResponse.getData().get(0);
        weatherResponse.setCity(datum.getCityName());
        weatherResponse.setLat(datum.getLat());
        weatherResponse.setLon(datum.getLon());

        weatherResponse.setDescription(datum.getWeather().getDescription());
        weatherResponse.setTemp(datum.getTemp());

        return weatherResponse;
    }

}
