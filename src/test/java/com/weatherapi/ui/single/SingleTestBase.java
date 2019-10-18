package com.weatherapi.ui.single;

import com.codeborne.selenide.Configuration;
import com.weatherapi.ui.pages.OpenWeatherWidget;
import com.weatherapi.ui.pages.WeatherBitWidget;
import com.weatherapi.ui.pages.WeatherStackWidget;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static com.weatherapi.MockWeatherFactory.*;
import static org.junit.jupiter.params.provider.Arguments.of;

/**
 * Created by alpa on 2019-08-26
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SingleTestBase {

    protected OpenWeatherWidget openWeatherWidget = new OpenWeatherWidget();
    protected WeatherBitWidget weatherBitWidget = new WeatherBitWidget();
    protected WeatherStackWidget weatherStackWidget = new WeatherStackWidget();

    @Value("${ui.url}")
    private String baseUrl;

    @BeforeEach
    public void setUp() {
        Configuration.baseUrl = this.baseUrl;
        Configuration.startMaximized = true;
        open(Configuration.baseUrl);
    }

    @AfterEach
    public void tearDown() {
        close();
    }

    protected Stream<Arguments> cities() {
        return Stream.of(
                of(getOpenWeatherResponse()),
                of(getWeatherBitResponse()),
                of(getWeatherStackResponse())
        );
    }
}
