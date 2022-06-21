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
import com.example.quickshare.model.InMusic;
import com.example.quickshare.model.InVideos;

import java.io.IOException;
import java.util.List;

public class MusicListAdapter extends RecyclerView.Adapter<MusicViewHolder>{

    Context context;
    List<InMusic> list;
    ClickListener<InMusic> listener;

    public MusicListAdapter(Context context, List<InMusic> list, ClickListener<InMusic> listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MusicViewHolder(LayoutInflater.from(context).inflate(R.layout.list_music, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        final InMusic music = list.get(position);
        holder.textView_app.setText(music.getName());
        try {
            holder.imageView_app.setImageBitmap(context.getContentResolver().loadThumbnail(music.getUri(), new Size(480, 480), null));
        } catch (IOException e) {
            e.printStackTrace();
        }
        holder.textView_app.setSelected(true);

        if (music.isSelected()){
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
                if (!music.isSelected()){
                    music.setSelected(true);
                    holder.imageView_tick.setVisibility(View.VISIBLE);
                    holder.app_container.setBackgroundResource(R.color.grey);
                    listener.onCLicked(music);
                }
                else{
                    music.setSelected(false);
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

class MusicViewHolder extends RecyclerView.ViewHolder {


    RelativeLayout app_container;
    ImageView imageView_app, imageView_tick;
    TextView textView_app;

    public MusicViewHolder(@NonNull View itemView) {
        super(itemView);

        textView_app = itemView.findViewById(R.id.textView_app);
        app_container = itemView.findViewById(R.id.app_container);
        imageView_app = itemView.findViewById(R.id.imageView_app);
        imageView_tick = itemView.findViewById(R.id.imageView_tick);
    }
}
