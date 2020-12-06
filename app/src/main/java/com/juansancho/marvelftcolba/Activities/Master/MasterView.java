package com.juansancho.marvelftcolba.Activities.Master;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.juansancho.marvelftcolba.R;

public class MasterView extends AppCompatActivity {

    private MasterPresenter presenter;

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
}