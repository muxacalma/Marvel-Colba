package com.juansancho.marvelftcolba.Adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.juansancho.marvelftcolba.Activities.Master.MasterView;
import com.juansancho.marvelftcolba.DTO.comicDTO;
import com.juansancho.marvelftcolba.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.MyViewHolder> {

    ArrayList<comicDTO> comics;
    Context context;
    Activity activity;

    public ComicAdapter(Context context, ArrayList<comicDTO> comics, Activity activity) {
            this.comics = comics;
            this.context = context;
            this.activity = activity;
            }

    @NonNull
    @Override
    public ComicAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_comic, null, false);
            return new ComicAdapter.MyViewHolder(v, activity);
            }

    @Override
    public void onBindViewHolder(@NonNull ComicAdapter.MyViewHolder myViewHolder, int i) {
            myViewHolder.asignarValores(comics.get(i));
            }

    @Override
    public int getItemCount() {
        return comics.size();
    }

    public void addItems(List<comicDTO> comics) {
        this.comics.addAll(comics);
        notifyDataSetChanged();
        ((MasterView) activity).updateListSize(this.comics.size());
    }

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView thumb;
    TextView title;

    public MyViewHolder(@NonNull final View itemView, final Activity act) {
        super(itemView);
        thumb = itemView.findViewById(R.id.thumb);
        title = itemView.findViewById(R.id.title);
    }

    public void asignarValores(comicDTO comic) {
        title.setText(comic.title);
        Picasso.get().load(comic.thumbPath).into(thumb);
    }
}
}
