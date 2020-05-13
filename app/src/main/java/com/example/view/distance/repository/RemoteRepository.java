package com.example.view.distance.repository;

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

    private MutableLiveData<AddressResponse> firstAddress = new MutableLiveData<>();
    private MutableLiveData<AddressResponse> secondAddress = new MutableLiveData<>();

    public RemoteRepository() {
        apiRequest = RetrofitRequest.getInstance().create(ApiRequest.class);
    }

    public LiveData<AddressResponse> getFirstAddress(double query1, double query2) {

        apiRequest.getAddress(query1, query2)
                .enqueue(new Callback<AddressResponse>() {
                    @Override
                    public void onResponse(Call<AddressResponse> call, Response<AddressResponse> response) {

                        if(response.body() != null) {
                            firstAddress.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<AddressResponse> call, Throwable t) {
                        firstAddress.setValue(null);
                    }
                });

        return firstAddress;
    }

    public LiveData<AddressResponse> getSecondAddress(double query1, double query2) {

        apiRequest.getAddress(query1, query2)
                .enqueue(new Callback<AddressResponse>() {
                    @Override
                    public void onResponse(Call<AddressResponse> call, Response<AddressResponse> response) {
                        if(response.body() != null) {
                            secondAddress.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<AddressResponse> call, Throwable t) {
                        secondAddress.setValue(null);
                    }
                });

        return secondAddress;
    }
}
