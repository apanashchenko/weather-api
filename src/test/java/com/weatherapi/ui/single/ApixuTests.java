package com.weatherapi.ui.single;

import com.weatherapi.pages.ApixuWidget;
import org.junit.jupiter.api.Test;


public class ApixuTests extends SingleTestBase {

    private ApixuWidget apixuWidget = new ApixuWidget();

    @Test
    public void apixuWidgetSearchTest() {
        String expectedCity = "New York";
        String expectedCoords = "lat: 40.71, lon: -74.01";
        String expectedTemperature = "17.2";
        String expectedDescription = "Partly cloudy";

        apixuWidget
                .load()
                .searchWeatherByCityName(expectedCity)
                .checkWeather(expectedCity, expectedCoords, expectedTemperature, expectedDescription);
    }
}
