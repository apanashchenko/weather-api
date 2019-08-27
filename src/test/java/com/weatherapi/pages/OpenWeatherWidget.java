package com.weatherapi.pages;


import static com.codeborne.selenide.Selenide.$;

/**
 * Created by alpa on 2019-08-26
 */
public class OpenWeatherWidget extends BaseWidget<OpenWeatherWidget>  {

    public OpenWeatherWidget() {
        super($("#owmWidget"), $("#searchFormOwp"));
    }

    @Override
    public String toString() {
        return "OpenWeatherWidget";
    }
}
