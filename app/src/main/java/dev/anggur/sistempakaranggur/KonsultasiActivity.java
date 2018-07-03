package dev.anggur.sistempakaranggur;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KonsultasiActivity extends AppCompatActivity {

    @BindView(R.id.lsv_konsultasi)
    ListView lsvKonsultasi;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konsultasi);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
    }
}
