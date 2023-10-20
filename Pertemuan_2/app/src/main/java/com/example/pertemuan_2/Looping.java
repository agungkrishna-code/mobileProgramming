package com.example.pertemuan_2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Looping extends AppCompatActivity {
    EditText editTextBilanganPertama, editTextBilanganKedua, editTextHasilBilangan;
    Button buttonLoopBiasa, buttonBilanganGenap, buttonBilanganGanjil, buttonBilanganPrima;
    Button buttonExitLooping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looping);

        editTextBilanganPertama = findViewById(R.id.editTextBilanganPertama);
        editTextBilanganKedua = findViewById(R.id.editTextBilanganKedua);
        editTextHasilBilangan = findViewById(R.id.editTextHasilBilangan);

        buttonLoopBiasa = findViewById(R.id.buttonLoopBiasa);
        buttonBilanganGenap = findViewById(R.id.buttonBilanganGenap);
        buttonBilanganGanjil = findViewById(R.id.buttonBilanganGanjil);
        buttonBilanganPrima = findViewById(R.id.buttonBilanganPrima);
        buttonExitLooping = findViewById(R.id.buttonExitLooping);

        buttonLoopBiasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int start = Integer.parseInt(editTextBilanganPertama.getText().toString());
                int end = Integer.parseInt(editTextBilanganKedua.getText().toString());
                StringBuilder result = new StringBuilder();
                for (int i = start; i <= end; i++) {
                    result.append(i);
                    if (i < end) {
                        result.append(", ");
                    }
                }
                editTextHasilBilangan.setText(result.toString());
            }
        });

        buttonBilanganGenap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int start = Integer.parseInt(editTextBilanganPertama.getText().toString());
                int end = Integer.parseInt(editTextBilanganKedua.getText().toString());
                StringBuilder result = new StringBuilder();
                for (int i = start; i <= end; i++) {
                    if (i % 2 == 0) {
                        result.append(i);
                        if (i < end) {
                            result.append(", ");
                        }
                    }
                }
                editTextHasilBilangan.setText(result.toString());
            }
        });

        buttonBilanganGanjil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int start = Integer.parseInt(editTextBilanganPertama.getText().toString());
                int end = Integer.parseInt(editTextBilanganKedua.getText().toString());
                StringBuilder result = new StringBuilder();
                for (int i = start; i <= end; i++) {
                    if (i % 2 != 0) {
                        result.append(i);
                        if (i < end) {
                            result.append(", ");
                        }
                    }
                }
                editTextHasilBilangan.setText(result.toString());
            }
        });

        buttonBilanganPrima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int start = Integer.parseInt(editTextBilanganPertama.getText().toString());
                int end = Integer.parseInt(editTextBilanganKedua.getText().toString());
                StringBuilder result = new StringBuilder();
                for (int i = start; i <= end; i++) {
                    if (isPrime(i)) {
                        result.append(i);
                        if (i < end) {
                            result.append(", ");
                        }
                    }
                }
                editTextHasilBilangan.setText(result.toString());
            }
        });

        buttonExitLooping.setOnClickListener(view -> finish());
    }

    private boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
