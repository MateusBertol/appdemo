package com.example.meuapp.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.meuapp.R;

import java.util.ArrayList;
import java.util.Random;

public class MegaSenaActivity extends AppCompatActivity {

    Context context;
    TextView lblNumerosGerados;
    Button btnGerarNumeros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mega_sena);

        context = MegaSenaActivity.this;
        btnGerarNumeros = findViewById(R.id.btnGerarNumeros_mega);
        lblNumerosGerados = findViewById(R.id.lblNumerosGerados_mega);

        btnGerarNumeros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random randomico = new Random();
                int numero;

                ArrayList<Integer> numerosMaisSorteados = new ArrayList<Integer>();
                numerosMaisSorteados.add(10);
                numerosMaisSorteados.add(56);
                numerosMaisSorteados.add(5);
                numerosMaisSorteados.add(53);
                numerosMaisSorteados.add(4);
                numerosMaisSorteados.add(54);
                numerosMaisSorteados.add(2);
                numerosMaisSorteados.add(16);
                numerosMaisSorteados.add(29);
                numerosMaisSorteados.add(36);
                numerosMaisSorteados.add(18);
                numerosMaisSorteados.add(41);
                numerosMaisSorteados.add(35);
                numerosMaisSorteados.add(50);

                ArrayList<Integer> listaNumeros = new ArrayList<>();
                while (listaNumeros.size() < 6){
                    //Gera um numero aleatórioa de 1 a 60
                    numero = randomico.nextInt(60) + 1;

                    //Verifica se o numero gerado faz parte dos numerosMaisSorteados
                    if(numerosMaisSorteados.contains(numero) == true){
                        //Verifica se  o numero gerado já está dentro da nossa lista
                        if(listaNumeros.contains(numero) == false){
                            listaNumeros.add(numero);
                        }
                    }
                }

                //Collections.sort(listNumeros);
                lblNumerosGerados.setText(listaNumeros.toString()
                        .replace("[", "")
                        .replace("]", ""));

            }
        });
    }
}
