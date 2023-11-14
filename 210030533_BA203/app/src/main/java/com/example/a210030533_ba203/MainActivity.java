package com.example.a210030533_ba203;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btInputSiswa, btInputMasalah, btLaporan, btExitApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btInputSiswa = findViewById(R.id.buttonInputSiswa);
        btInputMasalah = findViewById(R.id.buttonInputMasalah);
        btLaporan = findViewById(R.id.buttonLaporan);
        btExitApp = findViewById(R.id.buttonExitApp);

        btInputSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, inputDataSiswa.class);
                startActivity(intent);
            }
        });
        btInputMasalah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, inputMasalah.class);
                startActivity(intent);
            }
        });
        btLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, laporanKasusSiswa.class);
                startActivity(intent);
            }
        });
        btExitApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
