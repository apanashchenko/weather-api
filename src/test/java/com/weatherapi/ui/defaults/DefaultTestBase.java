package com.weatherapi.ui.defaults;

import com.codeborne.selenide.Configuration;
import com.weatherapi.ui.pages.MainSearchForm;
import com.weatherapi.ui.pages.OpenWeatherWidget;
import com.weatherapi.ui.pages.WeatherBitWidget;
import com.weatherapi.ui.pages.WeatherStackWidget;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by alpa on 2019-08-26
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DefaultTestBase {

    protected OpenWeatherWidget openWeatherWidget = new OpenWeatherWidget();
    MainSearchForm mainSearchForm = new MainSearchForm();
    WeatherBitWidget weatherBitWidget = new WeatherBitWidget();
    WeatherStackWidget weatherStackWidget = new WeatherStackWidget();

    @Value("${ui.url}")
    private String baseUrl;

    @BeforeAll
    public void setUp() {
        Configuration.startMaximized = true;
        open(baseUrl);
    }

    @AfterAll
    public void tearDown() {
        close();
    }
}
