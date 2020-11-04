package com.example.booksearcherapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://www.googleapis.com/books/v1/";

    public static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
//
//    public static GoogleBooksApiRequest ApiService() {
//        return getRetrofitInstance().create(GoogleBooksApiRequest.class);
//    }
}
