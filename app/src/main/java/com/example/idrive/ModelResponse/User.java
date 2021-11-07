package com.example.idrive.ModelResponse;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    String id;
    @SerializedName("username")
    String userName;
    @SerializedName("name")
    String name;
    @SerializedName("profile_image")
    ImageUrl imageUrl;

    public User(String id, String userName, String name, ImageUrl imageUrl) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageUrl getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(ImageUrl imageUrl) {
        this.imageUrl = imageUrl;
    }
}
