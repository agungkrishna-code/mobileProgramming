package com.example.pertemuan_7;

import android.os.Parcel;
import android.os.Parcelable;

public class Mahasiswa implements Parcelable {
    private String Nim, Nama;
    private int Umur, Foto;

    public String getNim() {
        return Nim;
    }

    public String getNama() {
        return Nama;
    }

    public int getUmur() {
        return Umur;
    }

    public int getFoto() {
        return Foto;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Nim);
        dest.writeInt(this.Foto);
        dest.writeInt(this.Umur);
        dest.writeString(this.Nama);
    }

    public void readFromParcel(Parcel source) {
        this.Nim = source.readString();
        this.Foto = source.readInt();
        this.Umur = source.readInt();
        this.Nama = source.readString();
    }

    public Mahasiswa(String Nama, String Nim, int Umur, int Foto) {
        this.Nama = Nama;
        this.Nim = Nim;
        this.Umur = Umur;
        this.Foto = Foto;
    }

    protected Mahasiswa(Parcel in) {
        this.Nim = in.readString();
        this.Foto = in.readInt();
        this.Umur = in.readInt();
        this.Nama = in.readString();
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
