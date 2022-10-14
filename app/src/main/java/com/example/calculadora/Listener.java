package com.example.calculadora;

import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

public class Listener implements View.OnClickListener {

    private TableLayout tabla;
    private TextView txtResultado;

    public Listener(TableLayout tabla, TextView txtResultado) {
        this.tabla = tabla;
        this.txtResultado=txtResultado;
    }

    @Override
    public void onClick(View view) {
        String id= String.valueOf(tabla.getId());
        if(id.equalsIgnoreCase("btnIgual")){
            txtResultado.setText("Le has dado igual");

        }else{
            txtResultado.setText("");
        }
    }

}
