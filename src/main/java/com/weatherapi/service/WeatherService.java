package com.weatherapi.service;

import com.weatherapi.model.CityCoordinate;
import com.weatherapi.model.WeatherResponse;

public interface WeatherService {

    WeatherResponse getWeatherByCityName(String city);

    WeatherResponse getWeatherByGeographicCoordinates(CityCoordinate cityCoordinate);

}

