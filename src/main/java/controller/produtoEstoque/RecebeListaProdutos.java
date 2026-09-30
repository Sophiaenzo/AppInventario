package controller.produtoEstoque;

import android.content.Context;
import android.os.AsyncTask;
import java.util.List;
import model.Produto;
import controller.webService.ControllerWebService;

public class RecebeListaProdutos extends AsyncTask<Void, Void, List<Produto>> {
    private Context context;

    public RecebeListaProdutos(Context context) {
        this.context = context;
    }

    @Override
    protected List<Produto> doInBackground(Void... params) {
        try {
            return new ControllerWebService().listarProduto();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Produto> itensProduto) {}
}