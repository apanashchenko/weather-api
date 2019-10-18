package com.weatherapi.ui.single.mocks;

import com.weatherapi.model.WeatherResponse;
import com.weatherapi.service.WeatherBitService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@Slf4j
class WeatherBitMockTests extends SingleMockTestBase {

    @MockBean
    private WeatherBitService weatherBitService;

    @BeforeEach
    void setUpMocks() {
        log.info("Start up mock");
        when(weatherBitService.getWeatherByCityName(eq(openWeatherResponse.getCity())))
                .thenReturn(openWeatherResponse);
        when(weatherBitService.getWeatherByCityName(eq(weatherBitResponse.getCity()))).thenReturn(weatherBitResponse);
        when(weatherBitService.getWeatherByCityName(eq(weatherStackResponse.getCity()))).thenReturn(weatherStackResponse);
        log.info("Finish up mock");
    }

    @AfterEach
    void resetMocks() {
        log.info("Reset mocks");
        Mockito.reset(weatherBitService);
    }

    @ParameterizedTest
    @MethodSource("com.weatherapi.ui.single.mocks.SingleMockTestBase#cities")
    void weatherBitSearchTest(WeatherResponse weatherResponse) {
        weatherBitWidget
                .searchWeatherByCityName(weatherResponse.getCity())
                .checkWeather(weatherResponse);
    }
}
