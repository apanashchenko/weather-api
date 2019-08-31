package com.weatherapi.it.mock;

import com.weatherapi.model.CityCoordinate;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.service.WeatherBitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.weatherapi.MockWeatherFactory.getWeatherBitResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeatherBitControllerItMockTests {

    private WeatherResponse weatherBitResponse = getWeatherBitResponse();

    @Autowired
    protected WebTestClient webClient;

    @MockBean
    private WeatherBitService weatherBitService;

    @BeforeEach
    public void resetMocks() {
        Mockito.reset(weatherBitService);
    }


    @Test
    public void canGetTemperatureByCityWithAnyQueryParameter() {
        when(weatherBitService.getWeatherByCityName(anyString())).thenReturn(weatherBitResponse);

        this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/wb/weather")
                        .queryParam("city", "blabla")
                        .build())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(WeatherResponse.class)
                .value(response -> {
                    assertThat(response.getCity()).isEqualToIgnoringCase(weatherBitResponse.getCity());
                    assertThat(response.getDescription()).isEqualTo(weatherBitResponse.getDescription());
                    assertThat(response.getTemp()).isEqualTo(weatherBitResponse.getTemp());
                    assertThat(response.getLat()).isEqualTo(weatherBitResponse.getLat());
                    assertThat(response.getLon()).isEqualTo(weatherBitResponse.getLon());
                });
    }


    @Test
    public void canGetTemperatureByCityWithSpecificParameter() {
        when(weatherBitService.getWeatherByCityName(eq(weatherBitResponse.getCity())))
                .thenReturn(weatherBitResponse);

        this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/wb/weather")
                        .queryParam("city", weatherBitResponse.getCity())
                        .build())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(WeatherResponse.class)
                .value(response -> {
                    assertThat(response.getCity()).isEqualToIgnoringCase(weatherBitResponse.getCity());
                    assertThat(response.getDescription()).isEqualTo(weatherBitResponse.getDescription());
                    assertThat(response.getTemp()).isEqualTo(weatherBitResponse.getTemp());
                    assertThat(response.getLat()).isEqualTo(weatherBitResponse.getLat());
                    assertThat(response.getLon()).isEqualTo(weatherBitResponse.getLon());
                });
    }

    @Test
    public void canNotGetTemperatureByCityWithSpecificParameter() {
        when(weatherBitService.getWeatherByCityName(eq(weatherBitResponse.getCity())))
                .thenReturn(weatherBitResponse);

        this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/wb/weather")
                        .queryParam("city", "dnipro")
                        .build())
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    public void canGetTemperatureByCoordinateAnyQueryParameter() {
        when(weatherBitService.getWeatherByGeographicCoordinates(any())).thenReturn(weatherBitResponse);

        this.webClient.post()
                .uri("/wb/weather")
                .body(fromObject(new CityCoordinate()))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(WeatherResponse.class)
                .value(response -> {
                    assertThat(response.getCity()).isEqualToIgnoringCase(weatherBitResponse.getCity());
                    assertThat(response.getDescription()).isEqualTo(weatherBitResponse.getDescription());
                    assertThat(response.getTemp()).isEqualTo(weatherBitResponse.getTemp());
                    assertThat(response.getLat()).isEqualTo(weatherBitResponse.getLat());
                    assertThat(response.getLon()).isEqualTo(weatherBitResponse.getLon());
                });
    }

    @Test
    public void canGetTemperatureByCoordinatesWithSpecificQuery() {
        CityCoordinate coordinate = new CityCoordinate(1.1, 2.2);

        when(weatherBitService.getWeatherByGeographicCoordinates(eq(coordinate))).thenReturn(weatherBitResponse);
        this.webClient.post()
                .uri("/wb/weather")
                .body(fromObject(coordinate))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(WeatherResponse.class)
                .value(response -> {
                    assertThat(response.getCity()).isEqualToIgnoringCase(weatherBitResponse.getCity());
                    assertThat(response.getDescription()).isEqualTo(weatherBitResponse.getDescription());
                    assertThat(response.getTemp()).isEqualTo(weatherBitResponse.getTemp());
                    assertThat(response.getLat()).isEqualTo(weatherBitResponse.getLat());
                    assertThat(response.getLon()).isEqualTo(weatherBitResponse.getLon());
                });
    }

    @Test
    public void canNotGetTemperatureByCoordinatesWithSpecificQuery() {
        CityCoordinate coordinate = new CityCoordinate(1.1, 2.2);
        when(weatherBitService.getWeatherByGeographicCoordinates(eq(coordinate))).thenReturn(weatherBitResponse);

        this.webClient.post()
                .uri("/wb/weather")
                .body(fromObject(new CityCoordinate(-1.1, -2.2)))
                .exchange()
                .expectStatus()
                .isNotFound();

}

}
