package dev.anggur.sistempakaranggur.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
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
import dev.anggur.sistempakaranggur.models.Diagnosa;
import dev.anggur.sistempakaranggur.models.Gejala;

public class DiagnosaActivity extends AppCompatActivity {

    @BindView(R.id.lsv_diagnosa)
    ListView lsvDiagnosa;
    @BindView(R.id.fab_tambah_diagnosa)
    FloatingActionButton fabTambahDiagnosa;

    ArrayList<Diagnosa> listDiagnosa = new ArrayList<>();
    ArrayList<Gejala> listGejala = new ArrayList<>();

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

        ListDiagnosaAdapter adapter = new ListDiagnosaAdapter(this,R.layout.list_item,listDiagnosa);
        lsvDiagnosa.setAdapter(adapter);

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
