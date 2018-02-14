package com.example.admin.practice_one;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ServiceConnection serviceConnection;
    MyService myService;
    Intent serviceIntent;
    boolean toggle = false;
    Button bindService, unBindService,getYourMessage, startService;
    TextView textView;
    String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serviceIntent= new Intent(getApplicationContext(), MyService.class);

        bindService = (Button) findViewById(R.id.bind);
        unBindService = (Button) findViewById(R.id.unbind);
        getYourMessage = (Button) findViewById(R.id.message);
        textView = (TextView) findViewById(R.id.textView2);
        startService = (Button) findViewById(R.id.startservice);


        bindService.setOnClickListener(this);
        unBindService.setOnClickListener(this);
        getYourMessage.setOnClickListener(this);
        startService.setOnClickListener(this);


        //service connection API
        //Intents
        //bound service toggle




    }

    @Override
    public void onClick(View v) {


        int item = v.getId();

        if (item == R.id.bind) {

            Log.i("on click", "on bind clicked");

            serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                toggle = true;
                Log.i("on click", "onServiceConnected");
                MyService.MyServiceBinder myServiceBinder = (MyService.MyServiceBinder) service;
                myService = myServiceBinder.getService();
                message = myService.getYourMessage();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                toggle = false;
                Log.i("on click", "onServiceDisconnected");
            }
            };
            bindService(serviceIntent, serviceConnection, BIND_AUTO_CREATE);  //this is a bound service--local binding

    }

        if(item == R.id.unbind)
        {
            Log.i("on click","on unbind clicked");
            stopService(serviceIntent);
        }
        if(item == R.id.message)
        {
            if(true)
            {
                Log.i("on click","get message clicked");
                textView.setText(message);
            }
            else
            {
                Toast.makeText(this, "bind the service first", Toast.LENGTH_SHORT).show();
            }

        }

        if(item == R.id.startservice)
        {
            startService(serviceIntent);
        }
    }
}
