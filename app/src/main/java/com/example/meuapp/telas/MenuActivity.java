package com.example.meuapp.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.meuapp.R;
import com.example.meuapp.ferramentas.Tools;

public class MenuActivity extends AppCompatActivity {

    Context context = MenuActivity.this;
    SharedPreferences sharedPreferences;
    TextView lblUsuario;
    Button btnCalculadora_menu, btnMegaSena_menu, btnLista_menu, btnBanco_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        lblUsuario = findViewById(R.id.lblUsuario_menu);

        sharedPreferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        String pref_usuario = sharedPreferences.getString("login_usuario", "");
        ;
        Tools.exibirMensagem(context, "Bem Vindo(a) " + pref_usuario);

        lblUsuario.setText("Usuário " + pref_usuario);

        btnCalculadora_menu = findViewById(R.id.btnCalculadora_menu);
        btnMegaSena_menu = findViewById(R.id.btnMegaSena_menu);
        btnLista_menu = findViewById(R.id.btnLinguagemProgramacao_menu);
        btnBanco_menu = findViewById(R.id.btnBanco_menu);

        btnCalculadora_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaCalculadora = new Intent(context, CalculadoraActivity.class);
                startActivity(telaCalculadora);
            }
        });

        btnMegaSena_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaMegaSena = new Intent(context, MegaSenaActivity.class);
                startActivity(telaMegaSena);
            }
        });

        btnLista_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaLista = new Intent(context, ListaActivity.class);
                startActivity(telaLista);
            }
        });

        btnBanco_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaBanco = new Intent(context, BancoActivity.class);
                startActivity(telaBanco);
            }
        });

    }
}
