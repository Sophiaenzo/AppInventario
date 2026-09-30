package util;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.io.FileReader;
import java.util.Scanner;

import br.example.app.FormLogin;
import br.example.app.R;

public class Funcoes extends AppCompatActivity {
    String arquivo;
    private static final int SUCESSO = 1;
    private static final int ATENCAO = 2;
    private static final int ATENCAO01 = 21;
    private static final int ATENCAO02 = 22;
    private static final int DANGER  = 3;
    private static final int DANGER01 = 31;
    private static final int DANGER02 = 32;
    private static final int DANGER03 = 33;
    private static final int DANGER04 = 34;
    private static final int DANGER05 = 35;
    private static final int DANGER06 = 36;
    private static final int DANGER07 = 37;
    private static final int DANGER08 = 38;


    public static Properties getPropValue() throws FileNotFoundException{
        FileInputStream fp = new FileInputStream("assets/config.properties");
        Properties prop = new Properties();
        try {
            prop.load(fp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return prop;
    }

    public static String getCodigo() throws IOException{
        return getPropValue().getProperty("codigo");
    }

    public static String getUsuario() throws IOException{
        return getPropValue().getProperty("usuario");
    }

    public static String get(URL urlConnect, String Json, String metodo) throws IOException {
        StringBuilder jsonResposta = new StringBuilder();

        HttpURLConnection connection = (HttpURLConnection) urlConnect.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setConnectTimeout(5000);
        connection.connect();

        Scanner scanner = new Scanner(urlConnect.openStream());
        while (scanner.hasNext()) {
            jsonResposta.append(scanner.next());
        }
        return jsonResposta.toString();
    }



    public void AvisoToast(int type, String message) {
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

        //playNotificationSound(arquivo);

    }


    /**
     * class que define o tipo de aviso sonoro ao usuario
     * @param arq - nome da arquivo wav
     */
    public void playNotificationSound(String arq) {
        try {
            Uri som = Uri.parse(arq);
            Ringtone r = RingtoneManager.getRingtone(this, som);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
