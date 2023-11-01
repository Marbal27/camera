package com.example.camera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DogsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs);
    }
    public void back_btn(View view) {
        Intent intent = new Intent(DogsActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}