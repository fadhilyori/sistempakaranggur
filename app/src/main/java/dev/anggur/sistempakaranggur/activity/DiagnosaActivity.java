package dev.anggur.sistempakaranggur.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.anggur.sistempakaranggur.R;

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
    }

    @OnClick(R.id.fab_tambah_diagnosa)
    public void onViewClicked() {
        Intent intentAddDiagnosa = new Intent(DiagnosaActivity.this,TambahDiagnosaActivity.class);
        startActivity(intentAddDiagnosa);
    }
}
