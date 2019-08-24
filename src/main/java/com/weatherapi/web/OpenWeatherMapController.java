package com.weatherapi.web;


import com.weatherapi.model.CityCoordinate;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.service.OpenWeatherMapService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class OpenWeatherMapController {

    private final OpenWeatherMapService openWeatherMapService;

    @RequestMapping(value = "/owm/weather", method = RequestMethod.GET)
    public WeatherResponse getWeatherByCityId(@RequestParam(name = "city") String city) {
        return openWeatherMapService.getWeatherByCityName(city);
    }

    @RequestMapping(value = "/owm/weather", method = RequestMethod.POST)
    public WeatherResponse getWeatherByGeographicCoordinates(@RequestBody CityCoordinate cityCoordinate) {
        return openWeatherMapService.getWeatherByGeographicCoordinates(cityCoordinate);
    }

}
