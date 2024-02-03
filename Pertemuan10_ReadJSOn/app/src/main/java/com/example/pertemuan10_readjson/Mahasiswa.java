package com.example.pertemuan10_readjson;

public class Mahasiswa {
    private String Nim, Nama, MyFoto;
    private int Umur;

    public Mahasiswa(String nim, String nama, int umur, String myFoto) {
        Nim = nim;
        Nama = nama;
        Umur = umur;
        MyFoto = myFoto;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getMyFoto() {
        return MyFoto;
    }

    public void setMyFoto(String myFoto) {
        MyFoto = myFoto;
    }

    public int getUmur() {
        return Umur;
    }

    public void setUmur(int umur) {
        Umur = umur;
    }

    public String getNim() {
        return Nim;
        }
    }
