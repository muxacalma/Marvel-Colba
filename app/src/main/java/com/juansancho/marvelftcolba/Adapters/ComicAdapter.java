package com.juansancho.marvelftcolba.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.google.android.material.card.MaterialCardView;
import com.juansancho.marvelftcolba.Activities.Master.MasterView;
import com.juansancho.marvelftcolba.DTO.comicDTO;
import com.juansancho.marvelftcolba.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ComicAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoaderVisible = false;

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
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case VIEW_TYPE_NORMAL:

                return new MyViewHolder(
                        LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_comic, viewGroup, false));
            case VIEW_TYPE_LOADING:
                return new ProgressHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_loading, viewGroup, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoaderVisible) {
            return position == comics.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return comics == null ? 0 : comics.size();
    }

    public void addItems(List<comicDTO> comics) {
        this.comics.addAll(comics);
        notifyDataSetChanged();
        ((MasterView) activity).updateListSize(this.comics.size());
    }

    public void addLoading() {
        isLoaderVisible = true;
        comics.add(new comicDTO());
        notifyItemInserted(comics.size() - 1);
    }
    public void removeLoading() {
        isLoaderVisible = false;
        int position = comics.size() - 1;
        comicDTO comic = getItem(position);
        if (comic != null) {
            comics.remove(position);
            notifyItemRemoved(position);
        }
    }
    public void clear() {
        comics.clear();
        notifyDataSetChanged();
    }
    comicDTO getItem(int position) {
        return comics.get(position);
    }


    public class MyViewHolder extends BaseViewHolder {

        ImageView thumb;
        TextView title;
        MaterialCardView card;

        public MyViewHolder(View inflate) {
            super(inflate);
        }

        @Override
        protected void clear() {}

        @Override
        public void onBind(int position) {
            super.onBind(position);
            comicDTO comicDTO = comics.get(position);
            card = itemView.findViewById(R.id.comicCard);
            thumb = itemView.findViewById(R.id.thumb);
            title = itemView.findViewById(R.id.title);
            title.setText(comicDTO.title);
            Picasso.get().load(comicDTO.thumbPath).into(thumb);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MasterView) context).showDetail(comicDTO);
                }
            });
        }
    }

    public class ProgressHolder extends BaseViewHolder {
        ProgressHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void clear() {

        }
    }
}
