package com.example.weather.gson;

/**
 * Created by zl on 19-10-28.
 */
public class AQI {
    public AQICity city;

    public class AQICity {
        public String aqi;
        public String pm25;
    }
}
