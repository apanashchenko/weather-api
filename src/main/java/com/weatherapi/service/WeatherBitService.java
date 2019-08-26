package com.weatherapi.service;

import com.weatherapi.model.CityCoordinate;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.model.wb.WeatherBitResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static com.weatherapi.utils.AppKeys.WB_KEY;
import static com.weatherapi.utils.ResponseConverter.convertResponse;

@RequiredArgsConstructor
@Service
@Slf4j
public class WeatherBitService implements WeatherService {

//    private static final String BASE_URL = "https://api.weatherbit.io/v2.0/current?%s&key=1595035ccf514b3cb19a785f58765b44";
    private static final String BASE_URL = "https://api.weatherbit.io/v2.0/current";
    private final WebClient client;

    @Override
    public WeatherResponse getWeatherByCityName(String city) {
        WebClient.ResponseSpec responseSpec = client.get()
                .uri(getBaseUrl().queryParam("city", city)
                .toUriString())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        Mono.error(new RuntimeException(clientResponse.statusCode().getReasonPhrase()))
                )
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        Mono.error(new RuntimeException(clientResponse.statusCode().getReasonPhrase()))
                );
        WeatherBitResponse weatherBitResponse = responseSpec.bodyToMono(WeatherBitResponse.class).block();

        return convertResponse(Objects.requireNonNull(weatherBitResponse));
    }

    @Override
    public WeatherResponse getWeatherByGeographicCoordinates(CityCoordinate coordinate) {
        WebClient.ResponseSpec responseSpec = client.get()
                .uri(getBaseUrl().queryParam("lat", coordinate.getLat())
                .queryParam("lon", coordinate.getLon()).toUriString())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        Mono.error(new RuntimeException(clientResponse.toString()))
                )
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        Mono.error(new RuntimeException(clientResponse.toString()))
                );
        WeatherBitResponse weatherBitResponse = responseSpec.bodyToMono(WeatherBitResponse.class).block();

        return convertResponse(Objects.requireNonNull(weatherBitResponse));
    }

    private UriComponentsBuilder getBaseUrl() {
        return UriComponentsBuilder.fromUriString(BASE_URL).queryParam("key", WB_KEY);
    }

}
