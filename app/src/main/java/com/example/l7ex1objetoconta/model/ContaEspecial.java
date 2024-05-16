package com.example.l7ex1objetoconta.model;

import com.example.l7ex1objetoconta.R;

public class ContaEspecial extends ContaBancaria{

    private float limite;

    public ContaEspecial() {
        super();
    }

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }

    @Override
    public String sacar(float valor) {
        if (valor>(getSaldo()+limite)) {
            return "Saldo Insuficiente";
        }
        else {
            float saldo = getSaldo() - valor;
            if (saldo<0) {
                float limite = getLimite() + saldo;
                setLimite(limite);
            }
            setSaldo(saldo);
            return "Valor sacado";
        }
    }

    @Override
    public String toString() {
        return "Conta Nº"+getNumConta()+"\nNome: "+getConta()+"\nSaldo: "+getSaldo()+"\nLimite: "+getLimite();
    }
}