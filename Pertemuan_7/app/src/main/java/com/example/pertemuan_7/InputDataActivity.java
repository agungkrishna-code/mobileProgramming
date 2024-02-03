
package com.example.pertemuan_7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class InputDataActivity extends AppCompatActivity {
    EditText etNama, etNim, etUmur;
    ImageView imgV;
    Button btSave;
    Spinner mySpin;
    ArrayList<Mahasiswa> arrmhs = new ArrayList<Mahasiswa>();
    int tempFoto = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        etNama = findViewById(R.id.editTextNamaInputData);
        etNim = findViewById(R.id.editTextNimInputData);
        etUmur = findViewById(R.id.editTextUmurInputData);

        imgV = findViewById(R.id.imageViewInputData);

        btSave = findViewById(R.id.buttonSave);

        mySpin = findViewById(R.id.spinnerInputFoto);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    arrmhs.add(new Mahasiswa(
                            etNama.getText().toString(),
                            etNim.getText().toString(),
                            Integer.parseInt(etUmur.getText().toString()),
                            tempFoto
                    ));
                    SaveValue.setMhs(arrmhs);
                    etUmur.setText("");
                    etNim.setText("");
                    etNama.setText("");
                    Toast.makeText(InputDataActivity.this, "Data Disimpan", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        mySpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (mySpin.getSelectedItem().toString()) {
                    case "luffi":
                        tempFoto = R.drawable.luffi;
                        imgV.setImageResource(R.drawable.luffi);
                        break;
                    case "inc":
                        tempFoto = R.drawable.inc;
                        imgV.setImageResource(R.drawable.inc);
                        break;
                    case "mickey":
                        tempFoto = R.drawable.mickey;
                        imgV.setImageResource(R.drawable.mickey);
                        break;
                    case "monster":
                        tempFoto = R.drawable.monster;
                        imgV.setImageResource(R.drawable.monster);
                        break;
                    case "naruto":
                        tempFoto = R.drawable.naruto;
                        imgV.setImageResource(R.drawable.naruto);
                        break;
                    case "pikachu":
                        tempFoto = R.drawable.pikachu;
                        imgV.setImageResource(R.drawable.pikachu);
                        break;
                    case "shizuka":
                        tempFoto = R.drawable.shizuka;
                        imgV.setImageResource(R.drawable.shizuka);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Handle nothing selected here if needed.
            }
        });
    }
}
