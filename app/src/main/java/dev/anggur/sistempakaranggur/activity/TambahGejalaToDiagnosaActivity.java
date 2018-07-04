package dev.anggur.sistempakaranggur.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.anggur.sistempakaranggur.R;
import dev.anggur.sistempakaranggur.adapters.ListPertanyaanAdapter;
import dev.anggur.sistempakaranggur.api.ApiRequest;
import dev.anggur.sistempakaranggur.api.RetroClient;
import dev.anggur.sistempakaranggur.models.Diagnosa;
import dev.anggur.sistempakaranggur.models.Gejala;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahGejalaToDiagnosaActivity extends AppCompatActivity {

    public static final String EXTRA_DIAGNOSA = "extra_diagnosa";

    @BindView(R.id.txv_judul_diagnosa)
    TextView txvJudulDiagnosa;
    @BindView(R.id.spn_gejala)
    Spinner spnGejala;
    @BindView(R.id.btn_tambah)
    Button btnTambah;

    private Diagnosa diagnosa;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_gejala_to_diagnosa);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sedang mengambil data");
        progressDialog.setCancelable(false);
        progressDialog.show();

        diagnosa = getIntent().getParcelableExtra(EXTRA_DIAGNOSA);
        txvJudulDiagnosa.setText(diagnosa.getNama_diagnosa());

        ApiRequest request = RetroClient.getRequestService();
        Call<ArrayList<Gejala>> getPertanyaan = request.getPertanyaan();
        getPertanyaan.enqueue(new Callback<ArrayList<Gejala>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Gejala>> call, @NonNull Response<ArrayList<Gejala>> response) {
                progressDialog.dismiss();
                if (response.code() == 200){
                    ArrayList<Gejala> listGejala = response.body();
                    ArrayList<String> strings = new ArrayList<>();
                    for (int i = 0; i<listGejala.size(); i++)
                        strings.add(listGejala.get(i).getNama_gejala());
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(TambahGejalaToDiagnosaActivity.this,android.R.layout.simple_dropdown_item_1line,strings);
                    spnGejala.setAdapter(adapter);
                }else{
                    Toast.makeText(TambahGejalaToDiagnosaActivity.this, "gagal mengambil data gejala", Toast.LENGTH_SHORT).show();
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

    @OnClick(R.id.btn_tambah)
    public void onViewClicked() {
    }
}
