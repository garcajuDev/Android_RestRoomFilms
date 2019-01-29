package com.itacademy.filmit.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.itacademy.filmit.Entity.LikedFilm;
import com.itacademy.filmit.controller.Repository;

import java.util.List;

public class FilmsViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<LikedFilm>> allFilms;

    public FilmsViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allFilms = repository.getAllFilms();//All liked films
    }

    public LiveData<List<LikedFilm>> getAllAnimals(){ return allFilms; }
    public void insert(LikedFilm film){ repository.insert(film); }
    public void delete(LikedFilm film){
        repository.delete(film);
    }
}
