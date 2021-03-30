package com.example.meuapp.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.meuapp.R;
import com.example.meuapp.ferramentas.Tools;
import com.example.meuapp.modelos.Conta;

public class BancoActivity extends AppCompatActivity {

    Context context;
    TextView lblNomeConta1, lblNomeConta2;
    TextView lblSaldoConta1, lblSaldoConta2;
    TextView lblChequeConta1, lblChequeConta2;
    TextView lblHistorico_banco;
    EditText txtValorTransferencia;
    Button btnTransfereConta1, btnTransfereConta2;
    Conta objConta1, objConta2;
    String historico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banco);

        context = BancoActivity.this;
        historico = "";
        lblNomeConta1 = findViewById(R.id.lblNomeConta1_banco);
        lblNomeConta2 = findViewById(R.id.lblNomeConta2_banco);
        lblSaldoConta1 = findViewById(R.id.lblSaldoConta1_banco);
        lblSaldoConta2 = findViewById(R.id.lblSaldoConta2_banco);
        lblChequeConta1 = findViewById(R.id.lblChequeConta1_banco);
        lblChequeConta2 = findViewById(R.id.lblChequeConta2_banco);
        txtValorTransferencia = findViewById(R.id.txtValorTransferencia);

        btnTransfereConta1 = findViewById(R.id.btnTransfereConta1_banco);
        btnTransfereConta2 = findViewById(R.id.btnTransfereConta2_banco);
        lblHistorico_banco = findViewById(R.id.lblHistorico_banco);

        objConta1 = new Conta(); //Instanciamos o objeto
        objConta1.setNome("Yuri");
        objConta1.setSaldo(10000);
        objConta1.setCheque_especial(2000);

        objConta2 = new Conta(); //Instanciamos o objeto
        objConta2.setNome("Jonas");
        objConta2.setSaldo(15000);
        objConta2.setCheque_especial(5000);

        preencheConta1(objConta1);
        //preenche a conta1

        preencheConta2(objConta2);
        //preenche a conta2

        btnTransfereConta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (!txtValorTransferencia.getText().toString().trim().equals("")) {
                        //transferir  da conta 1 para a conta 2 R$600,00
                        double valor = Double.parseDouble(txtValorTransferencia.getText().toString());
                        if (valor > 0) {
                            if (objConta1.sacar(valor)) {
                                objConta2.depositar(valor);
                                historico += objConta1.getNome() + " transferiu R$ " + valor + " para " + objConta2.getNome() + "\n";
                                lblHistorico_banco.setText(historico);
                                txtValorTransferencia.setText("");
                            } else {
                                Tools.exibirMensagem(context, "Saldo insuficiente para transferência");
                            }
                        } else {
                            Tools.exibirMensagem(context, "Valor precisa ser maior que 0");
                        }
                        preencheConta1(objConta1);
                        preencheConta2(objConta2);
                    }
                }
        });

        btnTransfereConta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtValorTransferencia.getText().toString().trim().equals("")) {
                    //transferir  da conta 2 para a conta 2 R$600,00
                    double valor = Double.parseDouble(txtValorTransferencia.getText().toString());
                    if (valor > 0) {
                        if (objConta2.sacar(valor)) {
                            objConta1.depositar(valor);
                            historico += objConta2.getNome() + " transferiu R$ " + valor + " para " + objConta1.getNome() + "\n";
                            lblHistorico_banco.setText(historico);
                            txtValorTransferencia.setText("");
                        } else {
                            Tools.exibirMensagem(context, "Saldo insuficiente para transferência");
                        }
                    } else {
                        Tools.exibirMensagem(context, "Valor precisa ser maior que 0");
                    }
                    preencheConta1(objConta1);
                    preencheConta2(objConta2);
                }
            }
        });

    }

    private void preencheConta1(Conta conta) {
        lblNomeConta1.setText(conta.getNome());
        lblSaldoConta1.setText("R$ " + conta.getSaldo());
        lblChequeConta1.setText("Limite: R$ " + conta.getCheque_especial());
    }

    private void preencheConta2(Conta conta) {
        lblNomeConta2.setText(conta.getNome());
        lblSaldoConta2.setText("R$ " + conta.getSaldo());
        lblChequeConta2.setText("Limite: R$ " + conta.getCheque_especial());
    }

}
