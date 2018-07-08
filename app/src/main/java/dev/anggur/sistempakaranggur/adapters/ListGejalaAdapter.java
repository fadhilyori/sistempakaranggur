package dev.anggur.sistempakaranggur.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dev.anggur.sistempakaranggur.R;
import dev.anggur.sistempakaranggur.api.ApiRequest;
import dev.anggur.sistempakaranggur.api.RetroClient;
import dev.anggur.sistempakaranggur.models.Diagnosa;
import dev.anggur.sistempakaranggur.models.Gejala;
import dev.anggur.sistempakaranggur.models.ResponseUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Imam Abu Mansur on 04/07/2018.
 */

public class ListGejalaAdapter extends ArrayAdapter<Gejala> {

    private int listItemLayout;
    private String kode_diagnosa;

    public ListGejalaAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Gejala> objects, String kode_diagnosa) {
        super(context, resource, objects);
        listItemLayout = resource;
        this.kode_diagnosa = kode_diagnosa;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Gejala gejala = getItem(position);

        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(listItemLayout, parent, false);
            viewHolder.txvJudulItem = convertView.findViewById(R.id.txv_judul_item);
            viewHolder.imvDelete = convertView.findViewById(R.id.imv_delete);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.txvJudulItem.setText(gejala.getNama_gejala());
        viewHolder.imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog dialog = new ProgressDialog(getContext());
                dialog.setMessage("Sedang menghapus data");
                dialog.setCancelable(false);
                dialog.show();
                ApiRequest request = RetroClient.getRequestService();
                Call<ResponseUser> deleteGejala = request.deleteGejala(kode_diagnosa,gejala.getKode_gejala());
                deleteGejala.enqueue(new Callback<ResponseUser>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseUser> call, @NonNull Response<ResponseUser> response) {
                        dialog.dismiss();
                        if (response.code()==200){
                            remove(getItem(position));
                            notifyDataSetChanged();
                            Toast.makeText(getContext(), "Berhasil menghapus gejala", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(), "Gagal menghapus gejala", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseUser> call, @NonNull Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(getContext(), "Gagal menghubungkan ke server", Toast.LENGTH_SHORT).show();
                        Log.d(getClass().getSimpleName(), "onFailure: " + t.getMessage());
                    }
                });
            }
        });

        return convertView;
    }
    private static class ViewHolder{
        TextView txvJudulItem;
        ImageView imvDelete;
    }
}
