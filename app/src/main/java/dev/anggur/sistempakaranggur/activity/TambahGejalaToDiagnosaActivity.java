package dev.anggur.sistempakaranggur.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.anggur.sistempakaranggur.R;
import dev.anggur.sistempakaranggur.models.Diagnosa;

public class TambahGejalaToDiagnosaActivity extends AppCompatActivity {

    public static final String EXTRA_DIAGNOSA = "extra_diagnosa";

    @BindView(R.id.txv_judul_diagnosa)
    TextView txvJudulDiagnosa;
    @BindView(R.id.spn_gejala)
    Spinner spnGejala;
    @BindView(R.id.btn_tambah)
    Button btnTambah;

    private Diagnosa diagnosa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_gejala_to_diagnosa);
        ButterKnife.bind(this);

        diagnosa = getIntent().getParcelableExtra(EXTRA_DIAGNOSA);
        txvJudulDiagnosa.setText(diagnosa.getNama_diagnosa());

        ArrayList<String> listGejala = new ArrayList<>();
        for (int i = 0; i<diagnosa.getGejala().size(); i++)
            listGejala.add(diagnosa.getGejala().get(i).getNama_gejala());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,listGejala);
        spnGejala.setAdapter(adapter);
    }

    @OnClick(R.id.btn_tambah)
    public void onViewClicked() {
    }
}
