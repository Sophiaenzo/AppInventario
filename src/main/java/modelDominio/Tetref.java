/*
 * The MIT License
 *
 * Copyright 2023 wilson.simoes.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package modelDominio;

import java.io.Serializable;

/**
 *
 * @author wilson.simoes
 */
public class Tetref implements Serializable{
    private static final long serialVersionUID = 123456789L;
    private String USU_SEQUNI;
    private String codigo;
    private String lote;
    private String qtdade;
    private String status;
    private String mensagem;

    public Tetref(String USU_SEQUNI, String codigo, String lote, String qtdade, String status, String mensagem) {
        this.USU_SEQUNI = USU_SEQUNI;
        this.codigo = codigo;
        this.lote = lote;
        this.qtdade = qtdade;
        this.status = status;
        this.mensagem = mensagem;
    }

    public String getUSU_SEQUNI() {
        return USU_SEQUNI;
    }

    public void setUSU_SEQUNI(String USU_SEQUNI) {
        this.USU_SEQUNI = USU_SEQUNI;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getQtdade() {
        return qtdade;
    }

    public void setQtdade(String qtdade) {
        this.qtdade = qtdade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }



}
