package com.weatherapi.service;

import com.weatherapi.model.CityCoordinate;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.model.owm.OpenWeatherMapResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static com.weatherapi.utils.AppKeys.OWM_KEY;
import static com.weatherapi.utils.ResponseConverter.convertResponse;

@RequiredArgsConstructor
@Service
@Slf4j
public class OpenWeatherMapService implements WeatherService{

//    private static final String BASE_URL =
//            "http://api.openweathermap.org/data/2.5/weather?%s&APPID=b6c205fb6377e965903f52b39ee02c01&units=metric";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
    private final WebClient client;

    @Override
    public WeatherResponse getWeatherByCityName(String city) {
        Mono<OpenWeatherMapResponse> responseSpec = client.get()
                .uri(getBaseUrl().queryParam("q", city).toUriString())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        Mono.error(new RuntimeException(clientResponse.statusCode().getReasonPhrase())))
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        Mono.error(new RuntimeException(clientResponse.statusCode().getReasonPhrase())))
                .bodyToMono(OpenWeatherMapResponse.class);

        return convertResponse(Objects.requireNonNull(responseSpec.block()));
    }

    @Override
    public WeatherResponse getWeatherByGeographicCoordinates(CityCoordinate coordinate) {
        WebClient.ResponseSpec responseSpec = client.get()
                .uri(getBaseUrl()
                .queryParam("lat", coordinate.getLat())
                .queryParam("lon", coordinate.getLon()).toUriString())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        Mono.error(new RuntimeException(clientResponse.toString())))
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        Mono.error(new RuntimeException(clientResponse.toString())));

        OpenWeatherMapResponse openWeatherMapResponse = responseSpec.bodyToMono(OpenWeatherMapResponse.class).block();

        return convertResponse(Objects.requireNonNull(openWeatherMapResponse));
    }



    private UriComponentsBuilder getBaseUrl() {
        return UriComponentsBuilder.fromUriString(BASE_URL)
                .queryParam("APPID", OWM_KEY)
                .queryParam("units", "metric");
    }

}
