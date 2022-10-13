package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnIgual= findViewById(R.id.btnIgual);
        TableLayout tabla = findViewById(R.id.miTabla);
        TextView txtRespuesta = findViewById(R.id.txtResultado);

        Listener l = new Listener(tabla, txtRespuesta);
        tabla.setOnClickListener(l);

    }
}