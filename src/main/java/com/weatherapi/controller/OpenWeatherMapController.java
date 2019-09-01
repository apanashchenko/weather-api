package com.weatherapi.controller;

import com.weatherapi.model.CityCoordinate;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.service.OpenWeatherMapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OpenWeatherMapController {

    private final OpenWeatherMapService openWeatherMapService;

    @GetMapping("/owm/weather")
    public ResponseEntity<WeatherResponse> getWeatherByCityId(@RequestParam(name = "city") String city) {
        WeatherResponse weatherByCityName = openWeatherMapService.getWeatherByCityName(city);
        if (weatherByCityName != null) {
            return ResponseEntity.ok(weatherByCityName);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/owm/weather")
    public ResponseEntity<WeatherResponse> getWeatherByGeographicCoordinates(@RequestBody CityCoordinate cityCoordinate) {
        WeatherResponse weatherByCityName = openWeatherMapService.getWeatherByGeographicCoordinates(cityCoordinate);
        if (weatherByCityName != null) {
            return ResponseEntity.ok(weatherByCityName);
        }
        return ResponseEntity.notFound().build();
    }

}
