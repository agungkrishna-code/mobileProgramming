package com.example.pertemuan_3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class thirdSharePreferenceActivity extends AppCompatActivity {

    TextView tvHJudul, tvHScore;

    Button btClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_share_preference);

        tvHJudul = (TextView) findViewById(R.id.textViewThirdHJudul);
        tvHScore = (TextView) findViewById(R.id.textViewThirdHScore);

        btClear = findViewById(R.id.buttonClearValue);

        tvHJudul.setText(MySharePreference.getKeyJudul(getBaseContext()));
        tvHScore.setText(String.valueOf(MySharePreference.getKeyScore(getBaseContext())));

        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MySharePreference.RemoveAllValue(getBaseContext());
                tvHJudul.setText(MySharePreference.getKeyJudul(getBaseContext()));
                tvHScore.setText(String.valueOf(MySharePreference.getKeyScore(getBaseContext())));
            }
        });
    }
}