package com.example.l7ex1objetoconta;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.l7ex1objetoconta.model.ContaEspecial;
import com.example.l7ex1objetoconta.model.ContaPoupanca;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private RadioButton rbEspecial;
    private RadioButton rbPoupanca;
    private EditText etNome;
    private EditText etNum;
    private EditText etSaldo;
    private EditText etValor;
    private EditText etLimite;
    private EditText etTaxa;
    private EditText etData;
    private TextView tvEspecial;
    private TextView tvPoupanca;
    private Button btnSalvar;
    private Button btnSacar;
    private Button btnDepositar;
    private Button btnDados;
    private Button btnRendimento;
    private ContaEspecial ce;
    private ContaPoupanca cp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rbEspecial = findViewById(R.id.rbEspecial);
        rbEspecial.setChecked(true);
        rbPoupanca = findViewById(R.id.rbPoupanca);
        etNome = findViewById(R.id.etNome);
        etNum = findViewById(R.id.etNum);
        etSaldo = findViewById(R.id.etSaldo);
        etValor = findViewById(R.id.etValor);
        etLimite = findViewById(R.id.etLimite);
        etTaxa = findViewById(R.id.etTaxa);
        etData = findViewById(R.id.etData);
        tvEspecial = findViewById(R.id.tvEspecial);
        tvPoupanca = findViewById(R.id.tvPoupanca);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnSacar = findViewById(R.id.btnSacar);
        btnDepositar = findViewById(R.id.btnDepositar);
        btnDados = findViewById(R.id.btnDados);
        btnRendimento = findViewById(R.id.btnRendimento);
        ce = new ContaEspecial();
        cp = new ContaPoupanca();

        atualizaActivity();

        for (RadioButton radioButton : Arrays.asList(rbEspecial, rbPoupanca)) {
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    MainActivity.this.atualizaActivity();
                }
            });
        }

        btnSalvar.setOnClickListener(op -> salvar());
        btnDados.setOnClickListener(op -> dados());
        btnSacar.setOnClickListener(op -> sacar());
        btnDepositar.setOnClickListener(op -> depositar());
        btnRendimento.setOnClickListener(op -> render());

    }

    private void salvar() {
        String nome = etNome.getText().toString();
        int num = Integer.parseInt(etNum.getText().toString());
        float saldo = Float.parseFloat(etSaldo.getText().toString());
        if (rbEspecial.isChecked()){
            float limite = Float.parseFloat(etLimite.getText().toString());
            ce.setConta(nome);
            ce.setNumConta(num);
            ce.setSaldo(saldo);
            ce.setLimite(limite);
        }
        else {
            int dia = Integer.parseInt(etData.getText().toString());
            cp.setConta(nome);
            cp.setNumConta(num);
            cp.setSaldo(saldo);
            cp.setDiaRendimento(dia);
        }
        Toast.makeText(this, getString(R.string.salvarconcluido), Toast.LENGTH_LONG).show();
    }

    private void dados() {
        if (rbEspecial.isChecked()){
            tvEspecial.setText(ce.toString());
        }
        else {
            tvPoupanca.setText(cp.toString());
        }
    }

    private void sacar() {
        float valor = Float.parseFloat(etValor.getText().toString());
        String res;
        if (rbEspecial.isChecked()){
            res = ce.sacar(valor);
            Toast.makeText(this, res, Toast.LENGTH_LONG).show();
        }
        else{
            res = cp.sacar(valor);
            Toast.makeText(this, res, Toast.LENGTH_LONG).show();
        }
    }

    private void depositar() {
        float valor = Float.parseFloat(etValor.getText().toString());
        String res;
        if (rbEspecial.isChecked()){
            res = ce.deposito(valor);
            Toast.makeText(this, res, Toast.LENGTH_LONG).show();
        }
        else{
            res = cp.deposito(valor);
            Toast.makeText(this, res, Toast.LENGTH_LONG).show();
        }
    }

    private void render() {
        float taxa = Float.parseFloat(etTaxa.getText().toString());
        float res = cp.calcNovoSaldo(taxa);
        Toast.makeText(this, getString(R.string.saldoatualizado)+res, Toast.LENGTH_LONG).show();
    }

    private void atualizaActivity() {
        etNome.setText("");
        etNum.setText("");
        etSaldo.setText("");
        etValor.setText("");
        if (rbEspecial.isChecked()){
            etLimite.setVisibility(View.VISIBLE);
            etLimite.setText("");
            tvEspecial.setVisibility(View.VISIBLE);
            tvEspecial.setText("");
            etData.setVisibility(View.INVISIBLE);
            etTaxa.setVisibility(View.INVISIBLE);
            btnRendimento.setVisibility(View.INVISIBLE);
            tvPoupanca.setVisibility(View.INVISIBLE);

        }
        else{
            etLimite.setVisibility(View.INVISIBLE);
            tvEspecial.setVisibility(View.INVISIBLE);
            etData.setVisibility(View.VISIBLE);
            etData.setText("");
            etTaxa.setVisibility(View.VISIBLE);
            etTaxa.setText("");
            btnRendimento.setVisibility(View.VISIBLE);
            tvPoupanca.setVisibility(View.VISIBLE);
            tvPoupanca.setText("");
        }
    }
}