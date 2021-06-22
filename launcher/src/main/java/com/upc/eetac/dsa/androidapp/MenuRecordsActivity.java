package com.upc.eetac.dsa.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MenuRecordsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_ranking);

    }

    public void recordIndividual (View view){
        Intent intent = new Intent(this, IndividualRecordsActivity.class);
        startActivity(intent);
    }

    public void recordGlobal (View view){
        Intent intent = new Intent(this,GlobalRecordsActivity.class);
        startActivity(intent);
    }
}
