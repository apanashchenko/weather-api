package com.weatherapi.ui.single;

import com.weatherapi.ui.pages.WeatherStackWidget;
import org.junit.jupiter.api.Test;


public class WeatherStackTests extends SingleTestBase {

    private WeatherStackWidget weatherStackWidget = new WeatherStackWidget();

    @Test
    public void weatherStackWidgetSearchTest() {
        String expectedCity = "New York";
        String expectedCoords = "lat: 40.71, lon: -74.01";
        String expectedTemperature = "17.2";
        String expectedDescription = "Partly cloudy";

        weatherStackWidget
                .load()
                .searchWeatherByCityName(expectedCity)
                .checkWeather(expectedCity, expectedCoords, expectedTemperature, expectedDescription);
    }
}
