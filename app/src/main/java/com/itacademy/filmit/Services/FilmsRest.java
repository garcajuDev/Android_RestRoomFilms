package com.itacademy.filmit.Services;

import com.itacademy.filmit.Entity.RequestedFilm;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FilmsRest {
    String BASE_URL = "https://ghibliapi.herokuapp.com/";

    @GET("films")
    Call<ArrayList<RequestedFilm>>getFilms();
}
