package com.itacademy.filmit.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.itacademy.filmit.Entity.RequestedFilm;
import com.itacademy.filmit.R;
import com.itacademy.filmit.controller.FilmsRequestedRepository;
import com.itacademy.filmit.controller.FilmsRequestedRepositoryListener;

import java.util.ArrayList;

public class FilmsActivity extends AppCompatActivity implements FilmsRequestedRepositoryListener {

    FilmsRequestedRepository filmListRepository;
    ArrayList<RequestedFilm> requestedFilms = new ArrayList<RequestedFilm>();
    RecyclerView recycler;
    FilmsActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films);

        filmListRepository = new FilmsRequestedRepository();
        filmListRepository.setListener(this);
        filmListRepository.getFilmsList();

        recycler = findViewById(R.id.firstActivityRecyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FilmsActivityAdapter(requestedFilms);
        recycler.setAdapter(adapter);

        adapter.setOnItemClickListener(new FilmsActivityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RequestedFilm film) {
                Intent toInfoActivity = new Intent(getApplicationContext(),
                       FilmInfoForm.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("film",film);
                toInfoActivity.putExtra("bundle",bundle);
                startActivity(toInfoActivity);
            }
        });
    }

    @Override
    public void onSuccess(ArrayList<RequestedFilm> films) {
        requestedFilms.clear();
        requestedFilms.addAll(films);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String e) {
        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
    }
}
