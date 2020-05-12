package com.example.view.distance.retrofit;

import com.example.view.distance.model.AddressResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {
    @GET("reverse?format=json")
    Call<AddressResponse> getAddress(@Query("lat") double lat,
                                     @Query("lon") double lon);
}
