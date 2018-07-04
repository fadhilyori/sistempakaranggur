package dev.anggur.sistempakaranggur.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.anggur.sistempakaranggur.R;
import dev.anggur.sistempakaranggur.adapters.ListDiagnosaAdapter;
import dev.anggur.sistempakaranggur.models.Diagnosa;
import dev.anggur.sistempakaranggur.models.Gejala;

public class DiagnosaActivity extends AppCompatActivity {

    @BindView(R.id.lsv_diagnosa)
    ListView lsvDiagnosa;
    @BindView(R.id.fab_tambah_diagnosa)
    FloatingActionButton fabTambahDiagnosa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosa);
        ButterKnife.bind(this);
        ArrayList<Diagnosa> listDiagnosa = new ArrayList<>();
        ArrayList<Gejala> listGejala = new ArrayList<>();
        listGejala.add(new Gejala("G01","Nama gejala","Keterangan Gejala"));
        listGejala.add(new Gejala("G01","Nama gejala","Keterangan Gejala"));
        listGejala.add(new Gejala("G01","Nama gejala","Keterangan Gejala"));
        listGejala.add(new Gejala("G01","Nama gejala","Keterangan Gejala"));
        listGejala.add(new Gejala("G01","Nama gejala","Keterangan Gejala"));
        listGejala.add(new Gejala("G01","Nama gejala","Keterangan Gejala"));
        listDiagnosa.add(new Diagnosa("D01","NAMA DIAGNOSA", "Keterangan",listGejala));
        listDiagnosa.add(new Diagnosa("D01","NAMA DIAGNOSA", "Keterangan",listGejala));
        listDiagnosa.add(new Diagnosa("D01","NAMA DIAGNOSA", "Keterangan",listGejala));
        listDiagnosa.add(new Diagnosa("D01","NAMA DIAGNOSA", "Keterangan",listGejala));
        listDiagnosa.add(new Diagnosa("D01","NAMA DIAGNOSA", "Keterangan",listGejala));
        listDiagnosa.add(new Diagnosa("D01","NAMA DIAGNOSA", "Keterangan",listGejala));
        listDiagnosa.add(new Diagnosa("D01","NAMA DIAGNOSA", "Keterangan",listGejala));

        ListDiagnosaAdapter adapter = new ListDiagnosaAdapter(this,R.layout.list_item,listDiagnosa);
        lsvDiagnosa.setAdapter(adapter);
    }

    @OnClick(R.id.fab_tambah_diagnosa)
    public void onViewClicked() {
        Intent intentAddDiagnosa = new Intent(DiagnosaActivity.this,TambahDiagnosaActivity.class);
        startActivity(intentAddDiagnosa);
    }
}
