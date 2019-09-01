package com.weatherapi.controller;

import com.weatherapi.model.CityCoordinate;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.service.ApixuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ApixuController {

    private final ApixuService apixuService;

    @GetMapping("/apixu/weather")
    public ResponseEntity<WeatherResponse> getWeatherByCityId(@RequestParam(name = "city") String city) {
        WeatherResponse weatherByCityName = apixuService.getWeatherByCityName(city);
        if (weatherByCityName != null) {
            return ResponseEntity.ok(weatherByCityName);
        }
        return  ResponseEntity.notFound().build();
    }

    @PostMapping("/apixu/weather")
    public ResponseEntity<WeatherResponse> getWeatherByGeographicCoordinates(@RequestBody CityCoordinate cityCoordinate) {
        WeatherResponse weatherByGeographicCoordinates = apixuService.getWeatherByGeographicCoordinates(cityCoordinate);
        if (weatherByGeographicCoordinates != null) {
            return ResponseEntity.ok(weatherByGeographicCoordinates);
        }
        return  ResponseEntity.notFound().build();
    }

}
