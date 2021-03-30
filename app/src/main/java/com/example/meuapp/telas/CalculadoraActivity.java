package com.example.meuapp.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;

import com.example.meuapp.R;
import com.example.meuapp.ferramentas.Tools;

public class CalculadoraActivity extends AppCompatActivity {

    //Declarando Váriavel
    Button btnSomarNumero, btnSubtrairNumero, btnApagarTexto;
    TextView lblTexto;
    EditText txtCampo1, txtCampo2;
    Context context = CalculadoraActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Referência par ao XML com o layout dos campos e botões
        setContentView(R.layout.activity_calculadora);

        //Criando o link com o layout
        btnSomarNumero = findViewById(R.id.btnSomar_calculadora);
        btnSubtrairNumero = findViewById(R.id.btnSubtrair_calculadora);
        btnApagarTexto = findViewById(R.id.btnApagarTexto_calculadora);
        lblTexto = findViewById(R.id.lblTexto_calculadora);
        txtCampo1 = findViewById(R.id.txtCampo1_calculadora);
        txtCampo2 = findViewById(R.id.txtCampo2_calculadora);

        btnSomarNumero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtCampo1.getText().toString().equals("")) {
                    //transformar o valor do campo para inteiro
                    int num1 = Integer.parseInt(txtCampo1.getText().toString());
                    int num2 = Integer.parseInt(txtCampo2.getText().toString());

                    int resultado;
                    int[] numeros = {num1 + num2};
                    resultado = Tools.somarInteiros(numeros);

                    //transformar  num1 e num2 em String
                    lblTexto.setText(String.valueOf(resultado));

                    //mostrar resultado na tela como tost
                    Tools.exibirMensagem(context, "Resultado = " + resultado);
                }

            }
        });

        btnSubtrairNumero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtCampo1.getText().toString().equals("")) {
                    //transformar o valor do campo para inteiro
                    int num1 = Integer.parseInt(txtCampo1.getText().toString());
                    int num2 = Integer.parseInt(txtCampo2.getText().toString());

                    int resultado = Tools.subtrairInteiros(num1, num2);
                    lblTexto.setText(String.valueOf(resultado));

                    Tools.exibirMensagem(context, "Resultado = " + resultado);
                }

            }
        });

        btnApagarTexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCampo1.setText("");
                txtCampo2.setText("");
                lblTexto.setText("VALOR");

            }
        });


    }
}

