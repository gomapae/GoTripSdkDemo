package com.express.gotripsdkdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.godrive.GoDriveSDK;
import com.godrive.utils.LogUtil;
import com.godrive.utils.contants.GoDriverConstant;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {


    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Location l = getLocationTemp();
            if (l != null) {
                GoDriveSDK.getInstance().onLocationUpdate(l);
                handler.sendEmptyMessageDelayed(0, 1000);
            } else {
                handler.removeMessages(0);
                Toast.makeText(MainActivity.this, "upload completed", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void init(View view) {
        GoDriveSDK.Builder builder = new GoDriveSDK.Builder()
                .setContext(this)
                .setDebug(true)
                .setUserId("220201001");
        GoDriveSDK.getInstance().init(builder.build(), result -> {
            LogUtil.iTag(GoDriverConstant.TAG, "result is ", new Gson().toJson(result));
        });
    }

    public void startTrip(View view) {
        GoDriveSDK.getInstance().startTrip();
        index = 0;
        handler.sendEmptyMessageDelayed(0, 1000);
    }

    public void endTrip(View view) {
        GoDriveSDK.getInstance().endTrip();
    }


    private int index = 0;
    private Location getLocationTemp() {
        if (index >= locations.length) {
            return null;
        }
        Location location = new Location(LocationManager.GPS_PROVIDER);
        String l = locations[(index ++) % locations.length];
        location.setLongitude(Double.parseDouble(l.split(",")[0]));
        location.setLatitude(Double.parseDouble(l.split(",")[1]));
        return location;
    }

    String[] locations = {"54.39979,24.490169999999996", "54.39922,24.490236666666668",
            "54.398888333333325,24.490276666666666",
            "54.39863666666667,24.490303333333333",
            "54.39831833333333,24.490345",
            "54.398066666666665,24.490371666666668",
            "54.39781500000001,24.490396666666665",
            "54.3977,24.490409999999997",
            "54.39769,24.490409999999997",
            "54.39719,24.490478333333332",
            "54.39683,24.490529999999996",
            "54.396339999999995,24.49057",
            "54.396283333333336,24.490205",
            "54.39625666666667,24.489989999999995",
            "54.39621999999999,24.489690000000003",
            "54.396150000000006,24.489261666666668",
            "54.39608166666667,24.488835",
            "54.39600666666667,24.488256666666665",
            "54.395979999999994,24.487959999999998",
            "54.395880000000005,24.48731",
            "54.39581,24.487019999999998",
            "54.39564,24.486490000000003",
            "54.395439999999994,24.485939999999996",
            "54.39511000000001,24.485070000000004",
            "54.394980000000004,24.484710000000003",
            "54.394819999999996,24.48439",
            "54.39468,24.48418",
            "54.394420000000004,24.483889999999995",
            "54.39411,24.48366",
            "54.39387,24.48352",
            "54.39339,24.483290000000004",
            "54.39291,24.483060000000002",
            "54.39241833333334,24.482823333333332",
            "54.39192,24.48261",
            "54.391418333333334,24.482391666666665",
            "54.390950000000004,24.48219",
            "54.390710000000006,24.482199999999995",
            "54.39038000000001,24.482090000000003",
            "54.39008333333333,24.481990000000003",
            "54.389786666666666,24.48189",
            "54.388929999999995,24.481589999999997",
            "54.38864166666667,24.481469999999998",
            "54.388353333333335,24.48135166666667",
            "54.38777833333334,24.481115",
            "54.387519999999995,24.48101",
            "54.387455,24.480978333333336",
            "54.387455,24.480978333333336",
            "54.38732666666667,24.48091833"};
}