package com.cloudpos.closehotspot;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

public class WifiHotUtil {
    public static final String TAG = "WifiApAdmin";
    private WifiManager mWifiManager = null;
    private Context mContext = null;

    public WifiHotUtil(Context context) {
        mContext = context;
        mWifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
    }

    public void startWifiAp(String ssid, String passwd) {
        //Wifi and hotspot cannot be turned on at the same time, so wifi needs to be turned off when the hotspot is turned on.
        if (mWifiManager.isWifiEnabled()) {
            mWifiManager.setWifiEnabled(false);
        }

        if (!isWifiApEnabled()) {
            stratWifiAp(ssid, passwd);
        }
    }

    /**
     * Whether the hot switch is on.
     *
     * @return
     */
    public boolean isWifiApEnabled() {
        try {
            return mWifiManager.isWifiEnabled();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Set the hot spot name and password, and create the hot spot
     *
     * @param mSSID
     * @param mPasswd
     */
    private void stratWifiAp(String mSSID, String mPasswd) {
        try {
            WifiConfiguration netConfig = new WifiConfiguration();
            netConfig.SSID = mSSID;
            netConfig.preSharedKey = mPasswd;
            netConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
            netConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
            netConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
            netConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
            netConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
            netConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
            netConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            netConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            mWifiManager.addNetwork(netConfig);
            mWifiManager.setWifiEnabled(true);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Turn off WiFi hotspots.
     */
    public void closeWifiAp() {
        WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        if (isWifiApEnabled()) {
            try {
                wifiManager.setWifiEnabled(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Turn off WiFi hotspots.
     */
    public void openWifiAp() {
        WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        if (isWifiApEnabled()) {
            try {
                wifiManager.setWifiEnabled(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
