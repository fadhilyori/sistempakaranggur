package dev.anggur.sistempakaranggur.models;

public class ResponseAddGejala {
    private boolean isSuccess;
    private String kode_gejala;
    private String nama_gejala;
    private String keterangan;

    public ResponseAddGejala(boolean isSuccess, String kode_gejala, String nama_gejala, String keterangan) {
        this.isSuccess = isSuccess;
        this.kode_gejala = kode_gejala;
        this.nama_gejala = nama_gejala;
        this.keterangan = keterangan;
    }

    public boolean isSuccess() {
        return isSuccess;
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
