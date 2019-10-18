package com.weatherapi.ui.single;

import org.junit.jupiter.api.Test;


class WeatherStackTests extends SingleTestBase {

    @Test
    void weatherStackWidgetSearchTest() {
        String expectedCity = "New York";
        String expectedCoords = "lat: 40.714, lon: -74.006";
        String expectedTemperature = "17.0";
        String expectedDescription = "Sunny";

        weatherStackWidget
                .load()
                .searchWeatherByCityName(expectedCity)
                .checkWeather(expectedCity, expectedCoords, expectedTemperature, expectedDescription);
    }
}
