package com.example.menghitungnilaiakhir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HasilHitung extends AppCompatActivity {
    TextView tvNama, tvAbsensi, tvTugas, tvQuis, tvUTS, tvTUAS, tvNTotal, tvHasil;
    Button btExitKeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_hitung);

        //menerima nilai
        Intent intent = getIntent();
        String nama = intent.getStringExtra("NAMA");
        double absen = intent.getDoubleExtra("ABSEN", 0);
        double tugas = intent.getDoubleExtra("TUGAS", 0);
        double quis = intent.getDoubleExtra("QUIS", 0);
        double uts = intent.getDoubleExtra("UTS", 0);
        double uas = intent.getDoubleExtra("UAS", 0);
        double totalScore = intent.getDoubleExtra("TOTAL_SCORE", 0);


        tvNama = findViewById(R.id.textViewNama);
        tvAbsensi = findViewById(R.id.textViewAbsensi);
        tvTugas = findViewById(R.id.textViewTugas);
        tvQuis = findViewById(R.id.textViewQuis);
        tvUTS = findViewById(R.id.textViewUTS);
        tvTUAS = findViewById(R.id.textViewUAS);
        tvNTotal = findViewById(R.id.textViewTotal);
        tvHasil = findViewById(R.id.textViewHasil);

        btExitKeluar = findViewById(R.id.buttonExitHasil);

        // Data Tampilan pada device
        tvNama.setText("Nama: " + nama);
        tvAbsensi.setText("Absensi: " + absen);
        tvTugas.setText("Tugas: " + tugas);
        tvQuis.setText("Quis: " + quis);
        tvUTS.setText("UTS: " + uts);
        tvTUAS.setText("UAS: " + uas);
        tvNTotal.setText("Total: " + totalScore);

        // if else lulus dan tidak lulus
        if (totalScore > 58) {
            tvHasil.setText("LULUS");
        } else {
            tvHasil.setText("TIDAK LULUS");
        }

        btExitKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HasilHitung.this, HitungNilai.class);
                startActivity(intent);
                finish();
            }
        });
    }
}