package com.example.l7ex1objetoconta.model;

public class ContaPoupanca extends ContaBancaria{

    private int diaRendimento;

    public ContaPoupanca() {
        super();
    }

    public int getDiaRendimento() {
        return diaRendimento;
    }

    public void setDiaRendimento(int diaRendimento) {
        this.diaRendimento = diaRendimento;
    }

    public float calcNovoSaldo(float taxa){
        float saldo = getSaldo() * (1+(taxa*0.01f));
        setSaldo(saldo);
        return getSaldo();
    }

    @Override
    public String toString() {
        return "Conta Nº"+getNumConta()+"\nNome: "+getConta()+"\nSaldo: "+getSaldo()+"\nDia de rendimento: dia "+getDiaRendimento();
    }
}
