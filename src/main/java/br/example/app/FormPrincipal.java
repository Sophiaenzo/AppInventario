package br.example.app;

import static util.ValidaData.isDataValida;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import java.text.DateFormatSymbols;
import java.util.ArrayList;

import controller.ConexaoController;
import modelDominio.Te220inv;
import util.Myalerta;

public class FormPrincipal extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    InformacoesApp infoApp;
    private static final int SUCESSO = 1;
    private static final int ATENCAO = 2;
    private static final int DANGER = 3;
    private static final int WIFI = 4;
    String arquivo;
    private Spinner spinner_deposito;
    String [] depositos;
    TextView EdtData;
    private Button button_confirmar;
    private int retorno;
    ArrayList<Te220inv> listaDepositos;
    Myalerta myalerta = new Myalerta();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_principal);
        IniciaComponentes();

        EdtData.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String data = EdtData.getText().toString();

                boolean isValidPatten = isDataValida(data);

                if (isValidPatten == true) {
                    int isDados =  populatespinner_deposito(data);
                } else {
                    //showToast(DANGER, "Data invalida");
                    myalerta.fshowtoast(FormPrincipal.this,DANGER,"Data informada invalida");
                }
                return false;
            }
        });
        spinner_deposito.setOnItemSelectedListener(this);

        ImageView img = (ImageView) findViewById(R.id.bt_exit);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                // System.exit();
            }
        });


        button_confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valor = null;
                if (spinner_deposito != null && spinner_deposito.getSelectedItem() != null ) {
                    valor = (String) spinner_deposito.getSelectedItem();
                    Intent it = new Intent(getApplicationContext(),FormLancamentos.class);
                    startActivity(it);
                } else {
                    //showToast(DANGER,"Favor selecionar um deposito ");
                    myalerta.fshowtoast(FormPrincipal.this,ATENCAO,"Favor selecionar um deposito");
                }
            }
        });
    }


    private void IniciaComponentes(){
        infoApp = (InformacoesApp) getApplicationContext();
        EdtData = findViewById(R.id.edit_data);
        spinner_deposito = findViewById(R.id.spinner_deposito);
        button_confirmar = findViewById(R.id.button_confirmar);
        EdtData.requestFocus();
    }




    private int populatespinner_deposito(String mdata) {
            Thread t = new Thread(){
            @Override
            public void run(){
                ConexaoController ccont = new ConexaoController(infoApp);
                ArrayList<Te220inv> listaDepositos = ccont.ListaTe220inv(mdata);
                ArrayList<String> comboDiasList = new ArrayList<String>();
                retorno = listaDepositos.size();
                for(Te220inv f: listaDepositos) {
                     comboDiasList.add(f.getcoddep());
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        retorno = comboDiasList.size();
                        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(FormPrincipal.this,R.layout.style_spinner,comboDiasList);
                        spinner_deposito.setAdapter(adapter);
                        //return retorno;
                    }
                });
                //return retorno;
            }
        };
        t.start();

            return retorno;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent,View view,int position, long id){
        if (parent.getId()==R.id.spinner_deposito){
            String selectedDeposito = parent.getSelectedItem().toString();
            FormLancamentos.mostra(selectedDeposito,EdtData.getText().toString());

            //Toast.makeText(this, "Deposito selecionado " + selectedDeposito, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    private void showToast(int type, String message) {
        ViewGroup view = findViewById(R.id.container_toast);
        View v = getLayoutInflater().inflate(R.layout.activity_custon_toast, view);
        ImageView toastImage = v.findViewById(R.id.toast_image);

        switch (type) {
            case SUCESSO:
                arquivo = "android.resource://"+getPackageName()+"/"+R.raw.success;
                v.setBackground(ContextCompat.getDrawable(this, R.drawable.toast_sucesso));
                toastImage.setImageResource(R.drawable.ic_sucesso_24);
                break;
            case ATENCAO:
                arquivo = "android.resource://"+getPackageName()+"/"+R.raw.atencao;
                v.setBackground(ContextCompat.getDrawable(this, R.drawable.toast_atencao));
                toastImage.setImageResource(R.drawable.ic_atencao_24);
                break;
            case DANGER:
                arquivo = "android.resource://"+getPackageName()+"/"+R.raw.error;
                v.setBackground(ContextCompat.getDrawable(this, R.drawable.toast_error));
                toastImage.setImageResource(R.drawable.ic_erro_24);
                break;
            default:
                throw new IllegalStateException("Erro de tipo " + message);
        }

        TextView toastMessage = v.findViewById(R.id.toast_message);
        toastMessage.setText(message);
        Toast toast = new Toast(this);
        toast.setView(v);
        toast.setDuration(toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        playNotificationSound(arquivo);

    }

    public void playNotificationSound(String arq) {
        try {
            Uri som = Uri.parse(arq);
            Ringtone r = RingtoneManager.getRingtone(FormPrincipal.this, som);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }








}







