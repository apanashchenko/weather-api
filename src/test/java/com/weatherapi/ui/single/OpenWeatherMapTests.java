package com.weatherapi.ui.single;

import org.junit.jupiter.api.Test;


public class OpenWeatherMapTests extends SingleTestBase {

    @Test
    public void openWidgetMapSearchTest() {
        String expectedCity = "Kiev";
        String expectedCoords = "lat: 50.43, lon: 30.52";
        String expectedTemperature = "25";
        String expectedDescription = "clear sky";

        openWeatherWidget.load()
                .searchWeatherByCityName(expectedCity)
                .checkWeather(expectedCity, expectedCoords, expectedTemperature, expectedDescription);
    }
}
