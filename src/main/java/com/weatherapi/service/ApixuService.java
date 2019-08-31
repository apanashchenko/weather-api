package com.weatherapi.service;

import com.weatherapi.model.CityCoordinate;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.model.apixu.ApixuWeatherResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static com.weatherapi.utils.AppKeys.APIXU_KEY;
import static com.weatherapi.utils.ResponseConverter.convertResponse;

@RequiredArgsConstructor
@Service
@Slf4j
public class ApixuService implements WeatherService {

//    private static final String BASE_URL = "http://api.apixu.com/v1/current.json?key=1df40b6f8fa646b695d84120190406&%s";
    private static final String BASE_URL = "http://api.apixu.com/v1/current.json";

    private RestTemplate restTemplate = new RestTemplateBuilder().build();
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

        ApixuWeatherResponse apixuWeatherResponse = responseSpec.bodyToMono(ApixuWeatherResponse.class).block();

//        String url = getBaseUrl().queryParam("q", city).toUriString();
//
//        ResponseEntity<ApixuWeatherResponse> response = restTemplate.getForEntity(url, ApixuWeatherResponse.class);
//        if (response.getStatusCodeValue() != 200) {
//            throw new RuntimeException(response.getBody().toString());
//        }
        return convertResponse(Objects.requireNonNull(apixuWeatherResponse));
    }

    @Override
    public WeatherResponse getWeatherByGeographicCoordinates(CityCoordinate coordinate) {
        String url = getBaseUrl().queryParam("q", coordinate.getLat(), coordinate.getLon()).toUriString();

        ResponseEntity<ApixuWeatherResponse> response =  restTemplate.getForEntity(url, ApixuWeatherResponse.class);
        if (response.getStatusCodeValue() != 200) {
            throw new RuntimeException(response.getBody().toString());
        }
        return convertResponse(response.getBody());
    }

    private UriComponentsBuilder getBaseUrl() {
        return UriComponentsBuilder.fromUriString(BASE_URL).queryParam("key", APIXU_KEY);
    }

}
