package com.example.pertemuan_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etNama, etNim, etUmur;
    Spinner mySpinFruit;
    Button btSave, btView;
    TextView tvjmlData;
    int jmlData = 0;
    ArrayList<Mahasiswa> mahasiswaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = findViewById(R.id.editTextNama);
        etNim = findViewById(R.id.editTextNim);
        etUmur = findViewById(R.id.editTextUmur);
        mySpinFruit = findViewById(R.id.spinnerBuah);
        btSave = findViewById(R.id.buttonSave);
        btView = findViewById(R.id.buttonView);
        tvjmlData = findViewById(R.id.editTextJmlData);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mahasiswa mahasiswa = new Mahasiswa();
                if (jmlData < 10) {
                    try {
                        mahasiswa.setNama(etNama.getText().toString());
                        mahasiswa.setNim(etNim.getText().toString());
                        mahasiswa.setFruit_L(mySpinFruit.getSelectedItem().toString());
                        mahasiswa.setUmur(Integer.parseInt(etUmur.getText().toString()));
                        mahasiswaList.add(mahasiswa); // Corrected ArrayList usage
                        jmlData = mahasiswaList.size();
                        tvjmlData.setText("Jumlah Data : " + jmlData);
                        etNama.setText("");
                        etNim.setText("");
                        etUmur.setText("");
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Data Error Simpan Gagal", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Data Full", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                intent.putParcelableArrayListExtra("Data", mahasiswaList);
                startActivity(intent);
            }
        });
    }
}
