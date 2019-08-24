package com.weatherapi.service;

import com.weatherapi.model.CityCoordinate;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.model.owm.OpenWeatherMapResponse;
import com.weatherapi.model.wb.WeatherBitResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static com.weatherapi.utils.AppKeys.OWM_KEY;
import static com.weatherapi.utils.ResponseConverter.convertResponse;

@RequiredArgsConstructor
@Service
public class OpenWeatherMapService implements WeatherService{

//    private static final String BASE_URL =
//            "http://api.openweathermap.org/data/2.5/weather?%s&APPID=b6c205fb6377e965903f52b39ee02c01&units=metric";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
    private final WebClient client;

    @Override
    public WeatherResponse getWeatherByCityName(String city) {
        WebClient.ResponseSpec responseSpec = client.get()
                .uri(getBaseUrl().queryParam("q", city).toUriString())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        Mono.error(new RuntimeException(clientResponse.statusCode().getReasonPhrase())))
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        Mono.error(new RuntimeException(clientResponse.statusCode().getReasonPhrase())));

        OpenWeatherMapResponse openWeatherMapResponse = responseSpec.bodyToMono(OpenWeatherMapResponse.class).block();

        return convertResponse(Objects.requireNonNull(openWeatherMapResponse));
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
