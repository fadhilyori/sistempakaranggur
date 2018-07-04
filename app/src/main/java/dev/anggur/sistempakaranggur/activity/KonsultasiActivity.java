package dev.anggur.sistempakaranggur.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.anggur.sistempakaranggur.R;
import dev.anggur.sistempakaranggur.adapters.ListGejalaAdapter;
import dev.anggur.sistempakaranggur.adapters.ListPertanyaanAdapter;
import dev.anggur.sistempakaranggur.api.ApiRequest;
import dev.anggur.sistempakaranggur.api.RetroClient;
import dev.anggur.sistempakaranggur.models.Diagnosa;
import dev.anggur.sistempakaranggur.models.Gejala;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KonsultasiActivity extends AppCompatActivity {

    @BindView(R.id.lsv_konsultasi)
    ListView lsvKonsultasi;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    ArrayList<Gejala> listGejala = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konsultasi);
        ButterKnife.bind(this);
        listGejala.add(new Gejala("G01","NAMA_GEJALA", "KETERANGAN GEJALA"));
        listGejala.add(new Gejala("G01","NAMA_GEJALA", "KETERANGAN GEJALA"));
        listGejala.add(new Gejala("G01","NAMA_GEJALA", "KETERANGAN GEJALA"));
        listGejala.add(new Gejala("G01","NAMA_GEJALA", "KETERANGAN GEJALA"));
        listGejala.add(new Gejala("G01","NAMA_GEJALA", "KETERANGAN GEJALA"));
        listGejala.add(new Gejala("G01","NAMA_GEJALA", "KETERANGAN GEJALA"));
        final ListPertanyaanAdapter adapter = new ListPertanyaanAdapter(this,R.layout.list_question,listGejala);
        lsvKonsultasi.setAdapter(adapter);

        ApiRequest request = RetroClient.getRequestService();
        Call<ArrayList<Gejala>> getPertanyaan = request.getPertanyaan();
        getPertanyaan.enqueue(new Callback<ArrayList<Gejala>>() {
            @Override
            public void onResponse(Call<ArrayList<Gejala>> call, Response<ArrayList<Gejala>> response) {
                if (response.code() == 200){
                    listGejala = response.body();
                    adapter.notifyDataSetChanged();
                    lsvKonsultasi.deferNotifyDataSetChanged();
                }else{
                    Toast.makeText(KonsultasiActivity.this, "gagal mengambil data gejala", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Gejala>> call, Throwable t) {
                Log.d(getLocalClassName(), "onFailure: " + t.getMessage());
                finish();
            }
        });
    }

    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
    }
}
