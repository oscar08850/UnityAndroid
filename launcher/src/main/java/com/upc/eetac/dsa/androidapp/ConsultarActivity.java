package com.upc.eetac.dsa.androidapp;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsultarActivity extends AppCompatActivity {

    TextView resultadoID;
    TextView resultadoUsername;
    TextView resultadoCorreo;
    TextView resultadoMonedas;

    EditText Username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        resultadoUsername=findViewById(R.id.resultadoConsultaUsername);
        resultadoCorreo=findViewById(R.id.resultadoConsultaCorreo);
        resultadoMonedas=findViewById(R.id.resultadoConsultaMonedas);

        Username=findViewById(R.id.usernameConsulta);

    }

    public void Consultar (View view){

        Call<User> call = ClientAPI.getUserService().getUser(Username.getText().toString());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code()==201){

                    resultadoUsername.setText(response.body().getUsername());
                    resultadoCorreo.setText(response.body().getEmail());
                    resultadoMonedas.setText(response.body().getCoins());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }

        });
    }
}