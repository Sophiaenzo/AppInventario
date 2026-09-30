package br.example.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

import controller.ConexaoController;
import modelDominio.R999usu;
import util.Myalerta;


/**
 * Tela principal do sistema
 */
public class FormLogin extends AppCompatActivity {

    public String codusu;
    String arquivo;
    private static final int SUCESSO = 1;
    private static final int ATENCAO = 2;
    private static final int DANGER = 3;
    private static final int WIFI = 4;
    private EditText edit_login;
    private EditText edit_senha;
    private EditText edit_verifica;
    TextView versionname;
    InformacoesApp infoApp;
    String bSer = "";
    public static String vPath = "";

    ConexaoController ccont;
    String retorna;
    boolean mconecta;
    // Funcoes funcoes = new Funcoes();
    Myalerta myalerta = new Myalerta();

    /*
        @Override
        protected void onResume(){
            super.onResume();
            versionname = findViewById(R.id.text_version);
            versionname.setText(BuildConfig.VERSION_NAME);
            new MyTask().execute("vAtual");
        }
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formlogin_main);

        versionname = findViewById(R.id.text_version);
        versionname.setText(BuildConfig.VERSION_NAME);

        infoApp = (InformacoesApp) getApplicationContext();

        Thread t = new Thread(() -> {
            //ConexaoController ccont = new ConexaoController(infoApp);
            ccont = new ConexaoController(infoApp);
            mconecta = ccont.Conectar();
        });
        t.start();

        /*if (!mconecta) {
            myalerta.fshowtoast(this,WIFI,"SEM CONEXAO COM WIFI - ENTRE EM CONTATO COM O DEPTO DE TI");
        }*/


        this.edit_login = findViewById(R.id.Edit_login);
        this.edit_senha = findViewById(R.id.Edit_senha);
        this.edit_verifica = findViewById(R.id.Edit_verifica);
        Button bt_confirmar = findViewById(R.id.button_confirmar);

        ImageView img = (ImageView) findViewById(R.id.bt_exit);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });

        ImageView imgeye = (ImageView) findViewById(R.id.bt_eye);
        imgeye.setImageResource(R.drawable.ic_eye_off_24);
        imgeye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_senha.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {
                    edit_senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    imgeye.setImageResource(R.drawable.ic_eye_off_24);
                } else {
                    edit_senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imgeye.setImageResource(R.drawable.ic_eye_24);
                }
            }
        });


        bt_confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tarefa tarefa = new Tarefa();
                tarefa.execute("http://192.168.0.154:8080/sapiensserver/conector?SIS=CO&LOGIN=SID&ACAO=EXESENHA&NOMUSU=" + edit_login.getText() + "&SENUSU=" + edit_senha.getText().toString());
                //verificaUsuario();
            }
        });
    }


    /**
     * Class efetua tarefa executar api
     */
    private class Tarefa extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... string) {
            return ConexaoWeb.getUsuario(string[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            edit_verifica.setText(s);
            verificaUsuario();
        }
    }


    /**
     * Class que verifica se e necessario efetuar update da versao do app
     * de acordo com o numero da versao
     */

    private class MyTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... params) {

            ConexaoController ccont = new ConexaoController(infoApp);
            //ccont.Conectar();
            vPath = ccont.versaoPath();
            retorna = ccont.versaoAtual();

            return retorna;
        }

        @Override
        protected void onPostExecute(String result) {
            //super.onPostExecute(result);
            bSer = result;
            bSer = bSer.replace(".", "");
            String bApp = versionname.getText().toString().replace(".", "");

            int numeroApp = Integer.parseInt(bApp);
            int numeroSer = Integer.parseInt(bSer);

            System.out.println("na class onPostExecute " + vPath);
            System.out.println("na class onPostExecute " + result);
            System.out.println("na class onPostExecute " + versionname.getText().toString());

            if (numeroApp < numeroSer) {
                TelaUpdate();
            }
        }
    }


    /**
     * class que vericar o codigo do usuario
     * apos o retorno de validacao de usuario
     * com utilizacao de api
     */
    private void verificaUsuario() {
        if (edit_verifica.getText().toString().equals("ERRO")) {
            //showToast(DANGER,"LOGIN INVALIDO");
            myalerta.fshowtoast(FormLogin.this, DANGER, "LOGIN INVALIDO");
            edit_login.requestFocus();
        } else {
            String nomeusuario = edit_login.getText().toString();
            pegausuario(nomeusuario);
            Menu();
            //TelaPrincipal();
        }
    }

    /**
     * class que busca o codigo do usuario conectado
     * esse codigo e utilizado na gravacao dos lancamentos
     *
     * @param nomeusuario
     */

    private void pegausuario(String nomeusuario) {
        System.out.println("pegausuario 01 " + nomeusuario);
        Thread t = new Thread() {
            @Override
            public void run() {
                System.out.println("pegausuario 02 ");
                ConexaoController ccont = new ConexaoController(infoApp);
                ArrayList<R999usu> lista = ccont.ListaR999usu(nomeusuario);
                for (R999usu f : lista) {
                    codusu = f.getCODUSU();
                    System.out.println("pegausuario 02 " + codusu);
                    SharedPreferences pref = getSharedPreferences("chaveUsuario", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("chaveNome", edit_login.getText().toString());
                    editor.putString("chaveCode", f.getCODUSU());
                    editor.commit();
                }
                //return null;
            }
        };
        t.start();
    }


    /**
     * Inicia a Menu
     */
    private void Menu() {
        Intent intent = new Intent(FormLogin.this, Menu.class);
        startActivity(intent);
        finish();
    }

//    private void TelaPrincipal(){
//         Intent intent = new Intent(FormLogin.this,FormPrincipal.class);
//         startActivity(intent);
//         finish();
//     }

    private void TelaUpdate() {
        Intent intent = new Intent(FormLogin.this, FormUpdate.class);
        startActivity(intent);
        finish();
    }


    /**
     * class que mostra mensagem ao usuario
     *
     * @param type    tipo de mensagem SUCESSO ATENCAO OU DANGER
     * @param message TEXTO DA MENSAGEM
     */

    private void showToast(int type, String message) {
        ViewGroup view = findViewById(R.id.container_toast);
        View v = getLayoutInflater().inflate(R.layout.activity_custon_toast, view);
        ImageView toastImage = v.findViewById(R.id.toast_image);

        switch (type) {
            case SUCESSO:
                arquivo = "android.resource://" + getPackageName() + "/" + R.raw.success;
                v.setBackground(ContextCompat.getDrawable(this, R.drawable.toast_sucesso));
                toastImage.setImageResource(R.drawable.ic_sucesso_24);
                break;
            case ATENCAO:
                arquivo = "android.resource://" + getPackageName() + "/" + R.raw.atencao;
                v.setBackground(ContextCompat.getDrawable(this, R.drawable.toast_atencao));
                toastImage.setImageResource(R.drawable.ic_atencao_24);
                break;
            case DANGER:
                arquivo = "android.resource://" + getPackageName() + "/" + R.raw.error;
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

    /**
     * class que define o tipo de aviso sonoro ao usuario
     *
     * @param arq - nome da arquivo wav
     */
    public void playNotificationSound(String arq) {
        try {
            Uri som = Uri.parse(arq);
            Ringtone r = RingtoneManager.getRingtone(FormLogin.this, som);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}