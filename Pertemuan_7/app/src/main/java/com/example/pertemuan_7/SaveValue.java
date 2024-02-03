package com.example.pertemuan_7;

import java.util.ArrayList;

public class SaveValue {
    private static ArrayList<Mahasiswa> mhs = new ArrayList<Mahasiswa>();

    public static ArrayList<Mahasiswa> getMhs() {
        return mhs;
    }

    public static void setMhs(ArrayList<Mahasiswa> mhs1) {
        mhs = mhs1;
    }
}
