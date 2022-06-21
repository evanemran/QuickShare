package com.example.quickshare.fragments;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickshare.R;
import com.example.quickshare.adapters.PackageListAdapter;
import com.example.quickshare.listeners.ClickListener;
import com.example.quickshare.model.InstalledApps;

import java.util.ArrayList;
import java.util.List;

public class AppsFragment extends Fragment {

    View view;
    List<InstalledApps> appsList = new ArrayList<>();
    RecyclerView recycler_apps;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_apps, container, false);

        recycler_apps = view.findViewById(R.id.recycler_apps);

        setupSystemList(getInstalledApps(true));

        return view;
    }

    private void setupSystemList(List<InstalledApps> appsList) {
        recycler_apps.setHasFixedSize(true);
        recycler_apps.setLayoutManager(new GridLayoutManager(getContext(), 4));
        PackageListAdapter systemFileAdapter = new PackageListAdapter(getContext(), appsList, clickListener);
        recycler_apps.setAdapter(systemFileAdapter);
    }

    private List<InstalledApps> getInstalledApps(boolean getSysPackages) {
        List<InstalledApps> res = new ArrayList<InstalledApps>();
        List<PackageInfo> packs = getActivity().getPackageManager().getInstalledPackages(0);
        for(int i=0;i<packs.size();i++) {
            PackageInfo p = packs.get(i);

            if ((p.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0){
                res.add(new InstalledApps(p.applicationInfo.loadLabel(getActivity().getPackageManager()).toString(),
                        p.packageName,
                        p.applicationInfo.loadIcon(getActivity().getPackageManager()), ""));
            }

        }
        return res;
    }

    private final ClickListener<InstalledApps> clickListener = new ClickListener<InstalledApps>() {
        @Override
        public void onCLicked(InstalledApps data) {
            Toast.makeText(getContext(), data.getTitle(), Toast.LENGTH_SHORT).show();
        }
    };
}
