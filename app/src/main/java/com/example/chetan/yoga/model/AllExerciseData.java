package com.example.chetan.yoga.model;

import android.graphics.drawable.Drawable;

/**
 * Created by Chetan on 9/21/2017.
 */

public class AllExerciseData {
    private String title;
    private Drawable pictures;

    public AllExerciseData(String title, Drawable pictures) {
        this.setTitle(title);
        this.setPictures(pictures);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getPictures() {
        return pictures;
    }

    public void setPictures(Drawable pictures) {
        this.pictures = pictures;
    }
}
