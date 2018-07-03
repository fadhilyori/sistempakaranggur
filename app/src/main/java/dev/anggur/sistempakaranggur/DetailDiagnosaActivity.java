package dev.anggur.sistempakaranggur;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailDiagnosaActivity extends AppCompatActivity {

    @BindView(R.id.txv_judul_diagnosa)
    TextView txvJudulDiagnosa;
    @BindView(R.id.btn_tambah_gejala)
    Button btnTambahGejala;
    @BindView(R.id.btn_edit_diagnosa)
    Button btnEditDiagnosa;
    @BindView(R.id.txv_keterangan)
    TextView txvKeterangan;
    @BindView(R.id.lsv_gejala)
    ListView lsvGejala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_diagnosa);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_tambah_gejala, R.id.btn_edit_diagnosa})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_tambah_gejala:
                startActivity(new Intent(DetailDiagnosaActivity.this,TambahGejalaToDiagnosaActivity.class));
                break;
            case R.id.btn_edit_diagnosa:
                break;
        }
    }
}
