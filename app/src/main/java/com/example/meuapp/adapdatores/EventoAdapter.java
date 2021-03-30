package com.example.meuapp.adapdatores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.meuapp.R;
import com.example.meuapp.ferramentas.Tools;
import com.example.meuapp.modelos.Evento;
import com.example.meuapp.telas.EventoActivity;

import java.util.ArrayList;

public class EventoAdapter extends ArrayAdapter<Evento> {

    private final Context context;
    private final ArrayList<Evento> elementos;

    public EventoAdapter(Context context, ArrayList<Evento> elementos){
        super(context, R.layout.item_lista_eventos, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        final Evento objEvento = elementos.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //toda vez que passa por um item da lista, os elementos são associados
        View rowView = inflater.inflate(R.layout.item_lista_eventos, parent, false);

        TextView lblTitulo = rowView.findViewById(R.id.lblTitulo_itemEvento);
        TextView lblData = rowView.findViewById(R.id.lblData_itemEvento);

        lblTitulo.setText(objEvento.getNome());
        lblData.setText(objEvento.getData());

        //clique na linha do ListView
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tools.exibirCaixaDeMensagem(context, "Mensagem", objEvento.getNome());
                Intent nova_tela = new Intent(context, EventoActivity.class);
                nova_tela.putExtra("codigo", String.valueOf(objEvento.getCodigo()));
                context.startActivity(nova_tela);
            }
        });

        return rowView;
    }
}
