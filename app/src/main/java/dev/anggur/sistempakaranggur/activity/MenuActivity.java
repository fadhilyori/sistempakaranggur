package dev.anggur.sistempakaranggur.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.anggur.sistempakaranggur.R;

public class MenuActivity extends AppCompatActivity {

    @BindView(R.id.btn_daftar_diagnosa)
    Button btnDaftarDiagnosa;
    @BindView(R.id.btn_konsultasi)
    Button btnKonsultasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_daftar_diagnosa, R.id.btn_konsultasi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_daftar_diagnosa:
                startActivity(new Intent(MenuActivity.this,DiagnosaActivity.class));
                break;
            case R.id.btn_konsultasi:
                startActivity(new Intent(MenuActivity.this,KonsultasiActivity.class));
                break;
        }
    }
}
