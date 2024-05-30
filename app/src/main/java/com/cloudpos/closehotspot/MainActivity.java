package com.cloudpos.closehotspot;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener {

    private Button enable_btn;
    private Button disable_btn;
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enable_btn = findViewById(R.id.enableBtn);
        disable_btn = findViewById(R.id.disableBtn);
        message = findViewById(R.id.message);
        enable_btn.setOnClickListener(this);
        disable_btn.setOnClickListener(this);
        //Please move to WifiAPService.
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        boolean result = false;
        switch (v.getId()) {
            case R.id.enableBtn:
                result = new HotspotManager(this).startHotspot("CloudPOS", "0b4c703f7d73");
//                result = new HotspotManager(this).startHotspot("CloudPOS","0b4c703f7d73");
                if (result) {
                    message.setText("enable wlan hotpot success!");
                } else {
                    message.setText("enable wlan hotpot failed!");
                }
                break;
            case R.id.disableBtn:
                result = new HotspotManager(this).stopHotspot();
                if (result) {
                    message.setText("disable wlan hotpot success!");
                } else {
                    message.setText("disable wlan hotpot failed!");
                }
                break;
            default:
                break;
        }
    }
}
