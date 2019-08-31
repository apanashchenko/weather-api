package com.weatherapi.service;

import com.weatherapi.model.CityCoordinate;
import com.weatherapi.model.WeatherResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.Optional;


public interface WeatherService <T extends WeatherService<T>> {

    WeatherResponse getWeatherByCityName(String city);

    WeatherResponse getWeatherByGeographicCoordinates(CityCoordinate cityCoordinate);

    default Optional<?> getWeather(UriComponentsBuilder uriComponentsBuilder, WebClient client, Class clazz) {
        return client.get()
                .uri(uriComponentsBuilder.toUriString())
                .exchange().flatMap(resp -> {
                    if (resp.statusCode().is2xxSuccessful()) {
                        return resp.bodyToMono(clazz);
                    } else {
                        return Mono.empty();
                    }
                }).blockOptional();
    }

}

