package dev.anggur.sistempakaranggur.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Imam Abu Mansur on 04/07/2018.
 */

public class ResponseKonsultasi implements Parcelable {
    private String nama_diagnosa;
    private double kepercayaan;
    private String solusi;

    public ResponseKonsultasi(String nama_diagnosa, double kepercayaan, String solusi) {
        this.nama_diagnosa = nama_diagnosa;
        this.kepercayaan = kepercayaan;
        this.solusi = solusi;
    }

    public String getNama_responseKonsultasi() {
        return nama_diagnosa;
    }

    public double getKepercayaan_responseKonsultasi() {
        return kepercayaan;
    }

    public String getSolusi_responseKonsultasi() {
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

    protected ResponseKonsultasi(Parcel in) {
        this.nama_diagnosa = in.readString();
        this.kepercayaan = in.readDouble();
        this.solusi = in.readString();
    }

    public static final Parcelable.Creator<ResponseKonsultasi> CREATOR = new Parcelable.Creator<ResponseKonsultasi>() {
        @Override
        public ResponseKonsultasi createFromParcel(Parcel source) {
            return new ResponseKonsultasi(source);
        }

        @Override
        public ResponseKonsultasi[] newArray(int size) {
            return new ResponseKonsultasi[size];
        }
    };
}