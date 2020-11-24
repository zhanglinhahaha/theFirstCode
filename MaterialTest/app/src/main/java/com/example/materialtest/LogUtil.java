package com.example.materialtest;

import android.util.Log;

/**
 * Created by zl on 19-10-25.
 * 打印日志工具
 * 当level=VERBOSE，日志全部打印
 * 当level=NOTHING，日志全部不打印
 */
public class LogUtil {
    public static final String tag = "zlLog";
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;
    public static int level = VERBOSE;

    public static void v(String msg) {
        if(level <= VERBOSE) {
            Log.v(tag, msg);
        }
    }
    public static void d(String msg) {
        if(level <= DEBUG) {
            Log.v(tag, msg);
        }
    }
    public static void i(String msg) {
        if(level <= INFO) {
            Log.v(tag, msg);
        }
    }
    public static void w(String msg) {
        if(level <= WARN) {
            Log.v(tag, msg);
        }
    }
    public static void e(String msg) {
        if(level <= ERROR) {
            Log.v(tag, msg);
        }
    }
}
