package com.example.pertemuan_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PencarianMinMax extends AppCompatActivity {

    EditText etA, etB, etC, etD, etE, etF, etG, etH, etHasil;
    Button btnMax, btnMin, btExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencarian_min_max);

        etA = (EditText) findViewById(R.id.editTextA);
        etB = (EditText) findViewById(R.id.editTextB);
        etC = (EditText) findViewById(R.id.editTextC);
        etD = (EditText) findViewById(R.id.editTextD);
        etE = (EditText) findViewById(R.id.editTextE);
        etF = (EditText) findViewById(R.id.editTextF);
        etG = (EditText) findViewById(R.id.editTextG);
        etH = (EditText) findViewById(R.id.editTextH);
        etHasil = (EditText) findViewById(R.id.editTextHasil);

        btnMax = (Button) findViewById(R.id.buttonMax);
        btnMin = (Button) findViewById(R.id.buttonMin);
        btExit = (Button) findViewById(R.id.buttonExitMinMax);

        btnMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(etA.getText().toString());
                int b = Integer.parseInt(etB.getText().toString());
                int c = Integer.parseInt(etC.getText().toString());
                int d = Integer.parseInt(etD.getText().toString());
                int e = Integer.parseInt(etE.getText().toString());
                int f = Integer.parseInt(etF.getText().toString());
                int g = Integer.parseInt(etG.getText().toString());
                int h = Integer.parseInt(etH.getText().toString());
                int max = a;
                if (b > max) max = b;
                if (c > max) max = c;
                if (d > max) max = d;
                if (e > max) max = e;
                if (f > max) max = f;
                if (g > max) max = g;
                if (h > max) max = h;
                etHasil.setText(String.valueOf(max));
            }
        });

        btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(etA.getText().toString());
                int b = Integer.parseInt(etB.getText().toString());
                int c = Integer.parseInt(etC.getText().toString());
                int d = Integer.parseInt(etD.getText().toString());
                int e = Integer.parseInt(etE.getText().toString());
                int f = Integer.parseInt(etF.getText().toString());
                int g = Integer.parseInt(etG.getText().toString());
                int h = Integer.parseInt(etH.getText().toString());
                int min = a;
                if (b < min) min = b;
                if (c < min) min = c;
                if (d < min) min = d;
                if (e < min) min = e;
                if (f < min) min = f;
                if (g < min) min = g;
                if (h < min) min = h;
                etHasil.setText(String.valueOf(min));
            }
        });

        btExit.setOnClickListener(view -> finish());
    }
}