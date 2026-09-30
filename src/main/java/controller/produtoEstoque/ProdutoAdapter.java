package controller.produtoEstoque;

import android.app.AlertDialog;
import android.bluetooth.BluetoothManager;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.example.app.R;
import br.example.app.capturaProduto;
import model.Produto;
import util.MensagemUsuario;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {

    private static List<Produto> produtos;
    private static capturaProduto activityCapturaProduto;
    public ProdutoAdapter(List<Produto> produtos, capturaProduto activityCapturaProduto) {
        this.produtos = produtos;
        this.activityCapturaProduto = activityCapturaProduto;
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_capturado, parent, false);
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

        private TextView qtdEstTextView;
        private TextView qtdColTextView;
        private TextView codProTextView;
        private ConstraintLayout ConstraintLayoutItemSelected;
        private ImageButton btnExcluiProduto;

        public ProdutoViewHolder(@NonNull View itemView) {
            super(itemView);

            qtdEstTextView = itemView.findViewById(R.id.textViewQuantidadeSistema);
            qtdColTextView = itemView.findViewById(R.id.textViewQuantidadeCap);
            codProTextView = itemView.findViewById(R.id.textViewCodigoPro);
            btnExcluiProduto = itemView.findViewById(R.id.btnExcluiProduto);
            ConstraintLayoutItemSelected =  (ConstraintLayout)itemView.findViewById(R.id.ContraintProdutos);

            btnExcluiProduto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    builder.setMessage("Tem certeza que deseja excluir este produto?")
                            .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    int position = getAdapterPosition();
                                    if (position != RecyclerView.NO_POSITION) {
                                        Produto produto = produtos.get(position);
                                        new ExcluirProduto(itemView.getContext(), produto).execute();

                                        // Chama a função após a exclusão
                                        activityCapturaProduto.chamaListaDeProduto();
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

            ConstraintLayoutItemSelected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Produto produto = produtos.get(position);

                        // Chama a função para abrir a nova tela e passa o codPro
                        activityCapturaProduto.abrirDeListaItensProduto(produto.getCodPro());
                    }
                }
            });
        }

        public void bind(Produto produto) {
            qtdEstTextView.setText(String.format("%.3f", produto.getQtdEst()));
            qtdColTextView.setText(String.format("%.3f", produto.getQtdCol()));
            codProTextView.setText(produto.getCodPro());
        }
    }

}

