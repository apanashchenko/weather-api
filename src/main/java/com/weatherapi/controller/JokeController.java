package com.weatherapi.controller;

import com.weatherapi.model.Joke;
import com.weatherapi.service.JokeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JokeController {

    private final JokeService jokeService;

    @GetMapping("/joke")
    public ResponseEntity<Joke> getJoke() {
        Joke randomJoke = jokeService.getRandomJoke();
        if (randomJoke != null) {
            return ResponseEntity.ok(randomJoke);
        }
        return ResponseEntity.notFound().build();
    }


}
