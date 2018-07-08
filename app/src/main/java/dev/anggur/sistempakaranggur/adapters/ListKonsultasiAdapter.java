package dev.anggur.sistempakaranggur.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import dev.anggur.sistempakaranggur.R;
import dev.anggur.sistempakaranggur.models.Konsultasi;

public class ListKonsultasiAdapter extends ArrayAdapter<Konsultasi> {

    private int listItemLayout;

    public ListKonsultasiAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Konsultasi> objects) {
        super(context, resource, objects);
        listItemLayout = resource;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Konsultasi konsultasi = getItem(position);

        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(listItemLayout, parent, false);
            viewHolder.txvIteration = convertView.findViewById(R.id.txv_iteration);
            viewHolder.txvNamaDiagnosa = convertView.findViewById(R.id.txv_nama_diagnosa);
            viewHolder.txvKepercayaan = convertView.findViewById(R.id.txv_kepercayaan);
            viewHolder.txvSolusi = convertView.findViewById(R.id.txv_solusi);
            viewHolder.linearSolusi = convertView.findViewById(R.id.linear_solusi);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (position == 0) {
            viewHolder.txvIteration.setText("Hasil Diagnosa : ");
            viewHolder.txvNamaDiagnosa.setText(konsultasi.getNama_diagnosa());
            viewHolder.txvKepercayaan.setText(String.valueOf(konsultasi.getKepercayaan() * 100) + "%");
            viewHolder.txvSolusi.setText(konsultasi.getSolusi());
        } else {
            viewHolder.txvIteration.setText("Diagnosa ke-" + String.valueOf(position + 1));
            viewHolder.txvNamaDiagnosa.setText(konsultasi.getNama_diagnosa());
            viewHolder.txvKepercayaan.setText(String.valueOf(konsultasi.getKepercayaan() * 100) + "%");
            viewHolder.linearSolusi.removeAllViewsInLayout();
        }
//        viewHolder.txvIteration.setText("Diagnosa ke-" + (position+1));
//        viewHolder.txvNamaDiagnosa.setText(konsultasi.getNama_diagnosa());
//        viewHolder.txvKepercayaan.setText(String.valueOf(konsultasi.getKepercayaan() * 100) + "%");
//        viewHolder.txvSolusi.setText(konsultasi.getSolusi());

        return convertView;
    }
    private static class ViewHolder{
        TextView txvIteration;
        TextView txvNamaDiagnosa;
        TextView txvKepercayaan;
        TextView txvSolusi;
        LinearLayout linearSolusi;
    }
}
