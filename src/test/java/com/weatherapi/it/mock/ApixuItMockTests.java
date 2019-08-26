package com.weatherapi.it.mock;

import com.weatherapi.model.CityCoordinate;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.service.ApixuService;
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

import static com.weatherapi.MockWeatherFactory.getApixuResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApixuItMockTests {

    private WeatherResponse weatherBitResponse = getApixuResponse();

    @Autowired
    protected WebTestClient webClient;

    @MockBean
    private ApixuService apixuService;

    @BeforeEach
    public void resetMocks() {
        Mockito.reset(apixuService);
    }


    @Test
    public void canGetTemperatureByCityWithAnyQueryParameter() {
        when(apixuService.getWeatherByCityName(anyString())).thenReturn(weatherBitResponse);

        this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/apixu/weather")
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
        when(apixuService.getWeatherByCityName(argThat(matchCity(weatherBitResponse.getCity()))))
                .thenReturn(weatherBitResponse);

        this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/apixu/weather")
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
        when(apixuService.getWeatherByCityName(argThat(matchCity(weatherBitResponse.getCity()))))
                .thenReturn(weatherBitResponse);

        this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/apixu/weather")
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
        when(apixuService.getWeatherByGeographicCoordinates(any())).thenReturn(weatherBitResponse);

        this.webClient.post()
                .uri("/apixu/weather")
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

        when(apixuService.getWeatherByGeographicCoordinates(argThat(matchCoordinate(coordinate))))
                .thenReturn(weatherBitResponse);
        this.webClient.post()
                .uri("/apixu/weather")
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

        when(apixuService.getWeatherByGeographicCoordinates(argThat(matchCoordinate(coordinate))))
                .thenReturn(weatherBitResponse);
        this.webClient.post()
                .uri("/apixu/weather")
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
