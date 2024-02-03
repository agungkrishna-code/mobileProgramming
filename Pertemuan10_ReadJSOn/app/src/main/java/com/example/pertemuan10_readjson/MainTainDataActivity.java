package com.example.pertemuan10_readjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainTainDataActivity extends AppCompatActivity {

    ImageView imvMaintain;
    EditText etNim, etUmur, etNama;
    ProgressBar pgs;
    Button btPilih, btSave, btUpdate, btDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tain_data);

        imvMaintain=(ImageView) findViewById(R.id.imageViewMaintain);
        etNim=(EditText) findViewById(R.id.editTextNimMaintain);
        etUmur=(EditText) findViewById(R.id.editTextUmurMaintain);
        etNama=(EditText) findViewById(R.id.editTextNamaMaintain);

        pgs=(ProgressBar) findViewById(R.id.progressBarMaintain);

        btPilih=(Button) findViewById(R.id.buttonPilihFotoMaintain);
        btSave=(Button) findViewById(R.id.buttonSaveMaintain);
        btUpdate=(Button) findViewById(R.id.buttonUpdateMaintain);
        btDelete=(Button) findViewById(R.id.buttonDeleteMaintain);
        pgs.setVisibility(View.GONE);
        imvMaintain.setImageResource(R.drawable.ic_launcher_background);
    }
}