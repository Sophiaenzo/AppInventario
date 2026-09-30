package br.example.app;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import br.example.app.databinding.ActivityMenuBinding;


public class Menu extends AppCompatActivity {

    private Button btnInventario;
    private Button btnEstoque;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        IniciaComponentes();

        btnInventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelaPrincipal();
            }
        });

        btnEstoque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturaProduto();
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void IniciaComponentes() {
        btnInventario = findViewById(R.id.buttonInventario);
        btnEstoque = findViewById(R.id.buttonEstoque);
        btnVoltar = findViewById(R.id.buttonVoltar);
    }

    private void TelaPrincipal() {
        Intent intent = new Intent(Menu.this, FormPrincipal.class);
        startActivity(intent);
        finish();
    }


    private void capturaProduto() {
        Intent intent = new Intent(Menu.this, capturaProduto.class);
        startActivity(intent);
        finish();
    }
}