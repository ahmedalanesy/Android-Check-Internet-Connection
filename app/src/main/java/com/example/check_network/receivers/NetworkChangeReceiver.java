package com.example.check_network.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import static com.example.check_network.MainActivity.refresh;


public class NetworkChangeReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(final Context context, Intent intent) {
        try {
            if (IsAvailable(context)) {

                        if (IsConnected(context)) {
                            if (internetIsConnected()) {

                                Toast.makeText(context, "Online  Intenet", Toast.LENGTH_SHORT).show();
                                Log.e("TAG", "Online  Intenet");
                                // refresh(1);
                            } else {
                                /// refresh(2);
                                Toast.makeText(context, "No Net", Toast.LENGTH_SHORT).show();
                                Log.e("TAG", " No Net");
                            }
                        } else {
                            Handler handler = new Handler();
                            Runnable delayrunnable = new Runnable() {
                                @Override
                                public void run() {
                            //refresh(4);
                            Toast.makeText(context, "No NetWork", Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "No NetWork");
                                }
                            };
                            handler.postDelayed(delayrunnable, 3000);

                        }

            } else {
                Toast.makeText(context, "wifi is disabled !!!", Toast.LENGTH_SHORT).show();
                Log.e("TAG", "wifi is disabled !!! ");
                // refresh(0);
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    private boolean internetIsConnected() {
        try {

            String command = "ping -c 1 google.com";
            return (Runtime.getRuntime().exec(command).waitFor() == 0);
        } catch (Exception e) {
            return false;
        }
    }
    private boolean IsConnected(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return ((netInfo!=null) && netInfo.isConnected() );
        } catch (Exception e) {
            return false;
        }
    }
    private boolean IsAvailable(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null
            Log.e("TAG", "wifi is  ==  "+ (netInfo != null));

            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

            return (wifiManager.isWifiEnabled() || (cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && cm
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED));//&& netInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }
}