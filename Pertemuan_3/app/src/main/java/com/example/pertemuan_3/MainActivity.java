package com.example.pertemuan_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btIntent, btSharePreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btIntent = (Button) findViewById(R.id.buttonIntent);
        btSharePreference = (Button) findViewById(R.id.buttonSharePreference);

        btIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyIntentActivity.class);
                startActivity(intent);
            }
        });

        btSharePreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, inputSharePreferenceActivity.class);
                startActivity(intent);
            }
        });
    }
}