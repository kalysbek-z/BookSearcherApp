package com.example.booksearcherapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class VolumeInfo implements Serializable {

    @SerializedName("title")

    private String mTitle;
    @SerializedName("authors")
    private List<String> mAuthors = null;
    @SerializedName("publishedDate")
    private String mPublishedDate;
    @SerializedName("pageCount")
    private Integer mPageCount;
    @SerializedName("averageRating")
    private double mAverageRating;
    @SerializedName("imageLinks")
    @Expose
    private ImageLinks mImageLinks;
    @SerializedName("infoLink")
    @Expose
    private String mInfoLink;

    public VolumeInfo(String title, List<String> authors, String publishedDate, Integer pageCount,
                      double averageRating, ImageLinks imageLinks, String infoLink) {
        //------------------------------------------------------------------------------------------
        mTitle = title;
        mAuthors = authors;
        mPublishedDate = publishedDate;
        mPageCount = pageCount;
        mAverageRating = averageRating;
        mImageLinks = imageLinks;
        mInfoLink = infoLink;
    }

    public String getTitle() {
        return mTitle;
    }

    public List<String> getAuthors() {
        return mAuthors;
    }

    public String getPublishedDate() {
        return mPublishedDate;
    }

    public Integer getPageCount() {
        return mPageCount;
    }

    public double getAverageRating() {
        return mAverageRating;
    }

    public ImageLinks getImageLinks() {
        return mImageLinks;
    }

    public String getInfoLink() {
        return mInfoLink;
    }
}
