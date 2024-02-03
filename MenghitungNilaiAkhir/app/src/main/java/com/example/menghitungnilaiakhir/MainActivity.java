/*
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*%
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%(
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*@@@%%%.
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@**,@@%%%%(
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@***@@@%%%%%
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@****@@@%%%%%%
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*****@@@%%%%%%%
@@@@@@@@@@@@@@@@@@@@@@@@@@@@#@@******@@@%%%%%%%*
@@@@@@@@@@@@@@@@@@@@@@@@@@%##@@******,@@@%%%%%%%%
@@@@@@@@@@@@@@@@@@@@@@@@@@####@@*******@@@%%%%%%%%%
@@@@@@@@@@@@@@@@@@@@@@@@@#####(@@*******@@@*%%%%%%%%%
@@@@@@@@@@@@@@@@@@@@@@@@@######%@@.*******@@@%%%%%%%%%
@@@@@@@@@@@@@@@@@@@@@@@@@@#######@@@*******%@@@%%%%%%%%%
@@@@@@@@@@@@@@@@@@@@@@@%@@@########@@&*******@@@%%%%%%%%%
@@@@@@@@@@@@@@@@@@@@@@@%%%@@@@#######@@/******@@@%%%%%%%%%
@@@@@@@@@@@@@@@@@@@@@@@%%%%%%#@@@@@@###@@******@@@%%%%%%%%
@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%%%%#@@@*@@.****@@@%%%%%%%%
@@@@@@@@@@@@@@@@@@@@@@#%%%%%%%%%%%%%%%%@@@@.***@@@%%%%%%%%
@@@@@@@@@@@@@@@@@@@@@@@&@/%%%%%%%%%%%%%%%@@@**@@@(%%%%%%%
@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%%%%%%%%%(@@*@@@%%%%%%%%
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%%%%%%@@*%%%%%%%%%
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%(@#%%%%%%%%%%%%%%%%%%&*%
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@,%%@@.%%%%%%%%(@%%%
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&@@@/%
NAMA  : DEWA GEDE AGUNG KRISHNANANDA SUDEWA
NIM   : 210030533
KELAS : BA203
*/
package com.example.menghitungnilaiakhir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btNilaiAkhir, btExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btNilaiAkhir = findViewById(R.id.buttonNilaiAkhir);
        btExit = findViewById(R.id.buttonExitNilaiAkhir);

        btNilaiAkhir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, HitungNilai.class);
                startActivity(intent);
            }
        });

        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}