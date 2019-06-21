package com.example.googlemaps2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MarkerDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Extraer lat. y lng.
        Intent intent = getIntent();
        String k="";
                k+="("+intent.getDoubleExtra("EXTRA_LATITUD", 0)+",";
                k+=intent.getDoubleExtra("EXTRA_LONGITUD", 0)+")";
        // Poblar
        TextView coordenadas = (TextView) findViewById(R.id.tv_latlng);
        coordenadas.setText(k);
    }
}
