package com.example.pertemuan_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class inputSharePreferenceActivity extends AppCompatActivity {
    EditText etJudul, etScore;
    Button btkirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_share_preference);
        etJudul = findViewById(R.id.editTextInputJudul);
        etScore = findViewById(R.id.editTextInputScore);

        btkirim = findViewById(R.id.buttonKirimSharePreference);

        btkirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etJudul.getText().toString().equalsIgnoreCase("")) {
                    etScore.getText().toString();

                    MySharePreference.setKeyJudul(getBaseContext(), etJudul.getText().toString());
                    MySharePreference.setKeyScore(getBaseContext(), Integer.parseInt(etScore.getText().toString()));

                    Intent intent = new Intent(inputSharePreferenceActivity.this, secondSharePreferenceActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}