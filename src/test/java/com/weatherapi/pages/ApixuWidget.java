package com.weatherapi.pages;


import static com.codeborne.selenide.Selenide.$;

/**
 * Created by alpa on 2019-08-26
 */
public class ApixuWidget extends BaseWidget<ApixuWidget> {

    public ApixuWidget() {
        super($("#apixuWidget"), $("#searchFormApixu"));
    }

    public ApixuWidget searchWeather(String city) {
        searchWeatherByCityName(city);
        return this;
    }

    @Override
    public String toString() {
        return "ApixuWidget";
    }
}
