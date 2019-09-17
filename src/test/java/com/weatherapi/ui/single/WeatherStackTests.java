package com.weatherapi.ui.single;

import com.weatherapi.ui.pages.WeatherStackWidget;
import org.junit.jupiter.api.Test;


public class WeatherStackTests extends SingleTestBase {

    private WeatherStackWidget weatherStackWidget = new WeatherStackWidget();

    @Test
    public void weatherStackWidgetSearchTest() {
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
