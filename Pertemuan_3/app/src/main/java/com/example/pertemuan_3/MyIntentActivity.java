package com.example.pertemuan_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MyIntentActivity extends AppCompatActivity {
    EditText NNama, N_A, N_B, NArray;
    Button btSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_intent);

        NNama = findViewById(R.id.etNama);
        N_A = findViewById(R.id.etNilaiA);
        N_B = findViewById(R.id.etNilaiB);
        NArray = findViewById(R.id.etNilaiArray);
        btSend = findViewById(R.id.btKirimIntent);

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyIntentActivity.this, resultIntentActivity.class);
//                Masukan nilai string
                intent.putExtra("NNama", NNama.getText().toString());
//                Muatkan nilai numeric
                double nA = Double.parseDouble(N_A.getText().toString());
                double nB = Double.parseDouble(N_B.getText().toString());
                intent.putExtra("nA", nA);
                intent.putExtra("nB", nB);

//                muatkan nilai array
                String temp = NArray.getText().toString();
                String nilaiD[] = temp.split(",");
                intent.putExtra("NArray", nilaiD);

                startActivity(intent);
            }
        });
    }
}