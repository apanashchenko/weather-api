package com.weatherapi.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

/**
 * Created by alpa on 2019-08-26
 */
public abstract class BaseWidget<T extends BaseWidget> {

    private SelenideElement widget;
    private SelenideElement searchFrom;

    public BaseWidget(SelenideElement widget, SelenideElement searchFrom) {
        this.widget = widget;
        this.searchFrom = searchFrom;
    }

    public T load() {
        getCity().waitUntil(Condition.not(Condition.empty), 5000);
        return (T) this;
    }

    public T load(String city) {
        getCity().waitUntil(Condition.text(city), 5000);
        return (T) this;
    }

    protected void searchWeatherByCityName(String city) {
        searchFrom.$("#searchFieldWidget").shouldBe(Condition.visible).setValue(city);
        searchFrom.$("#searchBtnWidget").shouldBe(Condition.visible).click();
        getCity().waitUntil(Condition.text(city), 5000);

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
}
