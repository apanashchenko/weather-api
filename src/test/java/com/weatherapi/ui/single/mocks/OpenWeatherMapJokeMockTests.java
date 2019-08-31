package com.weatherapi.ui.single.mocks;

import com.codeborne.selenide.Condition;
import com.weatherapi.model.Joke;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.ui.pages.JokeMessage;
import com.weatherapi.ui.pages.OpenWeatherWidget;
import com.weatherapi.service.JokeService;
import com.weatherapi.service.OpenWeatherMapService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.refresh;
import static com.weatherapi.MockJokeFactory.getJokeResponse;
import static org.junit.jupiter.params.provider.Arguments.of;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@Slf4j
public class OpenWeatherMapJokeMockTests extends SingleMockTestBase {

    private OpenWeatherWidget openWeatherWidget = new OpenWeatherWidget();
    private Joke jokeResponse = getJokeResponse();

    @MockBean
    private OpenWeatherMapService openWeatherMapService;

    @MockBean
    private JokeService jokeService;

    @BeforeEach
    public void setUpMocks() {
        Mockito.reset(openWeatherMapService, jokeService);
        refresh();
    }

    @Test
    public void openWidgetMapJokeTest() {
        openWeatherResponse.setTemp(21);
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


    private Stream<Arguments> temperatures() {
        return Stream.of(
                of(20.0, true, openWeatherResponse, jokeResponse),
                of(19.9, false, openWeatherResponse, jokeResponse),
                of(20.1, true, openWeatherResponse, jokeResponse)
        );
    }

    @ParameterizedTest
    @MethodSource("temperatures")
    public void openWidgetMapJokeBoundaryValuesTest(double temp, boolean isJokeShouldBe,
                                      WeatherResponse weatherResponse, Joke jokeResponse) {
        weatherResponse.setTemp(temp);
        when(openWeatherMapService.getWeatherByCityName(eq(weatherResponse.getCity())))
                .thenReturn(openWeatherResponse);
        if (isJokeShouldBe) when(jokeService.getRandomJoke()).thenReturn(jokeResponse);

        openWeatherWidget
                .searchWeatherByCityName(openWeatherResponse.getCity())
                .checkWeather(openWeatherResponse);

        JokeMessage jokeMessage = new JokeMessage();
        if (isJokeShouldBe) {
            jokeMessage.joke.shouldBe(Condition.exist);
            jokeMessage.message.shouldHave(Condition.text(jokeResponse.getValue().getJoke()));
        } else {
            jokeMessage.joke.shouldNotBe(Condition.exist);

        }
    }
}
