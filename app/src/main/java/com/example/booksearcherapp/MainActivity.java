package com.example.booksearcherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText searchBar;
    ImageButton search;
    String textFromSearchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBar = (EditText) findViewById(R.id.search_bar);
        search = (ImageButton) findViewById(R.id.search_button);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textFromSearchBar = searchBar.getText().toString();
                if (textFromSearchBar.isEmpty() || textFromSearchBar.equals(" ")) {
                    Toast.makeText(MainActivity.this, "Empty field!", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Intent resultIntent = new Intent(MainActivity.this, ResultActivity.class);
                    resultIntent.putExtra("Search", textFromSearchBar);
                    startActivity(resultIntent);
                }
            }
        });
    }
}