package com.weatherapi.ui.defaults.mocks;

import com.codeborne.selenide.Configuration;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.ui.pages.WeatherStackWidget;
import com.weatherapi.ui.pages.BaseWidget;
import com.weatherapi.ui.pages.OpenWeatherWidget;
import com.weatherapi.ui.pages.WeatherBitWidget;
import com.weatherapi.service.WeatherStackService;
import com.weatherapi.service.OpenWeatherMapService;
import com.weatherapi.service.WeatherBitService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class DefaultValuesWeatherMockTests {

    @Value("${ui.url}")
    private String baseUrl;

    @MockBean
    private OpenWeatherMapService openWeatherMapService;

    @MockBean
    private WeatherBitService weatherBitService;

    @MockBean
    private WeatherStackService weatherStackService;

    @BeforeAll
    public void setUp() {
        doReturn(getOpenWeatherResponse()).when(openWeatherMapService).getWeatherByCityName(anyString());
        doReturn(getWeatherBitResponse()).when(weatherBitService).getWeatherByCityName(anyString());
        doReturn(getWeatherStackResponse()).when(weatherStackService).getWeatherByCityName(anyString());

        Configuration.startMaximized = true;
        open(baseUrl);
    }

    @AfterAll
    public void tearDown() {
        Mockito.reset(openWeatherMapService, weatherBitService, weatherStackService);
        close();
    }

    private static Stream<Arguments> widgets() {
        return Stream.of(
                of(new OpenWeatherWidget(), getOpenWeatherResponse()),
                of(new WeatherBitWidget(), getWeatherBitResponse()),
                of(new WeatherStackWidget(), getWeatherStackResponse())
        );
    }

    @ParameterizedTest(name = "{index} ==> widgetId={0} => response => {1}")
    @MethodSource("widgets")
    public void checkWidgetsDefaultValues(BaseWidget widget, WeatherResponse weatherResponse) {
        widget.load().checkWeather(weatherResponse);
    }

}
