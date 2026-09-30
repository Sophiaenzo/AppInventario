package modelDominio;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author wilson.simoes
 */
public class Te220sal implements Serializable{
    private static final long serialVersionUID = 123456789L;
    private String deposito;
    private Float esto;
    private Float certo;
    private Float errado;

    public Te220sal(String deposito, Float esto, Float certo, Float errado) {
        this.deposito = deposito;
        this.esto = esto;
        this.certo = certo;
        this.errado = errado;
    }

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }

    public Float getEsto() {
        return esto;
    }

    public void setEsto(Float esto) {
        this.esto = esto;
    }

    public Float getCerto() {
        return certo;
    }

    public void setCerto(Float certo) {
        this.certo = certo;
    }

    public Float getErrado() {
        return errado;
    }

    public void setErrado(Float errado) {
        this.errado = errado;
    }
   
    
    
    
}