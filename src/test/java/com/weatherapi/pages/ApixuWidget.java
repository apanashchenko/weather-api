package com.weatherapi.pages;


import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by alpa on 2019-08-26
 */
@Slf4j
public class ApixuWidget extends BaseWidget<ApixuWidget> {

    public ApixuWidget() {
        super($("#apixuWidget"), $("#searchFormApixu"));
    }

    @Override
    public String toString() {
        return "ApixuWidget";
    }
}
