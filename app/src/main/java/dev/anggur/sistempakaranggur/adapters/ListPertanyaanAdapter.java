package dev.anggur.sistempakaranggur.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import dev.anggur.sistempakaranggur.R;
import dev.anggur.sistempakaranggur.models.Diagnosa;
import dev.anggur.sistempakaranggur.models.Gejala;

/**
 * Created by Imam Abu Mansur on 04/07/2018.
 */

public class ListPertanyaanAdapter extends ArrayAdapter<Gejala>{
    private int listItemLayout;

    public ListPertanyaanAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Gejala> objects) {
        super(context, resource, objects);
        listItemLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Gejala gejala = getItem(position);

        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(listItemLayout, parent, false);
            viewHolder.chbKonsultasi = convertView.findViewById(R.id.chb_konsultasi);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.chbKonsultasi.setText(gejala.getNama_gejala());
//        viewHolder.chbKonsultasi.setText(String.valueOf(position+1) + " : " +gejala.getNama_gejala() + "?");

        return convertView;
    }
    private static class ViewHolder{
        CheckBox chbKonsultasi;
    }
}
