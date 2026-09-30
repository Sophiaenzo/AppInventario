package br.example.app;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_MEDIA_IMAGES;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class FormUpdate extends AppCompatActivity {
    private static final int PERMISSION_STORAGE_CODE = 	1000;
    private ProgressBar progressDialog;

    String URL = "http://192.168.0.8:81/app_versao/coleta.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_update);

        URL = FormLogin.vPath;

        ActivityCompat.requestPermissions(this,
                new String[]{READ_MEDIA_IMAGES,WRITE_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);

        progressDialog = findViewById(R.id.progressBar);
        ImageView imageview = findViewById(R.id.download);
        Glide.with(this).asGif().load(R.raw.download).into(imageview);

        Button bt_confirmar = (Button) findViewById(R.id.button_confirmar);
        ImageView img_exit = (ImageView) findViewById(R.id.bt_exit_black);
        img_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });

        bt_confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadFile().execute(URL);
            }
        });
    }


    private class DownloadFile extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            exibirProgress();
        }


        @Override
        protected String doInBackground(String... strings) {

            try {
                java.net.URL url = new URL(strings[0]);
                URLConnection connection = url.openConnection();
                connection.connect();

                int fileLength = connection.getContentLength();

                String filePath = String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));

                InputStream input = new BufferedInputStream(url.openStream());
                OutputStream output = new FileOutputStream(filePath + "/" + "coleta.apk");

                System.out.println("1 path " + filePath);

                byte data[] = new byte[1024];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }
                output.flush();
                output.close();
                input.close();


            } catch (MalformedURLException e) {
            } catch (IOException e) {

            }
            return null;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }


        //android:layout_height="50dp"
        public void exibirProgress() {
            int min = 0;
            int max = 100;
            progressDialog.setMax(min);
            progressDialog.setMax(max);
            progressDialog.setVisibility(View.VISIBLE);
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.setVisibility(View.GONE);

            AlertDialog.Builder builder= new AlertDialog.Builder(FormUpdate.this);
            builder.setTitle("UPDATE APP");
            builder.setMessage("Download efetuado com sucesso");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    moveTaskToBack(true);
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            });
            builder.show();
        }
    }

    }