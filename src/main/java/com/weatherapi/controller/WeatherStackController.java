package com.weatherapi.controller;

import com.weatherapi.model.CityCoordinate;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.service.WeatherStackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class WeatherStackController {

    private final WeatherStackService weatherStackService;

    @GetMapping("/ws/weather")
    public ResponseEntity<WeatherResponse> getWeatherByCityId(@RequestParam(name = "city") String city) {
        WeatherResponse weatherByCityName = weatherStackService.getWeatherByCityName(city);
        if (weatherByCityName != null) {
            return ResponseEntity.ok(weatherByCityName);
        }
        return  ResponseEntity.notFound().build();
    }

    @PostMapping("/ws/weather")
    public ResponseEntity<WeatherResponse> getWeatherByGeographicCoordinates(@RequestBody CityCoordinate cityCoordinate) {
        WeatherResponse weatherByGeographicCoordinates = weatherStackService.getWeatherByGeographicCoordinates(cityCoordinate);
        if (weatherByGeographicCoordinates != null) {
            return ResponseEntity.ok(weatherByGeographicCoordinates);
        }
        return  ResponseEntity.notFound().build();
    }

}
