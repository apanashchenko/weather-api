package com.weatherapi.web;


import com.weatherapi.model.CityCoordinate;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.service.WeatherBitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class WeatherBitController {

    private final WeatherBitService weatherBitService;

    @RequestMapping(value = "/wb/weather", method = RequestMethod.GET)
    public WeatherResponse getWeatherByCityId(@RequestParam(name = "city") String city) {
        return weatherBitService.getWeatherByCityName(city);
    }

    @RequestMapping(value = "/wb/weather", method = RequestMethod.POST)
    public WeatherResponse getWeatherByGeographicCoordinates(@RequestBody CityCoordinate cityCoordinate) {
        return weatherBitService.getWeatherByGeographicCoordinates(cityCoordinate);
    }

}
