package com.weatherapi.pages;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by alpa on 2019-08-26
 */
public class WeatherBitWidget extends BaseWidget<WeatherBitWidget> {

    public WeatherBitWidget() {
        super($("#wbWidget"), $("#searchFormWb"));
    }

    public WeatherBitWidget searchWeather(String city) {
        searchWeatherByCityName(city);
        return this;
    }

    @Override
    public String toString() {
        return "WeatherBitWidget";
    }
}
