package com.example.quickshare.model;

import android.graphics.drawable.Drawable;

public class InstalledApps {

    String title = "";
    String packageName = "";
    Drawable icon;
    String path;
    boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public InstalledApps(String title, String packageName, Drawable icon, String path) {
        this.title = title;
        this.packageName = packageName;
        this.icon = icon;
        this.path = path;
    }
}
