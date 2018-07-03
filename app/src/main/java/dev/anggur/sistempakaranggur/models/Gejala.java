package dev.anggur.sistempakaranggur.models;

/**
 * Created by Imam Abu Mansur on 04/07/2018.
 */

public class Gejala {
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
}
