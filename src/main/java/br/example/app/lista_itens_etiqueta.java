package br.example.app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import controller.produtoEstoque.EtqsAdapter;
import controller.produtoEstoque.RetornaListaDeProdutosEtq;
import model.Produto;

public class lista_itens_etiqueta extends AppCompatActivity {

    private Button btnInicio;
    private RecyclerView recyclerView;
    String codPro;
    private TextView totalItemsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_itens_etiqueta);

        Intent intent = getIntent();
        codPro = intent.getStringExtra("codPro");

        IniciaComponentes();

        totalItemsTextView = findViewById(R.id.totalItemsTextView);

        btnInicio.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltarTelaCapturaProduto();
            }
        });

    }

    public void IniciaComponentes() {
        btnInicio = findViewById(R.id.btnInicioEtq1);
    }


    @Override
    protected void onResume() {
        super.onResume();
        chamaListaDEtqPorProduto();

        if (recyclerView.getAdapter() != null) {
            int totalItens = recyclerView.getAdapter().getItemCount();
            totalItemsTextView.setText("Total de Itens: " + totalItens);
        }
    }

    private void voltarTelaCapturaProduto() {
        Intent intent = new Intent(lista_itens_etiqueta.this, capturaProduto.class);
        startActivity(intent);
        finish();
    }

    public String chamaListaDEtqPorProduto() {
        try {
            Produto produto = new Produto();
            produto.setCodPro(codPro);
            RetornaListaDeProdutosEtq retornaListaDeProdutosEtq = new RetornaListaDeProdutosEtq(this, produto);
            List<Produto> retornalistaEtqsProdutoSelecionado = retornaListaDeProdutosEtq.execute().get();

            recyclerView = findViewById(R.id.recyclerViewEtq);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            EtqsAdapter adapter = new EtqsAdapter(retornalistaEtqsProdutoSelecionado, this);
            recyclerView.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}