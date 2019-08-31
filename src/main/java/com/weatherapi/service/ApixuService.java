package com.weatherapi.service;

import com.weatherapi.model.CityCoordinate;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.model.apixu.ApixuWeatherResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

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
    public WeatherResponse getWeatherByCityName(String city) {
        return getWeather(getBaseUrl().queryParam("q", city));
    }

    @Override
    public WeatherResponse getWeatherByGeographicCoordinates(CityCoordinate coordinate) {
        return getWeather(getBaseUrl().queryParam("q", coordinate.getLat(), coordinate.getLon()));
    }

    private UriComponentsBuilder getBaseUrl() {
        return UriComponentsBuilder.fromUriString(BASE_URL).queryParam("key", APIXU_KEY);
    }

    private WeatherResponse getWeather(UriComponentsBuilder uriComponentsBuilder) {
        Optional<ApixuWeatherResponse> apixuWeatherResponse = client.get()
                .uri(uriComponentsBuilder.toUriString())
                .exchange()
                .flatMap(resp -> {
                    if (resp.statusCode().is2xxSuccessful()) {
                        return resp.bodyToMono(ApixuWeatherResponse.class);
                    } else {
                        return Mono.empty();
                    }
                }).blockOptional();

        return apixuWeatherResponse
                .map(response -> convertResponse(Objects.requireNonNull(response)))
                .orElse(null);
    }


}
