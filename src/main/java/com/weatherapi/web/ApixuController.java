package com.weatherapi.web;


import com.weatherapi.model.CityCoordinate;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.service.ApixuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ApixuController {

    private final ApixuService apixuService;

    @RequestMapping(value = "/apixu/weather", method = RequestMethod.GET)
    public WeatherResponse getWeatherByCityId(@RequestParam(name = "city") String city) {
        return apixuService.getWeatherByCityName(city);
    }

    @RequestMapping(value = "/apixu/weather", method = RequestMethod.POST)
    public WeatherResponse getWeatherByGeographicCoordinates(@RequestBody CityCoordinate cityCoordinate) {
        return apixuService.getWeatherByGeographicCoordinates(cityCoordinate);
    }

}
