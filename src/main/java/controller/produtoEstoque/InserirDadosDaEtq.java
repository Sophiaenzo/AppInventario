package controller.produtoEstoque;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import controller.webService.ControllerWebService;
import util.BeepUtil;
import util.MensagemUsuario;

public class InserirDadosDaEtq extends AsyncTask<Void, Void, Boolean> {
    private Context context;

    String etiqueta;

    String usuario;
    private ProgressDialog load;

    public InserirDadosDaEtq(Context context, String etiqueta, String usuario) {
        this.context = context;
        this.etiqueta = etiqueta;
        this.usuario = usuario;
    }

    @Override
    protected void onPreExecute() {
        load = ProgressDialog.show(context, "Por favor Aguarde",
                "Inserindo etiqueta...");
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        Boolean isRetornoInserirEtq = null;
        try {
            isRetornoInserirEtq = new ControllerWebService().inserirDadosEtq(etiqueta, usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isRetornoInserirEtq;
    }

    @Override
    protected void onPostExecute(Boolean retorno) {
         load.dismiss();

        if (retorno  != null && retorno.booleanValue()) {
            MensagemUsuario.mensagemTemporariaParaUsuario("Etiqueta inserida com sucesso!", context);
          //// Chamando o bip de sucesso
          //   BeepUtil.bipSucesso();
        } else {
            MensagemUsuario.mostrarMensagemParaUsuario("Erro", "Erro ao inserir a etiqueta. " + etiqueta, "ok", context);
            BeepUtil.bipErro();
        }
    }
}
