package com.weatherapi.it.mock;

import com.weatherapi.model.CityCoordinate;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.service.WeatherStackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.weatherapi.MockWeatherFactory.getWeatherStackResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeatherStackControllerItMockTests {

    private WeatherResponse weatherStackResponse = getWeatherStackResponse();

    @Autowired
    protected WebTestClient webClient;

    @MockBean
    private WeatherStackService weatherStackService;

    @BeforeEach
    public void resetMocks() {
        Mockito.reset(weatherStackService);
    }


    @Test
    public void canGetTemperatureByCityWithAnyQueryParameter() {
        when(weatherStackService.getWeatherByCityName(anyString())).thenReturn(weatherStackResponse);

        this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/ws/weather")
                        .queryParam("city", "blabla")
                        .build())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(WeatherResponse.class)
                .value(response -> {
                    assertThat(response.getCity()).isEqualToIgnoringCase(weatherStackResponse.getCity());
                    assertThat(response.getDescription()).isEqualTo(weatherStackResponse.getDescription());
                    assertThat(response.getTemp()).isEqualTo(weatherStackResponse.getTemp());
                    assertThat(response.getLat()).isEqualTo(weatherStackResponse.getLat());
                    assertThat(response.getLon()).isEqualTo(weatherStackResponse.getLon());
                });
    }


    @Test
    public void canGetTemperatureByCityWithSpecificParameter() {
        when(weatherStackService.getWeatherByCityName(eq(weatherStackResponse.getCity()))).thenReturn(weatherStackResponse);

        this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/ws/weather")
                        .queryParam("city", weatherStackResponse.getCity())
                        .build())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(WeatherResponse.class)
                .value(response -> {
                    assertThat(response.getCity()).isEqualToIgnoringCase(weatherStackResponse.getCity());
                    assertThat(response.getDescription()).isEqualTo(weatherStackResponse.getDescription());
                    assertThat(response.getTemp()).isEqualTo(weatherStackResponse.getTemp());
                    assertThat(response.getLat()).isEqualTo(weatherStackResponse.getLat());
                    assertThat(response.getLon()).isEqualTo(weatherStackResponse.getLon());
                });
    }

    @Test
    public void canNotGetTemperatureByCityWithSpecificParameter() {
        when(weatherStackService.getWeatherByCityName(eq(weatherStackResponse.getCity()))).thenReturn(weatherStackResponse);

        this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/ws/weather")
                        .queryParam("city", "dnipro")
                        .build())
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    public void canGetTemperatureByCoordinateAnyQueryParameter() {
        when(weatherStackService.getWeatherByGeographicCoordinates(any())).thenReturn(weatherStackResponse);

        this.webClient.post()
                .uri("/ws/weather")
                .body(fromObject(new CityCoordinate()))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(WeatherResponse.class)
                .value(response -> {
                    assertThat(response.getCity()).isEqualToIgnoringCase(weatherStackResponse.getCity());
                    assertThat(response.getDescription()).isEqualTo(weatherStackResponse.getDescription());
                    assertThat(response.getTemp()).isEqualTo(weatherStackResponse.getTemp());
                    assertThat(response.getLat()).isEqualTo(weatherStackResponse.getLat());
                    assertThat(response.getLon()).isEqualTo(weatherStackResponse.getLon());
                });
    }

    @Test
    public void canGetTemperatureByCoordinatesWithSpecificQuery() {
        CityCoordinate coordinate = new CityCoordinate(1.1, 2.2);
        when(weatherStackService.getWeatherByGeographicCoordinates(eq(coordinate))).thenReturn(weatherStackResponse);

        this.webClient.post()
                .uri("/ws/weather")
                .body(fromObject(coordinate))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(WeatherResponse.class)
                .value(response -> {
                    assertThat(response.getCity()).isEqualToIgnoringCase(weatherStackResponse.getCity());
                    assertThat(response.getDescription()).isEqualTo(weatherStackResponse.getDescription());
                    assertThat(response.getTemp()).isEqualTo(weatherStackResponse.getTemp());
                    assertThat(response.getLat()).isEqualTo(weatherStackResponse.getLat());
                    assertThat(response.getLon()).isEqualTo(weatherStackResponse.getLon());
                });
    }

    @Test
    public void canNotGetTemperatureByCoordinatesWithSpecificQuery() {
        CityCoordinate coordinate = new CityCoordinate(1.1, 2.2);
        when(weatherStackService.getWeatherByGeographicCoordinates(eq(coordinate))).thenReturn(weatherStackResponse);

        this.webClient.post()
                .uri("/ws/weather")
                .body(fromObject(new CityCoordinate(-1.1, -2.2)))
                .exchange()
                .expectStatus()
                .isNotFound();

    }

}
