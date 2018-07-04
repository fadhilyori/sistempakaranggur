package dev.anggur.sistempakaranggur.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.anggur.sistempakaranggur.R;

public class TambahDiagnosaActivity extends AppCompatActivity {

    @BindView(R.id.edt_judul_diagnosa)
    EditText edtJudulDiagnosa;
    @BindView(R.id.edt_keterangan_diagnosa)
    EditText edtKeteranganDiagnosa;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_diagnosa);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
    }
}
