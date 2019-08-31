package com.weatherapi.web;


import com.weatherapi.model.Joke;
import com.weatherapi.service.JokeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JokeController {

    private final JokeService jokeService;

    @RequestMapping(value = "/joke", method = RequestMethod.GET)
    public ResponseEntity<Joke> getJoke() {
        Joke randomJoke = jokeService.getRandomJoke();
        if (randomJoke != null) {
            return ResponseEntity.ok(randomJoke);
        }
        return ResponseEntity.notFound().build();
    }


}
