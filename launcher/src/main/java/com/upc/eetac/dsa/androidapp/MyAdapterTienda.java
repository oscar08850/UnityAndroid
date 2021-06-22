package com.upc.eetac.dsa.androidapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import Others.EscudoMaderaActivity;
import Others.EscudoOroActivity;
import Others.EscudoPlataActivity;
import Others.FlechaMaderaActivity;
import Others.FlechaOroActivity;
import Others.FlechaPlataActivity;
import Others.ManzanaActivity;
import Others.PocimaAzulActivity;
import Others.PocimaRojaActivity;
import models.CredentialsCompra;
import models.Object;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.*;

import com.bumptech.glide.Glide;


import java.util.ArrayList;
import java.util.List;

public class MyAdapterTienda extends RecyclerView.Adapter<MyAdapterTienda.ViewHolder> implements View.OnClickListener {
    private List<Object> objects;
    private Context context;
    public int id;
    private UserService myapi;
    private MyAdapterTienda.OnItemClickListener mListener;
    private View.OnClickListener listener;
    private TiendaActivity tiendaActivity;



    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void SetOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView precio;
        public ImageView imageView;
        public ImageButton infoButton;
        public ImageButton comprarButton;
        EditText monedas;

        //public View layout;

        public ViewHolder(View v) {//, OnItemClickListener listener) {
            super(v);
            //layout=itemView;
            name = v.findViewById(R.id.nameObject);
            imageView = v.findViewById(R.id.avatarTienda);
            precio = v.findViewById(R.id.precio);
            infoButton = v.findViewById(R.id.botonInfo);
            comprarButton = v.findViewById(R.id.botonComprar);
            monedas = v.findViewById(R.id.monedasDisponiblesTienda);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });*/

        }
    }
    public MyAdapterTienda(TiendaActivity tiendaActivity, Context context) {
        this.tiendaActivity = tiendaActivity;
        this.context = context;
        this.objects = new ArrayList<>();
    }

    public MyAdapterTienda(List<Object> objects){
        this.objects = objects;
    }

    public void setData(List<Object> myDataset) {
        objects = myDataset;
        Log.i("PRUEBA", String.valueOf(objects.toArray()));
        for (Object o: myDataset)
            Log.i("PRUEBA", o.toString());
        notifyDataSetChanged();
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.row_recyclerview_catalogo, parent, false);
        ViewHolder vh = new ViewHolder(v);
        v.setOnClickListener(this);
        return vh;
    }


    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }


    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }

    }
    @Override
    public void onBindViewHolder(@NonNull MyAdapterTienda.ViewHolder holder, int position) {

        try {
            String url = "http://10.0.2.2:8080/" + objects.get(position).getAvatar();
            Log.i("PRUEBA", url);
            Glide.with(context).load("http://10.0.2.2:8080/" + objects.get(position).getAvatar()).into(holder.imageView);

        }
        catch (Throwable t) {
            Log.i("PRUEBA", "Error carregant imatge", t);
        }
            Log.i("PRUEBA", "posició" + position);
            Log.i("PRUEBA", "posició" + objects.get(position).getNombre());
            Log.i("PRUEBA", "posició" + objects.get(position).getAvatar());
            holder.name.setText(objects.get(position).getNombre());

            holder.precio.setText("" + objects.get(position).getCoste());

            holder.comprarButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("REGISTRO-COMPRA", "SE HA HECHO CLICK ");

                    CredentialsCompra credentialsCompra = new CredentialsCompra(objects.get(position).getNombre(), "prueba");
                    Call<CredentialsCompra> call = ClientAPI.getUserService().addObjetoTienda(credentialsCompra);
                    Log.i("REGISTRO-COMPRA", "Llamada: " + credentialsCompra);

                    call.enqueue(new Callback<CredentialsCompra>() {
                        @Override
                        public void onResponse(Call<CredentialsCompra> call, Response<CredentialsCompra> response) {
                            //CredentialsCompra compra = response.body();
                            Log.i("REGISTRO-COMPRA", "Codigo recibido: " + response.code());
                            if (response.code() == 200) {
                                Log.i("REGISTRO-COMPRA", "Valor monedas: "+response.body().getMonedasActualizadas());

                                MyAdapterTienda.this.tiendaActivity.monedas.setText(""+response.body().getMonedasActualizadas());

                            }
                            if (response.code() == 402){
                                Toast toast = Toast.makeText(context, "Monedas insuficientes!", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<CredentialsCompra> call, Throwable t) {
                            Log.e("REGISTRO-COMPRA", "ERROR: ", t);


                        }

                    });
                }
            });

            holder.infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("REGISTRO-COMPRA", "SE HA HECHO CLICK ");

                if (position == 0) {
                    Intent intent = new Intent(context, EscudoOroActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                if (position == 1) {
                        Intent intent = new Intent(context, EscudoPlataActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                }
                if (position == 2) {
                    Intent intent = new Intent(context, EscudoMaderaActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                if (position == 3) {
                    Intent intent = new Intent(context, FlechaOroActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                if (position == 4) {
                    Intent intent = new Intent(context, FlechaPlataActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                if (position == 5) {
                    Intent intent = new Intent(context, FlechaMaderaActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                if (position == 6) {
                    Intent intent = new Intent(context, ManzanaActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                if (position == 7) {
                    Intent intent = new Intent(context, PocimaAzulActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                if (position == 8) {
                    Intent intent = new Intent(context, PocimaRojaActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return objects.size();
    }

}