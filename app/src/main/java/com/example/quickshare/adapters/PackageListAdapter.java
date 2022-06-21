package com.example.quickshare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickshare.R;
import com.example.quickshare.listeners.ClickListener;
import com.example.quickshare.model.InstalledApps;

import java.util.List;

public class PackageListAdapter extends RecyclerView.Adapter<PackageViewHolder>{

    Context context;
    List<InstalledApps> list;
    ClickListener<InstalledApps> listener;

    public PackageListAdapter(Context context, List<InstalledApps> list, ClickListener<InstalledApps> listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PackageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PackageViewHolder(LayoutInflater.from(context).inflate(R.layout.list_apps, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PackageViewHolder holder, int position) {
        final InstalledApps app = list.get(position);
        holder.textView_app.setText(app.getTitle());
        holder.imageView_app.setImageDrawable(app.getIcon());
        holder.textView_app.setSelected(true);

        holder.app_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!app.isSelected()){
                    app.setSelected(true);
                    holder.imageView_tick.setVisibility(View.VISIBLE);
                    holder.app_container.setBackgroundResource(R.color.grey);
                    listener.onCLicked(app);
                }
                else{
                    app.setSelected(false);
                    holder.imageView_tick.setVisibility(View.GONE);
                    holder.app_container.setBackgroundResource(R.color.white);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class PackageViewHolder extends RecyclerView.ViewHolder {


    RelativeLayout app_container;
    ImageView imageView_app, imageView_tick;
    TextView textView_app;

    public PackageViewHolder(@NonNull View itemView) {
        super(itemView);

        textView_app = itemView.findViewById(R.id.textView_app);
        app_container = itemView.findViewById(R.id.app_container);
        imageView_app = itemView.findViewById(R.id.imageView_app);
        imageView_tick = itemView.findViewById(R.id.imageView_tick);
    }
}
