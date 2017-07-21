package com.zhuang.jackyli.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public final static String TEG = "MainActivity";

    private Button mStartDownloadButton;
    private MyService.MyServiceBinder myServiceBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStartDownloadButton = (Button) findViewById(R.id.startDownload_Button);
        Intent intent = new Intent(MainActivity.this,MyService.class);
        bindService(intent,connection,BIND_AUTO_CREATE);
        mStartDownloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myServiceBinder.startNotify();
            }
        });

    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myServiceBinder = (MyService.MyServiceBinder) iBinder;
            Log.d(TEG,"链接成功"+myServiceBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}
