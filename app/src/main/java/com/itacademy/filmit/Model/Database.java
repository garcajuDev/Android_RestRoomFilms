package com.itacademy.filmit.Model;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.itacademy.filmit.Entity.LikedFilm;

@android.arch.persistence.room.Database(entities = {LikedFilm.class}, version = 1)
public abstract class Database extends RoomDatabase {

    public abstract LikedFilmDAO filmDAO();
    private static volatile Database DB;

    public static Database getDataBase(final Context context) {
        if (DB == null) {
            synchronized (Database.class) {
                if (DB == null) {
                    DB = Room.databaseBuilder(context.getApplicationContext(),
                            Database.class, "likedFilmsDB")
                            .build();
                }
            }
        }
        return DB;
    }
}
