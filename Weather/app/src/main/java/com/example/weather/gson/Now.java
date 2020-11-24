package com.example.weather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zl on 19-10-28.
 */
public class Now {
    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    public class More {
        @SerializedName("txt")
        public String info;
    }

}