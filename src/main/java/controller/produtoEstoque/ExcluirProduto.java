package controller.produtoEstoque;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import controller.webService.ControllerWebService;
import model.Produto;

public class ExcluirProduto extends AsyncTask<Void, Void, String> {
    private Context context;
    private ProgressDialog load;
    private Produto produto;

    public ExcluirProduto(Context context, Produto produto){
        this.context = context;
        this.produto = produto;
    }

    @Override
    protected void onPreExecute() {
        load = ProgressDialog.show(context, "Por favor Aguarde",
                "Realizando a exclusão...");
    }

    @Override
    protected String doInBackground(Void... voids) {
        try{
            return new ControllerWebService().deleteProdutoSelecionado(produto);
        }catch(Exception e){
            e.printStackTrace();
            return "ERRO, "+e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        load.dismiss();
    }
}
