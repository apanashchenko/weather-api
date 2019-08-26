package com.weatherapi.ui.defaults.mocks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.pages.ApixuWidget;
import com.weatherapi.pages.MainSearchForm;
import com.weatherapi.pages.OpenWeatherWidget;
import com.weatherapi.pages.WeatherBitWidget;
import com.weatherapi.service.ApixuService;
import com.weatherapi.service.OpenWeatherMapService;
import com.weatherapi.service.WeatherBitService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.params.provider.Arguments.of;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class MainWeatherSearchMockTests {

    private static final String BERLIN = "Berlin";
    private static final String PARIS = "Paris";
    private static final String LONDON = "London";

    @Value("${ui.url}")
    private String baseUrl;

    @MockBean
    private OpenWeatherMapService openWeatherMapService;

    @MockBean
    private WeatherBitService weatherBitService;

    @MockBean
    private ApixuService apixuService;

    private MainSearchForm mainSearchForm = new MainSearchForm();
    private OpenWeatherWidget openWeatherWidget = new OpenWeatherWidget();
    private WeatherBitWidget weatherBitWidget = new WeatherBitWidget();
    private ApixuWidget apixuWidget = new ApixuWidget();

    @BeforeAll
    public void setUp() {
        Configuration.startMaximized = true;
        open(baseUrl);
    }

    @BeforeEach
    public void setUpMocks() {
        when(openWeatherMapService.getWeatherByCityName(argThat(matchRequestCity(BERLIN))))
                .thenReturn(getOwmBerlinResponse());
        when(openWeatherMapService.getWeatherByCityName(argThat(matchRequestCity(PARIS))))
                .thenReturn(getOwmParisResponse());
        when(openWeatherMapService.getWeatherByCityName(argThat(matchRequestCity(LONDON))))
                .thenReturn(getOwmLondonResponse());

        when(weatherBitService.getWeatherByCityName(argThat(matchRequestCity(BERLIN))))
                .thenReturn(getWbBerlinResponse());
        when(weatherBitService.getWeatherByCityName(argThat(matchRequestCity(PARIS))))
                .thenReturn(getWbParisResponse());
        when(weatherBitService.getWeatherByCityName(argThat(matchRequestCity(LONDON))))
                .thenReturn(getWbLondonResponse());

        when(apixuService.getWeatherByCityName(argThat(matchRequestCity(BERLIN))))
                .thenReturn(getApixuBerlinResponse());
        when(apixuService.getWeatherByCityName(argThat(matchRequestCity(PARIS))))
                .thenReturn(getApixuParisResponse());
        when(apixuService.getWeatherByCityName(argThat(matchRequestCity(LONDON))))
                .thenReturn(getApixuLondonResponse());
    }

    @AfterEach
    public void resetMocks() {
        Mockito.reset(openWeatherMapService, weatherBitService,apixuService);
    }

    @AfterAll
    public void tearDown() {
        close();
    }

    private Stream<Arguments> widgets() {
        return Stream.of(
                of(BERLIN, getOwmBerlinResponse(), getWbBerlinResponse(), getApixuBerlinResponse()),
                of(PARIS, getOwmParisResponse(), getWbParisResponse(), getApixuParisResponse()),
                of(LONDON, getOwmLondonResponse(), getWbLondonResponse(), getApixuLondonResponse())
        );
    }


    @ParameterizedTest
    @MethodSource("widgets")
    public void checkWidgetsDefaultValues(String city, WeatherResponse owp, WeatherResponse wb, WeatherResponse apixu) {
        mainSearchForm.searchWeather(city);
        openWeatherWidget.load(city);
        weatherBitWidget.load(city);
        apixuWidget.load(city);

        openWeatherWidget.getCity().shouldHave(Condition.text(city));
        weatherBitWidget.getCity().shouldHave(Condition.text(city));
        apixuWidget.getCity().shouldHave(Condition.text(city));

        openWeatherWidget.getCoordinates().shouldHave(Condition.text(getCoordinates(owp)));
        weatherBitWidget.getCoordinates().shouldHave(Condition.text(getCoordinates(wb)));
        apixuWidget.getCoordinates().shouldHave(Condition.text(getCoordinates(apixu)));

        openWeatherWidget.getTemperature().shouldHave(Condition.text(String.valueOf(owp.getTemp())));
        weatherBitWidget.getTemperature().shouldHave(Condition.text(String.valueOf(wb.getTemp())));
        apixuWidget.getTemperature().shouldHave(Condition.text(String.valueOf(apixu.getTemp())));

        openWeatherWidget.getDescription().shouldHave(Condition.text(owp.getDescription()));
        weatherBitWidget.getDescription().shouldHave(Condition.text(wb.getDescription()));
        apixuWidget.getDescription().shouldHave(Condition.text(apixu.getDescription()));
    }

    protected ArgumentMatcher<String> matchRequestCity(final String city) {
        return arg -> Objects.nonNull(city) && city.equals(arg);
    }

    private String getCoordinates(WeatherResponse response) {
        return String.format("lat: %s, lon: %s", response.getLat(), response.getLon());
    }

    private WeatherResponse getOwmBerlinResponse() {
        WeatherResponse owmBerlinResponse = new WeatherResponse();
        owmBerlinResponse.setCity(BERLIN);
        owmBerlinResponse.setDescription("thunderstorm with light rain");
        owmBerlinResponse.setTemp(26.2);
        owmBerlinResponse.setLat(52.52);
        owmBerlinResponse.setLon(13.39);

        return owmBerlinResponse;
    }

    private WeatherResponse getOwmParisResponse() {
        WeatherResponse owmParisResponse = new WeatherResponse();
        owmParisResponse.setCity(PARIS);
        owmParisResponse.setDescription("clear sky");
        owmParisResponse.setTemp(30.5);
        owmParisResponse.setLat(48.86);
        owmParisResponse.setLon(2.35);

        return owmParisResponse;
    }

    private WeatherResponse getOwmLondonResponse() {
        WeatherResponse owmLondonResponse = new WeatherResponse();
        owmLondonResponse.setCity(LONDON);
        owmLondonResponse.setDescription("clear sky");
        owmLondonResponse.setTemp(30.9);
        owmLondonResponse.setLat(51.51);
        owmLondonResponse.setLon(-0.13);

        return owmLondonResponse;
    }

    private WeatherResponse getWbBerlinResponse() {
        WeatherResponse wbBerlinResponse = new WeatherResponse();
        wbBerlinResponse.setCity(BERLIN);
        wbBerlinResponse.setDescription("Moderate rain");
        wbBerlinResponse.setTemp(25.8);
        wbBerlinResponse.setLat(52.52437);
        wbBerlinResponse.setLon(13.41053);

        return wbBerlinResponse;
    }

    private WeatherResponse getWbParisResponse() {
        WeatherResponse wbParisResponse = new WeatherResponse();
        wbParisResponse.setCity(PARIS);
        wbParisResponse.setDescription("Clear sky");
        wbParisResponse.setTemp(25.8);
        wbParisResponse.setLat(48.85341);
        wbParisResponse.setLon(2.3488);

        return wbParisResponse;
    }

    private WeatherResponse getWbLondonResponse() {
        WeatherResponse wbLondonResponse = new WeatherResponse();
        wbLondonResponse.setCity(LONDON);
        wbLondonResponse.setDescription("Few clouds");
        wbLondonResponse.setTemp(31.3);
        wbLondonResponse.setLat(35.32897);
        wbLondonResponse.setLon(-93.25296);

        return wbLondonResponse;
    }

    public WeatherResponse getApixuBerlinResponse() {
        WeatherResponse apixuBerlinResponse = new WeatherResponse();
        apixuBerlinResponse.setCity(BERLIN);
        apixuBerlinResponse.setDescription("Moderate or heavy rain with thunder");
        apixuBerlinResponse.setTemp(30.3);
        apixuBerlinResponse.setLat(52.52);
        apixuBerlinResponse.setLon(13.4);

        return apixuBerlinResponse;
    }

     public WeatherResponse getApixuParisResponse() {
        WeatherResponse apixuParisResponse = new WeatherResponse();
        apixuParisResponse.setCity(PARIS);
        apixuParisResponse.setDescription("Sunny");
        apixuParisResponse.setTemp(32.2);
        apixuParisResponse.setLat(48.87);
        apixuParisResponse.setLon(2.33);

        return apixuParisResponse;
    }

    public WeatherResponse getApixuLondonResponse() {
        WeatherResponse apixuLondonResponse = new WeatherResponse();
        apixuLondonResponse.setCity(LONDON);
        apixuLondonResponse.setDescription("Sunny");
        apixuLondonResponse.setTemp(31.1);
        apixuLondonResponse.setLat(51.52);
        apixuLondonResponse.setLon(-0.11);

        return apixuLondonResponse;
    }


}