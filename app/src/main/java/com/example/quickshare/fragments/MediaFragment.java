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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickshare.R;
import com.example.quickshare.adapters.MusicListAdapter;
import com.example.quickshare.adapters.VideosListAdapter;
import com.example.quickshare.listeners.ClickListener;
import com.example.quickshare.model.InMusic;
import com.example.quickshare.model.InVideos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MediaFragment extends Fragment {

    View view;
    List<InMusic> musicList = new ArrayList<>();
    Uri collection;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_music, container, false);

        musicList = getMusicFiles();

        RecyclerView recycler_music = view.findViewById(R.id.recycler_music);
        recycler_music.setHasFixedSize(true);
        recycler_music.setLayoutManager(new GridLayoutManager(getContext(), 4));
        MusicListAdapter adapter = new MusicListAdapter(getContext(), musicList, clickListener);
        recycler_music.setAdapter(adapter);

        return view;
    }

    private List<InMusic> getMusicFiles() {
        List<InMusic> list = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            collection = MediaStore.Audio.Media.getContentUri(
                    MediaStore.VOLUME_EXTERNAL
            );
        }
        else{
            collection = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        }

        String[] projection = new String[] {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.SIZE
        };

        String selection = MediaStore.Audio.Media.DURATION +
                " >= ?";
        String[] selectionArgs = new String[] {
                String.valueOf(TimeUnit.MILLISECONDS.convert(5, TimeUnit.MINUTES))
        };
        String sortOrder = MediaStore.Audio.Media.DISPLAY_NAME + " ASC";

        try (Cursor cursor = getContext().getContentResolver().query(
                collection,
                projection,
                selection,
                selectionArgs,
                sortOrder
        )) {
            // Cache column indices.
            int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID);
            int nameColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);
            int durationColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION);
            int sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE);

            while (cursor.moveToNext()) {
                // Get values of columns for a given video.
                long id = cursor.getLong(idColumn);
                String name = cursor.getString(nameColumn);
                int duration = cursor.getInt(durationColumn);
                int size = cursor.getInt(sizeColumn);

                Uri contentUri = ContentUris.withAppendedId(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id);

                // Stores column values and the contentUri in a local object
                // that represents the media file.
                list.add(new InMusic(contentUri, name, duration, size));
            }
        }
        return list;
    }

    private final ClickListener<InMusic> clickListener = new ClickListener<InMusic>() {
        @Override
        public void onCLicked(InMusic data) {
            Toast.makeText(getContext(), data.getName(), Toast.LENGTH_SHORT).show();
        }
    };
}
