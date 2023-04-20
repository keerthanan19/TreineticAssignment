package com.assignment.treineticassignment.Network;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IpService {

    @GET("products")
    Call<JsonArray> getAllData();

    @GET("featured")
    Call<JsonObject> getFeaturedProducts();
}
