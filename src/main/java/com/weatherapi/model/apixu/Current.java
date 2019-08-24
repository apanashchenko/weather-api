package com.weatherapi.model.apixu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by alpa on 2019-06-04
 */
@Data
public class Current {

    @JsonProperty("last_updated_epoch")
    private Integer lastUpdatedEpoch;
    @JsonProperty("last_updated")
    private String lastUpdated;
    @JsonProperty("temp_c")
    private Double tempC;
    @JsonProperty("temp_f")
    private Double tempF;
    @JsonProperty("is_day")
    private Integer isDay;
    @JsonProperty("condition")
    private Condition condition;
    @JsonProperty("wind_mph")
    private Double windMph;
    @JsonProperty("wind_kph")
    private Double windKph;
    @JsonProperty("wind_degree")
    private Integer windDegree;
    @JsonProperty("wind_dir")
    private String windDir;
    @JsonProperty("pressure_mb")
    private Double pressureMb;
    @JsonProperty("pressure_in")
    private Double pressureIn;
    @JsonProperty("precip_mm")
    private Double precipMm;
    @JsonProperty("precip_in")
    private Double precipIn;
    @JsonProperty("humidity")
    private Integer humidity;
    @JsonProperty("cloud")
    private Integer cloud;
    @JsonProperty("feelslike_c")
    private Double feelslikeC;
    @JsonProperty("feelslike_f")
    private Double feelslikeF;
    @JsonProperty("vis_km")
    private Double visKm;
    @JsonProperty("vis_miles")
    private Double visMiles;
    @JsonProperty("uv")
    private Double uv;
    @JsonProperty("gust_mph")
    private Double gustMph;
    @JsonProperty("gust_kph")
    private Double gustKph;
}
