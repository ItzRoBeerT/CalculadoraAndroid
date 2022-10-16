package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public String sumatorioCadena="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //prueba manejador de eventos

    public void controlarBotones(View v){
        TextView txtV = findViewById(R.id.txtNumeros);
        Button b = (Button) v;

        if(b.getText().equals("=")){
            txtV.setText(calcular(sumatorioCadena)+"");
            sumatorioCadena="";
        }else{
            sumatorioCadena += b.getText();
            txtV.setText(sumatorioCadena);
        }
    }

    public int calcular(String operacion){

        ArrayList<Character> listaNumeros = new ArrayList<Character>();
        ArrayList<Character> listaOperadores = new ArrayList<>();
        int resultado=0, index=0, indexOp=0;


        for (Character caracter : operacion.toCharArray()) {

            if(caracter >= 48 && caracter <=57){
                index++;
                listaNumeros.add(caracter);
            }else
                indexOp=index;
                listaOperadores.add(caracter);
        }

        for(Character op: listaOperadores){

            if(op.equals('+')){
              resultado= sumar(listaNumeros, indexOp);

            }
            if(op.equals('-')){
                resultado= sumar(listaNumeros, indexOp);

            }

        }
        return resultado;
    }

    public int sumar(ArrayList<Character> numeros, int indOp){

        String numero1="";
        String numero2="";
        int resultado = 0;

        for(int i = 0; i<indOp; i++){
            numero1+=numeros.get(i);
        }
        for(int i = indOp; i<numeros.size(); i++){
            numero2+=numeros.get(i);
        }
        Integer n1= Integer.parseInt(numero1);
        Integer n2 = Integer.parseInt(numero2);
        resultado = n1+n2;

        Character a = (char) resultado;
        return resultado;
    }

    public int restar(){

        return 0;
    }

}

