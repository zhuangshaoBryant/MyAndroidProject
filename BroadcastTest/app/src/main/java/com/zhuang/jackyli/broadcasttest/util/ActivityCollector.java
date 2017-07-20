package com.zhuang.jackyli.broadcasttest.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackyli on 2017/7/20.
 */

public class ActivityCollector {
    public static List<Activity> activityList = new ArrayList<>();
    public static void addActivity(Activity activity){
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public static void finishAllActivity() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
        activityList.clear();
    }
}
