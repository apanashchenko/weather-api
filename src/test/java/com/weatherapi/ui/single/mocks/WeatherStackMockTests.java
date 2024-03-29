package com.weatherapi.ui.single.mocks;

import com.weatherapi.model.WeatherResponse;
import com.weatherapi.service.WeatherStackService;
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
class WeatherStackMockTests extends SingleMockTestBase {

    @MockBean
    private WeatherStackService weatherStackService;

    @BeforeEach
    void setUpMocks() {
        log.info("Start up mock");
        when(weatherStackService.getWeatherByCityName(eq(openWeatherResponse.getCity()))).thenReturn(openWeatherResponse);
        when(weatherStackService.getWeatherByCityName(eq(weatherBitResponse.getCity()))).thenReturn(weatherBitResponse);
        when(weatherStackService.getWeatherByCityName(eq(weatherStackResponse.getCity()))).thenReturn(weatherStackResponse);
        log.info("Finish up mock");
    }

    @AfterEach
    void resetMocks() {
        log.info("Reset mocks");
        Mockito.reset(weatherStackService);
    }


    @ParameterizedTest
    @MethodSource("com.weatherapi.ui.single.mocks.SingleMockTestBase#cities")
    void weatherStackSearchTest(WeatherResponse weatherResponse) {
        weatherStackWidget
                .searchWeatherByCityName(weatherResponse.getCity())
                .checkWeather(weatherResponse);
    }
}
