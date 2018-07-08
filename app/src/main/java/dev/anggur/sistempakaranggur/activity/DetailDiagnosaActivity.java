package dev.anggur.sistempakaranggur.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.anggur.sistempakaranggur.NonScrollListView;
import dev.anggur.sistempakaranggur.R;
import dev.anggur.sistempakaranggur.adapters.ListGejalaAdapter;
import dev.anggur.sistempakaranggur.models.Diagnosa;
import dev.anggur.sistempakaranggur.utils.SessionManager;

public class DetailDiagnosaActivity extends AppCompatActivity {

    public static final String EXTRA_DIAGNOSA = "extra_diagnosa";

    @BindView(R.id.txv_judul_diagnosa)
    TextView txvJudulDiagnosa;
    @BindView(R.id.btn_tambah_gejala)
    Button btnTambahGejala;
    @BindView(R.id.btn_edit_diagnosa)
    Button btnEditDiagnosa;
    @BindView(R.id.txv_keterangan)
    TextView txvKeterangan;
    @BindView(R.id.lsv_gejala)
    NonScrollListView lsvGejala;
    @BindView(R.id.ll_button_container)
    LinearLayout llButtonContainer;

    private Diagnosa diagnosa;
    private ListGejalaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_diagnosa);
        ButterKnife.bind(this);
        SessionManager sessionManager = new SessionManager(this);
        if (!sessionManager.getDetaiLogin().get(SessionManager.KEY_LEVEL).equals("admin"))
            llButtonContainer.setVisibility(View.GONE);
        diagnosa = getIntent().getParcelableExtra(EXTRA_DIAGNOSA);
        txvJudulDiagnosa.setText(diagnosa.getNama_diagnosa());
        txvKeterangan.setText(diagnosa.getKeterangan());

        adapter = new ListGejalaAdapter(this, R.layout.list_item_gejala, diagnosa.getGejala(), diagnosa.getKode_diagnosa());
        lsvGejala.setAdapter(adapter);
    }

    @OnClick({R.id.btn_tambah_gejala, R.id.btn_edit_diagnosa})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_tambah_gejala:
                Intent intent = new Intent(DetailDiagnosaActivity.this, TambahGejalaToDiagnosaActivity.class);
                intent.putExtra(TambahGejalaToDiagnosaActivity.EXTRA_DIAGNOSA, diagnosa);
                startActivity(intent);
                break;
            case R.id.btn_edit_diagnosa:
                Intent intent1 = new Intent(DetailDiagnosaActivity.this, UpdateDiagnosaActivity.class);
                intent1.putExtra(TambahGejalaToDiagnosaActivity.EXTRA_DIAGNOSA, diagnosa);
                startActivity(intent1);
                break;
        }
    }
}
