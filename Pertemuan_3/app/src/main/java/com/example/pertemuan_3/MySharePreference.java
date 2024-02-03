package com.example.pertemuan_3;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MySharePreference {
    static final String KEY_SCORE = "KEY_SCORE";
    static final String KEY_JUDUL = "KEY_JUDUL";

    private static SharedPreferences getSharedPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setKeyJudul(Context context, String judul) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_JUDUL, judul);
        editor.apply();
    }
    public static void setKeyScore(Context context, int score) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putInt(KEY_SCORE, score);
        editor.apply();
    }
    public static String getKeyJudul(Context context) {
        return getSharedPreference(context).getString(KEY_JUDUL, "");

    }

    public static int getKeyScore(Context context) {
        return getSharedPreference(context).getInt(KEY_SCORE, 0);

    }

    public static void RemoveAllValue(Context context) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(KEY_SCORE);
        editor.remove(KEY_JUDUL);
        editor.apply();
    }

}