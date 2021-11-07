package com.example.idrive.ModelResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostModel {

    @SerializedName("id")
    String id;
    @SerializedName("created_at")
    String created_at;
    @SerializedName("likes")
    int likes;
    String imageUrl;
    String text;
    int pic;
    @SerializedName("urls")
    PostUrl urls;
    @SerializedName("user")
    User user;
    @SerializedName("categories")
    List<Category> categoryList;

    public PostModel(int imageUrl, String text) {
        this.pic = imageUrl;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPic(){
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public PostUrl getUrls() {
        return urls;
    }

    public void setUrls(PostUrl urls) {
        this.urls = urls;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
