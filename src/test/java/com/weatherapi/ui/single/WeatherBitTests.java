package com.weatherapi.ui.single;

import com.weatherapi.ui.pages.WeatherBitWidget;
import org.junit.jupiter.api.Test;


public class WeatherBitTests extends SingleTestBase {

    private WeatherBitWidget weatherBitWidget = new WeatherBitWidget();

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
