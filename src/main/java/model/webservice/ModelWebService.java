package model.webservice;

import org.json.JSONArray;
import org.json.JSONException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.net.SocketTimeoutException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import model.Produto;


public class ModelWebService {
    private static final String NAMESPACE = "http://mm.com.br/";// diretorio informacaoEstoque

    public final static String URL = "http://192.168.0.154:8080/WebBaseTeste/ProdutoDoEstoque?wsdl";// hefesto - producao

    // public final static String URL = "http://192.168.0.154:8080/WebBaseTesteHomol/ProdutoDoEstoque?wsdl";// hefesto - Homologação

    //public final static String URL = "http://192.168.0.34:8080/WebBaseTesteHomol/ProdutoDoEstoque?wsdl"; // local - Desenvolvimento

    private static final String WSQTDPRODUTONOSISTEMA = "qtdProdutoNoSistema";
    private static final String WSINSERIRDADOSRETORNADOETQ = "inserirDadosRetornadoEtq";
    private static final String WSVERIFICAETQESTOQUE = "verificaEtgEstoque";
    private static final String WSVERIFICARETQPRODUTOSELECIONADO = "verificarEtqEDoProdutoSelecionado";
    private static final String WSLISTARETORNAPRODUTOESTOQUE = "listarRetornaProdutoEstoque";
    private static final String WSDELETEPRODUTOCAPTURADO = "deleteProdutoCapturado";
    private static final String WSRETORNAETQSDOPRODUTOSELECIONADO = "retornaEtqsDoProdutoSelecionado";
    private static final String WSDELETEETQCAPTURADO = "deleteEtqCapturado";
    private static ModelWebService instancia = null;

    public static ModelWebService getInstancia() {

        if (instancia == null)
            instancia = new ModelWebService();

        return instancia;
    }

    public String retornaQtdProdutoNoSistema(String codPro, String codDep) throws NullPointerException, JSONException {
        SoapObject soapDados = new SoapObject(NAMESPACE, WSQTDPRODUTONOSISTEMA);
        soapDados.addProperty("codPro", codPro);
        soapDados.addProperty("codDep", codDep);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(110);
        envelope.setOutputSoapObject(soapDados);
        envelope.implicitTypes = true;
        String retorno = "";
        try {
            new HttpTransportSE(URL, 60000).call("urn:qtdProdutoNoSistema", envelope);
            retorno = String.valueOf(((SoapPrimitive) envelope.getResponse()).toString());
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }

    public Boolean inserirDadosRetornadoEtq(String etiqueta, String usuario) throws NullPointerException, JSONException {
        SoapObject soapDados = new SoapObject(NAMESPACE, WSINSERIRDADOSRETORNADOETQ);
        soapDados.addProperty("etiqueta", etiqueta);
        soapDados.addProperty("usuCap", usuario);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(110);
        envelope.setOutputSoapObject(soapDados);
        envelope.implicitTypes = true;
        Boolean retorno = null;
        try {
            new HttpTransportSE(URL, 60000).call("urn:inserirDadosRetornadoEtq", envelope);
            retorno = Boolean.parseBoolean(((SoapPrimitive) envelope.getResponse()).toString());
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retorno;
    }

    public Boolean verificaEtqProdutoEstoque(String etiqueta) throws NullPointerException, JSONException {
        SoapObject soapDados = new SoapObject(NAMESPACE, WSVERIFICAETQESTOQUE);
        soapDados.addProperty("etiqueta", etiqueta);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(110);
        envelope.setOutputSoapObject(soapDados);
        envelope.implicitTypes = true;
        Boolean retorno = null;
        try {
            new HttpTransportSE(URL, 60000).call("urn:verificaEtgEstoque", envelope);
            retorno = Boolean.parseBoolean(((SoapPrimitive) envelope.getResponse()).toString());
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retorno;
    }

    public Boolean verificarEtqEDoProdutoSelecionado(String etiqueta, String codPro) throws NullPointerException, JSONException {
        SoapObject soapDados = new SoapObject(NAMESPACE, WSVERIFICARETQPRODUTOSELECIONADO);
        soapDados.addProperty("etiqueta", etiqueta);
        soapDados.addProperty("codPro", codPro);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(110);
        envelope.setOutputSoapObject(soapDados);
        envelope.implicitTypes = true;
        Boolean retorno = null;
        try {
            new HttpTransportSE(URL, 60000).call("urn:verificarEtqEDoProdutoSelecionado", envelope);
            retorno = Boolean.parseBoolean(((SoapPrimitive) envelope.getResponse()).toString());
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }

    public List<Produto> listarRetornaProdutoEstoque() {
        SoapObject soapDados = new SoapObject(NAMESPACE, WSLISTARETORNAPRODUTOESTOQUE);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(110);
        envelope.setOutputSoapObject(soapDados);
        envelope.implicitTypes = true;
        try {
            new HttpTransportSE(URL, 60000).call("urn:listarRetornaProdutoEstoque", envelope);
            String retornojson = String.valueOf(((SoapPrimitive) envelope.getResponse()).toString());
            if (retornojson.contains("ERRO")) {
                List<Produto> listaProdutos = new ArrayList<>();
                Produto produto = new Produto();
                listaProdutos.add(produto);
                return listaProdutos;
            }

            List<Produto> listaProdutos = new ArrayList<>();
            JSONArray array;

            try {
                array = new JSONArray(retornojson);
                for (int i = 0; i < array.length(); i++) {
                    Produto produto = new Produto();
                    produto.setEtiqueta(array.getJSONObject(i).getString("etiqueta"));
                    produto.setQtdCol(array.getJSONObject(i).getDouble("qtdCol"));
                    produto.setCodPro(array.getJSONObject(i).getString("codPro"));

                    boolean produtoExistente = false;

                    for (Produto item : listaProdutos) {
                        if (item.getCodPro().equals(produto.getCodPro())) {
                            item.setQtdCol(item.getQtdCol() + produto.getQtdCol());
                            produtoExistente = true;
                            break;
                        }
                    }

                    if (!produtoExistente) {
                        listaProdutos.add(produto);
                    }
                }
                return listaProdutos;
            } catch (JSONException e) {
                e.printStackTrace();
                System.out.println("Erro ao processar o JSON");
            }
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public String deleteProdutoCapturado(Produto produto) {
        SoapObject soapDados = new SoapObject(NAMESPACE, WSDELETEPRODUTOCAPTURADO);
        soapDados.addProperty("codPro", produto.getCodPro());
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soapDados);
        envelope.implicitTypes = true;
        String retornojson = "";
        try {
            new HttpTransportSE(URL, 600000000).call("urn:deleteProdutoCapturado", envelope);
            retornojson = String.valueOf(((SoapPrimitive) envelope.getResponse()).toString());
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            return "ERRO, Problema de conexão, conexão instavel.";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERRO, Problema interno Android.";
        }
        return retornojson;
    }

    public List<Produto> retornaEtqsDoProdutoSelecionado(Produto produto) {
        SoapObject soapDados = new SoapObject(NAMESPACE, WSRETORNAETQSDOPRODUTOSELECIONADO);
        soapDados.addProperty("codPro", produto.getCodPro());
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(110);
        envelope.setOutputSoapObject(soapDados);
        envelope.implicitTypes = true;
        try {
            new HttpTransportSE(URL, 60000).call("urn:retornaEtqsDoProdutoSelecionado", envelope);
            String retornojson = String.valueOf(((SoapPrimitive) envelope.getResponse()).toString());
            if (retornojson.contains("ERRO")) {
                List<Produto> listaProdutos = new ArrayList<>();
                listaProdutos.add(produto);
                return listaProdutos;
            }

            List<Produto> listaProdutos = new ArrayList<>();
            JSONArray array;

            try {
                array = new JSONArray(retornojson);
                for (int i = 0; i < array.length(); i++) {
                    Produto produtos = new Produto();
                    produtos.setEtiqueta(array.getJSONObject(i).getString("etiqueta"));
                    produtos.setQtdCol(array.getJSONObject(i).getDouble("qtdCol"));
                    produtos.setCodPro(array.getJSONObject(i).getString("codPro"));

                    listaProdutos.add(produtos);
                }

                return listaProdutos;
            } catch (JSONException e) {
                e.printStackTrace();
                System.out.println("Erro ao processar o JSON");
            }
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public String deleteEtqCapturado(Produto produto) {
        SoapObject soapDados = new SoapObject(NAMESPACE, WSDELETEETQCAPTURADO);
        soapDados.addProperty("etiqueta", produto.getEtiqueta());
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soapDados);
        envelope.implicitTypes = true;
        String retornojson = "";
        try {
            new HttpTransportSE(URL, 600000000).call("urn:deleteEtqCapturado", envelope);
            retornojson = String.valueOf(((SoapPrimitive) envelope.getResponse()).toString());
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            return "ERRO, Problema de conexão, conexão instavel.";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERRO, Problema interno Android.";
        }
        return retornojson;
    }

}
