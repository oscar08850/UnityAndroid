package com.upc.eetac.dsa.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.unity3d.player.UnityPlayerActivity;

import models.Credentials;
import models.CredentialsCompra;
import models.CredentialsPartida;
import models.Inventario;
import models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PrincipalActivity extends Activity {

    int pocionRoja = 6;
    int pocionAzul = 7;
    int manzana = 10;
    int fuerza = 1000;
    int maxHealth = 130;

    Inventario inventarioUser;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);


    }

    public void consultarUsuario(View view) {
        Intent intent = new Intent(this, ConsultarActivity.class);
        startActivity(intent);
    }

    public void editarUsuario(View view) {
        Intent intent = new Intent(this, MenuEditarActivity.class);
        startActivity(intent);
    }

    public void tienda(View view) {
        Intent intent = new Intent(this, TiendaActivity.class);
        startActivity(intent);
    }

    public void iniciarPartida(View view) {

        Intent intent = new Intent(this, UnityPlayerActivity.class);

        sharedPreferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("user", "No existe info");

        getInventario(user);
        Consultar(user);

        intent.putExtra("pocionRoja", this.pocionRoja);
        intent.putExtra("pocionAzul", this.pocionAzul);

        intent.putExtra("manzana", this.manzana);
        intent.putExtra("maxHealth", this.maxHealth);
        intent.putExtra("powerFlecha", this.fuerza);


        startActivity(intent);


        //      Intent intent = new Intent(this,IniciarPartidaActivity.class);
//        startActivity(intent);
    }

    public void rankings(View view) {
        Intent intent = new Intent(this, MenuRecordsActivity.class);
        startActivity(intent);
    }

    public void inventario(View view) {
        Intent intent = new Intent(this, InventarioActivity.class);
        startActivity(intent);


    }


    public void getInventario(String user) {
        //Inventario inventario = new Inventario("prueba");
        Call<Inventario> call = ClientAPI.getUserService().getObjetosUser(user);
        Log.i("SHAREDPREF", user);
        call.enqueue(new Callback<Inventario>() {
            @Override
            public void onResponse(Call<Inventario> call, Response<Inventario> response) {
                Log.i("PROVA", "" + response.code());

                if (response.code() == 200) {

                    inventarioUser = response.body();

                    pocionAzul = inventarioUser.getPocionAzul();
                    pocionRoja = inventarioUser.getPocionRoja();
                    manzana = inventarioUser.getManzana();

                } else {
                    Log.d("INVENTARIO", "Codigo" + String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Inventario> call, Throwable t) {

            }
        });
    }


    public void Consultar(String username) {

        Call<User> call = ClientAPI.getUserService().getUser(username);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    maxHealth = response.body().getVida();
                    fuerza = response.body().getFuerza();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }

        });
    }


    public void onGameFinish(int monedas, int muertos, int tiempoContador) {
        sharedPreferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("user", "No existe info");
        CredentialsPartida credentialsPartida = new CredentialsPartida(user, muertos, tiempoContador, monedas);


        Call<CredentialsPartida> call = ClientAPI.getUserService().addPartida(credentialsPartida);
        Log.i("SHAREDPREF", user);
        call.enqueue(new Callback<CredentialsPartida>() {
            @Override
            public void onResponse(Call<CredentialsPartida> call, Response<CredentialsPartida> response) {
                Log.i("PROVA", "" + response.code());

                if (response.code() == 200) {


                } else {
                    Log.d("INVENTARIO", "Codigo" + String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<CredentialsPartida> call, Throwable t) {

            }



        });
    }






    public void onGamePocionAzul(String pocion) {
        sharedPreferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("user", "No existe info");
        CredentialsCompra credentialsCompraAzul = new CredentialsCompra(pocion ,user);

        Call<CredentialsCompra> call = ClientAPI.getUserService().consumir(credentialsCompraAzul);

        Log.i("SHAREDPREF", user);
        call.enqueue(new Callback<CredentialsCompra>() {
            @Override
            public void onResponse(Call<CredentialsCompra> call, Response<CredentialsCompra> response) {
                Log.i("PROVA", "" + response.code());

                if (response.code() == 200) {


                } else {
                    Log.d("INVENTARIO", "Codigo" + String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<CredentialsCompra> call, Throwable t) {

            }

        });
    }


    public void onGamePocionRoja(String pocion) {
        sharedPreferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("user", "No existe info");
        CredentialsCompra credentialsCompraRoja = new CredentialsCompra(pocion ,user);

        Call<CredentialsCompra> call = ClientAPI.getUserService().consumir(credentialsCompraRoja);

        Log.i("SHAREDPREF", user);
        call.enqueue(new Callback<CredentialsCompra>() {
            @Override
            public void onResponse(Call<CredentialsCompra> call, Response<CredentialsCompra> response) {
                Log.i("PROVA", "" + response.code());

                if (response.code() == 200) {


                } else {
                    Log.d("INVENTARIO", "Codigo" + String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<CredentialsCompra> call, Throwable t) {

            }

        });
    }


    public void onGameManzana(String manzana) {
        sharedPreferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("user", "No existe info");
        CredentialsCompra credentialsCompraManzana = new CredentialsCompra(manzana ,user);

        Call<CredentialsCompra> call = ClientAPI.getUserService().consumir(credentialsCompraManzana);

        Log.i("SHAREDPREF", user);
        call.enqueue(new Callback<CredentialsCompra>() {
            @Override
            public void onResponse(Call<CredentialsCompra> call, Response<CredentialsCompra> response) {
                Log.i("PROVA", "" + response.code());

                if (response.code() == 200) {


                } else {
                    Log.d("INVENTARIO", "Codigo" + String.valueOf(response.code()));
                }
            }
            @Override
            public void onFailure(Call<CredentialsCompra> call, Throwable t) {

            }

        });
    }

}