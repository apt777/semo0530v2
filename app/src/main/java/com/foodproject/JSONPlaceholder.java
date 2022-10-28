package com.foodproject;

import com.foodproject.model.Chart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceholder {
    @GET("posts")
    Call<List<Chart>> getPost();
}
