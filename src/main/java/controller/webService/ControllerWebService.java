package controller.webService;

import com.google.gson.JsonIOException;

import java.util.List;

import model.Produto;
import model.webservice.ModelWebService;

public class ControllerWebService {

    private ModelWebService chamadaModelWSDL = ModelWebService.getInstancia();

    public double qtdProdutoNoSistema( String codPro, String codDep) {
        double QtdProdutoEstoqueSistema = 0;

        try {
            QtdProdutoEstoqueSistema = Double.parseDouble(chamadaModelWSDL.retornaQtdProdutoNoSistema(codPro, codDep));

            return QtdProdutoEstoqueSistema;

        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return QtdProdutoEstoqueSistema;
    }

    public Boolean inserirDadosEtq(String etiqueta, String usuario) {
        Boolean DadosEtq = null;
        try {
            DadosEtq = chamadaModelWSDL.inserirDadosRetornadoEtq(etiqueta, usuario);
            return DadosEtq;

        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return DadosEtq;
    }

    public Boolean verificaEtqEstoque(String etiqueta) {
        Boolean retornoEtq = null;
        try {
            retornoEtq = chamadaModelWSDL.verificaEtqProdutoEstoque(etiqueta);

            return retornoEtq;
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retornoEtq;
    }

    public Boolean verificaSeEtqEDoProdutoSelecionado(String etiqueta, String codPro) {
        Boolean retornoEtqProdutoSelecionado = null;
        try {
            retornoEtqProdutoSelecionado = chamadaModelWSDL.verificarEtqEDoProdutoSelecionado(etiqueta, codPro);

            return retornoEtqProdutoSelecionado;
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retornoEtqProdutoSelecionado;
    }


    public List<Produto> listarProduto() {
        List<Produto> produtos = chamadaModelWSDL.listarRetornaProdutoEstoque();
        return produtos;
    }

    public String deleteProdutoSelecionado(Produto produto) {
        return chamadaModelWSDL.deleteProdutoCapturado(produto);
    }

    public List<Produto> listarEtqsProduto(Produto produto) {
        List<Produto> produtos = chamadaModelWSDL.retornaEtqsDoProdutoSelecionado(produto);
        return produtos;
    }

    public String deleteEtqSelecionado(Produto produto) {
        return chamadaModelWSDL.deleteEtqCapturado(produto);
    }

}
