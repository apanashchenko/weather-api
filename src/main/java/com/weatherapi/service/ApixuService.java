package com.weatherapi.service;

import com.weatherapi.model.CityCoordinate;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.model.apixu.ApixuWeatherResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;
import java.util.Optional;

import static com.weatherapi.utils.AppKeys.APIXU_KEY;
import static com.weatherapi.utils.ResponseConverter.convertResponse;

@RequiredArgsConstructor
@Service
@Slf4j
public class ApixuService implements WeatherService {

    private static final String BASE_URL = "http://api.apixu.com/v1/current.json";
    private final WebClient client;

    @Override
    @SuppressWarnings("unchecked")
    public WeatherResponse getWeatherByCityName(String city) {
        Optional<ApixuWeatherResponse> apixuWeatherResponse =
                getWeather(getBaseUrl().queryParam("q", city), client, ApixuWeatherResponse.class);
        return apixuWeatherResponse
                .map(response -> convertResponse(Objects.requireNonNull(response)))
                .orElse(null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public WeatherResponse getWeatherByGeographicCoordinates(CityCoordinate coordinate) {
        Optional<ApixuWeatherResponse> apixuWeatherResponse =
                getWeather(getBaseUrl().queryParam("q", coordinate.getLat(), coordinate.getLon()),
                        client, ApixuWeatherResponse.class);
        return apixuWeatherResponse
                .map(response -> convertResponse(Objects.requireNonNull(response)))
                .orElse(null);
    }


    private UriComponentsBuilder getBaseUrl() {
        return UriComponentsBuilder.fromUriString(BASE_URL).queryParam("key", APIXU_KEY);
    }

}
