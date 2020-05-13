package com.example.view.distance.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.view.distance.model.AddressResponse;
import com.example.view.distance.model.Point;
import com.example.view.distance.repository.RemoteRepository;
//Warsaw 52.237049, 21.017532
//London 51.508530, -0.076132
public class ViewModelMainActivity extends AndroidViewModel {
    private RemoteRepository remoteRepository;

    private LiveData<AddressResponse> firstAddress;
    private LiveData<AddressResponse> secondAddress;

    public ViewModelMainActivity(@NonNull Application application) {
        super(application);

        remoteRepository = new RemoteRepository();
        firstAddress = remoteRepository.getFirstAddress(52.237049, 21.017532);
        secondAddress = remoteRepository.getSecondAddress(51.508530, -0.076132);
    }

    public void updateFirstAddress(Point firstPoint) {
        firstAddress = remoteRepository.getFirstAddress(firstPoint.getLatitude(), firstPoint.getLongitude());
    }

    public void updateSecondAddress(Point secondPoint) {
        secondAddress = remoteRepository.getSecondAddress(secondPoint.getLatitude(), secondPoint.getLongitude());
    }

    public LiveData<AddressResponse> getFirstAddress() {
        return firstAddress;
    }

    public LiveData<AddressResponse> getSecondAddress() {
        return secondAddress;
    }
}
