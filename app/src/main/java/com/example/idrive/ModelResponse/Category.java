package com.example.idrive.ModelResponse;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("id")
    int id;
    @SerializedName("title")
    String title;
    @SerializedName("photo_count")
    String photoCount;

    public Category(int id, String title, String photoCount) {
        this.id = id;
        this.title = title;
        this.photoCount = photoCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(String photoCount) {
        this.photoCount = photoCount;
    }
}
