package controller.produtoEstoque;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import br.example.app.lista_itens_etiqueta;
import controller.webService.ControllerWebService;
import model.Produto;

public class RetornaListaDeProdutosEtq extends AsyncTask<Void, Void, List<Produto>> {
    private Context context;
    private Produto produto;

    public RetornaListaDeProdutosEtq(Context context, Produto produto) {
        this.context = context;
        this.produto = produto;
    }

    @Override
    protected List<Produto> doInBackground(Void... params) {
        try {
            return new ControllerWebService().listarEtqsProduto(produto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Produto> itensEtqProduto) {}
}