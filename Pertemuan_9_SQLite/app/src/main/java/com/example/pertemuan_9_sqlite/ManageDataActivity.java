package com.example.pertemuan_9_sqlite;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class ManageDataActivity extends AppCompatActivity {
    ImageView imv;
    EditText etNama, etUmur, etNim;

    Button btAdd, btUpdate, btDelete, btView, btChoose;

    Mahasiswa data = null;
    String mode, path;
    DatabaseHelper databaseHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_data);

        etNim = findViewById(R.id.editTextNim);
        etNama = findViewById(R.id.editTextNama);
        etUmur = findViewById(R.id.editTextUmur);
        imv = findViewById(R.id.imageViewFotoManageData);

        btAdd = findViewById(R.id.buttonInputData);
        btUpdate = findViewById(R.id.buttonUpdateData);
        btDelete = findViewById(R.id.buttonDeleteData);
        btView = findViewById(R.id.buttonView);
        btChoose = findViewById(R.id.buttonPilihGambar);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAlert("insert");
            }
        });

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAlert("update");
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAlert("delete");
            }
        });

        btView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageDataActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myPath = Environment.getExternalStorageDirectory() + "/" + "Pictures" + "/";
                Uri uri = Uri.parse(myPath);
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setDataAndType(uri, "image/*");
                activityResultLauncher.launch(intent);
            }
        });

        refreshActivity();

        Intent intent = getIntent();
        mode = intent.getStringExtra("Mode");
        if (mode.equalsIgnoreCase("Insert")) {
            btAdd.setEnabled(true);
            btUpdate.setEnabled(false);
            btDelete.setEnabled(false);
            etNim.setEnabled(true);
        } else {
            btDelete.setEnabled(true);
            btUpdate.setEnabled(true);
            btAdd.setEnabled(false);
            data = intent.getParcelableExtra("data");
            etNim.setEnabled(false);

            if (data != null) {
                Bitmap bMap = BitmapFactory.decodeFile(data.getPath().toString());
                imv.setImageBitmap(bMap);
                path = data.getPath();
                etNim.setText(data.getNim().toString());
                etNama.setText(data.getNama().toString());
                etUmur.setText(String.valueOf(data.getUmur()));
            }
        }
    }

    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Uri data = result.getData().getData();
                    imv.setImageURI(data);
                    path = getRealPath(ManageDataActivity.this, data);
                }
            }
    );

    public String getRealPath(Activity context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] data_media_uri = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, data_media_uri, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void refreshActivity() {
        imv.setImageResource(R.drawable.ic_launcher_background);
        etNim.setText("");
        etNama.setText("");
        etUmur.setText("");
        path = "";
    }

    private void showDialogAlert(String Mode) {
        int buttonpic = -1;
        String title = "", message = "";
        switch (Mode) {
            case "insert":
                title = "Do you sure save data?";
                message = "click yes to save data";
                buttonpic = R.drawable.save_icon;
                break;
            case "update":
                title = "Do you sure update data?";
                message = "click yes to update data";
                buttonpic = R.drawable.update_icon;
                break;
            case "delete":
                title = "Do you sure delete data?";
                message = "click yes to delete data";
                buttonpic = R.drawable.delete_icon;
                break;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setIcon(buttonpic)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (Mode) {
                            case "insert":
                                Mahasiswa mhs = databaseHelper.getExitsData(
                                        getApplicationContext(),
                                        etNim.getText().toString());

                                if (mhs == null) {
                                    Mahasiswa myMhs = new Mahasiswa(
                                            etNim.getText().toString(),
                                            etNama.getText().toString(),
                                            Integer.parseInt(etUmur.getText().toString()),
                                            path
                                    );
                                    boolean benarInsert = databaseHelper.insertData(getApplicationContext(), myMhs);
                                    if (benarInsert) {
                                        Toast.makeText(ManageDataActivity.this, "Insert Success", Toast.LENGTH_SHORT).show();
                                        refreshActivity();
                                    } else {
                                        Toast.makeText(ManageDataActivity.this, "Insert Failed", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(ManageDataActivity.this, "Nim Owned by", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case "update":
                                Mahasiswa myMhsUpdate = new Mahasiswa(
                                        etNim.getText().toString(),
                                        etNama.getText().toString(),
                                        Integer.parseInt(etUmur.getText().toString()),
                                        path
                                );
                                boolean benarUpdate = databaseHelper.updateData(getApplicationContext(), myMhsUpdate);
                                if (benarUpdate) {
                                    Toast.makeText(ManageDataActivity.this, "Update Success", Toast.LENGTH_SHORT).show();
                                    refreshActivity();
                                } else {
                                    Toast.makeText(ManageDataActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case "delete":
                                int benarDelete = databaseHelper.deleteData(getApplicationContext(), etNim.getText().toString());
                                if (benarDelete > 0) {
                                    Toast.makeText(ManageDataActivity.this, "Delete Success", Toast.LENGTH_SHORT).show();
                                    refreshActivity();
                                } else {
                                    Toast.makeText(ManageDataActivity.this, "Delete Failed", Toast.LENGTH_SHORT).show();
                                }
                                break;
                        }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Do nothing or add logic as needed
                    }
                })
                .show();
    }
}