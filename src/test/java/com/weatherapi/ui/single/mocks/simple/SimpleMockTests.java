package com.weatherapi.ui.single.mocks.simple;

import com.codeborne.selenide.Configuration;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.pages.ApixuWidget;
import com.weatherapi.pages.OpenWeatherWidget;
import com.weatherapi.pages.WeatherBitWidget;
import com.weatherapi.service.ApixuService;
import com.weatherapi.service.OpenWeatherMapService;
import com.weatherapi.service.WeatherBitService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static com.weatherapi.MockWeatherFactory.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SimpleMockTests {

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
    public void resetMocks() {
        Mockito.reset(apixuService, openWeatherMapService, weatherBitService);
    }

    @AfterAll
    public void tearDown() {
        close();
    }

    @Test
    public void apixuSearchWithAnyValueTest() {
        when(apixuService.getWeatherByCityName(anyString())).thenReturn(apixuResponse);

        apixuWidget
                .searchWeatherByCityName("blabla")
                .checkWeather(apixuResponse);
    }

    @Test
    public void apixuSearchWithPredefineValueTest() {
        when(apixuService.getWeatherByCityName(eq(apixuResponse.getCity()))).thenReturn(apixuResponse);

        apixuWidget
                .searchWeatherByCityName(apixuResponse.getCity())
                .checkWeather(apixuResponse);
    }

    @Test
    public void openWidgetMapSearchWithAnyValueTest() {
        when(openWeatherMapService.getWeatherByCityName(anyString())).thenReturn(openWeatherResponse);

        apixuWidget
                .searchWeatherByCityName("trololo")
                .checkWeather(openWeatherResponse);
    }

    @Test
    public void openWidgetMapSearchWithPredefineValueTest() {
        when(openWeatherMapService.getWeatherByCityName(eq(openWeatherResponse.getCity())))
                .thenReturn(openWeatherResponse);

        openWeatherWidget
                .searchWeatherByCityName(openWeatherResponse.getCity())
                .checkWeather(openWeatherResponse);
    }

    @Test
    public void weatherBitSearchWithAnyValueTest() {
        when(weatherBitService.getWeatherByCityName(anyString())).thenReturn(weatherBitResponse);

        weatherBitWidget
                .searchWeatherByCityName("ggggg")
                .checkWeather(weatherBitResponse);
    }

    @Test
    public void weatherBitSearchWithPredefineValueTest() {
        when(weatherBitService.getWeatherByCityName(eq(weatherBitResponse.getCity())))
                .thenReturn(weatherBitResponse);

        weatherBitWidget
                .searchWeatherByCityName(weatherBitResponse.getCity())
                .checkWeather(weatherBitResponse);
    }
}
