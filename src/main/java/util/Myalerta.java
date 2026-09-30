package util;

import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.example.app.R;

//import br.teste.custontoast.R;

public class Myalerta extends AppCompatActivity {
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
    int tempo;
    String arquivo;
    ViewGroup view;
    private Context context;
    MediaPlayer player;
    public void fshowtoast(Context context, int type, String mensagem) {
        Dialog dialog;
        dialog = new Dialog(context);
        TextView texto;
        this.context = context;

        switch (type) {
            case SUCESSO:
                tempo=2500;
                arquivo = "android.resource://"+context.getPackageName()+"/"+ R.raw.error;
                dialog.setContentView(R.layout.toast_sucesso);
                texto = dialog.findViewById(R.id.texto_sucesso2);
                texto.setText(mensagem);
                dialog.show();

                player = MediaPlayer.create(context,R.raw.success);
                player.start();

                break;
            case ATENCAO:
                tempo=2700;
                dialog.setContentView(R.layout.toast_atencao);
                texto = dialog.findViewById(R.id.texto_atencao2);
                texto.setText(mensagem);
                dialog.show();
                player = MediaPlayer.create(context,R.raw.atencao);
                player.start();
                break;
            case DANGER:
                tempo=2700;
                dialog.setContentView(R.layout.toast_erro);
                texto = dialog.findViewById(R.id.texto_erro2);
                texto.setText(mensagem);
                dialog.show();
                player = MediaPlayer.create(context,R.raw.error);
                player.start();
                break;
            case WIFI:
                tempo=4000;
                dialog.setContentView(R.layout.toast_internet);
                texto = dialog.findViewById(R.id.texto_internet2);
                texto.setText(mensagem);
                dialog.show();
                player = MediaPlayer.create(context,R.raw.atencao);
                player.start();
                break;
            default:
                throw new IllegalStateException("Erro de tipo ");
        }


        Handler handler = new Handler();
        long delay = tempo; // tempo de delay em millisegundos
        handler.postDelayed(new Runnable() {
            public void run() {

                dialog.dismiss();
                if (tempo == 4000) {
                    moveTaskToBack(true);
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            }
        }, delay);


    }

    /**
     * class que define o tipo de aviso sonoro ao usuario
     * @param arq - nome da arquivo wav
     */
    public void playNotificationSound(String arq) {
        try {
            Uri som = Uri.parse(arq);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), som);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
