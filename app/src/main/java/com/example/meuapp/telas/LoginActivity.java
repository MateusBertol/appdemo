package com.example.meuapp.telas;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meuapp.R;
import com.example.meuapp.ferramentas.Tools;

public class LoginActivity extends AppCompatActivity {

    Context context = LoginActivity.this;
    EditText txtUsuario, txtSenha;
    Button btnLogin;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("login_usuario", "yuri");
        editor.putString("senha_usuario", "123");
        editor.apply();

        txtUsuario = findViewById(R.id.txtLogin_usuario);
        txtSenha = findViewById(R.id.txtSenha_usuario);
        btnLogin = findViewById(R.id.btnLogin_usuario);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
                String pref_usuario = sharedPreferences.getString("login_usuario", "");;
                String pref_senha = sharedPreferences.getString("senha_usuario", "");;

                if(txtUsuario.getText().toString().equals(pref_usuario) && txtSenha.getText().toString().equals(pref_senha)){
                    Intent telaMenu = new Intent(context, MenuActivity.class);
                    startActivity(telaMenu);
                    finish();
                } else{
                    Tools.exibirMensagem(context, "Usuário ou senha inválido(s)");
                }
            }
        });
    }
}
