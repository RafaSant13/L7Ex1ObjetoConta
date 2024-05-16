package com.example.l7ex1objetoconta.model;

public class ContaBancaria {

    private String conta;
    private int numConta;
    private float saldo;

    public ContaBancaria() {
        this.conta = "";
        this.numConta = 0;
        this.saldo = 0;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        if (saldo < 0) {
            this.saldo = 0;
        } else {
            this.saldo = saldo;
        }
    }

    public String sacar(float valor) {
        if (valor > saldo) {
            return "Saldo Insuficiente";
        } else {
            this.saldo = saldo - valor;
            return "Valor sacado";
        }
    }

    public String deposito(float valor) {
        this.saldo = saldo + valor;
        return "Valor depositado";
    }

    @Override
    public String toString() {
        return "ContaBancaria{" +
                "conta='" + conta + '\'' +
                ", numConta=" + numConta +
                ", saldo=" + saldo +
                '}';
    }
}

