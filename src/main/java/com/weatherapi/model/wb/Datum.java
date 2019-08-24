package com.weatherapi.model.wb;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Datum {

    @JsonProperty("rh")
    private Integer rh;
    @JsonProperty("pod")
    private String pod;
    @JsonProperty("lon")
    private Double lon;
    @JsonProperty("pres")
    private Double pres;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("ob_time")
    private String obTime;
    @JsonProperty("country_code")
    private String countryCode;
    @JsonProperty("clouds")
    private Integer clouds;
    @JsonProperty("ts")
    private Integer ts;
    @JsonProperty("solar_rad")
    private Double solarRad;
    @JsonProperty("state_code")
    private String stateCode;
    @JsonProperty("city_name")
    private String cityName;
    @JsonProperty("wind_spd")
    private Integer windSpd;
    @JsonProperty("last_ob_time")
    private String lastObTime;
    @JsonProperty("wind_cdir_full")
    private String windCdirFull;
    @JsonProperty("wind_cdir")
    private String windCdir;
    @JsonProperty("slp")
    private Integer slp;
    @JsonProperty("vis")
    private Integer vis;
    @JsonProperty("h_angle")
    private Double hAngle;
    @JsonProperty("sunset")
    private String sunset;
    @JsonProperty("dni")
    private Double dni;
    @JsonProperty("dewpt")
    private Double dewpt;
    @JsonProperty("snow")
    private Integer snow;
    @JsonProperty("uv")
    private Double uv;
    @JsonProperty("precip")
    private Integer precip;
    @JsonProperty("wind_dir")
    private Integer windDir;
    @JsonProperty("sunrise")
    private String sunrise;
    @JsonProperty("ghi")
    private Double ghi;
    @JsonProperty("dhi")
    private Double dhi;
    @JsonProperty("aqi")
    private Integer aqi;
    @JsonProperty("lat")
    private Double lat;
    @JsonProperty("weather")
    private Weather weather;
    @JsonProperty("datetime")
    private String datetime;
    @JsonProperty("temp")
    private Integer temp;
    @JsonProperty("station")
    private String station;
    @JsonProperty("elev_angle")
    private Double elevAngle;
    @JsonProperty("app_temp")
    private Double appTemp;
}
