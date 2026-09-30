package model;

import java.io.Serializable;
import java.util.Objects;

public class Produto implements Serializable {

    private String codPro;
    private String etiqueta;
    private String codDep;
    private double qtdCol;
    private String usuCap;
    private double qtdEst;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Produto produto = (Produto) o;
//        return Objects.equals(codPro, produto.codPro);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(codPro);
//    }

    public String toString() {
        return "Etiqueta: " + etiqueta + ", Quantidade: " + qtdCol + ", Código do Produto: " + codPro;
    }
    public String getCodPro() {
        return codPro;
    }

    public void setCodPro(String codPro) {
        this.codPro = codPro;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getCodDep() {
        return codDep;
    }

    public void setCodDep(String codDep) {
        this.codDep = codDep;
    }

    public String getUsuCap() {
        return usuCap;
    }

    public void setUsuCap(String usuCap) {
        this.usuCap = usuCap;
    }

    public double getQtdCol() {
        return qtdCol;
    }

    public void setQtdCol(double qtdCol) {
        this.qtdCol = qtdCol;
    }

    public double getQtdEst() {
        return qtdEst;
    }

    public void setQtdEst(double qtdEst) {
        this.qtdEst = qtdEst;
    }

}
