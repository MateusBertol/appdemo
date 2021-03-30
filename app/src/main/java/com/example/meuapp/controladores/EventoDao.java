package com.example.meuapp.controladores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.meuapp.banco.DadosOpenHelper;
import com.example.meuapp.modelos.Evento;

import java.util.ArrayList;
import java.util.List;

public class EventoDao {

    private SQLiteDatabase conexao;

    private static final String TABELA = "eventos";

    public EventoDao(Context ctx) {

        DadosOpenHelper db = new DadosOpenHelper(ctx);

        conexao = db.getWritableDatabase();

    }

    public Boolean inserir(Evento evento) {

        try {

            ContentValues valores = new ContentValues();
            valores.put("nome", evento.getNome());
            valores.put("data", evento.getData());

            conexao.insertOrThrow(TABELA, null, valores);

            return true;
        } catch (SQLException ex) {
            Log.e("Inserir", ex.getMessage());
            return false;
        }
    }

    public boolean excluir(int codigo) {
        try {
            String[] parametros = new String[1];
            parametros[0] = String.valueOf(codigo);

            conexao.delete(TABELA, "codigo = ?", parametros);

        } catch (SQLException ex) {
            Log.e("Excluir", ex.getMessage());
            return false;
        }
        return true;
    }

    public void alterar(Evento evento) {

        try {
            ContentValues valores = new ContentValues();
            valores.put("nome", evento.getNome());
            valores.put("data", evento.getData());

            String[] parametros = new String[1];
            parametros[0] = String.valueOf(evento.getCodigo());

            conexao.update(TABELA, valores, "codigo = ? ", parametros);

        } catch (SQLException ex) {
            Log.e("Alterar", ex.getMessage());
        }

    }

    public ArrayList<Evento> buscarTodos() {

        ArrayList<Evento> lista = new ArrayList<Evento>();

        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT codigo, nome, data FROM " + TABELA);

            Cursor resultado = conexao.rawQuery(sql.toString(), null);

            if (resultado.getCount() > 0) {
                resultado.moveToFirst();

                Evento evento;
                do {
                    evento = new Evento();
                    evento.setCodigo(resultado.getInt(resultado.getColumnIndexOrThrow("codigo")));
                    evento.setNome(resultado.getString(resultado.getColumnIndexOrThrow("nome")));
                    evento.setData(resultado.getString(resultado.getColumnIndexOrThrow("data")));

                    lista.add(evento);
                } while (resultado.moveToNext());

            }
            return lista;
        } catch (SQLException ex) {
            Log.e("Buscar", ex.getMessage());
            return lista;
        }
    }

    public Evento buscarEvento(int codigo) {

        Evento evento = new Evento();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT codigo, nome, data FROM " + TABELA);
        sql.append(" WHERE codigo = " + codigo);


        Cursor resultado = conexao.rawQuery(sql.toString(), null);

        if (resultado.getCount() > 0) {
            resultado.moveToFirst();

            evento.setCodigo(resultado.getInt(resultado.getColumnIndex("codigo")));
            evento.setNome(resultado.getString(resultado.getColumnIndex("nome")));
            evento.setData(resultado.getString(resultado.getColumnIndex("data")));

            return evento;
        }
        return null;
    }
}
