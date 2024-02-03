package com.example.pertemuan_8_permission;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imvFoto, imvFoto2;
    Button btLoad;
    TextView tvRealPath, tvAndroURIPath;
    private final int REQUEST_PERMISSION_READIMAGE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imvFoto = findViewById(R.id.imageViewFotoLoad);
        imvFoto2 = findViewById(R.id.imageViewFotoLoad2);
        btLoad = findViewById(R.id.buttonLoadFile);
        tvRealPath = findViewById(R.id.textViewRealPath);
        tvAndroURIPath = findViewById(R.id.textViewpathAndroidURI);

        showReadStorageExternalPermission();

        btLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = Environment.getExternalStorageDirectory() + "/" + "Picture" + "/";
                Uri uri = Uri.parse(path);
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setDataAndType(uri, "image/*");
                activityResultLauncher.launch(intent);
            }
        });
    }
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                Uri data = o.getData().getData();
                tvAndroURIPath.setText(data.getPath());
                imvFoto.setImageURI(data);

                String realPath = getRealPathFromURI(getApplicationContext(), data);
                tvRealPath.setText(realPath);
                Bitmap bMap = BitmapFactory.decodeFile(realPath);
                imvFoto2.setImageBitmap(bMap);
            }
        });

    private void requestPermission(String permissionName, int PermissionRequestCode) {
        ActivityCompat.requestPermissions(this, new String[]{permissionName}, PermissionRequestCode);
    }

    private void showExplanation (String title, String message,  final String permission, final int PermissionRequestCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        requestPermission(permission, PermissionRequestCode);
                    }
                });
        builder.create().show();
    }

    private void showReadStorageExternalPermission() {
        String kindPermission = "";
        if (Build.VERSION.SDK_INT >= 33) {
            kindPermission = "READ_MEDIA_IMAGES";
        } else {
            kindPermission = "READ_EXTERNAL_STORAGE";
        }
        int permissionCheck = ContextCompat.checkSelfPermission(this, kindPermission);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, kindPermission)) {
                showExplanation("Permission Needed", "Application need permission please", kindPermission,REQUEST_PERMISSION_READIMAGE);
            } else {
                requestPermission(kindPermission, REQUEST_PERMISSION_READIMAGE);
            }
        } else {
            Toast .makeText(this, "Permission (already) Granted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_READIMAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Read Image Permission Granted", Toast.LENGTH_SHORT).show();
            } else if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this, "Read Image Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] data_media_uri = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  data_media_uri, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
