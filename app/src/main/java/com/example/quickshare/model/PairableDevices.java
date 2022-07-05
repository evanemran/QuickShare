package com.example.quickshare.model;

public class PairableDevices {
    int id;
    String name;
    String ip;
    String mac;
    boolean isQuickShareEnabled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PairableDevices(int id, String name, String ip, String mac, boolean isQuickShareEnabled) {
        this.id = id;
        this.name = name;
        this.ip = ip;
        this.mac = mac;
        this.isQuickShareEnabled = isQuickShareEnabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public boolean isQuickShareEnabled() {
        return isQuickShareEnabled;
    }

    public void setQuickShareEnabled(boolean quickShareEnabled) {
        isQuickShareEnabled = quickShareEnabled;
    }
}
