package com.weatherapi.ui.defaults;

import com.weatherapi.model.WeatherResponse;
import com.weatherapi.ui.pages.WeatherStackWidget;
import com.weatherapi.ui.pages.MainSearchForm;
import com.weatherapi.ui.pages.OpenWeatherWidget;
import com.weatherapi.ui.pages.WeatherBitWidget;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.of;


@Slf4j
public class MainWeatherSearchTests extends DefaultTestBase {

    private MainSearchForm mainSearchForm = new MainSearchForm();
    private OpenWeatherWidget openWeatherWidget = new OpenWeatherWidget();
    private WeatherBitWidget weatherBitWidget = new WeatherBitWidget();
    private WeatherStackWidget weatherStackWidget = new WeatherStackWidget();

    private Stream<Arguments> widgets() {
        return Stream.of(
                of("Berlin", getOwmBerlinResponse(), getWbBerlinResponse(), getApixuBerlinResponse()),
                of("Paris", getOwmParisResponse(), getWbParisResponse(), getApixuParisResponse()),
                of("London", getOwmLondonResponse(), getWbLondonResponse(), getApixuLondonResponse())
        );
    }


    @ParameterizedTest
    @MethodSource("widgets")
    public void checkWidgetsDefaultValues(String city, WeatherResponse owp, WeatherResponse wb, WeatherResponse apixu) {
        mainSearchForm.searchWeather(city);

        openWeatherWidget.load(city).checkWeather(owp);

        weatherBitWidget.load(city).checkWeather(owp);

        weatherStackWidget.load(city).checkWeather(owp);

    }

    private WeatherResponse getOwmBerlinResponse() {
        WeatherResponse owmBerlinResponse = new WeatherResponse();
        owmBerlinResponse.setCity("Berlin");
        owmBerlinResponse.setDescription("thunderstorm with light rain");
        owmBerlinResponse.setTemp(26);
        owmBerlinResponse.setLat(52.52);
        owmBerlinResponse.setLon(13.39);

        return owmBerlinResponse;
    }

    private WeatherResponse getOwmParisResponse() {
        WeatherResponse owmParisResponse = new WeatherResponse();
        owmParisResponse.setCity("Paris");
        owmParisResponse.setDescription("clear sky");
        owmParisResponse.setTemp(30);
        owmParisResponse.setLat(48.86);
        owmParisResponse.setLon(2.35);

        return owmParisResponse;
    }

    private WeatherResponse getOwmLondonResponse() {
        WeatherResponse owmLondonResponse = new WeatherResponse();
        owmLondonResponse.setCity("London");
        owmLondonResponse.setDescription("clear sky");
        owmLondonResponse.setTemp(30);
        owmLondonResponse.setLat(51.51);
        owmLondonResponse.setLon(-0.13);

        return owmLondonResponse;
    }

    private WeatherResponse getWbBerlinResponse() {
        WeatherResponse wbBerlinResponse = new WeatherResponse();
        wbBerlinResponse.setCity("Berlin");
        wbBerlinResponse.setDescription("Moderate rain");
        wbBerlinResponse.setTemp(25);
        wbBerlinResponse.setLat(52.52437);
        wbBerlinResponse.setLon(13.41053);

        return wbBerlinResponse;
    }

    private WeatherResponse getWbParisResponse() {
        WeatherResponse wbParisResponse = new WeatherResponse();
        wbParisResponse.setCity("Paris");
        wbParisResponse.setDescription("Clear sky");
        wbParisResponse.setTemp(25);
        wbParisResponse.setLat(48.85341);
        wbParisResponse.setLon(2.3488);

        return wbParisResponse;
    }

    private WeatherResponse getWbLondonResponse() {
        WeatherResponse wbLondonResponse = new WeatherResponse();
        wbLondonResponse.setCity("London");
        wbLondonResponse.setDescription("Few clouds");
        wbLondonResponse.setTemp(31);
        wbLondonResponse.setLat(35.32897);
        wbLondonResponse.setLon(-93.25296);

        return wbLondonResponse;
    }

    public WeatherResponse getApixuBerlinResponse() {
        WeatherResponse apixuBerlinResponse = new WeatherResponse();
        apixuBerlinResponse.setCity("Berlin");
        apixuBerlinResponse.setDescription("Moderate or heavy rain with thunder");
        apixuBerlinResponse.setTemp(30);
        apixuBerlinResponse.setLat(52.52);
        apixuBerlinResponse.setLon(13.4);

        return apixuBerlinResponse;
    }

     public WeatherResponse getApixuParisResponse() {
        WeatherResponse apixuParisResponse = new WeatherResponse();
        apixuParisResponse.setCity("Paris");
        apixuParisResponse.setDescription("Sunny");
        apixuParisResponse.setTemp(32);
        apixuParisResponse.setLat(48.87);
        apixuParisResponse.setLon(2.33);

        return apixuParisResponse;
    }

    public WeatherResponse getApixuLondonResponse() {
        WeatherResponse apixuLondonResponse = new WeatherResponse();
        apixuLondonResponse.setCity("London");
        apixuLondonResponse.setDescription("Sunny");
        apixuLondonResponse.setTemp(31);
        apixuLondonResponse.setLat(51.52);
        apixuLondonResponse.setLon(-0.11);

        return apixuLondonResponse;
    }


}
