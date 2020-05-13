package com.example.view.distance.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.location.Location;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.view.distance.R;
import com.example.view.distance.filters.InputFilterCoordinates;
import com.example.view.distance.model.AddressResponse;
import com.example.view.distance.model.Point;
import com.example.view.distance.viewmodel.ViewModelMainActivity;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.editTextFirstLatitude)
    EditText editTextFirstLatitude;
    @BindView(R.id.editTextFirstLongitude)
    EditText editTextFirstLongitude;
    @BindView(R.id.editTextSecondLatitude)
    EditText editTextSecondLatitude;
    @BindView(R.id.editTextSecondLongitude)
    EditText editTextSecondLongitude;

    @BindView(R.id.buttonCalculate)
    Button buttonCalculate;
    @BindView(R.id.buttonReset)
    Button buttonReset;

    @BindView(R.id.textViewStart)
    TextView textViewStart;
    @BindView(R.id.textViewFinish)
    TextView textViewFinish;
    @BindView(R.id.textViewDistance)
    TextView textViewDistance;

    private ViewModelMainActivity viewModelMainActivity;
    private DecimalFormat decimalFormat = new DecimalFormat("0.#");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setFilter();

        buttonCalculate.setOnClickListener(this);
        buttonReset.setOnClickListener(this);

        viewModelMainActivity = new ViewModelProvider(this).get(ViewModelMainActivity.class);
        getFirstAddress();
        getSecondAddress();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.buttonCalculate) {
            calculate();

        } else if(id == R.id.buttonReset) {
            reset();
        }
    }

    private void reset() {
        editTextFirstLatitude.setText("");
        editTextFirstLongitude.setText("");
        editTextSecondLatitude.setText("");
        editTextSecondLongitude.setText("");

        textViewStart.setText("?");
        textViewFinish.setText("?");
        textViewDistance.setText("");
    }

    private void calculate() {
        String firstLatitude = editTextFirstLatitude.getText().toString();
        String firstLongitude = editTextFirstLongitude.getText().toString();
        String secondLatitude = editTextSecondLatitude.getText().toString();
        String secondLongitude = editTextSecondLongitude.getText().toString();

        if(firstLatitude.trim().isEmpty() || firstLongitude.trim().isEmpty() || secondLatitude.trim().isEmpty() || secondLongitude.trim().isEmpty()) {
            Toast.makeText(this, "You haven't entered all the data!", Toast.LENGTH_LONG).show();

        } else {

            Point firstPoint = new Point(Double.parseDouble(firstLatitude), Double.parseDouble(firstLongitude));
            Point secondPoint = new Point(Double.parseDouble(secondLatitude), Double.parseDouble(secondLongitude));

            viewModelMainActivity.updateFirstAddress(firstPoint);
            viewModelMainActivity.updateSecondAddress(secondPoint);

            Location firstLocation = new Location("");
            firstLocation.setLatitude(firstPoint.getLatitude());
            firstLocation.setLongitude(firstPoint.getLongitude());

            Location secondLocation = new Location("");
            secondLocation.setLatitude(secondPoint.getLatitude());
            secondLocation.setLongitude(secondPoint.getLongitude());

            double distance = firstLocation.distanceTo(secondLocation);
            textViewDistance.setText("Distance in kilometers: " + decimalFormat.format(distance/1000) + "\n"
                    + "Distance in meters: " + decimalFormat.format(distance));
        }
    }

    private void getFirstAddress() {

        viewModelMainActivity.getFirstAddress().observe(this, new Observer<AddressResponse>() {
            @Override
            public void onChanged(AddressResponse addressResponse) {

                if((addressResponse != null) && (addressResponse.getAddress() != null) && ((!addressResponse.getAddress().getCountry().equals(""))
                        || (!addressResponse.getAddress().getCity().equals("")))) {
                    textViewStart.setText(addressResponse.getAddress().getCountry() + "\n" + addressResponse.getAddress().getCity());

                } else {
                    textViewStart.setText("?");
                }
            }
        });
    }

    private void getSecondAddress() {

        viewModelMainActivity.getSecondAddress().observe(this, new Observer<AddressResponse>() {
            @Override
            public void onChanged(AddressResponse addressResponse) {

                if((addressResponse != null) && (addressResponse.getAddress() != null) && ((!addressResponse.getAddress().getCountry().equals(""))
                        || (!addressResponse.getAddress().getCity().equals("")))) {
                    textViewFinish.setText(addressResponse.getAddress().getCountry() + "\n" + addressResponse.getAddress().getCity());

                } else {
                    textViewFinish.setText("?");
                }
            }
        });
    }

    private void setFilter() {
        editTextFirstLatitude.setFilters(new InputFilter[] {new InputFilterCoordinates(2, 10)});
        editTextFirstLongitude.setFilters(new InputFilter[] {new InputFilterCoordinates(2, 10)});
        editTextSecondLatitude.setFilters(new InputFilter[] {new InputFilterCoordinates(2, 10)});
        editTextSecondLongitude.setFilters(new InputFilter[] {new InputFilterCoordinates(2, 10)});
    }
}
