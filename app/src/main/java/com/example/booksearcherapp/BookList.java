package com.example.booksearcherapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookList {
    @SerializedName("items")
    private List<Items> items;

    public List<Items> getItems() {
        return items;
    }
}
