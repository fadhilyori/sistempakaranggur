package dev.anggur.sistempakaranggur.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.anggur.sistempakaranggur.R;
import dev.anggur.sistempakaranggur.adapters.ListDiagnosaAdapter;
import dev.anggur.sistempakaranggur.api.ApiRequest;
import dev.anggur.sistempakaranggur.api.RetroClient;
import dev.anggur.sistempakaranggur.models.Diagnosa;
import dev.anggur.sistempakaranggur.models.Gejala;
import dev.anggur.sistempakaranggur.models.ResponseUser;
import dev.anggur.sistempakaranggur.utils.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiagnosaActivity extends AppCompatActivity {

    @BindView(R.id.lsv_diagnosa)
    ListView lsvDiagnosa;
    @BindView(R.id.fab_tambah_diagnosa)
    FloatingActionButton fabTambahDiagnosa;

    ArrayList<Diagnosa> listDiagnosa = new ArrayList<>();
    ListDiagnosaAdapter adapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosa);
        ButterKnife.bind(this);
        SessionManager sessionManager = new SessionManager(this);
        HashMap<String,String> user = sessionManager.getDetaiLogin();
        if (!user.get(SessionManager.KEY_LEVEL).equals("admin"))
            fabTambahDiagnosa.setVisibility(View.GONE);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sedang Mengambil Data . . . .");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ApiRequest request = RetroClient.getRequestService();
        Call<ArrayList<Diagnosa>> getDiagnosa = request.getDiagnosa();
        getDiagnosa.enqueue(new Callback<ArrayList<Diagnosa>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Diagnosa>> call, @NonNull Response<ArrayList<Diagnosa>> response) {
                progressDialog.dismiss();
                if (response.code() == 200){
                    listDiagnosa = response.body();
                    adapter = new ListDiagnosaAdapter(DiagnosaActivity.this,R.layout.list_item,listDiagnosa);
                    lsvDiagnosa.setAdapter(adapter);
                }else{
                    Toast.makeText(DiagnosaActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Diagnosa>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DiagnosaActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                Log.d(getLocalClassName(), "onFailure: " + t.getMessage());
                finish();
            }
        });

        setUpListItemClickListener();
    }

    private void setUpListItemClickListener() {
        lsvDiagnosa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DiagnosaActivity.this,DetailDiagnosaActivity.class);
                Diagnosa diagnosa = listDiagnosa.get(position);
                intent.putExtra(DetailDiagnosaActivity.EXTRA_DIAGNOSA,diagnosa);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.fab_tambah_diagnosa)
    public void onViewClicked() {
        Intent intentAddDiagnosa = new Intent(DiagnosaActivity.this,TambahDiagnosaActivity.class);
        startActivity(intentAddDiagnosa);
    }
}
