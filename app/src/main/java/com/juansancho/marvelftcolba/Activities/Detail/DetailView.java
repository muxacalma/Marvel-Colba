package com.juansancho.marvelftcolba.Activities.Detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.juansancho.marvelftcolba.DTO.comicDTO;
import com.juansancho.marvelftcolba.R;
import com.squareup.picasso.Picasso;

public class DetailView extends AppCompatActivity {

    DetailPresenter presenter;

    TextView txtTitle;
    ImageView imgComic;

    Toolbar toolbar;

    //Back arrow
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        findViews();
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        comicDTO comic = (comicDTO) getIntent().getSerializableExtra("comic");

        presenter = new DetailPresenter(this, this);
        loadComic(comic);
    }

    public void findViews(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txtTitle = findViewById(R.id.txtTitle);
        imgComic = findViewById(R.id.imgComic);
    }

    public void loadComic(comicDTO comic){
        presenter.loadComic(comic);
    }

    public void setTitle(String title){
        txtTitle.setText(title);
    }

    public void setImg(String img){
        Picasso.get().load(img).into(imgComic);
    }
}