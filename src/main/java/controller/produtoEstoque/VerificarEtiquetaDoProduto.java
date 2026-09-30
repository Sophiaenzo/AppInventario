package controller.produtoEstoque;

import android.content.Context;
import android.os.AsyncTask;

import controller.webService.ControllerWebService;
import util.MensagemUsuario;

public class VerificarEtiquetaDoProduto extends AsyncTask<Void, Boolean, Boolean> {
    private Context context;

    String etiqueta;

    public VerificarEtiquetaDoProduto(Context context, String etiqueta) {
        this.context = context;
        this.etiqueta = etiqueta;
    }


    @Override
    protected void onPreExecute() {}

    @Override
    protected Boolean doInBackground(Void... params) {
        Boolean isRetornoEtq = null;
        try {
            isRetornoEtq = new ControllerWebService().verificaEtqEstoque(etiqueta);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isRetornoEtq;
    }

    @Override
    protected void onPostExecute(Boolean retorno) {
    }
}
