package com.weatherapi.ui.single.mocks;

import com.codeborne.selenide.Condition;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.pages.ApixuWidget;
import com.weatherapi.service.ApixuService;
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
public class ApixuMockTests extends SingleMockBase {

    private ApixuWidget apixuWidget = new ApixuWidget();

    @MockBean
    private ApixuService apixuService;

    @BeforeEach
    public void setUpMocks() {
        when(apixuService.getWeatherByCityName(argThat(matchRequestCity(openWeatherResponse.getCity()))))
                .thenReturn(openWeatherResponse);
        when(apixuService.getWeatherByCityName(argThat(matchRequestCity(weatherBitResponse.getCity()))))
                .thenReturn(weatherBitResponse);
        when(apixuService.getWeatherByCityName(argThat(matchRequestCity(apixuResponse.getCity()))))
                .thenReturn(apixuResponse);
    }

    @AfterEach
    public void resetMocks() {
        Mockito.reset(apixuService);
    }


    @ParameterizedTest
    @MethodSource("com.weatherapi.ui.single.mocks.SingleMockBase#cities")
    public void weatherBitSearchTest(WeatherResponse weatherResponse) {
        apixuWidget.searchWeather(weatherResponse.getCity());

        log.info("city: {}",  apixuWidget.getCity().text());
        apixuWidget.getCity().shouldHave(Condition.text(weatherResponse.getCity()));

        log.info("coordinates: {}",  apixuWidget.getCoordinates().text());
        String coordinates = String.format("lat: %s, lon: %s", weatherResponse.getLat(), weatherResponse.getLon());
        apixuWidget.getCoordinates().shouldHave(Condition.text(coordinates));

        log.info("temperature: {}",  apixuWidget.getTemperature().text());
        apixuWidget.getTemperature().shouldHave(Condition.text(String.valueOf(weatherResponse.getTemp())));

        log.info("description: {}",  apixuWidget.getDescription().text());
        apixuWidget.getDescription().shouldHave(Condition.text(weatherResponse.getDescription()));
    }
}
