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
            Double resultado = eval(sumatorioCadena);
            txtV.setText(resultado+"");
            sumatorioCadena="";
        }else{
            sumatorioCadena += b.getText();
            txtV.setText(sumatorioCadena);
        }
    }

    public int calcular(String operacion){

        ArrayList<Character> listaNumeros = new ArrayList<Character>();
        ArrayList<Character> listaOperadores = new ArrayList<>();
        ArrayList<Integer> indicesOperaciones= new ArrayList<>();
        int resultado=0, index=0, indexOp=0;


        for (Character caracter : operacion.toCharArray()) {

            if(caracter >= 48 && caracter <=57){
                index++;
                listaNumeros.add(caracter);
            }else{
                indexOp=index;
                indicesOperaciones.add(index);
                listaOperadores.add(caracter);
            }

        }

        int indexO=0;
        for(Character op: listaOperadores){

            if(op.equals('+')){
              resultado= sumar(listaNumeros, indicesOperaciones.get(indexO));
              indexO++;
            }
            else if(op.equals('-')){
                if(resultado==0) resultado= restar(listaNumeros, indicesOperaciones.get(indexO));
                else{
                   resultado= restar(resultado, listaNumeros,5);
                }
            }
            else if(op.equals('X')){
                if(resultado==0) resultado= mult(listaNumeros, indexOp);

            }
            else if(op.equals('/')){
                if(resultado==0) resultado= div(listaNumeros, indexOp);
            }
            indexO++;

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

    public int restar(ArrayList<Character> numeros, int indOp){

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
        resultado = n1-n2;

        Character a = (char) resultado;
        return resultado;
    }
    public int restar(int resul,ArrayList<Character> numeros, int indOp ){

        int resultado=0;

        String numero2="";
        for(int i = indOp; i<numeros.size(); i++){
            numero2+=numeros.get(i);
        }
        Integer numeroRestar= Integer.parseInt(numero2);
        resultado= resul- numeroRestar;
        return resultado;
    }
    public int mult(ArrayList<Character> numeros, int indOp){

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
        resultado = n1*n2;

        Character a = (char) resultado;
        return resultado;
    }
    public int div(ArrayList<Character> numeros, int indOp){

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
        resultado = n1/n2;

        Character a = (char) resultado;
        return resultado;
    }

    //METODO INTERNET

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }
            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('X')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return +parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    if (!eat(')')) throw new RuntimeException("Missing ')'");
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    if (eat('(')) {
                        x = parseExpression();
                        if (!eat(')'))
                            throw new RuntimeException("Missing ')' after argument to " + func);
                    } else {
                        x = parseFactor();
                    }
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }




}

