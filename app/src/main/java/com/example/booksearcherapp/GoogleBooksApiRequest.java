package com.example.booksearcherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleBooksApiRequest {
    @GET("volumes?")
    Call<BookList> getData(@Query("q") String input);
}
