package com.example.menghitungnilaiakhir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HitungNilai extends AppCompatActivity {
    EditText etNama, etAbsen, etQuis, etTugas, etUTS, etTUAS;
    Button btHapus, btHitung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hitung_nilai);

        etNama = findViewById(R.id.editTextNama);
        etAbsen = findViewById(R.id.editTextAbsen);
        etQuis = findViewById(R.id.editTextQuis);
        etTugas = findViewById(R.id.editTextTugas);
        etUTS = findViewById(R.id.editTextUTS);
        etTUAS = findViewById(R.id.editTextUAS);

        btHapus = findViewById(R.id.buttonHapus);
        btHitung = findViewById(R.id.buttonHitung);

        btHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearEditTexts();
            }
        });

        btHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = etNama.getText().toString();
                double absen = getDoubleFromEditText(etAbsen);
                double tugas = getDoubleFromEditText(etTugas);
                double quis = getDoubleFromEditText(etQuis);
                double uts = getDoubleFromEditText(etUTS);
                double uas = getDoubleFromEditText(etTUAS);

                // Perhitungan berdasarkan ketentuan
                double absenWeight = absen * 0.15;
                double tugasWeight = tugas * 0.15;
                double quisWeight = quis * 0.20;
                double utsWeight = uts * 0.20;
                double uasWeight = uas * 0.30;

                // Menghitung Total
                double totalScore = absenWeight + tugasWeight + quisWeight + utsWeight + uasWeight;

                Intent intent = new Intent(HitungNilai.this, HasilHitung.class);
                intent.putExtra("NAMA", nama);
                intent.putExtra("ABSEN", absenWeight);
                intent.putExtra("TUGAS", tugasWeight);
                intent.putExtra("QUIS", quisWeight);
                intent.putExtra("UTS", utsWeight);
                intent.putExtra("UAS", uasWeight);
                intent.putExtra("TOTAL_SCORE", totalScore);

                startActivity(intent);
            }
        });
    }

    private void clearEditTexts() {
        etNama.setText("");
        etAbsen.setText("");
        etQuis.setText("");
        etTugas.setText("");
        etUTS.setText("");
        etTUAS.setText("");
    }

    private double getDoubleFromEditText(EditText editText) {
        try {
            return Double.parseDouble(editText.getText().toString());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
