package com.example.view.distance.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.view.distance.model.AddressResponse;
import com.example.view.distance.model.Point;
import com.example.view.distance.repository.RemoteRepository;

public class ViewModelMainActivity extends AndroidViewModel {
    private RemoteRepository remoteRepository;
    private LiveData<AddressResponse> addressResponse;
    private Point firstPoint, secondPoint;

    public ViewModelMainActivity(@NonNull Application application) {
        super(application);

        remoteRepository = new RemoteRepository();
        this.addressResponse = remoteRepository.getAddress(getFirstPoint().getLatitude(), getFirstPoint().getLongitude());
    }

    public LiveData<AddressResponse> getAddressResponse() {
        return addressResponse;
    }

    public Point getFirstPoint() {
        return firstPoint;
    }

    public void setFirstPoint(Point firstPoint) {
        this.firstPoint = firstPoint;
    }

    public Point getSecondPoint() {
        return secondPoint;
    }

    public void setSecondPoint(Point secondPoint) {
        this.secondPoint = secondPoint;
    }
}
