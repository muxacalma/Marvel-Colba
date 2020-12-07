package com.juansancho.marvelftcolba.Activities.Master;

import android.content.Context;
import android.content.Intent;

import com.juansancho.marvelftcolba.Activities.Detail.DetailView;
import com.juansancho.marvelftcolba.DTO.comicDTO;

import java.util.ArrayList;

import static com.juansancho.marvelftcolba.Global.PaginationListener.PAGE_START;

public class MasterPresenter {

    private Context context;
    private MasterView view;
    private MasterModel model;

    public int currentPage = PAGE_START;
    public boolean isLastPage = false;
    public boolean isLoading = false;
    public int comicCount = 0;
    public int totalComics = 0;

    public MasterPresenter(Context context, MasterView view){
        this.context = context;
        this.view = view;
        this.model = new MasterModel(context, this);
    }

    public void getComics(){
        model.getComics();
    }

    public void loadComics(ArrayList<comicDTO> comics){
        comicCount += comics.size();
        view.loadComics(comics);
    }

    public void getMoreComics(){
        isLoading = true;
        currentPage++;
        getComics();
    }

    public void removeLoading(){
        view.removeLoading();
    }

    public void addLoading(){
        view.addLoading();
    }

    public void showDetail(comicDTO comic){
        Intent intent = new Intent(context, DetailView.class);
        intent.putExtra("comic", comic);
        context.startActivity(intent);
    }

    public void setError(String s){
        view.setError(s);
        isLoading = false;
        removeLoading();
    }

    public void refresh(){
        comicCount = 0;
        currentPage = PAGE_START;
        isLastPage = false;
        view.clearAdapter();
        getComics();
    }
}
