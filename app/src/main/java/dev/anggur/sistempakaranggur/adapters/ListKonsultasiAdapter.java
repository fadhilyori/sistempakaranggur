package dev.anggur.sistempakaranggur.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Konsultasi konsultasi = getItem(position);

        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(listItemLayout, parent, false);
            viewHolder.txvNamaDiagnosa = convertView.findViewById(R.id.txv_nama_diagnosa);
            viewHolder.txvKepercayaan = convertView.findViewById(R.id.txv_kepercayaan);
            viewHolder.txvSolusi = convertView.findViewById(R.id.txv_solusi);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.txvNamaDiagnosa.setText(konsultasi.getNama_diagnosa());
        viewHolder.txvKepercayaan.setText(String.valueOf(konsultasi.getKepercayaan()));
        viewHolder.txvSolusi.setText(konsultasi.getSolusi());

        return convertView;
    }
    private static class ViewHolder{
        TextView txvNamaDiagnosa;
        TextView txvKepercayaan;
        TextView txvSolusi;
    }
}
