package dev.anggur.sistempakaranggur.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Hasil implements Parcelable {

    private String nama_diagnosa;
    private double kepercayaan;
    private String solusi;

    public Hasil(String nama_diagnosa, double kepercayaan, String solusi) {
        this.nama_diagnosa = nama_diagnosa;
        this.kepercayaan = kepercayaan;
        this.solusi = solusi;
    }

    public String getNama_hasil() {
        return nama_diagnosa;
    }

    public double getKepercayaan_hasil() {
        return kepercayaan;
    }

    public String getSolusi_hasil() {
        return solusi;
    }

    public double getKepercayaan() {
        return kepercayaan;
    }

    public String getSolusi() {
        return solusi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama_diagnosa);
        dest.writeDouble(this.kepercayaan);
        dest.writeString(this.solusi);
    }

    protected Hasil(Parcel in) {
        this.nama_diagnosa = in.readString();
        this.kepercayaan = in.readDouble();
        this.solusi = in.readString();
    }

    public static final Parcelable.Creator<Hasil> CREATOR = new Parcelable.Creator<Hasil>() {
        @Override
        public Hasil createFromParcel(Parcel source) {
            return new Hasil(source);
        }

        @Override
        public Hasil[] newArray(int size) {
            return new Hasil[size];
        }
    };
}
