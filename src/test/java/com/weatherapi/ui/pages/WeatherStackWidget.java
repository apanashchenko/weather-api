package com.weatherapi.ui.pages;

import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by alpa on 2019-08-26
 */
@Slf4j
public class WeatherStackWidget extends BaseWidget<WeatherStackWidget> {

    public WeatherStackWidget() {
        super($("#apixuWidget"), $("#searchFormApixu"));
    }

    @Override
    public String toString() {
        return "ApixuWidget";
    }
}
