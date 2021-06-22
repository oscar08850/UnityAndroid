package com.upc.eetac.dsa.androidapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import models.RecordUsuario;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.*;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterRecords extends RecyclerView.Adapter<MyAdapterRecords.ViewHolder> implements View.OnClickListener {

    private List<RecordUsuario> recordsList;
    private Context context;
    public int id;
    private View.OnClickListener listener;
    private UserService myapi;


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView username;
        public TextView kills;
        public TextView tiempo;
        public TextView monedasTot;
        public TextView puntuacionTot;
        //public View layout;

        public ViewHolder(View v) {//, OnItemClickListener listener) {
            super(v);
            //layout=itemView;
            username = v.findViewById(R.id.usuarioColumna);
            tiempo = v.findViewById(R.id.tiempoColumna);
            kills = v.findViewById(R.id.killsColumna);
            monedasTot = v.findViewById(R.id.monedasColumna);
            puntuacionTot = v.findViewById(R.id.puntuacionColumna);

        }
    }

    public MyAdapterRecords(Context context) {
        this.context = context;
        this.recordsList = new ArrayList<>();
    }

    public void MyAdapterTienda(List<RecordUsuario> recordList){
        this.recordsList = recordList;
    }

    public void setData(List<RecordUsuario> myDataset) {
        recordsList = myDataset;
        Log.i("PRUEBA", String.valueOf(recordsList.toArray()));
        for (RecordUsuario o: myDataset)
            Log.i("PRUEBA", o.toString());
        notifyDataSetChanged();
    }



    @Override
    public MyAdapterRecords.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.row_recyclerview_records, parent, false);
        MyAdapterRecords.ViewHolder vh = new MyAdapterRecords.ViewHolder(v);
        v.setOnClickListener(this);
        return vh;
    }
    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }

    }
    @Override
    public void onBindViewHolder(@NonNull MyAdapterRecords.ViewHolder holder, int position) {

        holder.username.setText(recordsList.get(position).getUsername());

        holder.kills.setText("" + recordsList.get(position).getEnemigosMatados());

        holder.tiempo.setText("" + recordsList.get(position).getTiempo());

        holder.monedasTot.setText("" + recordsList.get(position).getMonedasRecogidas());

        holder.puntuacionTot.setText("" + recordsList.get(position).getPuntuacionFinal());


    }

    @Override
    public int getItemCount() {
        return recordsList.size();
    }

}

