package com.weatherapi.web;


import com.weatherapi.model.Joke;
import com.weatherapi.service.JokeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class JokeController {

    private final JokeService jokeService;

    @RequestMapping(value = "/joke", method = RequestMethod.GET)
    public Joke getJoke() {
        return jokeService.getRandomJoke();
    }


}
