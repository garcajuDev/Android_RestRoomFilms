package com.itacademy.filmit.controller;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.itacademy.filmit.Entity.LikedFilm;
import com.itacademy.filmit.Model.Database;
import com.itacademy.filmit.Model.LikedFilmDAO;

import java.util.List;

public class Repository {

    private LikedFilmDAO filmDAO;
    private LiveData<List<LikedFilm>> allFilms;

    public Repository (Application app){
        Database DB = Database.getDataBase(app);
        filmDAO = DB.filmDAO();
        allFilms = filmDAO.getAllLikedFilms();
    }

    public LiveData<List<LikedFilm>> getAllFilms() {
        return allFilms;
    }
    public void insert (LikedFilm film){
        new insertAsyncTask(filmDAO).execute(film);
    }
    public void delete (LikedFilm film){
        new deleteAsyncTask(filmDAO).execute(film);
    }

    private class insertAsyncTask extends AsyncTask< LikedFilm, Void, Void> {
        private LikedFilmDAO dao;
        public insertAsyncTask(LikedFilmDAO filmDAO) {
            dao = filmDAO;
        }

        @Override
        protected Void doInBackground(LikedFilm...params) {
            dao.insert(params[0]);
            return null;
        }
    }

    private class deleteAsyncTask extends AsyncTask< LikedFilm, Void, Void> {
        private LikedFilmDAO dao;
        public deleteAsyncTask(LikedFilmDAO filmDAO) {
            dao = filmDAO;
        }

        @Override
        protected Void doInBackground(LikedFilm... params) {
            dao.delete(params[0]);
            return null;
        }
    }
}
