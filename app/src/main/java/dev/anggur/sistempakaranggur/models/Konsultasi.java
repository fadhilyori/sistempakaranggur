package dev.anggur.sistempakaranggur.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Imam Abu Mansur on 04/07/2018.
 */

public class Konsultasi implements Parcelable {
    private String nama_diagnosa;
    private double kepercayaan;
    private String solusi;

    public Konsultasi(String nama_diagnosa, double kepercayaan, String solusi) {
        this.nama_diagnosa = nama_diagnosa;
        this.kepercayaan = kepercayaan;
        this.solusi = solusi;
    }

    public String getNama_diagnosa() {
        return nama_diagnosa;
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

    protected Konsultasi(Parcel in) {
        this.nama_diagnosa = in.readString();
        this.kepercayaan = in.readDouble();
        this.solusi = in.readString();
    }

    public static final Parcelable.Creator<Konsultasi> CREATOR = new Parcelable.Creator<Konsultasi>() {
        @Override
        public Konsultasi createFromParcel(Parcel source) {
            return new Konsultasi(source);
        }

        @Override
        public Konsultasi[] newArray(int size) {
            return new Konsultasi[size];
        }
    };
}
