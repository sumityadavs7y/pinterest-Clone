package com.example.idrive.ModelResponse;

import com.google.gson.annotations.SerializedName;

public class ImageUrl{
    @SerializedName("small")
    String small;
    @SerializedName("medium")
    String medium;
    @SerializedName("large")
    String large;

    public ImageUrl(String small, String medium, String large) {
        this.small = small;
        this.medium = medium;
        this.large = large;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }
}
