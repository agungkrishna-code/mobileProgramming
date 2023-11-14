package com.example.a210030533_ba203;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class inputDataSiswa extends AppCompatActivity {
    EditText etNISsiswa, etNamaSiswa;
    Spinner spvFotoSiswa;
    ImageView ivFotoSiswa;
    Button btnSimpan;

    ArrayList<Siswa> arrmhs = new ArrayList<>();
    int tempFoto = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_siswa);

        etNISsiswa = findViewById(R.id.editTextNISsiswa);
        etNamaSiswa = findViewById(R.id.editTextNamaSiswa);
        spvFotoSiswa = findViewById(R.id.spinnerFotoSiswa);
        ivFotoSiswa = findViewById(R.id.imageViewFotoSiswa);
        btnSimpan = findViewById(R.id.buttonSimpanInput);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.siswa, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spvFotoSiswa.setAdapter(adapter);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nis = etNISsiswa.getText().toString().trim();
                if (isNISAlreadyExists(nis)) {
                    showToast("NIS Sudah Diinputkan, Coba Yang Lain.");
                    return;
                }

                try {
                    Siswa newSiswa = new Siswa();
                    newSiswa.setNis(nis);
                    newSiswa.setNama(etNamaSiswa.getText().toString());
                    newSiswa.setFoto(tempFoto);

                    arrmhs.add(newSiswa);
                    SaveValue.setMhs(arrmhs);

                    resetFields();
                    showToast("Data Berhasil Disimpan");
                    Log.d("InputDataSiswaActivity", "Data Siswa Berhasil Disimpan: " + newSiswa.getNama());
                } catch (Exception e) {
                    showToast("Gagal menyimpan data: " + e.getMessage());
                    Log.e("InputDataSiswaActivity", "Gagal Menyimpan Data: " + e.getMessage());
                }
            }
        });

        spvFotoSiswa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                handlePhotoSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(inputDataSiswa.this, message, Toast.LENGTH_SHORT).show();
    }

    private boolean isNISAlreadyExists(String nis) {
        for (Siswa siswa : arrmhs) {
            if (siswa.getNis().equals(nis)) {
                return true;
            }
        }
        return false;
    }

    private void handlePhotoSelection(int position) {
        switch (spvFotoSiswa.getSelectedItem().toString()) {
            case "siswa1":
                tempFoto = R.drawable.siswa1;
                ivFotoSiswa.setImageResource(R.drawable.siswa1);
                break;
            case "siswa2":
                tempFoto = R.drawable.siswa2;
                ivFotoSiswa.setImageResource(R.drawable.siswa2);
                break;
            case "siswa3":
                tempFoto = R.drawable.siswa3;
                ivFotoSiswa.setImageResource(R.drawable.siswa3);
                break;
            case "siswa4":
                tempFoto = R.drawable.siswa4;
                ivFotoSiswa.setImageResource(R.drawable.siswa4);
                break;
            case "siswa5":
                tempFoto = R.drawable.siswa5;
                ivFotoSiswa.setImageResource(R.drawable.siswa5);
                break;
            case "siswa6":
                tempFoto = R.drawable.siswa6;
                ivFotoSiswa.setImageResource(R.drawable.siswa6);
                break;
            default:
        }
    }

    private void resetFields() {
        etNISsiswa.setText("");
        etNamaSiswa.setText("");
    }
}
