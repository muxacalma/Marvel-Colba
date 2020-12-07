package com.juansancho.marvelftcolba.Activities.Master;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.juansancho.marvelftcolba.Adapters.ComicAdapter;
import com.juansancho.marvelftcolba.DTO.comicDTO;
import com.juansancho.marvelftcolba.R;

import java.util.ArrayList;

public class MasterView extends AppCompatActivity {

    private MasterPresenter presenter;

    private ArrayList<comicDTO> comics;
    private RecyclerView comicList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_view);

        findViews();
        layoutManager = new GridLayoutManager(this, 1);
        comicList.setLayoutManager(layoutManager);

        presenter = new MasterPresenter(this, this);
        presenter.getComics();
    }

    private void findViews(){
        comicList = findViewById(R.id.comicList);
    }

    public void loadComics(ArrayList<comicDTO> comics){
        this.comics = comics;
        mAdapter = new ComicAdapter(this, comics, this);
        comicList.setAdapter(mAdapter);
    }
}