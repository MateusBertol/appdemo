package com.example.meuapp.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.meuapp.R;
import com.example.meuapp.adapdatores.EventoAdapter;
import com.example.meuapp.controladores.EventoDao;
import com.example.meuapp.modelos.Evento;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {

    Context context;
    FloatingActionButton btnAdicionarItem;
    ListView lviItens;
    ArrayList<String> listaItens;
    Evento itemEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        //Vincular os itens  da tela
        context = ListaActivity.this;

        btnAdicionarItem = findViewById(R.id.btnAdicionarEvento_lista);
        lviItens = findViewById(R.id.listaItens_lista);

        listaItens = new ArrayList<>();

        btnAdicionarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tela = new Intent(context, EventoActivity.class);
                startActivity(tela);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        listarEventos();
    }

    private void listarEventos(){
        ArrayList<Evento> listaEventos = new ArrayList<Evento>();

        EventoDao objDao = new EventoDao(context);
        listaEventos = objDao.buscarTodos();

        ArrayAdapter arrayAdapter = new EventoAdapter(context, listaEventos);
        lviItens.setAdapter(arrayAdapter);

    }
}
