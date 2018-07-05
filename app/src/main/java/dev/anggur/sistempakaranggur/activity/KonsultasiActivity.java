package dev.anggur.sistempakaranggur.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.anggur.sistempakaranggur.R;
import dev.anggur.sistempakaranggur.adapters.ListPertanyaanAdapter;
import dev.anggur.sistempakaranggur.api.ApiRequest;
import dev.anggur.sistempakaranggur.api.RetroClient;
import dev.anggur.sistempakaranggur.models.Gejala;
import dev.anggur.sistempakaranggur.models.Konsultasi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KonsultasiActivity extends AppCompatActivity {

    @BindView(R.id.lsv_konsultasi)
    ListView lsvKonsultasi;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    ArrayList<Gejala> listGejala = new ArrayList<>();
    ListPertanyaanAdapter adapter;
    ProgressDialog progressDialog;
    ArrayList<Konsultasi> listHasil = new ArrayList<>();
    String gejala = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konsultasi);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sedang ambil data");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ApiRequest request = RetroClient.getRequestService();
        Call<ArrayList<Gejala>> getPertanyaan = request.getPertanyaan();
        getPertanyaan.enqueue(new Callback<ArrayList<Gejala>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Gejala>> call, @NonNull Response<ArrayList<Gejala>> response) {
                progressDialog.dismiss();
                if (response.code() == 200){
                    listGejala = response.body();
                    adapter = new ListPertanyaanAdapter(KonsultasiActivity.this,R.layout.list_question,listGejala);
                    lsvKonsultasi.setAdapter(adapter);
                }else{
                    Toast.makeText(KonsultasiActivity.this, "gagal mengambil data gejala", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Gejala>> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Log.d(getLocalClassName(), "onFailure: " + t.getMessage());
                finish();
            }
        });

    }

    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        CheckBox checkBox;
        gejala = "";
        for (int i = 0; i < lsvKonsultasi.getChildCount(); i++) {
            checkBox = lsvKonsultasi.getChildAt(i).findViewById(R.id.chb_konsultasi);
            if (checkBox.isChecked()){
                if (gejala.equals("")) {
                    gejala = gejala + listGejala.get(i).getKode_gejala();
                }else {
                    gejala = gejala + "," + listGejala.get(i).getKode_gejala();
                }
            }
        }

        if (gejala.equals("")){
            Toast.makeText(this, "Centang Minimal 1 Gejala", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiRequest request = RetroClient.getRequestService();
        Call<ArrayList<Konsultasi>> konsultasi = request.konsultasi(gejala);
        konsultasi.enqueue(new Callback<ArrayList<Konsultasi>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Konsultasi>> call, @NonNull Response<ArrayList<Konsultasi>> response) {
                if (response.code() == 200) {
                    listHasil = response.body();
                    Toast.makeText(KonsultasiActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(KonsultasiActivity.this,HasilKonsultasiActivity.class);
                    intent.putExtra(HasilKonsultasiActivity.EXTRA_HASIL_KONSULTASI,listHasil);
                    startActivity(intent);
                }else{
                    Toast.makeText(KonsultasiActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Konsultasi>> call, @NonNull Throwable t) {
                Toast.makeText(KonsultasiActivity.this, "Gagal Menghubung ke Server", Toast.LENGTH_SHORT).show();
                Log.d(getLocalClassName(), "onFailure: " + t.getMessage());
            }
        });
    }

}