package com.weatherapi.it.mock;

import com.weatherapi.model.CityCoordinate;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.service.OpenWeatherMapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Objects;

import static com.weatherapi.MockWeatherFactory.getOpenWeatherResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OpenWeatherMapItMockTests {

    private WeatherResponse openWeatherResponse = getOpenWeatherResponse();

    @Autowired
    protected WebTestClient webClient;

    @MockBean
    private OpenWeatherMapService openWeatherMapService;

    @BeforeEach
    public void resetMocks() {
        Mockito.reset(openWeatherMapService);
    }


    @Test
    public void canGetTemperatureByCityWithAnyQueryParameter() {
        when(openWeatherMapService.getWeatherByCityName(anyString())).thenReturn(openWeatherResponse);

        this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/owm/weather")
                        .queryParam("city", "blabla")
                        .build())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(WeatherResponse.class)
                .value(response -> {
                    assertThat(response.getCity()).isEqualToIgnoringCase(openWeatherResponse.getCity());
                    assertThat(response.getDescription()).isEqualTo(openWeatherResponse.getDescription());
                    assertThat(response.getTemp()).isEqualTo(openWeatherResponse.getTemp());
                    assertThat(response.getLat()).isEqualTo(openWeatherResponse.getLat());
                    assertThat(response.getLon()).isEqualTo(openWeatherResponse.getLon());
                });
    }


    @Test
    public void canGetTemperatureByCityWithSpecificParameter() {
        when(openWeatherMapService.getWeatherByCityName(argThat(matchCity(openWeatherResponse.getCity()))))
                .thenReturn(openWeatherResponse);

        this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/owm/weather")
                        .queryParam("city", openWeatherResponse.getCity())
                        .build())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(WeatherResponse.class)
                .value(response -> {
                    assertThat(response.getCity()).isEqualToIgnoringCase(openWeatherResponse.getCity());
                    assertThat(response.getDescription()).isEqualTo(openWeatherResponse.getDescription());
                    assertThat(response.getTemp()).isEqualTo(openWeatherResponse.getTemp());
                    assertThat(response.getLat()).isEqualTo(openWeatherResponse.getLat());
                    assertThat(response.getLon()).isEqualTo(openWeatherResponse.getLon());
                });
    }

    @Test
    public void canNotGetTemperatureByCityWithSpecificParameter() {
        when(openWeatherMapService.getWeatherByCityName(argThat(matchCity(openWeatherResponse.getCity()))))
                .thenReturn(openWeatherResponse);

        this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/owm/weather")
                        .queryParam("city", "dnipro")
                        .build())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(WeatherResponse.class)
                .value(response -> assertThat(response).isNull());
    }

    @Test
    public void canGetTemperatureByCoordinateAnyQueryParameter() {
        when(openWeatherMapService.getWeatherByGeographicCoordinates(any())).thenReturn(openWeatherResponse);

        this.webClient.post()
                .uri("/owm/weather")
                .body(fromObject(new CityCoordinate()))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(WeatherResponse.class)
                .value(response -> {
                    assertThat(response.getCity()).isEqualToIgnoringCase(openWeatherResponse.getCity());
                    assertThat(response.getDescription()).isEqualTo(openWeatherResponse.getDescription());
                    assertThat(response.getTemp()).isEqualTo(openWeatherResponse.getTemp());
                    assertThat(response.getLat()).isEqualTo(openWeatherResponse.getLat());
                    assertThat(response.getLon()).isEqualTo(openWeatherResponse.getLon());
                });
    }

    @Test
    public void canGetTemperatureByCoordinatesWithSpecificQuery() {
        CityCoordinate coordinate = new CityCoordinate(1.1, 2.2);

        when(openWeatherMapService.getWeatherByGeographicCoordinates(argThat(matchCoordinate(coordinate))))
                .thenReturn(openWeatherResponse);
        this.webClient.post()
                .uri("/owm/weather")
                .body(fromObject(coordinate))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(WeatherResponse.class)
                .value(response -> {
                    assertThat(response.getCity()).isEqualToIgnoringCase(openWeatherResponse.getCity());
                    assertThat(response.getDescription()).isEqualTo(openWeatherResponse.getDescription());
                    assertThat(response.getTemp()).isEqualTo(openWeatherResponse.getTemp());
                    assertThat(response.getLat()).isEqualTo(openWeatherResponse.getLat());
                    assertThat(response.getLon()).isEqualTo(openWeatherResponse.getLon());
                });
    }

    @Test
    public void canNotGetTemperatureByCoordinatesWithSpecificQuery() {
        CityCoordinate coordinate = new CityCoordinate(1.1, 2.2);

        when(openWeatherMapService.getWeatherByGeographicCoordinates(argThat(matchCoordinate(coordinate))))
                .thenReturn(openWeatherResponse);
        this.webClient.post()
                .uri("/owm/weather")
                .body(fromObject(new CityCoordinate(-1.1, -2.2)))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(WeatherResponse.class)
                .value(response -> assertThat(response).isNull());
}

    protected ArgumentMatcher<String> matchCity(final String city) {
        return arg -> Objects.nonNull(city) && city.equals(arg);
    }

    protected ArgumentMatcher<CityCoordinate> matchCoordinate(final CityCoordinate cityCoordinate) {
        return arg -> Objects.nonNull(cityCoordinate) && cityCoordinate.equals(arg);
    }


}
