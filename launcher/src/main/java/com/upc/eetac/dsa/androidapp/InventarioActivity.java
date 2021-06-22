package com.upc.eetac.dsa.androidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.List;

import models.Inventario;
import models.Object;
import models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InventarioActivity extends AppCompatActivity {

    Inventario inventarioUser;
    EditText escudoMadera;
    EditText escudoPlata;
    EditText escudoOro;
    EditText flechaMadera;
    EditText flechaPlata;
    EditText flechaOro;
    EditText pocionAzul;
    EditText pocionRoja;
    EditText manzana;
    SharedPreferences sharedPreferences;

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);
        context = getApplicationContext();

        escudoMadera = findViewById(R.id.escudoMaderaCantidad);
        escudoPlata = findViewById(R.id.escudoPlataCantidad);
        escudoOro = findViewById(R.id.escudoOroCantidad);
        flechaMadera = findViewById(R.id.flechaMaderaCantidad);
        flechaPlata = findViewById(R.id.flechaPlataCantidad);
        flechaOro = findViewById(R.id.flechaOroCantidad);
        pocionAzul = findViewById(R.id.pocimaAzulCantidad);
        pocionRoja = findViewById(R.id.pocimaRojaCantidad);
        manzana = findViewById(R.id.mazanaCantidad);
        sharedPreferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("user","No existe info");


        getInventario(user);
    }


    public void getInventario(String user)
    {
        //Inventario inventario = new Inventario("prueba");
        Call<Inventario> call = ClientAPI.getUserService().getObjetosUser(user);
        Log.i("SHAREDPREF",  user);
        call.enqueue(new Callback<Inventario>() {
            @Override
            public void onResponse(Call<Inventario> call, Response<Inventario> response) {
                Log.i("PROVA", "" + response.code());

                if(response.code() == 200)
                {
                    Log.i("PROVA", "" + response.body().getEscudoMadera());

                    inventarioUser = response.body();
                    //progressBar.setVisibility(View.INVISIBLE);
                    escudoMadera.setText(""+inventarioUser.getEscudoMadera());
                    escudoPlata.setText(""+inventarioUser.getEscudoPlata());
                    escudoOro.setText(""+inventarioUser.getEscudoOro());
                    flechaMadera.setText(""+inventarioUser.getFlechaMadera());
                    flechaPlata.setText(""+inventarioUser.getFlechaPlata());
                    flechaOro.setText(""+inventarioUser.getFlechaOro());
                    pocionAzul.setText(""+inventarioUser.getPocionAzul());
                    pocionRoja.setText(""+inventarioUser.getPocionRoja());
                    manzana.setText(""+inventarioUser.getManzana());

                }
                else {
                    Log.d("INVENTARIO", "Codigo" + String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Inventario> call, Throwable t) {

            }
        });
    }

}