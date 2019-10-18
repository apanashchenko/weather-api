package com.weatherapi.ui.pages;


import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by alpa on 2019-08-26
 */
@Slf4j
public class MainSearchForm {

    private SelenideElement searchFrom = $("#searchForm");
    private SelenideElement searchField = searchFrom.$("#searchField");
    private SelenideElement searchBtn = searchFrom.$("#searchBtn");

    public MainSearchForm searchWeather(String city) {
        log.info("Search weather for: {}", city);
        searchField.setValue(city);
        searchBtn.click();
        return this;
    }

    @Override
    public String toString() {
        return "MainSearchWidget";
    }
}
