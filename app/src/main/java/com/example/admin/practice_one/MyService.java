package com.example.admin.practice_one;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by admin on 12/02/18.
 */

public class MyService extends Service {


    String yourMessage;

    class MyServiceBinder extends Binder
    {
        public MyService getService()
        {
            return MyService.this;
        }
    }

    private IBinder binder = new MyServiceBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("new bind requested","inside onBind");
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i("new service started","inside onStartCommand");

        new Thread(new Runnable() {
            @Override
            public void run() {
                generateSweetMessage();
            }
        }).start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("onDestroy","stop service called");
        yourMessage = "";
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("onCreate","onCreate called");
    }

    public void generateSweetMessage()
    {
        try {
            Thread.sleep(1000);
            String messages = "HAPPY VALENTINES DAY BUNNY!!! :P";

                yourMessage = messages;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("generateSweetMessage","generateSweetMessage called");

    }

    public String getYourMessage()
    {
        return yourMessage;
    }

}
