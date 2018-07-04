package dev.anggur.sistempakaranggur.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiagnosaActivity extends AppCompatActivity {

    @BindView(R.id.lsv_diagnosa)
    ListView lsvDiagnosa;
    @BindView(R.id.fab_tambah_diagnosa)
    FloatingActionButton fabTambahDiagnosa;

    ArrayList<Diagnosa> listDiagnosa = new ArrayList<>();
    ArrayList<Gejala> listGejala = new ArrayList<>();
    ListDiagnosaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosa);
        ButterKnife.bind(this);
        listGejala.add(new Gejala("G01","Nama gejala 1","Keterangan Gejala"));
        listGejala.add(new Gejala("G01","Nama gejala 2","Keterangan Gejala"));
        listGejala.add(new Gejala("G01","Nama gejala 3","Keterangan Gejala"));
        listGejala.add(new Gejala("G01","Nama gejala 4","Keterangan Gejala"));
        listGejala.add(new Gejala("G01","Nama gejala 5","Keterangan Gejala"));
        listGejala.add(new Gejala("G01","Nama gejala 6","Keterangan Gejala"));
        listGejala.add(new Gejala("G01","Nama gejala 7","Keterangan Gejala"));
        listDiagnosa.add(new Diagnosa("D01","NAMA DIAGNOSA 1", "Keterangan",listGejala));
        listDiagnosa.add(new Diagnosa("D01","NAMA DIAGNOSA 2", "Keterangan",listGejala));
        listDiagnosa.add(new Diagnosa("D01","NAMA DIAGNOSA 3", "Keterangan",listGejala));
        listDiagnosa.add(new Diagnosa("D01","NAMA DIAGNOSA 4", "Keterangan",listGejala));
        listDiagnosa.add(new Diagnosa("D01","NAMA DIAGNOSA 5", "Keterangan",listGejala));
        listDiagnosa.add(new Diagnosa("D01","NAMA DIAGNOSA 6", "Keterangan",listGejala));
        listDiagnosa.add(new Diagnosa("D01","NAMA DIAGNOSA 7", "Keterangan",listGejala));

        adapter = new ListDiagnosaAdapter(this,R.layout.list_item,listDiagnosa);
        lsvDiagnosa.setAdapter(adapter);
        ApiRequest request = RetroClient.getRequestService();
        Call<ArrayList<Diagnosa>> getDiagnosa = request.getDiagnosa();
        getDiagnosa.enqueue(new Callback<ArrayList<Diagnosa>>() {
            @Override
            public void onResponse(Call<ArrayList<Diagnosa>> call, Response<ArrayList<Diagnosa>> response) {
                if (response.code() == 200){
                    listDiagnosa = response.body();
                    adapter.notifyDataSetChanged();
                    lsvDiagnosa.deferNotifyDataSetChanged();
                }else{
                    Toast.makeText(DiagnosaActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Diagnosa>> call, Throwable t) {
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
