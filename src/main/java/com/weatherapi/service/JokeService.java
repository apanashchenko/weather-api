package com.weatherapi.service;

import com.weatherapi.model.Joke;
import com.weatherapi.model.owm.OpenWeatherMapResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

/**
 * Created by alpa on 2019-08-29
 */

@RequiredArgsConstructor
@Service
@Slf4j
public class JokeService {

    private static final String BASE_URL = "http://api.icndb.com/jokes/random";

    private final WebClient client;

    public Joke getRandomJoke() {
        return client.get()
                .uri(UriComponentsBuilder.fromUriString(BASE_URL).toUriString())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        Mono.error(new RuntimeException(clientResponse.statusCode().getReasonPhrase())))
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        Mono.error(new RuntimeException(clientResponse.statusCode().getReasonPhrase())))
                .bodyToMono(Joke.class).block();
    }

}
