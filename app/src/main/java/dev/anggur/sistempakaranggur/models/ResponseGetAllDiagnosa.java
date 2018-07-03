package dev.anggur.sistempakaranggur.models;

import java.util.ArrayList;

/**
 * Created by Imam Abu Mansur on 04/07/2018.
 */

public class ResponseGetAllDiagnosa {
    private String kode_diagnosa;
    private String nama_diagnosa;
    private String keterangan;
    private ArrayList<Gejala> gejala;

    public ResponseGetAllDiagnosa(String kode_diagnosa, String nama_diagnosa, String keterangan, ArrayList<Gejala> gejala) {
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
}
