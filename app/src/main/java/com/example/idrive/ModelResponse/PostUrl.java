package com.example.idrive.ModelResponse;

import com.google.gson.annotations.SerializedName;

public class PostUrl {
    @SerializedName("raw")
    String raw;
    @SerializedName("full")
    String full;
    @SerializedName("regular")
    String regular;
    @SerializedName("small")
    String small;
    @SerializedName("thumb")
    String thumb;

    public PostUrl(String raw, String full, String regular, String small, String thumb) {
        this.raw = raw;
        this.full = full;
        this.regular = regular;
        this.small = small;
        this.thumb = thumb;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
