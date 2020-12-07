package com.juansancho.marvelftcolba.Activities.Detail;

import android.content.Context;

import com.juansancho.marvelftcolba.Activities.Master.MasterModel;
import com.juansancho.marvelftcolba.Activities.Master.MasterView;
import com.juansancho.marvelftcolba.DTO.comicDTO;
import com.juansancho.marvelftcolba.R;


public class DetailPresenter {

    private Context context;
    private DetailView view;
    private DetailModel model;

    public DetailPresenter(Context context, DetailView view){
        this.context = context;
        this.view = view;
        this.model = new DetailModel(context, this);
    }

    public void loadComic(comicDTO comic){
        String path = comic.thumbPath.replace(context.getResources().getString(R.string.thumbnailSize), context.getResources().getString(R.string.portraitSize));
        view.setImg(path);
        view.setTitle(comic.title.toUpperCase());
    }
}
