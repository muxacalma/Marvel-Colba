package com.juansancho.marvelftcolba.Activities.Master;

import android.content.Context;

import com.juansancho.marvelftcolba.DTO.comicDTO;

import java.util.ArrayList;

public class MasterPresenter {

    private Context context;
    private MasterView view;
    private MasterModel model;

    public MasterPresenter(Context context, MasterView view){
        this.context = context;
        this.view = view;
        this.model = new MasterModel(context, this);
    }

    public void getComics(){
        model.getComics();
    }

    public void loadComics(ArrayList<comicDTO> comics){
        view.loadComics(comics);
    }
}
