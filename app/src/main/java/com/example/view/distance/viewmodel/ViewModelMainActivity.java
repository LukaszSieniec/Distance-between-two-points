package com.example.view.distance.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.view.distance.model.AddressResponse;
import com.example.view.distance.repository.RemoteRepository;

public class ViewModelMainActivity extends AndroidViewModel {
    private RemoteRepository remoteRepository;
    private LiveData<AddressResponse> addressResponse;

    public ViewModelMainActivity(@NonNull Application application) {
        super(application);

        remoteRepository = new RemoteRepository();
        this.addressResponse = remoteRepository.getAddress(52.54877605, -1.8162703328316416);
    }

    public LiveData<AddressResponse> getAddressResponse() {
        return addressResponse;
    }
}
