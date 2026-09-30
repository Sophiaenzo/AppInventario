package controller;


import android.app.AlertDialog;
import android.widget.Toast;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import br.example.app.InformacoesApp;
import br.example.app.R;
import modelDominio.R999usu;
import modelDominio.Te220inv;
import modelDominio.Tetref;
import util.Operacoes;

/**
 * informacoes da app
 */
public class ConexaoController {
    private static final int SUCESSO = 1;
    private static final int ATENCAO = 2;
    private static final int DANGER = 3;
    private static final int WIFI = 4;
    static InformacoesApp infoApp;
    String retorna = null;

    /**
     * dados de conexao
     * numero de IP
     * numero da porta
     */
    public static boolean Conectar(){
        try {
            //infoApp.socket = new Socket("192.168.0.170",12347);
            infoApp.socket = new Socket("192.168.0.121",12347);
            infoApp.out = new ObjectOutputStream(infoApp.socket.getOutputStream());
            infoApp.in = new ObjectInputStream(infoApp.socket.getInputStream());
            return true;
        }catch(Exception e){
            System.out.println("ERRO AO CONECTAR "+e.getMessage());

            return false;

        }
    }

    public ConexaoController(InformacoesApp infoApp){
        this.infoApp = infoApp;
    }

    public ArrayList<Te220inv> ListaTe220inv(String mData){
        try
        {
            Operacoes m = new Operacoes("enchecombo");
            m.setParam("data",mData);
            m.setParam("tipo","Preencher Combo");
            infoApp.out.writeObject(m);
            infoApp.out.flush();
            ArrayList<Te220inv> Dadoslista = (ArrayList<Te220inv>)  infoApp.in.readObject();
            return Dadoslista;

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public ArrayList<Tetref> ListaTetref(String seleData,String seleDepo,String seleEtiqueta,String seleMate, String seleCode){
        try
        {
            Operacoes m = new Operacoes("leretiqueta");
            m.setParam("data",seleData);
            m.setParam("deposito",seleDepo);
            m.setParam("codigo",seleEtiqueta);
            m.setParam("tipo",seleMate);
            m.setParam("codusu",seleCode);

            infoApp.out.writeObject(m);
            infoApp.out.flush();
            infoApp.out.reset();
            ArrayList<Tetref> Dadoslista = (ArrayList<Tetref>)  infoApp.in.readObject();
            return Dadoslista;

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public ArrayList<R999usu> ListaR999usu(String mData){
        try
        {
            Operacoes m = new Operacoes("verificausuario");
            m.setParam("data",mData);
            m.setParam("tipo","Pegar codigo do usuario");
            infoApp.out.writeObject(m);
            infoApp.out.flush();

            ArrayList<R999usu> Dadoslista = (ArrayList<R999usu>)  infoApp.in.readObject();
            return Dadoslista;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String versaoAtual(){
                try
                {
                    Operacoes m = new Operacoes("versaoatual");
                    m.setParam("data",null);
                    m.setParam("tipo","Verificar versao");
                    infoApp.out.writeObject(m);
                    infoApp.out.flush();

                    String mVersao = (String) infoApp.in.readObject();
                    return mVersao;
                }catch(Exception e){
                    System.out.println("Erro ao conectar -> " + e.getMessage());
                }
        return retorna;
    }


    public String versaoPath(){
        try
        {
            Operacoes m = new Operacoes("versaopath");
            m.setParam("data",null);
            m.setParam("tipo","Verificar path de download versao");
            infoApp.out.writeObject(m);
            infoApp.out.flush();

            String mVersao = (String) infoApp.in.readObject();
            return mVersao;
        }catch(Exception e){
            System.out.println("Erro ao conectar -> " + e.getMessage());
        }
        return retorna;
    }




}
