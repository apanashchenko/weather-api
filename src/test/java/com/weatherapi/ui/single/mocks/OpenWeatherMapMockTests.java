package com.weatherapi.ui.single.mocks;

import com.weatherapi.model.WeatherResponse;
import com.weatherapi.pages.OpenWeatherWidget;
import com.weatherapi.service.OpenWeatherMapService;
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
public class OpenWeatherMapMockTests extends SingleMockTestBase {

    private OpenWeatherWidget openWeatherWidget = new OpenWeatherWidget();

    @MockBean
    private OpenWeatherMapService openWeatherMapService;

    @BeforeEach
    public void setUpMocks() {
        when(openWeatherMapService.getWeatherByCityName(eq(openWeatherResponse.getCity())))
                .thenReturn(openWeatherResponse);
        when(openWeatherMapService.getWeatherByCityName(eq(weatherBitResponse.getCity())))
                .thenReturn(weatherBitResponse);
        when(openWeatherMapService.getWeatherByCityName(eq(apixuResponse.getCity()))).thenReturn(apixuResponse);
    }

    @AfterEach
    public void resetMocks() {
        Mockito.reset(openWeatherMapService);
    }

    @ParameterizedTest
    @MethodSource("com.weatherapi.ui.single.mocks.SingleMockTestBase#cities")
    public void openWidgetMapSearchTest(WeatherResponse weatherResponse) {
        openWeatherWidget
                .searchWeatherByCityName(weatherResponse.getCity())
                .checkWeather(weatherResponse);
    }
}
