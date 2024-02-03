package com.example.pertemuan_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class secondSharePreferenceActivity extends AppCompatActivity {
    TextView tvHJudul, tvHScore;

    Button btKirim2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_share_preference);

        tvHJudul = (TextView) findViewById(R.id.textViewSecondHJudul);
        tvHScore = (TextView) findViewById(R.id.textViewSecondHScore);

        btKirim2 = findViewById(R.id.buttonKirim2);

        tvHJudul.setText(MySharePreference.getKeyJudul(getBaseContext()));
        tvHScore.setText(String.valueOf(MySharePreference.getKeyScore(getBaseContext())));

        btKirim2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(secondSharePreferenceActivity.this, thirdSharePreferenceActivity.class);
                startActivity(intent);
            }
        });
    }
}