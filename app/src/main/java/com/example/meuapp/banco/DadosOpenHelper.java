package com.example.meuapp.banco;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DadosOpenHelper extends SQLiteOpenHelper {

    private static final String NM_BANCO = "banco";
    private static final int VERSION = 2;

    private static final String TABELA_EVENTOS = "eventos";

    public DadosOpenHelper(Context context) {
        super(context, NM_BANCO, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Cria as tabelas do banco de dados
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE IF NOT EXISTS " + TABELA_EVENTOS + "( ");
        sql.append(" codigo INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sql.append(" nome VARCHAR(50) NOT NULL ");
        sql.append(" data VARCHAR(10) ");
        sql.append(" ); ");

        db.execSQL(sql.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == 2) {
            try {
                StringBuilder sql = new StringBuilder();
                sql.append("ALTER TABLE " + TABELA_EVENTOS);
                sql.append(" ADD data VARCHAR(10) ");

                db.execSQL(sql.toString());
            } catch (SQLException ex){
                Log.e("UPDATE_DB", ex.getMessage());
            }
        }

    }
}