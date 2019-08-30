package com.weatherapi.it;

import com.weatherapi.model.Joke;
import com.weatherapi.model.JokeValue;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.service.JokeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by alpa on 2019-08-29
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JokeControllerItTests extends BaseItTest {

    @Test
    public void canGetJokeTest() {
        this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/joke").build())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Joke.class)
                .value(response -> {
                    assertThat(response.getType()).isEqualToIgnoringCase("success");
                    JokeValue value = response.getValue();
                    assertThat(value.getId()).isNotEqualTo(0);
                    assertThat(value.getJoke()).isNotNull().isNotEmpty();
                    assertThat(value.getCategories()).isNullOrEmpty();
                });
    }
}
