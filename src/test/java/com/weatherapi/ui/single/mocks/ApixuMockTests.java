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
public class ApixuMockTests extends SingleMockTestBase {

    private ApixuWidget apixuWidget = new ApixuWidget();

    @MockBean
    private ApixuService apixuService;

    @BeforeEach
    public void setUpMocks() {
        log.info("Start up mock");
        when(apixuService.getWeatherByCityName(argThat(matchRequestCity(openWeatherResponse.getCity()))))
                .thenReturn(openWeatherResponse);
        when(apixuService.getWeatherByCityName(argThat(matchRequestCity(weatherBitResponse.getCity()))))
                .thenReturn(weatherBitResponse);
        when(apixuService.getWeatherByCityName(argThat(matchRequestCity(apixuResponse.getCity()))))
                .thenReturn(apixuResponse);
        log.info("Finish up mock");
    }

    @AfterEach
    public void resetMocks() {
        log.info("Reset mocks");
        Mockito.reset(apixuService);
    }


    @ParameterizedTest
    @MethodSource("com.weatherapi.ui.single.mocks.SingleMockTestBase#cities")
    public void apixuSearchTest(WeatherResponse weatherResponse) {
        apixuWidget
                .searchWeatherByCityName(weatherResponse.getCity())
                .checkWeather(weatherResponse);
    }
}
