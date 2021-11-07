package com.example.idrive.Util.Retrofit;

import com.example.idrive.ModelResponse.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("wgkJgazE")
    Call<List<PostModel>> posts();
}
