package com.weatherapi.ui.single;

import com.codeborne.selenide.Condition;
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

        openWeatherWidget.load().searchWeather(expectedCity);

        log.info("city: {}",  openWeatherWidget.getCity().text());
        openWeatherWidget.getCity().shouldHave(Condition.text(expectedCity));

        log.info("coordinates: {}",  openWeatherWidget.getCoordinates().text());
        openWeatherWidget.getCoordinates().shouldHave(Condition.text(expectedCoords));

        log.info("temperature: {}",  openWeatherWidget.getTemperature().text());
        openWeatherWidget.getTemperature().shouldHave(Condition.text(expectedTemperature));

        log.info("description: {}",  openWeatherWidget.getDescription().text());
        openWeatherWidget.getDescription().shouldHave(Condition.text(expectedDescription));
    }
}
