package dev.anggur.sistempakaranggur;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TambahGejalaToDiagnosaActivity extends AppCompatActivity {

    @BindView(R.id.txv_judul_diagnosa)
    TextView txvJudulDiagnosa;
    @BindView(R.id.spn_gejala)
    Spinner spnGejala;
    @BindView(R.id.btn_tambah)
    Button btnTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_gejala_to_diagnosa);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_tambah)
    public void onViewClicked() {
    }
}
