package dev.anggur.sistempakaranggur.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.anggur.sistempakaranggur.R;
import dev.anggur.sistempakaranggur.adapters.ListKonsultasiAdapter;
import dev.anggur.sistempakaranggur.models.Konsultasi;

public class HasilKonsultasiActivity extends AppCompatActivity {

    public static final String EXTRA_HASIL_KONSULTASI = "hasil_konsultasi";

    @BindView(R.id.lsv_hasil)
    ListView lsvHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_konsultasi);
        ButterKnife.bind(this);

        ArrayList<Konsultasi> listKonsultasi = getIntent().getParcelableArrayListExtra(EXTRA_HASIL_KONSULTASI);
        ListKonsultasiAdapter adapter = new ListKonsultasiAdapter(this,R.layout.list_konsultasi, listKonsultasi);
        lsvHasil.setAdapter(adapter);
    }
}
