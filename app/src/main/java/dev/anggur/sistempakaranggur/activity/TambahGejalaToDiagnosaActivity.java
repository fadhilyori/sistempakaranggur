package dev.anggur.sistempakaranggur.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.anggur.sistempakaranggur.R;
import dev.anggur.sistempakaranggur.api.ApiRequest;
import dev.anggur.sistempakaranggur.api.RetroClient;
import dev.anggur.sistempakaranggur.models.Diagnosa;
import dev.anggur.sistempakaranggur.models.Gejala;
import dev.anggur.sistempakaranggur.models.ResponseAddGejala;
import dev.anggur.sistempakaranggur.models.ResponseUser;
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
    @BindView(R.id.edt_mb)
    EditText edtMb;
    @BindView(R.id.edt_md)
    EditText edtMd;

    private Diagnosa diagnosa;
    private ProgressDialog progressDialog;
    private ArrayList<Gejala> listGejala = new ArrayList<>();

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
                if (response.code() == 200) {
                    listGejala = response.body();
                    ArrayList<String> strings = new ArrayList<>();
                    for (int i = 0; i < listGejala.size(); i++)
                        strings.add(listGejala.get(i).getNama_gejala());
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(TambahGejalaToDiagnosaActivity.this, android.R.layout.simple_dropdown_item_1line, strings);
                    spnGejala.setAdapter(adapter);
                } else {
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
        String md = edtMd.getText().toString().trim();
        String mb = edtMb.getText().toString().trim();
        String kode_gejala = listGejala.get(spnGejala.getSelectedItemPosition()).getKode_gejala();
        String kode_diagnosa = diagnosa.getKode_diagnosa();
        if (TextUtils.isEmpty(md)){
            edtMd.setError("Masukkan nilai md");
            return;
        }
        if (TextUtils.isEmpty(mb)){
            edtMb.setError("Masukkan nilai mb");
            return;
        }
        final ProgressDialog dialog = new ProgressDialog(TambahGejalaToDiagnosaActivity.this);
        dialog.setMessage("Sedang menambahkan data");
        dialog.setCancelable(false);
        dialog.show();
        ApiRequest request = RetroClient.getRequestService();
        Call<ResponseAddGejala> addGejala = request.addGejalaToDiagnosa(kode_diagnosa,kode_gejala,md,mb);
        addGejala.enqueue(new Callback<ResponseAddGejala>() {
            @Override
            public void onResponse(@NonNull Call<ResponseAddGejala> call, @NonNull Response<ResponseAddGejala> response) {
                dialog.dismiss();
                if (response.code()==200){
                    ResponseAddGejala gejala = response.body();
                    if (gejala.isSuccess()){
                        Toast.makeText(TambahGejalaToDiagnosaActivity.this, "Berhasil Memasukkan Data Gejala", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TambahGejalaToDiagnosaActivity.this,DetailDiagnosaActivity.class);
                        diagnosa.getGejala().add(new Gejala(gejala.getKode_gejala(),gejala.getNama_gejala(),gejala.getKeterangan()));
                        intent.putExtra(DetailDiagnosaActivity.EXTRA_DIAGNOSA,diagnosa);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }else{
                        Toast.makeText(TambahGejalaToDiagnosaActivity.this, "Data Gejala Sudah Ada", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(TambahGejalaToDiagnosaActivity.this, "Gagal Memasukkan Data Gejala", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAddGejala> call, @NonNull Throwable t) {
                dialog.dismiss();
                Toast.makeText(TambahGejalaToDiagnosaActivity.this, "Berhasil Memasukkan Data Gejala", Toast.LENGTH_SHORT).show();
                Log.d(getLocalClassName(), "onFailure: " + t.getMessage());
            }
        });
    }
}
