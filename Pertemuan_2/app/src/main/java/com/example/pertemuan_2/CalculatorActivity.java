package com.example.pertemuan_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalculatorActivity extends AppCompatActivity {
    EditText etBilangan1, etBilangan2, etHasil;
    Button btTambah, btKurang, btKali, btBagi, btMod, btPangkat, btExit;
    double A=0, B=0, hasil=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        etBilangan1 = (EditText) findViewById(R.id.editTextBilangan1);
        etBilangan2 = (EditText) findViewById(R.id.editTextBilangan2);
        etHasil = (EditText) findViewById(R.id.editTextHasil);

        btTambah = (Button) findViewById(R.id.buttonTambah);
        btKurang = (Button) findViewById(R.id.buttonKurang);
        btKali = (Button) findViewById(R.id.buttonKali);
        btBagi = (Button) findViewById(R.id.buttonBagi);
        btMod = (Button) findViewById(R.id.buttonMod);
        btPangkat = (Button) findViewById(R.id.buttonPangkat);
        btExit = (Button) findViewById(R.id.buttonExitCalculator);

        btTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                A=Double.parseDouble(etBilangan1.getText().toString());
                B=Double.parseDouble(etBilangan2.getText().toString());
                hasil=A+B;
                etHasil.setText(String.valueOf(hasil));
            }
        });

        btKurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                A=Double.parseDouble(etBilangan1.getText().toString());
                B=Double.parseDouble(etBilangan2.getText().toString());
                hasil=A-B;
                etHasil.setText(String.valueOf(hasil));
            }
        });

        btKali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                A=Double.parseDouble(etBilangan1.getText().toString());
                B=Double.parseDouble(etBilangan2.getText().toString());
                hasil=A*B;
                etHasil.setText(String.valueOf(hasil));
            }
        });

        btBagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                A=Double.parseDouble(etBilangan1.getText().toString());
                B=Double.parseDouble(etBilangan2.getText().toString());
                hasil=A/B;
                etHasil.setText(String.valueOf(hasil));
            }
        });

        btMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                A=Double.parseDouble(etBilangan1.getText().toString());
                B=Double.parseDouble(etBilangan2.getText().toString());
                hasil=A%B;
                etHasil.setText(String.valueOf(hasil));
            }
        });

        btPangkat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                A=Double.parseDouble(etBilangan1.getText().toString());
                B=Double.parseDouble(etBilangan2.getText().toString());
                hasil=Math.pow(A,B);
                etHasil.setText(String.valueOf(hasil));
            }
        });

        btExit.setOnClickListener(view -> finish());
    }
}

