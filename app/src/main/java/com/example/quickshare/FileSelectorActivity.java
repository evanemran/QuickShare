package com.example.quickshare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quickshare.adapters.ViewPagerAdapter;
import com.example.quickshare.fragments.AppsFragment;
import com.example.quickshare.fragments.FileFragment;
import com.example.quickshare.fragments.ImageFragment;
import com.example.quickshare.fragments.MediaFragment;
import com.example.quickshare.fragments.VideoFragment;
import com.example.quickshare.listeners.ClickListener;
import com.example.quickshare.model.InstalledApps;
import com.google.android.material.tabs.TabLayout;

public class FileSelectorActivity extends AppCompatActivity implements ClickListener<InstalledApps> {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    TextView textView_total;
    int total = 0;
    LinearLayout footer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_selector);

        toolbar =findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        textView_total = findViewById(R.id.textView_total);
        footer = findViewById(R.id.footer);

        footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FileSelectorActivity.this, RadarActivity.class));
            }
        });

        setSupportActionBar(toolbar);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        AppsFragment appsFragment = new AppsFragment();
        MediaFragment mediaFragment = new MediaFragment();
        ImageFragment imageFragment = new ImageFragment();
        VideoFragment videoFragment = new VideoFragment();
        FileFragment fileFragment = new FileFragment();

        viewPagerAdapter.addFragment(appsFragment, "Apps");
        viewPagerAdapter.addFragment(mediaFragment, "Music");
        viewPagerAdapter.addFragment(imageFragment, "Images");
        viewPagerAdapter.addFragment(videoFragment, "Videos");
        viewPagerAdapter.addFragment(fileFragment, "Files");

        viewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    public void onCLicked(InstalledApps data) {
        textView_total.setText(String.valueOf(total+=1) + " Items");
    }
}