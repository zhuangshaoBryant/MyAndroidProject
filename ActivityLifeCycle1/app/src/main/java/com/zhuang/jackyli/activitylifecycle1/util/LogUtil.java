package com.zhuang.jackyli.activitylifecycle1.util;

import android.util.Log;

/**
 * Created by jackyli on 2017/7/17.
 */

public class LogUtil {
    public static final int RELEASE = 100;             //不输出任何Log日志
    public static final int customLevel = Log.DEBUG; //自定义Log输出级别

    public static void v(String tag, String msg) {
        if (customLevel <= Log.VERBOSE) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (customLevel <= Log.DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (customLevel <= Log.INFO) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (customLevel <= Log.WARN) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (customLevel <= Log.ERROR) {
            Log.e(tag, msg);
        }
    }
}
