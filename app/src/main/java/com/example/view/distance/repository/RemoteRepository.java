package com.example.view.distance.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.view.distance.model.AddressResponse;
import com.example.view.distance.retrofit.ApiRequest;
import com.example.view.distance.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteRepository {
    private ApiRequest apiRequest;

    public RemoteRepository() {
        apiRequest = RetrofitRequest.getInstance().create(ApiRequest.class);
    }

    public LiveData<AddressResponse> getAddress(double query1, double query2) {
        final MutableLiveData<AddressResponse> data = new MutableLiveData<>();

        apiRequest.getAddress(query1, query2)
                .enqueue(new Callback<AddressResponse>() {
                    @Override
                    public void onResponse(Call<AddressResponse> call, Response<AddressResponse> response) {
                        Log.i("Test", "onRespone response: " + response);

                        if(response.body() != null) {
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<AddressResponse> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}
