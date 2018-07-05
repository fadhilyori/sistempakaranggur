package dev.anggur.sistempakaranggur.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.anggur.sistempakaranggur.R;
import dev.anggur.sistempakaranggur.adapters.ListDiagnosaAdapter;
import dev.anggur.sistempakaranggur.adapters.ListGejalaAdapter;
import dev.anggur.sistempakaranggur.adapters.ListPertanyaanAdapter;
import dev.anggur.sistempakaranggur.adapters.ListResponseKonsultasiAdapter;
import dev.anggur.sistempakaranggur.api.ApiRequest;
import dev.anggur.sistempakaranggur.api.RetroClient;
import dev.anggur.sistempakaranggur.models.Diagnosa;
import dev.anggur.sistempakaranggur.models.Gejala;
import dev.anggur.sistempakaranggur.models.ResponseKonsultasi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KonsultasiActivity extends AppCompatActivity {

    @BindView(R.id.lsv_konsultasi)
    ListView lsvKonsultasi;
    @BindView(R.id.lst_hasil)
    ListView lstHasil;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    ArrayList<Gejala> listGejala = new ArrayList<>();
    ListPertanyaanAdapter adapter;
    ListResponseKonsultasiAdapter adapter2;
    ProgressDialog progressDialog;
//    ArrayList<String> gejala = new ArrayList<>();
    ListView lst_hasil;
    ArrayList<ResponseKonsultasi> listHasil = new ArrayList<>();
    String gejala = "";
    CheckBox chb_konsultasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konsultasi);
        ButterKnife.bind(this);
        lst_hasil = (ListView) findViewById(R.id.lst_hasil);
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
//                gejala = gejala + checkBox.getText().toString().trim() + ",";
                if (gejala.equals("")) {
                    if (i < 10) {
                        gejala = gejala + "G00" + String.valueOf(i + 1);
                    } else if (i < 100) {
                        gejala = gejala + "G0" + String.valueOf(i + 1);
                    }
                } else {
                    if (i < 10) {
                        gejala = gejala + ",G00" + String.valueOf(i + 1);
                    } else if (i < 100) {
                        gejala = gejala + ",G0" + String.valueOf(i + 1);
                    }
                }
            }
        }

        ApiRequest request = RetroClient.getRequestService();
        Call<ArrayList<ResponseKonsultasi>> konsultasi = request.konsultasi(gejala);
        konsultasi.enqueue(new Callback<ArrayList<ResponseKonsultasi>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<ResponseKonsultasi>> call, @NonNull Response<ArrayList<ResponseKonsultasi>> response) {
                if (response.code() == 200) {
                    listHasil = response.body();
                    adapter2 = new ListResponseKonsultasiAdapter(KonsultasiActivity.this,R.layout.list_item, listHasil);
                    lstHasil.setAdapter(adapter2);
                    Toast.makeText(KonsultasiActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<ResponseKonsultasi>> call, @NonNull Throwable t) {
                Toast.makeText(KonsultasiActivity.this, "Success", Toast.LENGTH_SHORT).show();
                Log.d(getLocalClassName(), "onFailure: " + t.getMessage());
            }
        });
    }

}
