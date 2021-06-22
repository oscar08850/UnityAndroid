package com.DefaultCompany.Archer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.unity3d.player.UnityPlayerActivity;

public class MainActivity extends Activity {

    Button button;
    int pocionRoja = 2;
    int pocionAzul = 2;
    int manzana = 2;
    int fuerza = 40;
    int maxHealth = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.upc.eetac.dsa.androidapp.R.layout.activity_main);
    }


    public void archer(View view) {
        Intent intent = new Intent(this, UnityPlayerActivity.class);
        intent.putExtra("pocionRoja", pocionRoja);
        intent.putExtra("pocionAzul", pocionAzul);

        intent.putExtra("manzana", manzana);
        intent.putExtra("maxHealth", maxHealth);
        intent.putExtra("powerFlecha", fuerza);

        startActivity(intent);


    }
}