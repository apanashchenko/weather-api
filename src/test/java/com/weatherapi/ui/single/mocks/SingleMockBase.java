package com.weatherapi.ui.single.mocks;

import com.codeborne.selenide.Configuration;
import com.weatherapi.model.WeatherResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.ArgumentMatcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;
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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SingleMockBase {

    @Value("${ui.url}")
    private String baseUrl;

    WeatherResponse openWeatherResponse = getOpenWeatherResponse();
    WeatherResponse weatherBitResponse = getWeatherBitResponse();
    WeatherResponse apixuResponse = getApixuResponse();

    @BeforeAll
    public void setUp() {
        Configuration.startMaximized = true;
        open(baseUrl);
    }

    @AfterAll
    public void tearDown() {
        close();
    }

    protected ArgumentMatcher<String> matchRequestCity(final String city) {
        return arg -> Objects.nonNull(city) && city.equals(arg);
    }

    protected Stream<Arguments> cities() {
        return Stream.of(
                of(openWeatherResponse),
                of(weatherBitResponse),
                of(apixuResponse)
        );
    }


}
