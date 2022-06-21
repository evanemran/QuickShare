package com.example.quickshare.model;

import android.net.Uri;

public class InImages {
    String name;
    int size;
    Uri uri;
    boolean isSelected;

    public InImages(String name, int size, Uri uri, boolean isSelected) {
        this.name = name;
        this.size = size;
        this.uri = uri;
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
