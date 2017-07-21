package com.zhuang.jackyli.broadcasttest.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.os.Bundle;
import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zhuang.jackyli.broadcasttest.R;
import com.zhuang.jackyli.broadcasttest.util.ActivityCollector;

public class MainActivity extends BaseActivity {

    private DongtaiTaiBroadcast mDongtaiTaiBroadcast;
    private IntentFilter mIntentFilter;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("com.zhuang.jackyli.mylocal");
        mDongtaiTaiBroadcast = new DongtaiTaiBroadcast();
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(mDongtaiTaiBroadcast,mIntentFilter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.zhuang.jackyli.mylocal");
                localBroadcastManager.sendBroadcast(intent);
            }
        });
       // registerReceiver(mDongtaiTaiBroadcast, mIntentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(mDongtaiTaiBroadcast);
    }

    class DongtaiTaiBroadcast extends BroadcastReceiver{
        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("警告").setMessage("强制退出");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCollector.finishAllActivity();
                    context.startActivity(new Intent(context,LoginActivity.class));
                }
            });
            builder.setCancelable(true).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).show();


        }
    }
}
