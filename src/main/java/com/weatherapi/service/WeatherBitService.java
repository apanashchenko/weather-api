package com.weatherapi.service;

import com.weatherapi.model.CityCoordinate;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.model.wb.WeatherBitResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;
import java.util.Optional;

import static com.weatherapi.utils.AppKeys.WB_KEY;
import static com.weatherapi.utils.ResponseConverter.convertResponse;

@RequiredArgsConstructor
@Service
@Slf4j
public class WeatherBitService implements WeatherService {

    private static final String BASE_URL = "https://api.weatherbit.io/v2.0/current";
    private final WebClient client;

    @Override
    @SuppressWarnings("unchecked")
    public WeatherResponse getWeatherByCityName(String city) {
        Optional<WeatherBitResponse> weatherBitResponse =
                getWeather(getBaseUrl().queryParam("city", city), client, WeatherBitResponse.class);
        return weatherBitResponse
                .map(response -> convertResponse(Objects.requireNonNull(response)))
                .orElse(null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public WeatherResponse getWeatherByGeographicCoordinates(CityCoordinate coordinate) {
        Optional<WeatherBitResponse> weatherBitResponse =
                getWeather(getBaseUrl().queryParam("lat", coordinate.getLat())
                        .queryParam("lon", coordinate.getLon()), client, WeatherBitResponse.class);
        return weatherBitResponse
                .map(response -> convertResponse(Objects.requireNonNull(response)))
                .orElse(null);
    }


    private UriComponentsBuilder getBaseUrl() {
        return UriComponentsBuilder.fromUriString(BASE_URL).queryParam("key", WB_KEY);
    }

}
