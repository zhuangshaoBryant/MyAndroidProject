package com.zhuang.jackyli.activitylifecycle1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhuang.jackyli.activitylifecycle1.util.LogUtil;

public class ThirdActivity extends AppCompatActivity {
    public static final String TEG = "ThirdActivityCycleTest";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        LogUtil.d(TEG, TEG+"onCreate方法调用" +"Task id = " +getTaskId());
    }
}
