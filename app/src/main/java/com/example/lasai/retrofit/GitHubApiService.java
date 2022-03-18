package com.example.lasai.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GitHubApiService {

    @GET("tips")
    Call<List<Tips>> getAllTips();
}
