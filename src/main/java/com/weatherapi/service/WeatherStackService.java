package com.weatherapi.service;

import com.weatherapi.model.CityCoordinate;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.model.apixu.WeatherStackResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;
import java.util.Optional;

import static com.weatherapi.utils.AppKeys.WS_KEY;
import static com.weatherapi.utils.ResponseConverter.convertResponse;

@RequiredArgsConstructor
@Service
@Slf4j
public class WeatherStackService implements WeatherService {

    private static final String BASE_URL = "http://api.weatherstack.com/current";
    private final WebClient client;

    @Override
    @SuppressWarnings("unchecked")
    public WeatherResponse getWeatherByCityName(String city) {
        Optional<WeatherStackResponse> apixuWeatherResponse =
                getWeather(getBaseUrl().queryParam("query", city), client, WeatherStackResponse.class);
        return apixuWeatherResponse
                .map(response -> convertResponse(Objects.requireNonNull(response)))
                .orElse(null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public WeatherResponse getWeatherByGeographicCoordinates(CityCoordinate coordinate) {
        Optional<WeatherStackResponse> apixuWeatherResponse =
                getWeather(getBaseUrl().queryParam("query", coordinate.getLat(), coordinate.getLon()),
                        client, WeatherStackResponse.class);
        return apixuWeatherResponse
                .map(response -> convertResponse(Objects.requireNonNull(response)))
                .orElse(null);
    }


    private UriComponentsBuilder getBaseUrl() {
        return UriComponentsBuilder.fromUriString(BASE_URL)
                .queryParam("access_key", WS_KEY);
    }

}
