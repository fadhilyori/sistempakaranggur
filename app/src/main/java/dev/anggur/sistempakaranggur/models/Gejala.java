package dev.anggur.sistempakaranggur.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Imam Abu Mansur on 04/07/2018.
 */

public class Gejala implements Parcelable {
    private String kode_gejala;
    private String nama_gejala;
    private String keterangan;

    public Gejala(String kode_gejala, String nama_gejala, String keterangan) {
        this.kode_gejala = kode_gejala;
        this.nama_gejala = nama_gejala;
        this.keterangan = keterangan;
    }

    public String getKode_gejala() {
        return kode_gejala;
    }

    public String getNama_gejala() {
        return nama_gejala;
    }

    public String getKeterangan() {
        return keterangan;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kode_gejala);
        dest.writeString(this.nama_gejala);
        dest.writeString(this.keterangan);
    }

    protected Gejala(Parcel in) {
        this.kode_gejala = in.readString();
        this.nama_gejala = in.readString();
        this.keterangan = in.readString();
    }

    public static final Parcelable.Creator<Gejala> CREATOR = new Parcelable.Creator<Gejala>() {
        @Override
        public Gejala createFromParcel(Parcel source) {
            return new Gejala(source);
        }

        @Override
        public Gejala[] newArray(int size) {
            return new Gejala[size];
        }
    };
}
