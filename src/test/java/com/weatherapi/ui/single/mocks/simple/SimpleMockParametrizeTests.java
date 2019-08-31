package com.weatherapi.ui.single.mocks.simple;

import com.codeborne.selenide.Configuration;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.ui.pages.ApixuWidget;
import com.weatherapi.ui.pages.BaseWidget;
import com.weatherapi.ui.pages.OpenWeatherWidget;
import com.weatherapi.ui.pages.WeatherBitWidget;
import com.weatherapi.service.ApixuService;
import com.weatherapi.service.OpenWeatherMapService;
import com.weatherapi.service.WeatherBitService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static com.weatherapi.MockWeatherFactory.*;
import static org.junit.jupiter.params.provider.Arguments.of;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class SimpleMockParametrizeTests {

    private ApixuWidget apixuWidget = new ApixuWidget();
    private OpenWeatherWidget openWeatherWidget = new OpenWeatherWidget();
    private WeatherBitWidget weatherBitWidget = new WeatherBitWidget();

    private WeatherResponse apixuResponse = getApixuResponse();
    private WeatherResponse openWeatherResponse = getOpenWeatherResponse();
    private WeatherResponse weatherBitResponse = getWeatherBitResponse();

    @Value("${ui.url}")
    private String baseUrl;

    @MockBean
    private ApixuService apixuService;

    @MockBean
    private OpenWeatherMapService openWeatherMapService;

    @MockBean
    private WeatherBitService weatherBitService;

    @BeforeAll
    public void setUp() {
        Configuration.startMaximized = true;
        open(baseUrl);
    }


    @BeforeEach
    public void setUpMocks() {
        log.info("Start up mocks");
        when(openWeatherMapService.getWeatherByCityName(eq(openWeatherResponse.getCity())))
                .thenReturn(openWeatherResponse);
        when(weatherBitService.getWeatherByCityName(eq(weatherBitResponse.getCity())))
                .thenReturn(weatherBitResponse);
        when(apixuService.getWeatherByCityName(eq(apixuResponse.getCity()))).thenReturn(apixuResponse);
        log.info("Finish up mocks");
    }

    @AfterEach
    public void resetMocks() {
        log.info("Reset mocks");
        Mockito.reset(apixuService, openWeatherMapService, weatherBitService);
    }

    @AfterAll
    public void tearDown() {
        close();
    }

    private Stream<Arguments> cities() {
        return Stream.of(
                of(openWeatherWidget, openWeatherResponse),
                of(weatherBitWidget, weatherBitResponse),
                of(apixuWidget, apixuResponse)
        );
    }

    @ParameterizedTest
    @MethodSource("cities")
    public void searchWithSpecificValueTest(BaseWidget widget, WeatherResponse response) {
        widget
              .searchWeatherByCityName(response.getCity())
              .checkWeather(response);
    }
}
