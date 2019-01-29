package com.itacademy.filmit.Model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.itacademy.filmit.Entity.LikedFilm;

import java.util.List;

@Dao
public interface LikedFilmDAO {
    @Insert
    void insert(LikedFilm film);

    @Delete
    void delete(LikedFilm film);

    @Query("select * from film order by id desc")
    LiveData<List<LikedFilm>> getAllLikedFilms();
}
