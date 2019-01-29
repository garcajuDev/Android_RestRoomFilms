package com.itacademy.filmit.Entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RequestedFilm implements Serializable {

    private String id;
    private String title;
    private String description;
    private String director;
    private String producer;
    private String release_date;
    private String rt_score;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public RequestedFilm() {
    }

    public RequestedFilm(String id, String title, String description, String director, String producer, String releaseDate, String rtScore) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.director = director;
        this.producer = producer;
        this.release_date = releaseDate;
        this.rt_score = rtScore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public void setReleaseDate(String releaseDate) {
        this.release_date = releaseDate;
    }

    public String getRtScore() {
        return rt_score;
    }

    public void setRtScore(String rtScore) {
        this.rt_score = rtScore;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}