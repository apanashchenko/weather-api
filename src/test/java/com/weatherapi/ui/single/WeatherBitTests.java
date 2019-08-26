package com.weatherapi.ui.single;

import com.codeborne.selenide.Condition;
import com.weatherapi.pages.WeatherBitWidget;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


@Slf4j
public class WeatherBitTests extends SingleTestBase {

    private WeatherBitWidget weatherBitWidget = new WeatherBitWidget();

    @Test
    public void witherWidgetSearchTest() {
        String expectedCity = "Zurich";
        String expectedCoords = "lat: 53.11134, lon: 5.39437";
        String expectedTemperature = "25";
        String expectedDescription = "Few clouds";

        weatherBitWidget.load().searchWeather(expectedCity);

        log.info("city: {}",  weatherBitWidget.getCity().text());
        weatherBitWidget.getCity().shouldHave(Condition.text(expectedCity));

        log.info("coordinates: {}",  weatherBitWidget.getCoordinates().text());
        weatherBitWidget.getCoordinates().shouldHave(Condition.text(expectedCoords));

        log.info("temperature: {}",  weatherBitWidget.getTemperature().text());
        weatherBitWidget.getTemperature().shouldHave(Condition.text(expectedTemperature));

        log.info("description: {}",  weatherBitWidget.getDescription().text());
        weatherBitWidget.getDescription().shouldHave(Condition.text(expectedDescription));
    }
}
