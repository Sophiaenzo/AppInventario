package br.example.app;

import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

import controller.ConexaoController;
import modelDominio.Tetref;
import util.Myalerta;

public class FormLancamentos extends AppCompatActivity {
    InformacoesApp infoApp;

    private static final int SUCESSO = 1;
    private static final int ATENCAO = 2;
    private static final int ATENCAO01 = 21;
    private static final int ATENCAO02 = 22;
    private static final int DANGER = 3;
    private static final int DANGER01 = 31;
    private static final int DANGER02 = 32;
    private static final int DANGER03 = 33;
    private static final int DANGER04 = 34;
    private static final int DANGER05 = 35;
    private static final int DANGER06 = 36;
    private static final int DANGER07 = 37;
    private static final int DANGER08 = 38;
    private static final int WIFI = 4;
    String arquivo;
    public static String seleDepo;
    public static String seleData;
    public static String seleMate;
    public static String seleCode;
    private TextView text_titulo1;
    private EditText Edit_etiqueta,Edit_mensagem,Edit_status;
    private EditText Edit_codigo;
    private EditText Edit_lote;
    private EditText Edit_qtdad;
    Myalerta myalerta = new Myalerta();

    ArrayList<Tetref> listaEtiquetas;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_lancamentos);

        IniciaComponentes();

        ImageView img = (ImageView) findViewById(R.id.bt_exit);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });



        Edit_etiqueta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                runner.execute();
            }
        });


    }


    private void IniciaComponentes(){
        infoApp = (InformacoesApp) getApplicationContext();
        Edit_etiqueta = findViewById(R.id.Edit_etiqueta);
        Edit_codigo = findViewById(R.id.Edit_codigo);
        Edit_lote   = findViewById(R.id.Edit_lote);
        Edit_qtdad  = findViewById(R.id.Edit_qtdad);
        text_titulo1 = findViewById(R.id.text_titulo1);
        Edit_mensagem=findViewById(R.id.Edit_mensagem);
        Edit_status  = findViewById(R.id.Edit_status);
        text_titulo1.setText(seleDepo);
        Edit_etiqueta.requestFocus();
    }




    public static void mostra(String selectedDeposito,String qData){
         seleDepo = selectedDeposito;
         seleData = qData;
    }

/*
    public void buscaEtiqueta(String etiqueta){
        Toast.makeText(getApplicationContext(),etiqueta,Toast.LENGTH_LONG).show();
        seleCode = "000";
        if(seleDepo.equals("DEPGERAL") || seleDepo.equals("DEPKANBAN")) {
            seleMate = "MP";
        }else{
            seleMate = "PR";
        }

        Thread t = new Thread(){
            @Override
            public void run(){
                ConexaoController ccont = new ConexaoController(infoApp);
                ArrayList<Tetref> listaEtiquetas = ccont.ListaTetref(seleData,seleDepo,etiqueta,seleMate,seleCode);
                for(Tetref f: listaEtiquetas) {
                    Edit_codigo.setText(f.getCodigo());
                    Edit_lote.setText(f.getLote());
                    Edit_qtdad.setText(f.getQtdade());
                    Edit_status.setText(f.getStatus());
                    Edit_mensagem.setText("Lancamento teste testebb");
                }

            }
        };
        t.start();
        showToast(SUCESSO,Edit_mensagem.getText().toString());

        return;
    }

*/

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
            case ATENCAO01:
                arquivo = "android.resource://"+getPackageName()+"/"+R.raw.atencao;
                v.setBackground(ContextCompat.getDrawable(this, R.drawable.toast_atencao01));
                toastImage.setImageResource(R.drawable.ic_atencao_24);
                break;
            case ATENCAO02:
                arquivo = "android.resource://"+getPackageName()+"/"+R.raw.atencao;
                v.setBackground(ContextCompat.getDrawable(this, R.drawable.toast_atencao02));
                toastImage.setImageResource(R.drawable.ic_atencao_24);
                break;
            case DANGER:
                arquivo = "android.resource://"+getPackageName()+"/"+R.raw.error;
                v.setBackground(ContextCompat.getDrawable(this, R.drawable.toast_error));
                toastImage.setImageResource(R.drawable.ic_erro_24);
                break;
            case DANGER01:
                arquivo = "android.resource://"+getPackageName()+"/"+R.raw.error;
                v.setBackground(ContextCompat.getDrawable(this, R.drawable.toast_danger01));
                toastImage.setImageResource(R.drawable.ic_erro_24);
                break;
            case DANGER02:
                arquivo = "android.resource://"+getPackageName()+"/"+R.raw.error;
                v.setBackground(ContextCompat.getDrawable(this, R.drawable.toast_danger02));
                toastImage.setImageResource(R.drawable.ic_erro_24);
                break;
            case DANGER03:
                arquivo = "android.resource://"+getPackageName()+"/"+R.raw.error;
                v.setBackground(ContextCompat.getDrawable(this, R.drawable.toast_danger03));
                toastImage.setImageResource(R.drawable.ic_erro_24);
                break;
            case DANGER04:
                arquivo = "android.resource://"+getPackageName()+"/"+R.raw.error;
                v.setBackground(ContextCompat.getDrawable(this, R.drawable.toast_danger04));
                toastImage.setImageResource(R.drawable.ic_erro_24);
                break;
            case DANGER05:
                arquivo = "android.resource://"+getPackageName()+"/"+R.raw.error;
                v.setBackground(ContextCompat.getDrawable(this, R.drawable.toast_danger05));
                toastImage.setImageResource(R.drawable.ic_erro_24);
                break;
            case DANGER06:
                arquivo = "android.resource://"+getPackageName()+"/"+R.raw.error;
                v.setBackground(ContextCompat.getDrawable(this, R.drawable.toast_danger06));
                toastImage.setImageResource(R.drawable.ic_erro_24);
                break;
            case DANGER07:
                arquivo = "android.resource://"+getPackageName()+"/"+R.raw.error;
                v.setBackground(ContextCompat.getDrawable(this, R.drawable.toast_danger07));
                toastImage.setImageResource(R.drawable.ic_erro_24);
                break;
            case DANGER08:
                arquivo = "android.resource://"+getPackageName()+"/"+R.raw.error;
                v.setBackground(ContextCompat.getDrawable(this, R.drawable.toast_danger08));
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
            Ringtone r = RingtoneManager.getRingtone(FormLancamentos.this, som);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private class AsyncTaskRunner extends AsyncTask<String, String, ArrayList<String>> {

        private String resp;
        @Override
        protected ArrayList<String> doInBackground(String... string) {
            ArrayList<String>content = new ArrayList<>();
            try {
                String etiqueta = Edit_etiqueta.getText().toString();


                String nome,codigo;
                SharedPreferences prefs = getSharedPreferences("chaveUsuario",MODE_PRIVATE);
                nome = prefs.getString("chaveNome","");
                codigo = prefs.getString("chaveCode","");

                seleCode = codigo;

                if(seleDepo.equals("DEPGERAL") || seleDepo.equals("DEPKANBAN")) {
                    seleMate = "MP";
                }else{
                    seleMate = "PR";
                }
                ConexaoController ccont = new ConexaoController(infoApp);

                ArrayList<Tetref> listaEtiquetas = ccont.ListaTetref(seleData,seleDepo,etiqueta,seleMate,seleCode);

                for(Tetref f: listaEtiquetas) {
                    content.add(0,f.getCodigo());
                    content.add(1,f.getLote());
                    content.add(2,f.getQtdade());
                    content.add(3,f.getStatus());
                    content.add(4,f.getMensagem());
                }

            } catch (Exception e) {
                e.printStackTrace();
                content.add(0,null);
                content.add(1,null);
                content.add(2,null);
                content.add(3,null);
                content.add(4,null);
            }
            return content;
        }


        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            Log.i("parametro 0 ",strings.get(0));
            Log.i("parametro 1 ",strings.get(1));
            Log.i("parametro 2 ",strings.get(2));
            Log.i("parametro 3 ",strings.get(3));
            Log.i("parametro 4 ",strings.get(4));

            Edit_codigo.setText(strings.get(0));
            Edit_lote.setText(strings.get(1));
            Edit_qtdad .setText(strings.get(2));
            Edit_status.setText(strings.get(3));
            Edit_mensagem.setText(strings.get(4));

            if(Edit_status.getText().toString().equals("SUCESSO")) {
                //showToast(SUCESSO, Edit_mensagem.getText().toString());
                myalerta.fshowtoast(FormLancamentos.this,SUCESSO,Edit_mensagem.getText().toString());
            }else if(Edit_status.getText().toString().equals("ATENCAO")) {
                //showToast(ATENCAO, Edit_mensagem.getText().toString());
                myalerta.fshowtoast(FormLancamentos.this,ATENCAO,Edit_mensagem.getText().toString());
            }else{
                //showToast(DANGER, Edit_mensagem.getText().toString());
                myalerta.fshowtoast(FormLancamentos.this,DANGER,Edit_mensagem.getText().toString());
            }
        }

        @Override
        protected void onPreExecute() {}
        @Override
        protected void onProgressUpdate(String... text) {}
    }


}


