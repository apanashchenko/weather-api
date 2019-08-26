package com.weatherapi.ui.single;

import com.codeborne.selenide.Condition;
import com.weatherapi.pages.ApixuWidget;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


@Slf4j
public class ApixuTests extends SingleTestBase {

    private ApixuWidget apixuWidget = new ApixuWidget();

    @Test
    public void apixuWidgetSearchTest() {
        String expectedCity = "New York";
        String expectedCoords = "lat: 40.71, lon: -74.01";
        String expectedTemperature = "17.2";
        String expectedDescription = "Partly cloudy";

        apixuWidget.load().searchWeather(expectedCity);

        log.info("city: {}",  apixuWidget.getCity().text());
        apixuWidget.getCity().shouldHave(Condition.text(expectedCity));

        log.info("coordinates: {}",  apixuWidget.getCoordinates().text());
        apixuWidget.getCoordinates().shouldHave(Condition.text(expectedCoords));

        log.info("temperature: {}",  apixuWidget.getTemperature().text());
        apixuWidget.getTemperature().shouldHave(Condition.text(expectedTemperature));

        log.info("description: {}",  apixuWidget.getDescription().text());
        apixuWidget.getDescription().shouldHave(Condition.text(expectedDescription));
    }
}
