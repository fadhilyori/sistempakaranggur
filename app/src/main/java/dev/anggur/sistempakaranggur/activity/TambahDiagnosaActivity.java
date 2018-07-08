package dev.anggur.sistempakaranggur.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
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
import dev.anggur.sistempakaranggur.models.ResponseUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahDiagnosaActivity extends AppCompatActivity {

    @BindView(R.id.edt_judul_diagnosa)
    EditText edtJudulDiagnosa;
    @BindView(R.id.edt_keterangan_diagnosa)
    EditText edtKeteranganDiagnosa;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.edt_kode_diagnosa)
    EditText edtKodeDiagnosa;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_diagnosa);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sedang Mengirim Data");
    }

    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        final String kode = edtKodeDiagnosa.getText().toString().trim();
        final String judul = edtJudulDiagnosa.getText().toString().trim();
        final String keterangan = edtKeteranganDiagnosa.getText().toString().trim();
        if (TextUtils.isEmpty(kode)){
            edtKodeDiagnosa.setError("Masukkan kode diagnosa");
            return;
        }
        if (TextUtils.isEmpty(judul)){
            edtJudulDiagnosa.setError("Masukkan keterangan diagnosa");
            return;
        }
        if (TextUtils.isEmpty(keterangan)){
            edtKeteranganDiagnosa.setError("Masukkan keterangan diagnosa");
            return;
        }
        progressDialog.show();
        ApiRequest request = RetroClient.getRequestService();
        Call<ResponseUser> addDiagnosa = request.addDiagnosa(kode,judul,keterangan);
        addDiagnosa.enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(@NonNull Call<ResponseUser> call, @NonNull Response<ResponseUser> response) {
                progressDialog.dismiss();
                if (response.code() == 200){
                    ResponseUser responseUser = response.body();
                    if (responseUser.isSuccess()){
                        Diagnosa diagnosa = new Diagnosa(kode,judul,keterangan,new ArrayList<Gejala>());
                        Toast.makeText(TambahDiagnosaActivity.this, "Berhasil Menambahkan Diagnosa", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TambahDiagnosaActivity.this,DetailDiagnosaActivity.class);
                        intent.putExtra(DetailDiagnosaActivity.EXTRA_DIAGNOSA,diagnosa);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(TambahDiagnosaActivity.this, "Kode Diagnosa Sudah Ada", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(TambahDiagnosaActivity.this, "Gagal Menambahkan Diagnosa", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseUser> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(TambahDiagnosaActivity.this, "Gagal Menghubungkan ke Server", Toast.LENGTH_SHORT).show();
                Log.d(getLocalClassName(), "onFailure: " + t.getMessage());
            }
        });

    }
}
