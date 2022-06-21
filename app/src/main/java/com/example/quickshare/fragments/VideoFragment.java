package com.example.quickshare.fragments;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickshare.R;
import com.example.quickshare.adapters.VideosListAdapter;
import com.example.quickshare.listeners.ClickListener;
import com.example.quickshare.model.InVideos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class VideoFragment extends Fragment {

    View view;
    List<InVideos> vidList = new ArrayList<>();
    Uri collection;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_videos, container, false);

        vidList = getVideoFiles();

        RecyclerView recycler_videos = view.findViewById(R.id.recycler_videos);
        recycler_videos.setHasFixedSize(true);
        recycler_videos.setLayoutManager(new GridLayoutManager(getContext(), 4));
        VideosListAdapter adapter = new VideosListAdapter(getContext(), vidList, clickListener);
        recycler_videos.setAdapter(adapter);


        return view;
    }

    private List<InVideos> getVideoFiles() {
        List<InVideos> videos = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            collection = MediaStore.Video.Media.getContentUri(
                    MediaStore.VOLUME_EXTERNAL
            );
        }
        else{
            collection = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        }

        String[] projection = new String[] {
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.DURATION,
                MediaStore.Video.Media.SIZE
        };

        String selection = MediaStore.Video.Media.DURATION +
                " >= ?";
        String[] selectionArgs = new String[] {
                String.valueOf(TimeUnit.MILLISECONDS.convert(5, TimeUnit.MINUTES))
        };
        String sortOrder = MediaStore.Video.Media.DISPLAY_NAME + " ASC";

        try (Cursor cursor = getContext().getContentResolver().query(
                collection,
                projection,
                selection,
                selectionArgs,
                sortOrder
        )) {
            // Cache column indices.
            int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
            int nameColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);
            int durationColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION);
            int sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE);

            while (cursor.moveToNext()) {
                // Get values of columns for a given video.
                long id = cursor.getLong(idColumn);
                String name = cursor.getString(nameColumn);
                int duration = cursor.getInt(durationColumn);
                int size = cursor.getInt(sizeColumn);

                Uri contentUri = ContentUris.withAppendedId(
                        MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id);

                // Stores column values and the contentUri in a local object
                // that represents the media file.
                videos.add(new InVideos(contentUri, name, duration, size));
            }
        }
        return videos;
    }

    private final ClickListener<InVideos> clickListener = new ClickListener<InVideos>() {
        @Override
        public void onCLicked(InVideos data) {
            Toast.makeText(getContext(), data.getName(), Toast.LENGTH_SHORT).show();
        }
    };
}
