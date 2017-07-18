package com.zhuang.jackyli.activitylifecycle1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zhuang.jackyli.activitylifecycle1.util.LogUtil;


public class MainActivity extends AppCompatActivity {
    public static final String TEG = "MainActivityCycleTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtil.d(TEG,"onCreate方法调用");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d(TEG,"onStart方法调用");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d(TEG,"onResume方法调用");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d(TEG,"onPause方法调用");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d(TEG,"onStop方法调用");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.d(TEG,"onRestart方法调用");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d(TEG,"onDestroy方法调用");
    }
}
