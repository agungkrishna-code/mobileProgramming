package com.example.pertemuan_9_sqlite;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView myGrid;
    ImageButton ibtAddData;
    ArrayList<Mahasiswa> data_mhs = new ArrayList<Mahasiswa>();
    Mahasiswa tempData;

    private final int REQUEST_PERMISSION_READIMAGE = 1001;

    DatabaseHelper databaseHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myGrid = findViewById(R.id.gridViewItem);
        ibtAddData = findViewById(R.id.imageButtonMainActivity);

        showPermission();

        ibtAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ManageDataActivity.class);
                intent.putExtra("Mode", "Insert");
                startActivity(intent);
            }
        });

        if (databaseHelper.getCountData() > 0) {
            data_mhs = databaseHelper.transfertoArraylist(getApplicationContext());
            if (data_mhs.size() > 0) {
                setAdapterGrid();
            }
        }
    }

    private void setAdapterGrid() {
        ArrayAdapter<Mahasiswa> adapter = new myListAdapter();
        myGrid.setAdapter(adapter);

        myGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tempData = data_mhs.get(i);
                Intent intent = new Intent(MainActivity.this, ManageDataActivity.class);
                intent.putExtra("data", (CharSequence) tempData);
                intent.putExtra("Mode", "UpdateDelete");
                startActivity(intent);
            }
        });
    }

    public void showPermission() {
        String kindPermission;
        if (Build.VERSION.SDK_INT >= 29) {
            kindPermission = Manifest.permission.READ_MEDIA_IMAGES;
        } else {
            kindPermission = Manifest.permission.READ_EXTERNAL_STORAGE;
        }
        int permissionCheck = ContextCompat.checkSelfPermission(this, kindPermission);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, kindPermission)) {
                showExplanation("Permission Needed", "Application needs permission to read images", kindPermission, REQUEST_PERMISSION_READIMAGE);
            } else {
                requestPermissions(kindPermission, REQUEST_PERMISSION_READIMAGE);
            }
        } else {
            Toast.makeText(this, "Permission (already) Granted", Toast.LENGTH_SHORT).show();
        }
    }

    private void requestPermissions(String permissionName, int permissionRequestCode) {
        ActivityCompat.requestPermissions(this, new String[]{permissionName}, permissionRequestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_READIMAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Read Image Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Read Image Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void showExplanation(String title, String message, String permission, int permissionRequestCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        requestPermissions(permission, permissionRequestCode);
                    }
                });
        builder.create().show();
    }

    class myListAdapter extends ArrayAdapter<Mahasiswa> {
        public myListAdapter() {
            super(MainActivity.this, R.layout.data_item, data_mhs);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.data_item, parent, false);
            }
            Mahasiswa myMhs = data_mhs.get(position);
            ImageView imv = convertView.findViewById(R.id.imageViewGambar);
            TextView tvNim = convertView.findViewById(R.id.textView2);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 10;
            Bitmap bMap = BitmapFactory.decodeFile(myMhs.getPath(), options);
            imv.setImageBitmap(bMap);
            tvNim.setText(myMhs.getNim());
            return convertView;
        }
    }
}