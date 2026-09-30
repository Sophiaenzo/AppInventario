package controller.produtoEstoque;

import android.content.Context;
import android.os.AsyncTask;

import controller.webService.ControllerWebService;

public class VerificarSeEtqEDoProduto extends AsyncTask<Void, Boolean, Boolean> {
    private Context context;

    String etiqueta;

    String codPro;

    public VerificarSeEtqEDoProduto(Context context, String etiqueta, String codPro) {
        this.context = context;
        this.etiqueta = etiqueta;
        this.codPro = codPro;
    }


    @Override
    protected void onPreExecute() {}

    @Override
    protected Boolean doInBackground(Void... params) {
        Boolean isRetornoEtqDoProduto = null;
        try {
            isRetornoEtqDoProduto = new ControllerWebService().verificaSeEtqEDoProdutoSelecionado(etiqueta, codPro);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isRetornoEtqDoProduto;
    }

    @Override
    protected void onPostExecute(Boolean retorno) {}
}
