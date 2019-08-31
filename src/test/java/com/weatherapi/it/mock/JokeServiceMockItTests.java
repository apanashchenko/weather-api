package com.weatherapi.it.mock;

import com.weatherapi.it.BaseItTest;
import com.weatherapi.model.Joke;
import com.weatherapi.model.JokeValue;
import com.weatherapi.service.JokeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.weatherapi.MockJokeFactory.getJokeResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by alpa on 2019-08-29
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JokeServiceMockItTests extends BaseItTest {

    private Joke jokeResponse = getJokeResponse();

    @MockBean
    private JokeService jokeService;

    @BeforeEach
    public void resetMocks() {
        Mockito.reset(jokeService);
    }


    @Test
    public void canGetJokeTest() {
        when(jokeService.getRandomJoke()).thenReturn(jokeResponse);

        this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/joke").build())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Joke.class)
                .value(response -> {
                    assertThat(response.getType()).isEqualTo(jokeResponse.getType());
                    JokeValue value = response.getValue();
                    assertThat(value.getId()).isEqualTo(jokeResponse.getValue().getId());
                    assertThat(value.getJoke()).isEqualTo(jokeResponse.getValue().getJoke());
                    assertThat(value.getCategories()).isEqualTo(jokeResponse.getValue().getCategories());
                });
    }

    @Test
    public void canNotFoundJokeTest() {
        when(jokeService.getRandomJoke()).thenReturn(null);

        this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/joke").build())
                .exchange()
                .expectStatus()
                .isNotFound();
    }
}
