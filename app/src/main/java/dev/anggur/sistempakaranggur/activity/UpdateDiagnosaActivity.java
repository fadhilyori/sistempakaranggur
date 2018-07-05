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
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.anggur.sistempakaranggur.R;
import dev.anggur.sistempakaranggur.api.ApiRequest;
import dev.anggur.sistempakaranggur.api.RetroClient;
import dev.anggur.sistempakaranggur.models.Diagnosa;
import dev.anggur.sistempakaranggur.models.ResponseUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDiagnosaActivity extends AppCompatActivity {


    @BindView(R.id.txv_kode_diagnosa)
    TextView txvKodeDiagnosa;
    @BindView(R.id.edt_judul_diagnosa)
    EditText edtJudulDiagnosa;
    @BindView(R.id.edt_keterangan_diagnosa)
    EditText edtKeteranganDiagnosa;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    ProgressDialog progressDialog;
    private Diagnosa diagnosa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_diagnosa);
        ButterKnife.bind(this);
        diagnosa = getIntent().getParcelableExtra(DetailDiagnosaActivity.EXTRA_DIAGNOSA);
        txvKodeDiagnosa.setText(diagnosa.getKode_diagnosa());
        edtJudulDiagnosa.setText(diagnosa.getNama_diagnosa());
        edtKeteranganDiagnosa.setText(diagnosa.getKeterangan());
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sedang Mengirim Data");
    }

    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        String kode = txvKodeDiagnosa.getText().toString().trim();
        final String judul = edtJudulDiagnosa.getText().toString().trim();
        final String keterangan = edtKeteranganDiagnosa.getText().toString().trim();
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
        Call<ResponseUser> updateDiagnosa = request.updateDiagnosa(kode,judul,keterangan);
        updateDiagnosa.enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(@NonNull Call<ResponseUser> call, @NonNull Response<ResponseUser> response) {
                progressDialog.dismiss();
                if (response.code() == 200){
                    ResponseUser responseUser = response.body();
                    if (responseUser.isSuccess()){
                        Toast.makeText(UpdateDiagnosaActivity.this, "Berhasil Mengedit Diagnosa", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UpdateDiagnosaActivity.this,DetailDiagnosaActivity.class);
                        diagnosa.setKeterangan(keterangan);
                        diagnosa.setNama_diagnosa(judul);
                        intent.putExtra(DetailDiagnosaActivity.EXTRA_DIAGNOSA,diagnosa);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                }else{
                    Toast.makeText(UpdateDiagnosaActivity.this, "Gagal Mengedit Diagnosa", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseUser> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(UpdateDiagnosaActivity.this, "Gagal Menghubungkan ke Server", Toast.LENGTH_SHORT).show();
                Log.d(getLocalClassName(), "onFailure: " + t.getMessage());
            }
        });
    }
}
