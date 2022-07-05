package com.example.quickshare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.quickshare.adapters.DevicesRecyclerAdapter;
import com.example.quickshare.listeners.ClickListener;
import com.example.quickshare.model.PairableDevices;

import java.util.ArrayList;
import java.util.List;

import pl.bclogic.pulsator4droid.library.PulsatorLayout;

public class SearchActivity extends AppCompatActivity {

    RecyclerView recyclerView_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView_search = findViewById(R.id.recyclerView_search);

        PulsatorLayout pulsator = (PulsatorLayout) findViewById(R.id.pulsator);
        pulsator.start();

        List<PairableDevices> list = new ArrayList<>();
        list.add(new PairableDevices(R.drawable.av_one, "Redmi Note 4X", "", "", false));
        list.add(new PairableDevices(R.drawable.av_two,"Galaxy Core Prime", "", "", false));
        list.add(new PairableDevices(R.drawable.av_three,"Mi 11X", "", "", false));
        list.add(new PairableDevices(R.drawable.av_two,"Mi 11X", "", "", false));
        list.add(new PairableDevices(R.drawable.av_four,"Mi 11X", "", "", false));
        list.add(new PairableDevices(R.drawable.av_five,"Mi 11X", "", "", false));

        recyclerView_search.setHasFixedSize(true);
        recyclerView_search.setLayoutManager(new GridLayoutManager(this, 3));
        DevicesRecyclerAdapter adapter = new DevicesRecyclerAdapter(this, list, listener);
        recyclerView_search.setAdapter(adapter);
    }

    private final ClickListener<PairableDevices> listener = new ClickListener<PairableDevices>() {
        @Override
        public void onCLicked(PairableDevices data) {
            Toast.makeText(SearchActivity.this, data.getName(), Toast.LENGTH_SHORT).show();
        }
    };
}