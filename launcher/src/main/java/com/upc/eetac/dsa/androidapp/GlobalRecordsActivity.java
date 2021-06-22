package com.upc.eetac.dsa.androidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import models.RecordUsuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GlobalRecordsActivity extends AppCompatActivity {

   UserService userAPI;
    TextView usuario;
    TextView kills;
    TextView tiempo;
    TextView monedasTotales;
    TextView puntuacionFinal;
    ProgressBar progressBar;
    private MyAdapterRecords myAdapterRecords;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    List<RecordUsuario> listaRecords;

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        userAPI = ClientAPI.getUserService();
        context = getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerViewRanking);
        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(GlobalRecordsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        myAdapterRecords = new MyAdapterRecords(context);
        recyclerView.setAdapter(myAdapterRecords);


        usuario = findViewById(R.id.usuario);
        kills = findViewById(R.id.kills);
        tiempo = findViewById(R.id.tiempo);
        monedasTotales = findViewById(R.id.monedas);
        puntuacionFinal = findViewById(R.id.puntuacion);

        getRecords();

    }

    public void getRecords()
        {
            Call<List<RecordUsuario>> call = ClientAPI.getUserService().getRecordsTotales();
            call.enqueue(new Callback<List<RecordUsuario>>() {
                @Override
                public void onResponse(Call<List<RecordUsuario>> call, Response<List<RecordUsuario>> response) {
                    if(response.code() == 200) {

                        myAdapterRecords.setData(response.body());

                    }

                }
                @Override
                public void onFailure(Call<List<RecordUsuario>> call, Throwable t) {

                }
            });
        }
    }

