package com.example.weather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zl on 19-10-28.
 */
public class Weather {

    public String status;
    public AQI aqi;
    public Basic basic;
    public Now now;
    public Suggestion suggestion;

    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;

}
