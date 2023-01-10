package com.copas;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class historicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        getHistoric();

    }

    private void getHistoric() {
    }
}
