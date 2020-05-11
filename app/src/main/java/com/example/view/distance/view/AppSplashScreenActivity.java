package com.example.view.distance.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.view.distance.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppSplashScreenActivity extends AppCompatActivity {

    @BindView(R.id.imageViewAppSplashScreen)
    ImageView imageViewAppSplashScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        ButterKnife.bind(this);

        setImage();
        startMainActivity();
    }

    private void startMainActivity() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }

                startActivity(new Intent(AppSplashScreenActivity.this, MainActivity.class));
                finish();
            }
        }).start();
    }

    private void setImage() {
        Glide.with(this)
                .load(R.drawable.road)
                .into(imageViewAppSplashScreen);
    }
}
