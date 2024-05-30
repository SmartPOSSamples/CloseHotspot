package com.cloudpos.closehotspot;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;


public class WiFiAPService extends Service {
    private static String TAG = "WiFiAPService";
    private BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.net.wifi.WIFI_AP_STATE_CHANGED".equals(action)) {
                //ap state：10---closing；11---close；12---opening；13---opened
                int state = intent.getIntExtra("wifi_state", 0);
                Log.i(TAG, "WifiService BroadcastReceiver state= " + state);
                if (state == 13) {
                    new WifiHotUtil(context).closeWifiAp();
                }
            }
        }
    };

    @Override
    public void onCreate() {
        Log.d(TAG, "WiFiAPService onCreate!");
        registerReceiver(wifiReceiver, new IntentFilter("android.net.wifi.WIFI_AP_STATE_CHANGED"));
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(wifiReceiver);
        super.onDestroy();
        //service forever alive
        Intent localIntent = new Intent();
        localIntent.setClass(this, WiFiAPService.class);
        this.startService(localIntent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
