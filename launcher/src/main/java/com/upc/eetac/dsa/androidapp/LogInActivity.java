package com.upc.eetac.dsa.androidapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import models.Credentials;
import models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.DefaultCompany.Archer.MainActivity;

public class LogInActivity extends AppCompatActivity {

    UserService userAPI;
    EditText uname;
    EditText pswrd;
    Button signUpButton,loginButton;

    ProgressBarDialog loadingPB = new ProgressBarDialog(LogInActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userAPI = ClientAPI.getUserService();
        uname = (EditText) findViewById(R.id.user);
        pswrd = (EditText) findViewById(R.id.password);

        signUpButton=(Button)findViewById(R.id.register);
        loginButton=(Button)findViewById(R.id.login);
        cargarPreferencias();

        ProgressBarDialog loadingPB = new ProgressBarDialog(LogInActivity.this);



        /*
        signUpButton.setOnClickListener(new View.OnClickListener(){
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
        */
    }

    private void cargarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        String user = preferences.getString("user","No existe info");
        String pass = preferences.getString("pass","No existe info");

    }

    public void guardarPreferencias(){

        SharedPreferences preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        String usuario = uname.getText().toString();
        String password = pswrd.getText().toString();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user",usuario);
        editor.putString("pass", password);

        editor.apply();
    }

    public void botonRegistrar (View v){
       /* loadingPB.startPBDialog();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingPB.dismissPBDialog();
            }
        }, 4000);
      */  Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }

    public void sendLogin(View view) {

        Intent intent = new Intent(this, MainActivity.class);

        uname = (EditText) findViewById(R.id.user);
        pswrd = (EditText) findViewById(R.id.password);
        String username = uname.getText().toString();
        String password = pswrd.getText().toString();
        startActivity(intent);




       Call<Credentials> call = userAPI.loginUser(new Credentials(username, password));
        call.enqueue(new Callback<Credentials>() {
            @Override
            public void onResponse(Call<Credentials> call, Response<Credentials> response) {
                Log.d("LOGIN", response.code() + " ");
                if (response.code() == 200) {
                    Credentials credentials = response.body();
                    String pswrd = credentials.getPassword();
                    String uname = credentials.getUsername();
                    Log.d("Usuario", uname + " " + pswrd + " ");
                    guardarPreferencias();
                    startActivity(intent);
                }

                else {
                    Log.d("ERROR", "Login failed");
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getApplicationContext());
                    alertDialog.setMessage("Login failed! Please, try again.");
                    alertDialog.setTitle(response.message());
                    alertDialog.setCancelable(true);

                    alertDialog.create().show();
                }
            }


            @Override
            public void onFailure(Call<Credentials> call, Throwable t) {
                call.cancel();
                Log.d("ERROR", "Failure");
            }
        });


    }


}
