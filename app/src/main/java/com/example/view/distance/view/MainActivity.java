package com.example.view.distance.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.view.distance.R;
import com.example.view.distance.model.AddressResponse;
import com.example.view.distance.model.Point;
import com.example.view.distance.viewmodel.ViewModelMainActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

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
        }
    }

    private void getFirstAddress() {

        viewModelMainActivity.getFirstAddress().observe(this, new Observer<AddressResponse>() {
            @Override
            public void onChanged(AddressResponse addressResponse) {

                textViewStart.setText(addressResponse.getAddress().getCity());
            }
        });
    }

    private void getSecondAddress() {

        viewModelMainActivity.getSecondAddress().observe(this, new Observer<AddressResponse>() {
            @Override
            public void onChanged(AddressResponse addressResponse) {

                textViewFinish.setText(addressResponse.getAddress().getCity());
            }
        });
    }
}
