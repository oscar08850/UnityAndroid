package com.upc.eetac.dsa.androidapp;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EliminarActivity extends AppCompatActivity {

    EditText contraseña1;
    EditText contraseña2;
    Button botonEliminar;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        botonEliminar=findViewById(R.id.buttonEliminar);
        contraseña1=findViewById(R.id.contraseñaEliminar);
        contraseña2=findViewById(R.id.contraseñaRepetida);
        sharedPreferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("user","No existe info");

        deleteUser(user);
    }


    public void deleteUser (String user){

    botonEliminar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (contraseña1.getText().toString().equals(contraseña2.getText().toString())) {
                Log.i("ELIMINAR", "El usuario es: " + user);
                Call<User> call = ClientAPI.getUserService().deleteUser(user, contraseña1.getText().toString());
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.i("ELIMINAR", "El código es: " + response.code());
                        if (response.code() == 200) {
                            Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
                            startActivity(intent);
                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        call.cancel();
                        Log.d("Error", "Failure");
                    }
                });
            }
            else {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(EliminarActivity.this);
                alertDialog.setMessage("Las contraseñas no coinciden!");
                alertDialog.show();
            }

        }
    });

    }
}