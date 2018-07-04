package dev.anggur.sistempakaranggur.models;

/**
 * Created by Imam Abu Mansur on 04/07/2018.
 */

public class ResponseKonsultasi {
    private String nama_diagnosa;
    private String solusi;
    private int kepercayaan;

    public ResponseKonsultasi(String nama_diagnosa, String solusi, int kepercayaan) {
        this.nama_diagnosa = nama_diagnosa;
        this.solusi = solusi;
        this.kepercayaan = kepercayaan;
    }

    public String getNama_diagnosa() {
        return nama_diagnosa;
    }

    public String getSolusi() {
        return solusi;
    }

    public int getKepercayaan() {
        return kepercayaan;
    }
}
