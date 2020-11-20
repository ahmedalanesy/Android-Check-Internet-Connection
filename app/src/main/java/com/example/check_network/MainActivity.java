package com.example.check_network;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.example.check_network.receivers.NetworkChangeReceiver;

public class MainActivity extends AppCompatActivity {
    private BroadcastReceiver mNetworkReceiver;
    static TextView tv_check_connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_check_connection=(TextView) findViewById(R.id.tv_check_connection);
//        mNetworkReceiver = new NetworkChangeReceiver();
//        registerNetworkBroadcastForNougat();
    }
    public static void refresh(int value){
        if(value==1){
            tv_check_connection.setText("Online  Intenet");
            tv_check_connection.setBackgroundColor(Color.GREEN);
            tv_check_connection.setTextColor(Color.WHITE);

//            Handler handler = new Handler();
//            Runnable delayrunnable = new Runnable() {
//                @Override
//                public void run() {
//                    tv_check_connection.setVisibility(View.GONE);
//                }
//            };
//            handler.postDelayed(delayrunnable, 3000);
        }else  if(value==0) {
            tv_check_connection.setVisibility(View.VISIBLE);
            tv_check_connection.setText("wifi is disabled");
            tv_check_connection.setBackgroundColor(Color.RED);
            tv_check_connection.setTextColor(Color.WHITE);
        }else  if(value==2) {
            tv_check_connection.setVisibility(View.VISIBLE);
            tv_check_connection.setText(" No Net");
            tv_check_connection.setBackgroundColor(Color.BLUE);
            tv_check_connection.setTextColor(Color.YELLOW);
        }else  if(value==4) {
            tv_check_connection.setVisibility(View.VISIBLE);
            tv_check_connection.setText("No NetWork");
            tv_check_connection.setBackgroundColor(Color.MAGENTA );
            tv_check_connection.setTextColor(Color.BLACK);
        }
    }
    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    protected void unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterNetworkChanges();
    }
}
