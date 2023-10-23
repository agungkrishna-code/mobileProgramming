package com.example.pertemuan_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {
    TextView tvNama, tvNim, tvUmur;
    ImageView img;
    Button btPrev, btNext;
    int jmlData = 0, indx = 0;
    ArrayList<Mahasiswa> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        tvNama = findViewById(R.id.textViewNama);
        tvNim = findViewById(R.id.textViewNim);
        tvUmur = findViewById(R.id.textViewUmur);
        img = findViewById(R.id.imageView);
        btPrev = findViewById(R.id.buttonPrev);
        btNext = findViewById(R.id.buttonNext);

        Intent intent = getIntent();
        arrayList = intent.getParcelableArrayListExtra("Data");
        showData(0);

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (indx + 1 > arrayList.size() - 1) {
                    Toast.makeText(ViewActivity.this, "Data Sudah Paling Akhir", Toast.LENGTH_SHORT).show();
                } else {
                    indx++;
                    showData(indx);
                }
            }
        });

        btPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (indx - 1 < 0) {
                    Toast.makeText(ViewActivity.this, "Data Sudah Paling Awal", Toast.LENGTH_SHORT).show();
                } else {
                    indx--;
                    showData(indx);
                }
            }
        });
    }

    private void showData(int indx) {
        tvNama.setText(arrayList.get(indx).getNama());
        tvNim.setText(arrayList.get(indx).getNim());
        tvUmur.setText(String.valueOf(arrayList.get(indx).getUmur()));
        switch (arrayList.get(indx).getFruit_L()) {
            case "Apple":
                img.setImageResource(R.drawable.apple);
                break;
            case "Banana":
                img.setImageResource(R.drawable.banana);
                break;
            case "Orange":
                img.setImageResource(R.drawable.orange);
                break;
            case "Pineapple":
                img.setImageResource(R.drawable.pineapple);
                break;
            case "Strawberry":
                img.setImageResource(R.drawable.strawberry);
                break;
            case "Watermelon":
                img.setImageResource(R.drawable.watermelon);
                break;
        }
    }
}
