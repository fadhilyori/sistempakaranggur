package dev.anggur.sistempakaranggur.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Imam Abu Mansur on 04/07/2018.
 */

public class Diagnosa implements Parcelable {
    private String kode_diagnosa;
    private String nama_diagnosa;
    private String keterangan;
    private ArrayList<Gejala> gejala;

    public Diagnosa(String kode_diagnosa, String nama_diagnosa, String keterangan, ArrayList<Gejala> gejala) {
        this.kode_diagnosa = kode_diagnosa;
        this.nama_diagnosa = nama_diagnosa;
        this.keterangan = keterangan;
        this.gejala = gejala;
    }

    public String getKode_diagnosa() {
        return kode_diagnosa;
    }

    public String getNama_diagnosa() {
        return nama_diagnosa;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public ArrayList<Gejala> getGejala() {
        return gejala;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kode_diagnosa);
        dest.writeString(this.nama_diagnosa);
        dest.writeString(this.keterangan);
        dest.writeList(this.gejala);
    }

    protected Diagnosa(Parcel in) {
        this.kode_diagnosa = in.readString();
        this.nama_diagnosa = in.readString();
        this.keterangan = in.readString();
        this.gejala = new ArrayList<Gejala>();
        in.readList(this.gejala, Gejala.class.getClassLoader());
    }

    public static final Parcelable.Creator<Diagnosa> CREATOR = new Parcelable.Creator<Diagnosa>() {
        @Override
        public Diagnosa createFromParcel(Parcel source) {
            return new Diagnosa(source);
        }

        @Override
        public Diagnosa[] newArray(int size) {
            return new Diagnosa[size];
        }
    };
}
