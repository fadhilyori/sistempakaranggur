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
import dev.anggur.sistempakaranggur.models.ResponseKonsultasi;

public class ListResponseKonsultasiAdapter extends ArrayAdapter<ResponseKonsultasi> {
    private int listItemLayout;

    public ListResponseKonsultasiAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ResponseKonsultasi> objects) {
        super(context, resource, objects);
        listItemLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ResponseKonsultasi responseKonsultasi = getItem(position);

        ListResponseKonsultasiAdapter.ViewHolder viewHolder = new ListResponseKonsultasiAdapter.ViewHolder();
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(listItemLayout, parent, false);
            viewHolder.txvJudulItem = convertView.findViewById(R.id.txv_judul_item);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ListResponseKonsultasiAdapter.ViewHolder) convertView.getTag();
        }

        viewHolder.txvJudulItem.setText(responseKonsultasi.getNama_responseKonsultasi() + " (" + String.valueOf(responseKonsultasi.getKepercayaan_responseKonsultasi() * 100) + "%)");

        return convertView;
    }
    private static class ViewHolder{
        TextView txvJudulItem;
    }
}
