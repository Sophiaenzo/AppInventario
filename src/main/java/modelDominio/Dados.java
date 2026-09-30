package modelDominio;

/**
 *
 * @author wilson.simoes
 */
import java.io.Serializable;

public class Dados  implements Serializable{
    private String rotina;
    private String data;
    private String deposito;

    public String getRotina() {
        return rotina;
    }

    public void setRotina(String rotina) {
        this.rotina = rotina;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }
    
    
}