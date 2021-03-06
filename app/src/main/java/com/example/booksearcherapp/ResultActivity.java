package com.example.booksearcherapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultActivity extends AppCompatActivity {
    private List<Items> listOfBooks = new ArrayList<>();
    private static final String BASE_URL = "https://www.googleapis.com/books/v1/";
    BooksAdapter booksAdapter;
    ProgressBar loading;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);
        loading = (ProgressBar) findViewById(R.id.loading);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN); //idk what it does but it helped
        checkConnection();
    }

    public void checkConnection() {
        ConnectivityManager conManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            getResponse();
        } else {
            loading.setVisibility(View.GONE);
            Toast.makeText(ResultActivity.this, "No Internet connection!", Toast.LENGTH_SHORT);
        }
    }

    private void getResponse() {
        String searchingBook = getIntent().getStringExtra("Search").replace(" ", "+");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GoogleBooksApiRequest apiRequest = retrofit.create(GoogleBooksApiRequest.class);
        Call<BookList> call = apiRequest.getData(searchingBook);

        call.enqueue(new Callback<BookList>() {
            @Override
            public void onResponse(Call<BookList> call, Response<BookList> response) {
                if (response.isSuccessful()) {
                    listOfBooks = response.body().getItems();
                    booksAdapter = new BooksAdapter(ResultActivity.this, listOfBooks);
                    loading.setVisibility(View.GONE);
                    layoutManager = new LinearLayoutManager(ResultActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(booksAdapter);
                }
            }

            @Override
            public void onFailure(Call<BookList> call, Throwable t) {
                Toast.makeText(ResultActivity.this, "чота не так", Toast.LENGTH_SHORT).show();
                Log.e(ResultActivity.class.getSimpleName(), "FAIL: " + t.getMessage());
                Log.e(ResultActivity.class.getSimpleName(), "failed at : " + call.request().url());
            }
        });
    }
}
