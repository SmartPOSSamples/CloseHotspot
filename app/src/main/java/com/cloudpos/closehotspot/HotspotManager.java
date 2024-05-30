package com.cloudpos.closehotspot;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

public class HotspotManager {
    private WifiManager wifiManager;

    public HotspotManager(Context context) {
        wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    public boolean startHotspot(String ssid, String password) {
        if (wifiManager != null) {
            if (wifiManager.isWifiEnabled()) {
                wifiManager.setWifiEnabled(false);
            }

            WifiConfiguration wifiConfig = new WifiConfiguration();
            wifiConfig.SSID = ssid;
            wifiConfig.preSharedKey = password;
            wifiConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
            wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
            wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
            wifiConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
            wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
            wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
            wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            try {
                wifiManager.setWifiEnabled(false);
                return (boolean) wifiManager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, boolean.class).invoke(wifiManager, wifiConfig, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean stopHotspot() {
        if (wifiManager != null) {
            try {
                return (boolean) wifiManager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, boolean.class).invoke(wifiManager, null, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
