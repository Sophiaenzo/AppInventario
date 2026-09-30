package br.example.app;

//import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import util.Myalerta;

public class FormCheck extends AppCompatActivity {

    private static final int SUCESSO = 1;
    private static final int ATENCAO = 2;
    private static final int DANGER = 3;
    private static final int WIFI = 4;
    TextView noInternet;
    ImageView noWifi;
    Context context;
    Myalerta myalerta = new Myalerta();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_check);

        ImageView img = (ImageView) findViewById(R.id.bt_exit);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });


        if(!isConnected(this)){
            //myalerta.fshowtoast(FormCheck.this,DANGER,"ERRO AO CONECTAR AO WIFI, ENTRE EM CONTATO COM DEPTO DE TI");
            }else{
            TelaLogin();
        }
    }

    private boolean isConnected(FormCheck mainActivity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mainActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network nw = connectivityManager.getActiveNetwork();
            if (nw == null) return false;
            NetworkCapabilities actNw = connectivityManager.getNetworkCapabilities(nw);
            return actNw != null && (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH));
        } else {
            NetworkInfo nwInfo = connectivityManager.getActiveNetworkInfo();
            return nwInfo != null && nwInfo.isConnected();
        }
    }


    private void TelaLogin(){
        Intent intent = new Intent(FormCheck.this, FormLogin.class);
        startActivity(intent);
        finish();
    }

}