package com.example.weather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zl on 19-10-28.
 */
public class Basic {

    //让JOSN字段和Java字段建立映射关系
    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update {

        @SerializedName("loc")
        public String updateTime;

    }
}
