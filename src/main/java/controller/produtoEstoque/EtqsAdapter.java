package controller.produtoEstoque;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.example.app.R;
import br.example.app.lista_itens_etiqueta;
import model.Produto;

public class EtqsAdapter extends RecyclerView.Adapter<EtqsAdapter.ProdutoViewHolder> {

    private static List<Produto> produtos;
    private static lista_itens_etiqueta activityItensEtiqueta;
    public EtqsAdapter(List<Produto> produtos, lista_itens_etiqueta activityItensEtiqueta) {
        this.produtos = produtos;
        this.activityItensEtiqueta = activityItensEtiqueta;
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_etq_capturado, parent, false);
        return new ProdutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        Produto produto = produtos.get(position);
        holder.bind(produto);
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public static class ProdutoViewHolder extends RecyclerView.ViewHolder {

        private TextView etiquetaTextView;
        private TextView qtdColTextView;
        private TextView codProTextView;
       // private TextView usuarioTextView;
        private ImageButton btnExcluiEtq;

        public ProdutoViewHolder(@NonNull View itemView) {
            super(itemView);

            etiquetaTextView = itemView.findViewById(R.id.textViewEtiqueta);
            qtdColTextView = itemView.findViewById(R.id.textViewQuantidadeCapEtq);
            codProTextView = itemView.findViewById(R.id.textViewCodigoProEtq);
           // usuarioTextView = itemView.findViewById(R.id.textViewUsuario);

            btnExcluiEtq = itemView.findViewById(R.id.btnExcluiProdutoteste);

            btnExcluiEtq.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    builder.setMessage("Tem certeza que deseja excluir essa etiqueta?")
                            .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    int position = getAdapterPosition();
                                    if (position != RecyclerView.NO_POSITION) {
                                        Produto produto = produtos.get(position);
                                        new ExcluirEtqs(itemView.getContext(), produto).execute();

                                        // Chama a função após a exclusão
                                        activityItensEtiqueta.chamaListaDEtqPorProduto();
                                    }
                                }
                            })
                            .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });
                    builder.create().show();
                }
            });

        }

        public void bind(Produto produto) {
            etiquetaTextView.setText(String.valueOf(produto.getEtiqueta()));
            qtdColTextView.setText(String.format("%.3f", produto.getQtdCol()));
            codProTextView.setText(produto.getCodPro());
           // usuarioTextView.setText(produto.getUsuCap());
        }
    }

}