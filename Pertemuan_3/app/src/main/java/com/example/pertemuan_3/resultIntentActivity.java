package com.example.pertemuan_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class resultIntentActivity extends AppCompatActivity {

    TextView tvRString, TvRNum1, tvRNum2, tvRArray, tvRHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_intent);

        tvRString = findViewById(R.id.textViewHNumericString);
        TvRNum1 = findViewById(R.id.textViewHNumericA);
        tvRNum2 = findViewById(R.id.textViewHNumericB);
        tvRHasil = findViewById(R.id.textViewHNumericResult);
        tvRArray = findViewById(R.id.textViewHArrayResult);
//        Cara mengambil Nilai
        Intent intent = getIntent();
        String NilaiString = intent.getStringExtra("NNama");
        double nA = intent.getDoubleExtra("nA", 0);
        double nB = intent.getDoubleExtra("nB", 0);
        String RArray[] = intent.getStringArrayExtra("NArray");
//        Menampilkan nilai
        tvRString.setText(NilaiString);
        TvRNum1.setText(String.valueOf(nA));
        tvRNum2.setText(String.valueOf(nB));
        tvRHasil.setText(String.valueOf(nA + nB));

        double hsum = 0;
        String format = "";
        for (int i = 0; i < RArray.length; i++) {
            if (i < RArray.length - 1) {
                format += RArray[i] + "+";
            } else {
                format += RArray[i];
            }
            hsum += Double.parseDouble(RArray[i]);
        }
        tvRArray.setText(format + "=" + String.valueOf(hsum));
    }
}