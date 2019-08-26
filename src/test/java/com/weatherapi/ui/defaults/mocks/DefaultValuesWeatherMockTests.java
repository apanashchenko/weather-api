package com.weatherapi.ui.defaults.mocks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.pages.ApixuWidget;
import com.weatherapi.pages.BaseWidget;
import com.weatherapi.pages.OpenWeatherWidget;
import com.weatherapi.pages.WeatherBitWidget;
import com.weatherapi.service.ApixuService;
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
    private ApixuService apixuService;

    @BeforeAll
    public void setUp() {
        doReturn(getOpenWeatherResponse()).when(openWeatherMapService).getWeatherByCityName(anyString());
        doReturn(getWeatherBitResponse()).when(weatherBitService).getWeatherByCityName(anyString());
        doReturn(getApixuResponse()).when(apixuService).getWeatherByCityName(anyString());

        Configuration.startMaximized = true;
        open(baseUrl);
    }

    @AfterAll
    public void tearDown() {
        Mockito.reset(openWeatherMapService, weatherBitService, apixuService);
        close();
    }

    private static Stream<Arguments> widgets() {
        return Stream.of(
                of(new OpenWeatherWidget(), getOpenWeatherResponse()),
                of(new WeatherBitWidget(), getWeatherBitResponse()),
                of(new ApixuWidget(), getApixuResponse())
        );
    }


    @ParameterizedTest(name = "{index} ==> widgetId={0} => response => {1}")
    @MethodSource("widgets")
    public void checkWidgetsDefaultValues(BaseWidget widget, WeatherResponse weatherResponse) {
        widget.getCity().waitUntil(Condition.not(Condition.empty), 5000);
        log.info("city: {}", widget.getCity().text());
        widget.getCity().shouldHave(Condition.text(weatherResponse.getCity()));

        log.info("coordinates: {}", widget.getCoordinates().text());
        String coordinates = String.format("lat: %s, lon: %s", weatherResponse.getLat(), weatherResponse.getLon());

        widget.getCoordinates().shouldHave(Condition.text(coordinates));

        log.info("temperature: {}", widget.getTemperature().text());
        widget.getTemperature().shouldHave(Condition.text(String.valueOf(weatherResponse.getTemp())));

        log.info("description: {}", widget.getDescription().text());
        widget.getDescription().shouldHave(Condition.text(weatherResponse.getDescription()));
    }

}
