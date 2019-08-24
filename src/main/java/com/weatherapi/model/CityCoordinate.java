package com.weatherapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
//@RequiredArgsConstructor
@NoArgsConstructor
public class CityCoordinate {

    private double lat;
    private double lon;

    public CityCoordinate(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }
}
