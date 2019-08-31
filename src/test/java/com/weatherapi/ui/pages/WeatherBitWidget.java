package com.weatherapi.ui.pages;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by alpa on 2019-08-26
 */
public class WeatherBitWidget extends BaseWidget<WeatherBitWidget> {

    public WeatherBitWidget() {
        super($("#wbWidget"), $("#searchFormWb"));
    }

    @Override
    public String toString() {
        return "WeatherBitWidget";
    }
}
