package com.weatherapi.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.weatherapi.model.WeatherResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by alpa on 2019-08-26
 */
@Slf4j
public abstract class BaseWidget<T extends BaseWidget> {

    private static final long UI_TIMEOUT = 5000L;
    private SelenideElement widget;
    private SelenideElement searchFrom;

    public BaseWidget(SelenideElement widget, SelenideElement searchFrom) {
        this.widget = widget;
        this.searchFrom = searchFrom;
    }

    public T load() {
        getCity().waitUntil(Condition.not(Condition.empty), UI_TIMEOUT);
        return (T) this;
    }

    public T load(String city) {
        getCity().waitUntil(Condition.text(city), UI_TIMEOUT);
        return (T) this;
    }

    public T checkWeather(String expectedCity, String expectedCoords,
                                    String expectedTemperature, String expectedDescription) {
        T clazz = (T) this;
        log.info("Start check weather for: {}", clazz);
        getCity().waitUntil(Condition.text(expectedCity), UI_TIMEOUT);
        log.info("city: {}", getCity().text());
        getCity().shouldHave(Condition.text(expectedCity));

        log.info("coordinates: {}",  getCoordinates().text());
        getCoordinates().shouldHave(Condition.text(expectedCoords));

        log.info("temperature: {}",  getTemperature().text());
        getTemperature().shouldHave(Condition.text(expectedTemperature));

        log.info("description: {}",  getDescription().text());
        getDescription().shouldHave(Condition.text(expectedDescription));
        log.info("Finish check weather for: {}", clazz);
        return clazz;
    }

    public T checkWeather(WeatherResponse weatherResponse) {
        return checkWeather(weatherResponse.getCity(), getCoordinates(weatherResponse),
                String.valueOf(weatherResponse.getTemp()), weatherResponse.getDescription());
    }

    public T searchWeatherByCityName(String city) {
        log.info("Search weather for: {}", city);
        searchFrom.$("#searchFieldWidget").shouldBe(Condition.visible).setValue(city);
        searchFrom.$("#searchBtnWidget").shouldBe(Condition.visible).click();
        return (T) this;
    }


    public SelenideElement getCity() {
        return widget.$("[data-id='city']");
    }

    public SelenideElement getCoordinates() {
        return widget.$("[data-id='coord']");
    }

    public SelenideElement getTemperature() {
        return widget.$("#temp");
    }

    public SelenideElement getDescription() {
        return widget.$("#desc");
    }

    protected String getCoordinates(WeatherResponse response) {
        return String.format("lat: %s, lon: %s", response.getLat(), response.getLon());
    }
}
