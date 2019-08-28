package com.weatherapi.ui.single.mocks;

import com.weatherapi.model.WeatherResponse;
import com.weatherapi.pages.WeatherBitWidget;
import com.weatherapi.service.WeatherBitService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@Slf4j
public class WeatherBitMockTests extends SingleMockTestBase {

    private WeatherBitWidget weatherBitWidget = new WeatherBitWidget();

    @MockBean
    private WeatherBitService weatherBitService;

    @BeforeEach
    public void setUpMocks() {
        log.info("Start up mock");
        when(weatherBitService.getWeatherByCityName(eq(openWeatherResponse.getCity())))
                .thenReturn(openWeatherResponse);
        when(weatherBitService.getWeatherByCityName(eq(weatherBitResponse.getCity()))).thenReturn(weatherBitResponse);
        when(weatherBitService.getWeatherByCityName(eq(apixuResponse.getCity()))).thenReturn(apixuResponse);
        log.info("Finish up mock");
    }

    @AfterEach
    public void resetMocks() {
        log.info("Reset mocks");
        Mockito.reset(weatherBitService);
    }

    @ParameterizedTest
    @MethodSource("com.weatherapi.ui.single.mocks.SingleMockTestBase#cities")
    public void weatherBitSearchTest(WeatherResponse weatherResponse) {
        weatherBitWidget
                .searchWeatherByCityName(weatherResponse.getCity())
                .checkWeather(weatherResponse);
    }
}
