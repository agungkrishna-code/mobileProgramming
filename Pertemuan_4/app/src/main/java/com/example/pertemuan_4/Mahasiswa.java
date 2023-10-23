package com.example.pertemuan_4;

import android.os.Parcel;
import android.os.Parcelable;

public class Mahasiswa implements Parcelable {
    private String Nama;
    private String Nim;
    private int Umur;
    private String Fruit_L;

    public Mahasiswa() {
        Nama = "";
        Nim = "";
        Umur = 0;
        Fruit_L = "";
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getNim() {
        return Nim;
    }

    public void setNim(String nim) {
        Nim = nim;
    }

    public int getUmur() {
        return Umur;
    }

    public void setUmur(int umur) {
        Umur = umur;
    }

    public String getFruit_L() {
        return Fruit_L;
    }

    public void setFruit_L(String fruit_L) {
        Fruit_L = fruit_L;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Nama);
        dest.writeString(this.Nim);
        dest.writeInt(this.Umur);
        dest.writeString(this.Fruit_L);
    }

    protected Mahasiswa(Parcel in) {
        this.Nama = in.readString();
        this.Nim = in.readString();
        this.Umur = in.readInt();
        this.Fruit_L = in.readString();
    }

    public static final Parcelable.Creator<Mahasiswa> CREATOR = new Parcelable.Creator<Mahasiswa>() {
        @Override
        public Mahasiswa createFromParcel(Parcel source) {
            return new Mahasiswa(source);
        }

        @Override
        public Mahasiswa[] newArray(int size) {
            return new Mahasiswa[size];
        }
    };
}
