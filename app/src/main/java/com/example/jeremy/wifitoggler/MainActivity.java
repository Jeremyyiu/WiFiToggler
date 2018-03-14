package com.example.jeremy.wifitoggler;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;

public class MainActivity extends AppCompatActivity {

    private WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifiManager = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        SwitchCompat wifiSwitch = (SwitchCompat) findViewById(R.id.wifiSwitch);
        initSwitch(wifiSwitch);
    }

    private void initSwitch(SwitchCompat wifiSwitch) {
        boolean wifiStatus = getWifiState();
        wifiSwitch.setChecked(wifiStatus);

        wifiSwitch.setOnCheckedChangeListener(new SwitchCompat.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toggleWifi();
            }
        });

        wifiSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleWifi();
            }
        });
    }

    private boolean getWifiState() {
        return wifiManager.isWifiEnabled();
    }

    public void toggleWifi() {
        boolean currentState = wifiManager.isWifiEnabled();
        wifiManager.setWifiEnabled(!currentState);
    }
}