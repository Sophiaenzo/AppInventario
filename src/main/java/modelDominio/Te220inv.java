package modelDominio;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author wilson.simoes
 */
public class Te220inv implements Serializable{
    private static final long serialVersionUID = 123456789L;
    private int codemp;
    private Date datinv;
    private String coddep;
    
    public Te220inv(int codemp, Date datinv, String coddep) {
        this.codemp = codemp;
        this.datinv = datinv;
        this.coddep = coddep;
    }

    
      

    public int getcodemp() {
        return codemp;
    }

    public void setcodemp(int codemp) {
        this.codemp = codemp;
    }

    public Date getdatinv() {
        return datinv;
    }

    public void setdatinv(Date datinv) {
        this.datinv = datinv;
    }

    public String getcoddep() {
        return coddep;
    }

    public void setcoddep(String coddep) {
        this.coddep = coddep;
    }

    @Override
    public String toString() {
        return this.getcoddep();
    }

    
}