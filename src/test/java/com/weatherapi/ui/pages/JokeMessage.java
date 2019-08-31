package com.weatherapi.ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by alpa on 2019-08-31
 */
public class JokeMessage {

    public SelenideElement joke = $("#joke");
    public SelenideElement message = joke.$(".text");


}
