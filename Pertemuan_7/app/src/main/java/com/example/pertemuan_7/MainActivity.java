package com.example.pertemuan_7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btInput, btListView, btGridView, btExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btInput=(Button) findViewById(R.id.buttonInput);
        btListView=(Button) findViewById(R.id.buttonListView);
        btGridView=(Button) findViewById(R.id.buttonGridView);
        btExit=(Button) findViewById(R.id.buttonExit);

        btInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, InputDataActivity.class);
                startActivity(intent);
            }
        });

        btListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(intent);
            }
        });

        btGridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, GridViewActivity.class);
                startActivity(intent);
            }
        });

        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
    }
}