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
import com.example.quickshare.adapters.ImagesListAdapter;
import com.example.quickshare.adapters.VideosListAdapter;
import com.example.quickshare.listeners.ClickListener;
import com.example.quickshare.model.InImages;
import com.example.quickshare.model.InVideos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ImageFragment extends Fragment {

    View view;
    List<InImages> imgList = new ArrayList<>();
    Uri collection;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_images, container, false);

        imgList = getImageFiles();

        RecyclerView recycler_images = view.findViewById(R.id.recycler_images);
        recycler_images.setHasFixedSize(true);
        recycler_images.setLayoutManager(new GridLayoutManager(getContext(), 4));
        ImagesListAdapter adapter = new ImagesListAdapter(getContext(), imgList, clickListener);
        recycler_images.setAdapter(adapter);


        return view;
    }

    private List<InImages> getImageFiles() {
        List<InImages> list = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            collection = MediaStore.Images.Media.getContentUri(
                    MediaStore.VOLUME_EXTERNAL
            );
        }
        else{
            collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }

        String[] projection = new String[] {
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.SIZE
        };

        String selection = MediaStore.Images.Media.SIZE +
                " >= ?";
        String[] selectionArgs = new String[] {
                String.valueOf(TimeUnit.MILLISECONDS.convert(5, TimeUnit.MINUTES))
        };
        String sortOrder = MediaStore.Images.Media.DISPLAY_NAME + " ASC";

        try (Cursor cursor = getContext().getContentResolver().query(
                collection,
                projection,
                selection,
                selectionArgs,
                sortOrder
        )) {
            // Cache column indices.
            int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
            int nameColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);
            int sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE);

            while (cursor.moveToNext()) {
                // Get values of columns for a given video.
                long id = cursor.getLong(idColumn);
                String name = cursor.getString(nameColumn);
                int size = cursor.getInt(sizeColumn);

                Uri contentUri = ContentUris.withAppendedId(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);

                // Stores column values and the contentUri in a local object
                // that represents the media file.
                list.add(new InImages(name, size, contentUri, false));
            }
        }
        return list;
    }

    private final ClickListener<InImages> clickListener = new ClickListener<InImages>() {
        @Override
        public void onCLicked(InImages data) {
            Toast.makeText(getContext(), data.getName(), Toast.LENGTH_SHORT).show();
        }
    };
}
