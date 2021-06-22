package com.upc.eetac.dsa.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import models.Object;


public class DetalleObjetoActivity extends AppCompatActivity {

    private static Object object;
    ImageView avatar;
    TextView nombreText, descripcionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_objeto);
        nombreText = findViewById(R.id.nombreDetalle);
        descripcionText = findViewById(R.id.descripcionDetalle);
        avatar = findViewById(R.id.fotoDetalle);
        object = getIntent().getParcelableExtra("Element");
        nombreText.setText(object.getNombre());
        descripcionText.setText(object.getDescripcion());
    }
}