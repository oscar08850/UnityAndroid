package com.upc.eetac.dsa.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MenuEditarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_editar);

    }

    public void eliminarCuenta (View view){
        Intent intent = new Intent(this, EliminarActivity.class);
        startActivity(intent);
    }
}

