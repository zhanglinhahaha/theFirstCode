package com.example.weather.util;

/**
 * Created by zl on 19-10-25.
 */

import android.util.Log;

/**
 * Created by zl on 19-10-25.
 * 打印日志工具
 * 当level=VERBOSE，日志全部打印
 * 当level=NOTHING，日志全部不打印
 */

public class LogUtil {

    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;
    public static int level = NOTHING;

    public static void v(String tag, String msg) {
        if(level <= VERBOSE) {
            Log.v(tag, msg);
        }
    }
    public static void d(String tag, String msg) {
        if(level <= DEBUG) {
            Log.v(tag, msg);
        }
    }
    public static void i(String tag, String msg) {
        if(level <= INFO) {
            Log.v(tag, msg);
        }
    }
    public static void w(String tag, String msg) {
        if(level <= WARN) {
            Log.v(tag, msg);
        }
    }
    public static void e(String tag, String msg) {
        if(level <= ERROR) {
            Log.v(tag, msg);
        }
    }
}
