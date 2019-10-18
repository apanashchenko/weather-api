package com.weatherapi.ui.single;

import org.junit.jupiter.api.Test;


class WeatherBitTests extends SingleTestBase {

    @Test
    void witherWidgetSearchTest() {
        String expectedCity = "Zurich";
        String expectedCoords = "lat: 53.11134, lon: 5.39437";
        String expectedTemperature = "25";
        String expectedDescription = "Few clouds";

        weatherBitWidget.load()
                .searchWeatherByCityName(expectedCity)
                .checkWeather(expectedCity, expectedCoords, expectedTemperature, expectedDescription);
    }
}
