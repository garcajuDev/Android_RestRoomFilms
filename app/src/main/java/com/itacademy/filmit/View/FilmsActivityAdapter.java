package com.itacademy.filmit.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itacademy.filmit.Entity.RequestedFilm;
import com.itacademy.filmit.R;

import java.util.ArrayList;

public class FilmsActivityAdapter extends Adapter<FilmsActivityAdapter.FilmsHolder> {
    private ArrayList<RequestedFilm> filmList;
    private OnItemClickListener listener;


    public FilmsActivityAdapter (ArrayList<RequestedFilm> films){
        this.filmList = films;
    }

    @NonNull
    @Override
    public FilmsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.films_activity_item, viewGroup, false);
        return new FilmsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmsHolder filmsHolder, int i) {
        RequestedFilm film = filmList.get(i);
        filmsHolder.title.setText(film.getTitle());
        filmsHolder.date.setText(film.getReleaseDate());
    }

    public void setFilmList(ArrayList<RequestedFilm> filmList) {
        this.filmList = filmList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(RequestedFilm film);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class FilmsHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView date;
        ImageView like;

        public FilmsHolder(@NonNull final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txtFilmActivityTitle);
            date = itemView.findViewById(R.id.txtFilmActivityYear);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = getAdapterPosition();

                    if (listener != null && i != RecyclerView.NO_POSITION) {
                        listener.onItemClick(filmList.get(i));
                    }
                }
            });
        }
    }
}
