package com.juansancho.marvelftcolba.Activities.Master;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.juansancho.marvelftcolba.Adapters.ComicAdapter;
import com.juansancho.marvelftcolba.DTO.comicDTO;
import com.juansancho.marvelftcolba.Global.PaginationListener;
import com.juansancho.marvelftcolba.R;

import java.util.ArrayList;

public class MasterView extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private MasterPresenter presenter;

    private ArrayList<comicDTO> comics;
    SwipeRefreshLayout swipeRefresh;
    private RecyclerView comicList;
    private ComicAdapter mAdapter;
    private GridLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_view);

        findViews();
        swipeRefresh.setOnRefreshListener(this);
        layoutManager = new GridLayoutManager(this, 1);
        comicList.setLayoutManager(layoutManager);
        mAdapter = new ComicAdapter(this, new ArrayList<>(), this);
        comicList.setAdapter(mAdapter);
        comicList.addOnScrollListener(new PaginationListener(layoutManager) {
            @Override
            protected void getMoreComics() {
                presenter.getMoreComics();
            }

            @Override
            public boolean isLastPage() {
                return false;
            }

            @Override
            public boolean isLoading() {
                return false;
            }
        });

        presenter = new MasterPresenter(this, this);
        presenter.getComics();
    }

    private void findViews(){
        swipeRefresh = findViewById(R.id.swipeRefresh);
        comicList = findViewById(R.id.comicList);
    }

    public void loadComics(ArrayList<comicDTO> comics){
        mAdapter.addItems(comics);
    }

    public void updateListSize(int i){
        presenter.comicCount = i;
    }

    public void removeLoading(){
        mAdapter.removeLoading();
    }

    public void addLoading(){
        mAdapter.addLoading();
    }

    public void showDetail(comicDTO comic){
        presenter.showDetail(comic);
    }

    public void setError(String s){
        Snackbar.make(comicList, s, Snackbar.LENGTH_SHORT).show();
    }

    public void clearAdapter(){
        mAdapter.clear();
    }

    @Override
    public void onRefresh() {
        swipeRefresh.setRefreshing(false);
        presenter.refresh();
    }
}