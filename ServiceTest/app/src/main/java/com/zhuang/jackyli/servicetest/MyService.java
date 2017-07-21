package com.zhuang.jackyli.servicetest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Binder;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;

public class MyService extends Service {
    private MyServiceBinder mServiceBinder;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public MyService() {
    }


    class MyServiceBinder extends Binder{
        public void startNotify(){
            Intent intent = new Intent(MyService.this,MainActivity.class);
            PendingIntent pi = PendingIntent.getActivity(MyService.this,0,intent,0);
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Notification notification = new NotificationCompat.Builder(MyService.this)
                    .setContentTitle("开始下载标题")
                    .setContentText("开始下载内容")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                    .setContentIntent(pi)
                    .setLights(Color.GREEN,1000,1000)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setAutoCancel(true).build();
            startForeground(1,notification);
            manager.notify(1,notification);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        mServiceBinder = new MyServiceBinder();
        return mServiceBinder;
    }
}
