package com.example.pertemuan_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainKata extends AppCompatActivity {
    EditText etPalindrom, etKataSatu, etKataDua, etHasilSwapKataSatu, etHasilSwapKataDua;

    Button btPalindrom, btSwapGenap, btSwapGanjil, btExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_kata);
        etPalindrom = (EditText) findViewById(R.id.editTextPalindrom);
        etKataSatu = (EditText) findViewById(R.id.editTextKataSatu);
        etKataDua = (EditText) findViewById(R.id.editTextKataDua);
        etHasilSwapKataSatu = (EditText) findViewById(R.id.editTextHasilSwapKataSatu);
        etHasilSwapKataDua = (EditText) findViewById(R.id.editTextHasilSwapKataDua);

        btPalindrom = (Button) findViewById(R.id.buttonCekpalindrom);
        btSwapGenap = (Button) findViewById(R.id.buttonSwapGenap);
        btSwapGanjil = (Button) findViewById(R.id.buttonSwapGanjil);
        btExit = (Button) findViewById(R.id.buttonExitMainKata);

        btPalindrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kata = etPalindrom.getText().toString().toLowerCase();
                if (isPalindrom(kata)) {
                    Toast.makeText(MainKata.this, "Ini adalah palindrom!", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MainKata.this, "Ini bukan palindrom.", Toast.LENGTH_SHORT).show();
            }
        });

        btSwapGenap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kataSatu = etKataSatu.getText().toString();
                String kataDua = etKataDua.getText().toString();
                if (kataSatu.length() == kataDua.length()) {
                    String hasilSwap = swapHuruf(kataSatu, kataDua, false);
                    etHasilSwapKataSatu.setText(hasilSwap.split(" ")[0]);
                    etHasilSwapKataDua.setText(hasilSwap.split(" ")[1]);
                    Toast.makeText(MainKata.this, "Hasil swap genap: " + hasilSwap, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainKata.this, "Panjang kata harus sama untuk swap genap.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btSwapGanjil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kataSatu = etKataSatu.getText().toString();
                String kataDua = etKataDua.getText().toString();
                if (kataSatu.length() == kataDua.length()) {
                    String hasilSwap = swapHuruf(kataSatu, kataDua, true);
                    etHasilSwapKataSatu.setText(hasilSwap.split(" ")[0]);
                    etHasilSwapKataDua.setText(hasilSwap.split(" ")[1]);
                    Toast.makeText(MainKata.this, "Hasil swap ganjil: " + hasilSwap, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainKata.this, "Panjang kata harus sama untuk swap ganjil.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btExit.setOnClickListener(view -> finish());
    }

    private boolean isPalindrom(String kata) {
        // Implementasi deteksi palindrom
        int length = kata.length();
        for (int i = 0; i < length / 2; i++) {
            if (kata.charAt(i) != kata.charAt(length - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    private String swapHuruf(String kataSatu, String kataDua, boolean ganjil) {
        // Implementasi swap huruf
        char[] arraySatu = kataSatu.toCharArray();
        char[] arrayDua = kataDua.toCharArray();

        for (int i = 0; i < kataSatu.length(); i++) {
            if (ganjil && i % 2 == 0) {
                char temp = arraySatu[i];
                arraySatu[i] = arrayDua[i];
                arrayDua[i] = temp;
            } else if (!ganjil && i % 2 != 0) {
                char temp = arraySatu[i];
                arraySatu[i] = arrayDua[i];
                arrayDua[i] = temp;
            }
        }

        return new String(arraySatu) + " " + new String(arrayDua);

    }
}