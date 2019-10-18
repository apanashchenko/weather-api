package com.weatherapi.ui.single.spy;

import com.codeborne.selenide.Condition;
import com.weatherapi.model.Joke;
import com.weatherapi.model.JokeValue;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.service.JokeService;
import com.weatherapi.service.OpenWeatherMapService;
import com.weatherapi.ui.pages.JokeMessage;
import com.weatherapi.ui.pages.OpenWeatherWidget;
import com.weatherapi.ui.single.SingleTestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


public class OpenWeatherMapJokeSpyTests extends SingleTestBase {

    private OpenWeatherWidget openWeatherWidget = new OpenWeatherWidget();
    private Joke jokeResponse;
    private WeatherResponse openWeatherResponse;

    @MockBean
    private OpenWeatherMapService openWeatherMapService;

    @SpyBean
    private JokeService jokeService;

    @BeforeEach
    public void setUpMocks() {
        setUpResponse();
        setUpJoke();
    }

    @AfterEach
    public void resetMocks() {
        Mockito.reset(openWeatherMapService, jokeService);
    }


    @Test
    public void openWidgetMapSpyJokeTest() {
        when(openWeatherMapService.getWeatherByCityName(eq(openWeatherResponse.getCity())))
                .thenReturn(openWeatherResponse);

        openWeatherWidget
                .searchWeatherByCityName(openWeatherResponse.getCity())
                .checkWeather(openWeatherResponse);

        JokeMessage jokeMessage = new JokeMessage();
        jokeMessage.joke.shouldBe(Condition.exist);
        jokeMessage.message.shouldNotBe(Condition.empty);
    }

    @Test
    public void openWidgetMapSpyAndStubJokeTest() {
        when(openWeatherMapService.getWeatherByCityName(eq(openWeatherResponse.getCity())))
                .thenReturn(openWeatherResponse);
        when(jokeService.getRandomJoke()).thenReturn(jokeResponse);

        openWeatherWidget
                .searchWeatherByCityName(openWeatherResponse.getCity())
                .checkWeather(openWeatherResponse);

        JokeMessage jokeMessage = new JokeMessage();
        jokeMessage.joke.shouldBe(Condition.exist);
        jokeMessage.message.shouldHave(Condition.text(jokeResponse.getValue().getJoke()));
    }


    private void setUpResponse() {
        openWeatherResponse = new WeatherResponse();
        openWeatherResponse.setCity("OWP City");
        openWeatherResponse.setDescription("OWP cool");
        openWeatherResponse.setTemp(21);
        openWeatherResponse.setLat(55.11);
        openWeatherResponse.setLon(43.52);
    }

    private void setUpJoke() {
        jokeResponse = new Joke();
        jokeResponse.setType("success");

        JokeValue value = new JokeValue();
        value.setId(1);
        value.setCategories(Arrays.asList("hoho", "blabla"));
        value.setJoke("Your joke is shit =)");
        value.setImg("https://i.gifer.com/5Btj.gif");

        jokeResponse.setValue(value);
    }
}
