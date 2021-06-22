package com.upc.eetac.dsa.androidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import java.util.List;

import Others.EscudoMaderaActivity;
import Others.EscudoOroActivity;
import Others.EscudoPlataActivity;
import Others.FlechaOroActivity;
import Others.FlechaPlataActivity;
import Others.ManzanaActivity;
import Others.PocimaAzulActivity;
import Others.PocimaRojaActivity;
import models.User;
import models.Object;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TiendaActivity extends AppCompatActivity {

    UserService userAPI;
    EditText monedas;
    ImageButton comprarButton;
    ProgressBar progressBar;
    private MyAdapterTienda myAdapterTienda;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    static boolean active = false;
    List<Object> objects;

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);
        userAPI = ClientAPI.getUserService();
        context = getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.MyRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(TiendaActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        myAdapterTienda = new MyAdapterTienda(this,context);
        recyclerView.setAdapter(myAdapterTienda);

        comprarButton = findViewById(R.id.botonComprar);

        monedas = findViewById(R.id.monedasDisponiblesTienda);

        getMonedas();
        getObjetos();
        MyObjectsRecyclerViewAdapter();


        //Bundle miBundle = this.getIntent().getExtras();
        //Log.i("BUNDLE", "" + miBundle);

    }

    private void checkMonedas() {
        Bundle miBundle = this.getIntent().getExtras();

        if (miBundle != null){
            getMonedas();
        }
    }

    public void getMonedas()
    {
        //SharedPreferences preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        //String user = preferences.getString("user","No existe info");

        Call<User> call = userAPI.getUser("prueba");

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    Log.i("Prueba2", "" + response.body().getCoins());
                    monedas.setText(""+response.body().getCoins());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public void getObjetos()
    {
        Call<List<Object>> call = ClientAPI.getUserService().getObjetosTienda();
        call.enqueue(new Callback<List<Object>>() {
            @Override
            public void onResponse(Call<List<Object>> call, Response<List<Object>> response) {
                if(response.code() == 200) {

                    myAdapterTienda.setData(response.body());

                }
                    /*comprarButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        loadingPB.startPBDialog();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadingPB.dismissPBDialog();
                            }
                        }, 4000);
                        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                        startActivity(intent);
                    }
                });
                    infoButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){


                    }
                    });*/
                    /*objects = response.body();
                    progressBar.setVisibility(View.INVISIBLE);
                    if(myAdapterTienda == null){
                        MyObjectsRecyclerViewAdapter(objects);
                    }else{
                        myAdapterTienda = null;
                        MyObjectsRecyclerViewAdapter(objects);
                    }*/
                //}
            }
            @Override
            public void onFailure(Call<List<Object>> call, Throwable t) {

            }
        });
    }

    private void MyObjectsRecyclerViewAdapter(){
        myAdapterTienda.SetOnItemClickListener(new MyAdapterTienda.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //TODO NEED TO IMPLEMENT PLAYER STATS DETAIL ACTIVITY
                Log.i("BUNDLE", "ha entradooo");

                getMonedas();
             }
        });
    }
    /*private void LaunchObjectDetailPopup(int position){
        //Start a new activity with the detailed data of the Museum
        Log.d("Museum Detail Activity","Detail of the Museum PopUp");
        Intent intent = new Intent(TiendaActivity.this ,DetalleObjetoActivity.class);
        intent.putExtra("Element", String.valueOf(objects.get(position)));
        startActivityForResult(intent,2);
    }*/


    public void escudoMadera(View view)
    {
        Intent intent = new Intent(this, EscudoMaderaActivity.class);
        startActivity(intent);
    }
    public void escudoPlata(View view)
    {
        Intent intent = new Intent(this, EscudoPlataActivity.class);
        startActivity(intent);
    }
    public void escudoOro(View view)
    {
        Intent intent = new Intent(this, EscudoOroActivity.class);
        startActivity(intent);
    }
    public void flechaPlata(View view)
    {
        Intent intent = new Intent(this, FlechaPlataActivity.class);
        startActivity(intent);
    }
    public void flechaOro(View view)
    {
        Intent intent = new Intent(this, FlechaOroActivity.class);
        startActivity(intent);
    }
    public void manzana(View view)
    {
        Intent intent = new Intent(this, ManzanaActivity.class);
        startActivity(intent);
    }
    public void pocimaAzul(View view)
    {
        Intent intent = new Intent(this, PocimaAzulActivity.class);
        startActivity(intent);
    }
    public void pocimaRoja(View view)
    {
        Intent intent = new Intent(this, PocimaRojaActivity.class);
        startActivity(intent);
    }

}

