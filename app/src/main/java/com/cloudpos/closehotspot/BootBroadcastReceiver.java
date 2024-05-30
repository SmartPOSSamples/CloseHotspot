package com.cloudpos.closehotspot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class BootBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Intent service = new Intent(context, WiFiAPService.class);
            context.startService(service);
        }
    }
}
