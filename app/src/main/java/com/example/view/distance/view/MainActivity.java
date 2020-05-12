package com.example.view.distance.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.view.distance.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        buttonCalculate.setOnClickListener(this);
        buttonReset.setOnClickListener(this);
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
        //TODO
    }
}
