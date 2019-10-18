package com.weatherapi.service;

import com.weatherapi.model.CityCoordinate;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.model.owm.OpenWeatherMapResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;
import java.util.Optional;

import static com.weatherapi.utils.AppKeys.OWM_KEY;
import static com.weatherapi.utils.ResponseConverter.convertResponse;

@RequiredArgsConstructor
@Service
@Slf4j
public class OpenWeatherMapService implements WeatherService {

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
    private final WebClient client;


    @Override
    @SuppressWarnings("unchecked")
    public WeatherResponse getWeatherByCityName(String city) {
        Optional<OpenWeatherMapResponse> openWeatherMapResponseMono =
                getWeather(getBaseUrl().queryParam("q", city), client, OpenWeatherMapResponse.class);
        return openWeatherMapResponseMono
                .map(openWeatherMapResponse -> convertResponse(Objects.requireNonNull(openWeatherMapResponse)))
                .orElse(null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public WeatherResponse getWeatherByGeographicCoordinates(CityCoordinate coordinate) {
        Optional<OpenWeatherMapResponse> openWeatherMapResponseMono =
                getWeather(getBaseUrl()
                        .queryParam("lat", coordinate.getLat())
                        .queryParam("lon", coordinate.getLon()), client, OpenWeatherMapResponse.class);
        return openWeatherMapResponseMono
                .map(openWeatherMapResponse -> convertResponse(Objects.requireNonNull(openWeatherMapResponse)))
                .orElse(null);
    }


    private UriComponentsBuilder getBaseUrl() {
        return UriComponentsBuilder.fromUriString(BASE_URL)
                .queryParam("APPID", OWM_KEY)
                .queryParam("units", "metric");
    }

}
