package com.example.pertemuan10_readjson;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;

public class ImageAccess {
    public Bitmap rotatedBitmap(Bitmap img, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
    }

    public Bitmap autoRotate_Image(Bitmap img, String pathImg) {
        Bitmap rotateBitmap = null;
        try {
            ExifInterface exif = new ExifInterface(pathImg);
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotateBitmap = rotatedBitmap(img, 90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotateBitmap = rotatedBitmap(img, 180);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotateBitmap = rotatedBitmap(img, 270);
                    break;
                case ExifInterface.ORIENTATION_NORMAL:
                default:
                    rotateBitmap = img;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rotateBitmap;
    }

    public String getRealPath(Context context, Intent data) {
        String result = "";
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(data.getData(), filePathColumn, null, null, null);
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            result = cursor.getString(columnIndex);
        } else {
            result = contenUri.getPath().toString();
        }
        cursor.close();
        return result;
    }
}
