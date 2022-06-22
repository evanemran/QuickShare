package com.example.quickshare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickshare.R;
import com.example.quickshare.listeners.ClickListener;
import com.example.quickshare.model.PairableDevices;

import java.util.List;

public class DevicesRecyclerAdapter extends RecyclerView.Adapter<DeviceRecyclerViewHolder>{

    Context context;
    List<PairableDevices> list;
    ClickListener<PairableDevices> listener;

    public DevicesRecyclerAdapter(Context context, List<PairableDevices> list, ClickListener<PairableDevices> listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DeviceRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DeviceRecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.list_devices, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceRecyclerViewHolder holder, int position) {
        final PairableDevices device = list.get(position);
        holder.textView_device.setText(device.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class DeviceRecyclerViewHolder extends RecyclerView.ViewHolder {

    TextView textView_device;

    public DeviceRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        textView_device = itemView.findViewById(R.id.textView_device);
    }
}
