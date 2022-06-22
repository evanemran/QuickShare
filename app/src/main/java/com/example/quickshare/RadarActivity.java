package com.example.quickshare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.quickshare.adapters.DevicesRecyclerAdapter;
import com.example.quickshare.listeners.ClickListener;
import com.example.quickshare.model.PairableDevices;
import com.example.quickshare.utils.RadarView;
import com.example.quickshare.utils.RainRadarView;

import java.util.ArrayList;
import java.util.List;

public class RadarActivity extends AppCompatActivity {

    RainRadarView radarView;
    RecyclerView recycler_devices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar);

        radarView = findViewById(R.id.radarView);
        recycler_devices = findViewById(R.id.recycler_devices);
        radarView.start();


        List<PairableDevices> list = new ArrayList<>();
        list.add(new PairableDevices("Redmi Note 4X", "", "", false));
        list.add(new PairableDevices("Galaxy Core Prime", "", "", false));
        list.add(new PairableDevices("Mi 11X", "", "", false));


        recycler_devices.setHasFixedSize(true);
        recycler_devices.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        DevicesRecyclerAdapter adapter = new DevicesRecyclerAdapter(this, list, listener);
        recycler_devices.setAdapter(adapter);
    }

    /*public void stopAnimation(View view) {
        if (radarView != null) radarView.stopAnimation();
    }

    public void startAniamtion(View view) {
        if (radarView != null) radarView.startAnimation();
    }*/

    private final ClickListener<PairableDevices> listener = new ClickListener<PairableDevices>() {
        @Override
        public void onCLicked(PairableDevices data) {
            Toast.makeText(RadarActivity.this, data.getName(), Toast.LENGTH_SHORT).show();
        }
    };
}