package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         ArrayList<String> sumaString= new ArrayList<>();
        Button btn1 = findViewById(R.id.btn1);
        TextView txtV = findViewById(R.id.txtNumeros);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sumaString.add(imprimir(btn1));
                for (String cad:sumaString
                     ) {
                    txtV.setText(cad);
                }

            }
        });
    }

    public String  imprimir(Button btn){
        String cad="";

        cad= (String) btn.getText();
        return cad;
    }
}

