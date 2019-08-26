package com.weatherapi.ui.single.mocks;

import com.codeborne.selenide.Condition;
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
public class WeatherBitMockTests extends SingleMockBase {

    private WeatherBitWidget weatherBitWidget = new WeatherBitWidget();

    @MockBean
    private WeatherBitService weatherBitService;

    @BeforeEach
    public void setUpMocks() {
        when(weatherBitService.getWeatherByCityName(argThat(matchRequestCity(openWeatherResponse.getCity()))))
                .thenReturn(openWeatherResponse);
        when(weatherBitService.getWeatherByCityName(argThat(matchRequestCity(weatherBitResponse.getCity()))))
                .thenReturn(weatherBitResponse);
        when(weatherBitService.getWeatherByCityName(argThat(matchRequestCity(apixuResponse.getCity()))))
                .thenReturn(apixuResponse);
    }

    @AfterEach
    public void resetMocks() {
        Mockito.reset(weatherBitService);
    }

    @ParameterizedTest
    @MethodSource("com.weatherapi.ui.single.mocks.SingleMockBase#cities")
    public void weatherBitSearchTest(WeatherResponse weatherResponse) {
        weatherBitWidget.searchWeather(weatherResponse.getCity());

        log.info("city: {}",  weatherBitWidget.getCity().text());
        weatherBitWidget.getCity().shouldHave(Condition.text(weatherResponse.getCity()));

        log.info("coordinates: {}",  weatherBitWidget.getCoordinates().text());
        String coordinates = String.format("lat: %s, lon: %s", weatherResponse.getLat(), weatherResponse.getLon());
        weatherBitWidget.getCoordinates().shouldHave(Condition.text(coordinates));

        log.info("temperature: {}",  weatherBitWidget.getTemperature().text());
        weatherBitWidget.getTemperature().shouldHave(Condition.text(String.valueOf(weatherResponse.getTemp())));

        log.info("description: {}",  weatherBitWidget.getDescription().text());
        weatherBitWidget.getDescription().shouldHave(Condition.text(weatherResponse.getDescription()));
    }
}
