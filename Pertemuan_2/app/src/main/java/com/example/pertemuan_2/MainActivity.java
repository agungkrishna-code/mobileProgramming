package com.example.pertemuan_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btKalkulator, btPencarianMinMax, btMainKata, btLooping,btExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btKalkulator=(Button) findViewById(R.id.buttonKalkulator);
        btPencarianMinMax=(Button) findViewById(R.id.buttonPenMinMax);
        btMainKata=(Button) findViewById(R.id.buttonMainKata);
        btLooping=(Button) findViewById(R.id.buttonLooping);
        btExit=(Button) findViewById(R.id.buttonExit);

        btKalkulator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
                startActivity(intent);
            }
        });
        btPencarianMinMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, PencarianMinMax.class);
                startActivity(intent);
            }
        });
        btMainKata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainKata.class);
                startActivity(intent);
            }
        });
        btLooping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Looping.class);
                startActivity(intent);

            }
        });
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.finish();
                System.exit(0);
            }
        });
    }
}