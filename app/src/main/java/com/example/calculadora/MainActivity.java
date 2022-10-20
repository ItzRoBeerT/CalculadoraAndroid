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
    Double resultado=0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void controlarBotones(View v){
        TextView txtV = findViewById(R.id.txtNumeros);
        Button b = (Button) v;


        if(b.getText().equals("MC")){
            resultado=0.0;
            sumatorioCadena=resultado+"";
            txtV.setText(resultado+"");
        }
        else if(b.getText().equals("MR")){
            sumatorioCadena=resultado+"";
            txtV.setText(resultado+"");

        }
        else if(b.getText().equals("M+")){
            resultado+= Double.parseDouble((String)txtV.getText());
            sumatorioCadena="";
            txtV.setText("");
        }
        else if(b.getText().equals("M-")){
            resultado-= Double.parseDouble((String)txtV.getText());
            sumatorioCadena="";
            txtV.setText("");
        }
        else if(b.getText().equals("+/-")){
            resultado= -resultado;
            sumatorioCadena="";
            txtV.setText(resultado+"");
        }
        else if(b.getText().equals("C")){
            resultado=0.0;
            sumatorioCadena="";
            txtV.setText("");
        }
        else if(b.getText().equals("=")){
            if(txtV.getText().equals("")){
                txtV.setText("");
            }else{
                resultado = eval(sumatorioCadena);
                txtV.setText(resultado+"");
                sumatorioCadena="";
            }
        }else{
            sumatorioCadena += b.getText();
            txtV.setText(sumatorioCadena);
        }
    }

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
                    if (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('X')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return +parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    if (!eat(')')) throw new RuntimeException("Missing ')'");
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') {
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
                return x;
            }
        }.parse();
    }




}

