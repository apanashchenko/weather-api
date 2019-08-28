package com.weatherapi.ui.defaults;

import com.codeborne.selenide.Condition;
import com.weatherapi.pages.ApixuWidget;
import com.weatherapi.pages.BaseWidget;
import com.weatherapi.pages.OpenWeatherWidget;
import com.weatherapi.pages.WeatherBitWidget;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.of;

@Slf4j
public class DefaultValuesWeatherTests extends DefaultTestBase {

    private static Stream<Arguments> widgets() {
        return Stream.of(
                of(new OpenWeatherWidget(), "Dnipro", "lat: 48.47, lon: 35.04", "31", "clear sky"),
                of(new WeatherBitWidget(), "Sydney", " lat: 46.1351, lon: -60.1831", "11", "overcast clouds"),
                of(new ApixuWidget(), "Los Angeles", "lat: 34.05, lon: -118.24", "21.1", "clear")
        );
    }


    @ParameterizedTest(name = "{index} ==> widget={0} => city={1} => coordinates={2} => temp={3} => description={4}")
    @MethodSource("widgets")
    public void checkWidgetsDefaultValues(BaseWidget widget, String city, String coordinates,
                                          String temp, String description) {

        widget.load(city)
                .checkWeather(city, coordinates, temp, description);
    }
}
