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
import java.util.List;

import dev.anggur.sistempakaranggur.R;
import dev.anggur.sistempakaranggur.models.Diagnosa;

/**
 * Created by Imam Abu Mansur on 04/07/2018.
 */

public class ListDiagnosaAdapter extends ArrayAdapter<Diagnosa> {

    private int listItemLayout;

    public ListDiagnosaAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Diagnosa> objects) {
        super(context, resource, objects);
        listItemLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Diagnosa diagnosa = getItem(position);

        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(listItemLayout, parent, false);
            viewHolder.txvJudulItem = convertView.findViewById(R.id.txv_judul_item);
            convertView.setTag(viewHolder); // view lookup cache stored in tag
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.txvJudulItem.setText(diagnosa.getNama_diagnosa());

        return convertView;
    }
    private static class ViewHolder{
        TextView txvJudulItem;
    }

}
