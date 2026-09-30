package controller.produtoEstoque;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import controller.webService.ControllerWebService;

public class QtdEstoqueSistema extends AsyncTask<Void, Void, Double> {
    private Context context;
    String codPro;
    String codDep;
    private ProgressDialog load;

    public QtdEstoqueSistema(Context context, String codPro, String codDep) {
        this.context = context;
        this.codPro = codPro;
        this.codDep = codDep;
    }

    @Override
    protected void onPreExecute() {
        load = ProgressDialog.show(context, "Por favor Aguarde",
                "Realizando a busca da quantidade do estoque...");

    }

    @Override
    protected Double doInBackground(Void... params) {
        double qtdEst = 0;
        try {
            qtdEst = new ControllerWebService().qtdProdutoNoSistema(codPro, codDep);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qtdEst;
    }

    @Override
    protected void onPostExecute(Double retorno) {
        load.dismiss();
    }
}
