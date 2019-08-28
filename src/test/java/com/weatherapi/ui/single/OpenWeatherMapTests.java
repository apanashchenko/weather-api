package com.weatherapi.ui.single;

import com.weatherapi.pages.OpenWeatherWidget;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


@Slf4j
public class OpenWeatherMapTests extends SingleTestBase {

    private OpenWeatherWidget openWeatherWidget = new OpenWeatherWidget();

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
