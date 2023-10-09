package com.example.senin_pertemuan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editUsername, editPassword;
    Button btMyLogin;
    TextView tvInfo;
    int counter=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUsername=(EditText) findViewById(R.id.etUsername);
        editPassword=(EditText) findViewById(R.id.etPassword);
        btMyLogin=(Button) findViewById(R.id.buttonLogin);
        tvInfo=(TextView) findViewById(R.id.textViewInfo);

        btMyLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Validasi(editUsername.getText().toString(), editPassword.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Username dan Password Valid", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean Validasi(String Username, String Password) {
        boolean valid=false;
        if (Username.equals("admin") && Password.equals("admin")) {
            valid = true;
        } else {
            counter--;
            tvInfo.setText("Toleransi Kesalahan" + String.valueOf(counter));
            if (counter == 0) {
                btMyLogin.setEnabled(false);
            }
        }

        return valid;
    }
}