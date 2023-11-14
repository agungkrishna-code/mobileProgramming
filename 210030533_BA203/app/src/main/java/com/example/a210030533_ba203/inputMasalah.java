package com.example.a210030533_ba203;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class inputMasalah extends AppCompatActivity {
    EditText etNISMasalah, etOutputSiswa;
    Button btnSimpanMasalah, btnCariSiswa;
    ImageView ivFotoMasalah;
    Spinner spnJenisMasalah;

    HashMap<String, Siswa> dataSiswa = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_masalah);

        etNISMasalah = findViewById(R.id.editTextNISMasalah);
        etOutputSiswa = findViewById(R.id.editTextOutputSiswa);
        btnSimpanMasalah = findViewById(R.id.buttonSimpanMasalah);
        btnCariSiswa = findViewById(R.id.buttonCari);
        ivFotoMasalah = findViewById(R.id.imageViewFotoMasalah);
        spnJenisMasalah = findViewById(R.id.spinnerMasalah);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.masalah, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnJenisMasalah.setAdapter(adapter);

        ArrayList<Siswa> savedData = SaveValue.getMhs();
        for (Siswa siswa : savedData) {
            dataSiswa.put(siswa.getNis(), siswa);
        }

        btnCariSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nis = etNISMasalah.getText().toString().trim();

                if (!dataSiswa.isEmpty() && dataSiswa.containsKey(nis)) {
                    Siswa siswa = dataSiswa.get(nis);

                    etOutputSiswa.setText(siswa.getNama());
                    ivFotoMasalah.setImageResource(siswa.getFoto());

                    Toast.makeText(getApplicationContext(), "Siswa Ditemukan!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Siswa Tidak Ditemukan!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSimpanMasalah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jenisMasalah = spnJenisMasalah.getSelectedItem().toString();
                String nis = etNISMasalah.getText().toString().trim();

                if (dataSiswa.containsKey(nis)) {
                    Siswa siswa = dataSiswa.get(nis);
                    siswa.addJenisMasalah(jenisMasalah);
                    dataSiswa.put(nis, siswa);

                    SaveValue.setMhs(new ArrayList<>(dataSiswa.values()));

                    Toast.makeText(getApplicationContext(), "Data Tersimpan!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "NIS Tidak Ditemukan!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}