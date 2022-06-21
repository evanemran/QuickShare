package com.example.quickshare.adapters;

import android.content.Context;
import android.os.Build;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickshare.R;
import com.example.quickshare.listeners.ClickListener;
import com.example.quickshare.model.InImages;
import com.example.quickshare.model.InVideos;

import java.io.IOException;
import java.util.List;

public class ImagesListAdapter extends RecyclerView.Adapter<ImagesViewHolder>{

    Context context;
    List<InImages> list;
    ClickListener<InImages> listener;

    public ImagesListAdapter(Context context, List<InImages> list, ClickListener<InImages> listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImagesViewHolder(LayoutInflater.from(context).inflate(R.layout.list_images, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onBindViewHolder(@NonNull ImagesViewHolder holder, int position) {
        final InImages video = list.get(position);
        holder.textView_app.setText(video.getName());
        try {
            holder.imageView_app.setImageBitmap(context.getContentResolver().loadThumbnail(video.getUri(), new Size(480, 480), null));
        } catch (IOException e) {
            e.printStackTrace();
        }
        holder.textView_app.setSelected(true);

        if (video.isSelected()){
            holder.imageView_tick.setVisibility(View.VISIBLE);
            holder.app_container.setBackgroundResource(R.color.grey);
        }
        else {
            holder.imageView_tick.setVisibility(View.GONE);
            holder.app_container.setBackgroundResource(R.color.white);
        }

        holder.app_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!video.isSelected()){
                    video.setSelected(true);
                    holder.imageView_tick.setVisibility(View.VISIBLE);
                    holder.app_container.setBackgroundResource(R.color.grey);
                    listener.onCLicked(video);
                }
                else{
                    video.setSelected(false);
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

class ImagesViewHolder extends RecyclerView.ViewHolder {


    RelativeLayout app_container;
    ImageView imageView_app, imageView_tick;
    TextView textView_app;

    public ImagesViewHolder(@NonNull View itemView) {
        super(itemView);

        textView_app = itemView.findViewById(R.id.textView_app);
        app_container = itemView.findViewById(R.id.app_container);
        imageView_app = itemView.findViewById(R.id.imageView_app);
        imageView_tick = itemView.findViewById(R.id.imageView_tick);
    }
}
