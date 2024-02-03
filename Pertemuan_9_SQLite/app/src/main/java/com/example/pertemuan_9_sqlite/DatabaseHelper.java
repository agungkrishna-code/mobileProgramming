package com.example.pertemuan_9_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MHS.db";
    private static final String TABLE_NAME = "Mahasiswa";
    private static final String COLUMN_NIM = "nim";
    private static final String COLUMN_NAMA = "nama";
    private static final String COLUMN_UMUR = "umur";
    private static final String COLUMN_PATH = "path";
    private static final String COLUMN_TEST1 = "test1";

    private static final String TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    COLUMN_NIM + " TEXT PRIMARY KEY, " +
                    COLUMN_NAMA + " TEXT, " +
                    COLUMN_UMUR + " INTEGER, " +
                    COLUMN_PATH + " TEXT)";

    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
        db = sqLiteDatabase;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            String query = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + COLUMN_TEST1 + " TEXT";
            db.execSQL(query);
        }
    }

    public int getCountData() {
        int result = 0;
        db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_NIM}, null, null, null,
                null, COLUMN_NIM + " ASC");
        result = cursor.getCount();
        db.close();
        return result;
    }

    public Mahasiswa getExitsData(Context context, String key) {
        Mahasiswa mhs = null;
        try {
            String Query = "SELECT * FROM " + TABLE_NAME + " WHERE upper(" + COLUMN_NIM + ") = '" + key.toUpperCase() + "'";
            db = getReadableDatabase();
            Cursor cursor = db.rawQuery(Query, null);

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                mhs = new Mahasiswa(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NIM)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMA)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_UMUR)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PATH))
                );
            }
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        return mhs;
    }

    public ArrayList<Mahasiswa> transfertoArraylist(Context context) {
        ArrayList<Mahasiswa> arrMhs = new ArrayList<>();

        try {
            db = getReadableDatabase();
            Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_NIM, COLUMN_NAMA, COLUMN_UMUR, COLUMN_PATH},
                    null, null, null, null, COLUMN_NIM + " ASC");

            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    arrMhs.add(new Mahasiswa(
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NIM)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMA)),
                            cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_UMUR)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PATH))
                    ));
                }
            }
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        return arrMhs;
    }

    public boolean insertData(Context context, Mahasiswa myData) {
        boolean benar = false;
        try {
            db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_NIM, myData.getNim());
            values.put(COLUMN_NAMA, myData.getNama());
            values.put(COLUMN_UMUR, myData.getUmur());
            values.put(COLUMN_PATH, myData.getPath());

            long result = db.insert(TABLE_NAME, null, values);
            if (result != -1) {
                benar = true;
            }
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        return benar;
    }

    public boolean updateData(Context context, Mahasiswa myData) {
        boolean benar = false;
        try {
            db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_NIM, myData.getNim());
            values.put(COLUMN_NAMA, myData.getNama());
            values.put(COLUMN_UMUR, myData.getUmur());
            values.put(COLUMN_PATH, myData.getPath());

            int affectedRows = db.update(TABLE_NAME, values, COLUMN_NIM + " = ?", new String[]{myData.getNim()});
            if (affectedRows > 0) {
                benar = true;
            }
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        return benar;
    }

    public int deleteData(Context context, String nim) {
        int jmlDelete = -1;
        try {
            db = getWritableDatabase();
            jmlDelete = db.delete(TABLE_NAME, COLUMN_NIM + " = ?", new String[]{nim});
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        return jmlDelete;
    }
}