package com.juansancho.marvelftcolba.Activities.Detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.juansancho.marvelftcolba.R;

public class DetailView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        Log.d("ID received", getIntent().getIntExtra("comicID", 0) + "");
    }
}