package com.weatherapi.web;


import com.weatherapi.model.CityCoordinate;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.service.WeatherBitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class WeatherBitController {

    private final WeatherBitService weatherBitService;

    @RequestMapping(value = "/wb/weather", method = RequestMethod.GET)
    public ResponseEntity<WeatherResponse> getWeatherByCityId(@RequestParam(name = "city") String city) {
        WeatherResponse weatherByCityName = weatherBitService.getWeatherByCityName(city);
        if (weatherByCityName != null) {
            return ResponseEntity.ok(weatherByCityName);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/wb/weather", method = RequestMethod.POST)
    public ResponseEntity<WeatherResponse> getWeatherByGeographicCoordinates(@RequestBody CityCoordinate cityCoordinate) {
        WeatherResponse weatherByGeographicCoordinates = weatherBitService.getWeatherByGeographicCoordinates(cityCoordinate);
        if (weatherByGeographicCoordinates != null) {
            return ResponseEntity.ok(weatherByGeographicCoordinates);
        }
        return ResponseEntity.notFound().build();
    }

}
