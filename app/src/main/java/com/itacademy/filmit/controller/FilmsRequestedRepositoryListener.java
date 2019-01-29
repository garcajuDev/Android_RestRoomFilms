package com.itacademy.filmit.controller;

import com.itacademy.filmit.Entity.RequestedFilm;

import java.util.ArrayList;

public interface FilmsRequestedRepositoryListener {
     public void onSuccess(ArrayList<RequestedFilm> films);
     public void onError(String e);
}
