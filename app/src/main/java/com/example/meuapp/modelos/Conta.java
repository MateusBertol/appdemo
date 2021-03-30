package com.example.meuapp.modelos;

public class Conta {

    private String nome;
    private double saldo;
    private double cheque_especial;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getCheque_especial() {
        return cheque_especial;
    }

    public void setCheque_especial(double cheque_especial) {
        this.cheque_especial = cheque_especial;
    }

    public boolean sacar(double valor) {
        if (saldo + cheque_especial >= valor) {
            saldo -= valor;
            return true;
        } else {
            return false;
        }
    }

    public void depositar(double valor){
        saldo += valor;
    }

}
