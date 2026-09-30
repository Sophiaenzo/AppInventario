package br.example.app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import controller.produtoEstoque.InserirDadosDaEtq;
import controller.produtoEstoque.ProdutoAdapter;
import controller.produtoEstoque.QtdEstoqueSistema;
import controller.produtoEstoque.RecebeListaProdutos;
import controller.produtoEstoque.VerificarEtiquetaDoProduto;
import controller.produtoEstoque.VerificarSeEtqEDoProduto;
import model.Produto;
import modelDominio.R999usu;
import util.BeepUtil;
import util.MensagemUsuario;

public class capturaProduto extends AppCompatActivity {
    private Button btnInicio;
    private Button btnCapturarEtiqueta;
    String codPro = "";
    String codDep;
    String codigo;
    String usuario;
    private EditText ETCodigoBarrasColetado = null;
    private EditText txtPegaProduto;
    private Spinner spinner_deposito2;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captura_produto);
        IniciaComponentes();

        String[] deposit = new String[]{"DEPOSITOCD", "DEPACABADO"};

        spinner_deposito2 = (Spinner) findViewById(R.id.spinner_deposito2);

        ArrayAdapter<CharSequence> adapter1 = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_list_item_checked, deposit);
        spinner_deposito2.setAdapter(adapter1);

        spinner_deposito2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                codDep = spinner_deposito2.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnInicio.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltarMenu();
            }
        });

        btnCapturarEtiqueta.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUserInteraction();
            }
        });
    }

    public void IniciaComponentes() {
        btnCapturarEtiqueta = findViewById(R.id.btnCapturarEtiqueta);
        btnInicio = findViewById(R.id.btnInicio);
        txtPegaProduto = findViewById(R.id.txtPegaProduto);
        ETCodigoBarrasColetado = (EditText) findViewById(R.id.editTextCaptEtiqueta);
    }

    @Override
    protected void onResume() {
        super.onResume();
        chamaListaDeProduto();
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        codigo = ETCodigoBarrasColetado.getText().toString();
        if (codigo.length() > 3) {
            if (codigo.charAt(codigo.length() - 1) == '$') {
                codPro = txtPegaProduto.getText().toString();
                ETCodigoBarrasColetado.setText("");
                chamada(codigo, usuario);
            }
        }
    }

    private void voltarMenu() {
        Intent intent = new Intent(capturaProduto.this, Menu.class);
        startActivity(intent);
        finish();
    }

    public void chamada(String codigo, String usuario) {
        try {
            if (codPro.isEmpty()) {
                MensagemUsuario.mostrarMensagemParaUsuario("Informação", "O campo produto não pode ser em branco.", "ok", this);
                BeepUtil.bipErro();
                return;
            }

            VerificarSeEtqEDoProduto isRetornoEtqDoProduto = new VerificarSeEtqEDoProduto(this, codigo, codPro);
            Boolean isRetornoEtqDoProdutoselecionada = isRetornoEtqDoProduto.execute().get();

            if (!isRetornoEtqDoProdutoselecionada) {
                MensagemUsuario.mostrarMensagemParaUsuario("Erro na Captura", "Essa etiqueta não é do produto selecionado!" + codigo, "Ok", this);
                BeepUtil.bipErro();
                return;
            }

            VerificarEtiquetaDoProduto isRetornoEtq = new VerificarEtiquetaDoProduto(this, codigo);
            Boolean isRetornoEtqEstoque = isRetornoEtq.execute().get();

            if (!isRetornoEtqEstoque) {
                MensagemUsuario.mostrarMensagemParaUsuario("Erro na Captura", "Essa etiqueta já foi coletada!" + codigo, "Ok", this);
                BeepUtil.bipErro();
                return;
            }

            InserirDadosDaEtq inserirDadosDaEtq = new InserirDadosDaEtq(this, codigo, usuario);
            inserirDadosDaEtq.execute();

            chamaListaDeProduto();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String chamaListaDeProduto() {
        try {
            RecebeListaProdutos recebeListaProdutos = new RecebeListaProdutos(this);
            List<Produto> RetornalistaProduto = recebeListaProdutos.execute().get();

            for (Produto produto : RetornalistaProduto) {
                QtdEstoqueSistema qtdEstoqueSistema = new QtdEstoqueSistema(this, produto.getCodPro(), codDep);
                double qtdEstoque = qtdEstoqueSistema.execute().get();
                produto.setQtdEst(qtdEstoque);
            }

            recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            ProdutoAdapter adapter = new ProdutoAdapter(RetornalistaProduto, this);
            recyclerView.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public void abrirDeListaItensProduto(String codPro) {
        Intent intent = new Intent(capturaProduto.this, lista_itens_etiqueta.class);
        intent.putExtra("codPro", codPro);
        startActivity(intent);
    }

}