package com.example.a210030533_ba203;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Siswa implements Parcelable {
    private String Nis;
    private String Nama;
    private int Foto;

    private List<String> jenisMasalahList = new ArrayList<>();

    public Siswa() {

    }

    public List<String> getJenisMasalahList() {
        return jenisMasalahList;
    }

    public void addJenisMasalah(String jenisMasalah) {
        this.jenisMasalahList.add(jenisMasalah);
    }

    public String getNis() {
        return Nis;
    }

    public void setNis(String nis) {
        Nis = nis;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public int getFoto() {
        return Foto;
    }

    public void setFoto(int foto) {
        Foto = foto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Nis);
        dest.writeString(this.Nama);
        dest.writeInt(this.Foto);
        dest.writeStringList(this.jenisMasalahList);
    }

    protected Siswa(Parcel in) {
        this.Nis = in.readString();
        this.Nama = in.readString();
        this.Foto = in.readInt();
        this.jenisMasalahList = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Siswa> CREATOR = new Parcelable.Creator<Siswa>() {
        @Override
        public Siswa createFromParcel(Parcel source) {
            return new Siswa(source);
        }

        @Override
        public Siswa[] newArray(int size) {
            return new Siswa[size];
        }
    };
}