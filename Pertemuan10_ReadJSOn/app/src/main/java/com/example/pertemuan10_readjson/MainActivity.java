package com.example.pertemuan10_readjson;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    final int permissionInternetCode = 1001;

    Button btvAll, btcrDat, btManitainData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH && checkSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            requestPermission(new String[]{
                    Manifest.permission.INTERNET
            }, 1000);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermission(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 1001);
        }

        btvAll = findViewById(R.id.buttonLihatData);
        btcrDat = findViewById(R.id.buttonCariData);
        btManitainData = findViewById(R.id.buttonMaintainData);
        showInternetPermission();

        btvAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityViewAll.class);
                startActivity(intent);
            }
        });
        btcrDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivitySearch.class);
                startActivity(intent);
            }
        });
        btManitainData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainTainData.class);
                startActivity(intent);
            }
        });
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1000:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Internet Granted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Permission Internet Denied", Toast.LENGTH_LONG).show();
                }
                break;
            case 1001:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Read Storage Granted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Permission Read Storage Denied", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void requestPermission(String[] permissions, int permissionRequestCode) {
        ActivityCompat.requestPermissions(this, permissions, permissionRequestCode);
    }

    private void showExplanation(String title, String message, final String permission, final int permissionCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        requestPermission(permission, permissionCode);
                    }
                });
        builder.create().show();
    }

    private void showInternetPermission() {
        String kindPermission = Manifest.permission.INTERNET;
        int permissionCheck = ContextCompat.checkSelfPermission(this, kindPermission);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, kindPermission)) {
                showExplanation("Permission Needed", "Application Needs Internet Permission. Please allow it.", kindPermission, permissionInternetCode);
            } else {
                requestPermission(kindPermission, permissionInternetCode);
            }
        }
    }
}
