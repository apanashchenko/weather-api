package com.weatherapi.model.apixu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by alpa on 2019-06-04
 */
@Data
public class Current {

    @JsonProperty("observation_time")
    private String observationTime;
    @JsonProperty("temperature")
    private Integer temperature;
    @JsonProperty("weather_code")
    private Integer weatherCode;
    @JsonProperty("weather_icons")
    private List<String> weatherIcons = null;
    @JsonProperty("weather_descriptions")
    private List<String> weatherDescriptions = null;
    @JsonProperty("wind_speed")
    private Integer windSpeed;
    @JsonProperty("wind_degree")
    private Integer windDegree;
    @JsonProperty("wind_dir")
    private String windDir;
    @JsonProperty("pressure")
    private Integer pressure;
    @JsonProperty("precip")
    private Integer precip;
    @JsonProperty("humidity")
    private Integer humidity;
    @JsonProperty("cloudcover")
    private Integer cloudcover;
    @JsonProperty("feelslike")
    private Integer feelslike;
    @JsonProperty("uv_index")
    private Integer uvIndex;
    @JsonProperty("visibility")
    private Integer visibility;
}
