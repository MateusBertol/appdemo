package com.example.meuapp.ferramentas;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

public class Tools {

    public static void exibirMensagem(Context telaOrigem, String mensagem) {
        Toast.makeText(telaOrigem, mensagem, Toast.LENGTH_SHORT).show();
    }

    public static void exibirMensagemLonga(Context telaOrigem, String mensagem) {
        Toast.makeText(telaOrigem, mensagem, Toast.LENGTH_LONG).show();
    }

    public static void exibirCaixaDeMensagem(Context context, String titulo, String texto) {
        try{
            AlertDialog.Builder mensagem = new AlertDialog.Builder(context);
            mensagem.setMessage(texto);
            mensagem.setTitle((titulo));
            mensagem.setNeutralButton("OK", null);
            mensagem.show();
        }catch (Exception ex){
            System.out.println("Erro: " + ex.getMessage());
        }
    }

    public static int somarInteiros(int[] inteiros) {
        int resultado = 0;
        for (int i = 0; i < inteiros.length; i++) {
            resultado += inteiros[i];
        }
        return resultado;
    }
    public static int subtrairInteiros(int num1, int num2) {
        int valorSoma = num1 - num2;
        return valorSoma;
    }
}
