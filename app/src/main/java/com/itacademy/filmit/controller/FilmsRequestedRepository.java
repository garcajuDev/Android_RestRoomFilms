package com.itacademy.filmit.controller;

import android.util.Log;

import com.itacademy.filmit.Entity.RequestedFilm;
import com.itacademy.filmit.Services.FilmsRest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilmsRequestedRepository {
    static ArrayList<RequestedFilm> filmsList;
    static FilmsRequestedRepositoryListener listener;

    public static void setListener(FilmsRequestedRepositoryListener listener) {
        FilmsRequestedRepository.listener = listener;
    }

    public FilmsRequestedRepository(){
        filmsList = new ArrayList<RequestedFilm>();
    }

    public void getFilmsList(){

        Retrofit retrofitObject = getRetrofitObject();
        FilmsRest api = retrofitObject.create(FilmsRest.class);
        callRemoteApi(api);
    }

    private Retrofit getRetrofitObject() {
        return new Retrofit.Builder().baseUrl(FilmsRest.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void callRemoteApi(FilmsRest api) {
        Call<ArrayList<RequestedFilm>> call = api.getFilms();
        doEnqueue(call);
    }

    private void doEnqueue(Call<ArrayList<RequestedFilm>> call) {
        call.enqueue(new Callback <ArrayList<RequestedFilm>>() {

            @Override
            public void onResponse(Call<ArrayList<RequestedFilm>> call, Response<ArrayList<RequestedFilm>> response) {
               filmsList = response.body();
               listener.onSuccess(filmsList);
            }
            @Override
            public void onFailure(Call<ArrayList<RequestedFilm>> call, Throwable t) {
                Log.d("onFailure", t.getMessage());
                listener.onError(t.getMessage());
            }
        });
    }
}
