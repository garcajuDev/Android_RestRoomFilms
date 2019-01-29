package com.itacademy.filmit.View;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.itacademy.filmit.Entity.LikedFilm;
import com.itacademy.filmit.Entity.RequestedFilm;
import com.itacademy.filmit.R;
import com.itacademy.filmit.ViewModel.FilmsViewModel;

public class FilmInfoForm extends AppCompatActivity {

    EditText txtViewDirector, txtViewProducer, txtViewYear, txtViewDescription;
    private FilmsViewModel filmsMVVM;
    boolean flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_info_form);

        txtViewDirector = findViewById(R.id.txtInfoDirector);
        txtViewProducer = findViewById(R.id.txtInfoPorducer);
        txtViewYear = findViewById(R.id.txtInfoYear);
        txtViewDescription = findViewById(R.id.txtInfoDescription);
    }

    @Override
    protected void onResume() {
        super.onResume();

        RequestedFilm requestedFilm = getRequestedFilm();

        setTitle(requestedFilm.getTitle());

        txtViewDirector.setText(requestedFilm.getDirector());
        txtViewProducer.setText(requestedFilm.getProducer());
        txtViewYear.setText(requestedFilm.getReleaseDate());
        txtViewDescription.setText(requestedFilm.getDescription());
        filmsMVVM = ViewModelProviders.of(this).get(FilmsViewModel.class);
        flag = false;

    }

    protected void onSaveFilm(RequestedFilm requestedfilm){
        filmsMVVM.insert(getLikedFilm(requestedfilm));
    }

    protected void onDeleteFilm(RequestedFilm requestedfilm){
        filmsMVVM.delete(getLikedFilm(requestedfilm));
    }

    protected RequestedFilm getRequestedFilm(){

        Intent fromFilmsActivityAdapter = getIntent();
        Bundle requestedFilmObjectBundle = fromFilmsActivityAdapter
                .getBundleExtra("bundle");

        RequestedFilm requestedFilm = (RequestedFilm) requestedFilmObjectBundle
                .getSerializable("film");

        return requestedFilm;
    }

    protected LikedFilm getLikedFilm(RequestedFilm requestedFilm){

        return new LikedFilm(requestedFilm.getTitle(), requestedFilm.getDescription(),
                requestedFilm.getDirector(),requestedFilm.getProducer(),requestedFilm.getReleaseDate(),
                requestedFilm.getRtScore());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.to_preferred_films, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        RequestedFilm film = getRequestedFilm();

        switch (item.getItemId()) {
            case R.id.go_Favourites:
                //Aqui accede al recycler view de las pelis favoritas
                return true;
            case R.id.like:
                //Aqui la acción de guardar/eliminar en función de la flag
                if (!flag){
                    item.setIcon(R.drawable.like);
                    onSaveFilm(film);
                    flag = true;
                }else{
                    item.setIcon(R.drawable.unlike);
                    onDeleteFilm(film);
                    flag = false;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
