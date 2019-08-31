package com.weatherapi.service;

import com.weatherapi.model.Joke;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.Optional;

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
        Optional<Joke> jokeMono = client.get()
                .uri(UriComponentsBuilder.fromUriString(BASE_URL).toUriString())
                .exchange()
                .flatMap(resp -> {
                    if (resp.statusCode().is2xxSuccessful()) {
                        return resp.bodyToMono(Joke.class);
                    } else {
                        return Mono.empty();
                    }
                }).blockOptional();
        return jokeMono.orElse(null);


    }

}
