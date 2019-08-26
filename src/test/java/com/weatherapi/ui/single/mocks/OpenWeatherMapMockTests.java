package com.weatherapi.ui.single.mocks;

import com.codeborne.selenide.Condition;
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

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;


@Slf4j
public class OpenWeatherMapMockTests extends SingleMockTestBase {

    private OpenWeatherWidget openWeatherWidget = new OpenWeatherWidget();

    @MockBean
    private OpenWeatherMapService openWeatherMapService;

    @BeforeEach
    public void setUpMocks() {
        when(openWeatherMapService.getWeatherByCityName(argThat(matchRequestCity(openWeatherResponse.getCity()))))
                .thenReturn(openWeatherResponse);
        when(openWeatherMapService.getWeatherByCityName(argThat(matchRequestCity(weatherBitResponse.getCity()))))
                .thenReturn(weatherBitResponse);
        when(openWeatherMapService.getWeatherByCityName(argThat(matchRequestCity(apixuResponse.getCity()))))
                .thenReturn(apixuResponse);
    }

    @AfterEach
    public void resetMocks() {
        Mockito.reset(openWeatherMapService);
    }

    @ParameterizedTest
    @MethodSource("com.weatherapi.ui.single.mocks.SingleMockBase#cities")
    public void openWidgetMapSearchTest(WeatherResponse weatherResponse) {
        openWeatherWidget.searchWeather(weatherResponse.getCity());

        log.info("city: {}",  openWeatherWidget.getCity().text());
        openWeatherWidget.getCity().shouldHave(Condition.text(weatherResponse.getCity()));

        log.info("coordinates: {}",  openWeatherWidget.getCoordinates().text());
        String coordinates = String.format("lat: %s, lon: %s", weatherResponse.getLat(), weatherResponse.getLon());
        openWeatherWidget.getCoordinates().shouldHave(Condition.text(coordinates));

        log.info("temperature: {}",  openWeatherWidget.getTemperature().text());
        openWeatherWidget.getTemperature().shouldHave(Condition.text(String.valueOf(weatherResponse.getTemp())));

        log.info("description: {}",  openWeatherWidget.getDescription().text());
        openWeatherWidget.getDescription().shouldHave(Condition.text(weatherResponse.getDescription()));
    }
}
