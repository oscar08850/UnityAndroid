package com.upc.eetac.dsa.androidapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import models.RecordUsuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IndividualRecordsActivity extends AppCompatActivity {

    UserService userAPI;
    SharedPreferences sharedPreferences;
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
        layoutManager = new LinearLayoutManager(IndividualRecordsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        myAdapterRecords = new MyAdapterRecords(context);
        recyclerView.setAdapter(myAdapterRecords);

        usuario = findViewById(R.id.usuario);
        kills = findViewById(R.id.kills);
        tiempo = findViewById(R.id.tiempo);
        monedasTotales = findViewById(R.id.monedas);
        puntuacionFinal = findViewById(R.id.puntuacion);

        sharedPreferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("user","No existe info");

        getRecords(user);

    }

    public void getRecords(String user)
        {
            Call<List<RecordUsuario>> call = ClientAPI.getUserService().getRecordsIndividual(user);
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

