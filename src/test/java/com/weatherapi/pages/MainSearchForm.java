package com.weatherapi.pages;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by alpa on 2019-08-26
 */
public class MainSearchForm {

    private SelenideElement searchFrom = $("#searchForm");
    private SelenideElement searchField = searchFrom.$("#searchField");
    private SelenideElement searchBtn = searchFrom.$("#searchBtn");

    public MainSearchForm searchWeather(String city) {
        searchField.setValue(city);
        searchBtn.click();
        return this;
    }

    @Override
    public String toString() {
        return "MainSearchWidget";
    }
}
