package br.example.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

/**
 * class para conexao com web para validar usuario
 * transformar dados recebidos joson em string
 */
public class ConexaoWeb {
    public static String getUsuario(String uri){
        BufferedReader bufferedReader = null;
        String retorna = null;
        try {

            URL url = new URL(uri);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


            System.out.println(url);

            StringBuilder stringBuilder = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                stringBuilder.append(linha + "\n");
            }

            retorna = stringBuilder.substring(0,4);
            //return stringBuilder.toString();
            return retorna;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        } finally {
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}


