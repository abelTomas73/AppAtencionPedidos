package com.example.s3k_user1.appatencionpedidos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.s3k_user1.appatencionpedidos.ui.ActividadPrincipal;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(SplashActivity.this, ActividadPrincipal.class));
        // close splash activity
        finish();
    }
}
