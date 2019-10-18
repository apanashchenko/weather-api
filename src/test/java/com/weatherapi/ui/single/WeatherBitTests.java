package com.weatherapi.ui.single;

import org.junit.jupiter.api.Test;


public class WeatherBitTests extends SingleTestBase {

    @Test
    public void witherWidgetSearchTest() {
        String expectedCity = "Zurich";
        String expectedCoords = "lat: 53.11134, lon: 5.39437";
        String expectedTemperature = "25";
        String expectedDescription = "Few clouds";

        weatherBitWidget.load()
                .searchWeatherByCityName(expectedCity)
                .checkWeather(expectedCity, expectedCoords, expectedTemperature, expectedDescription);
    }
}
