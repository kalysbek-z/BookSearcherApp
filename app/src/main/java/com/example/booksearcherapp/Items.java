package com.example.booksearcherapp;

import com.google.gson.annotations.SerializedName;

public class Items {
    @SerializedName("volumeInfo")
    private VolumeInfo volumeInfo;

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }
}
