package com.weatherapi.it;

import com.weatherapi.model.CityCoordinate;
import com.weatherapi.model.WeatherResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeatherBitTests {

    @Autowired
    private WebTestClient webClient;

    private static Stream<Arguments> cities() {
        return Stream.of(
                of("dnipro"),
                of("rome"),
                of("chicago")
        );
    }

    @ParameterizedTest
    @MethodSource("cities")
    public void canGetTemperatureByCity(String city) {
		this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/wb/weather")
                        .queryParam("city", city)
                        .build())
                .exchange()
                .expectStatus()
				.isOk()
                .expectBody(WeatherResponse.class)
                .value(response -> {
                    assertThat(response.getCity()).isEqualToIgnoringCase(city);
                    assertThat(response.getDescription()).isNotEmpty();
                    assertThat(response.getTemp()).isNotNull();
                    assertThat(response.getLat()).isNotNull();
                    assertThat(response.getLon()).isNotNull();
                });
    }

    @Test
    public void canGetTemperatureByCoordinates() {
        double lat = 48.47;
        double lon = 35.04;

        CityCoordinate coordinate = new CityCoordinate(lat, lon);
        coordinate.setLat(lat);
        coordinate.setLon(lon);

		this.webClient.post()
                .uri("/wb/weather")
                .body(fromObject(coordinate))
                .exchange()
                .expectStatus()
				.isOk()
                .expectBody(WeatherResponse.class)
                .value(response -> {
                    assertThat(response.getCity()).isEqualToIgnoringCase("dnipro");
                    assertThat(response.getDescription()).isNotEmpty();
                    assertThat(response.getTemp()).isNotNull();
                    assertThat(response.getLat()).isEqualTo(lat);
                    assertThat(response.getLon()).isEqualTo(lon);
                });
    }

}
