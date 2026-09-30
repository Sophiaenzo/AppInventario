package modelDominio;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author wilson
 */
public class Te220con implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private int USU_CODEMP;
    private Date USU_DATINV;
    private String USU_CODDEP;
    private String USU_CODPRO;
    private String USU_CODDER;
    private int USU_NUMCON;
    private float USU_QTDCON;
    private int USU_USUCON;
    private Date USU_DATCON;
    private String USU_ETIQUETA;
    private String USU_INDBIP;
    private String USU_OBSBIP;

    public Te220con(String USU_CODDEP, String USU_CODPRO, String USU_ETIQUETA, float USU_QTDCON, Date USU_DATCON, int USU_USUCON, String USU_INDBIP, String USU_OBSBIP) {
        this.USU_CODDEP = USU_CODDEP;
        this.USU_CODPRO = USU_CODPRO;
        this.USU_ETIQUETA = USU_ETIQUETA;
        this.USU_QTDCON = USU_QTDCON;
        this.USU_DATCON = USU_DATCON;
        this.USU_USUCON = USU_USUCON;        
        this.USU_INDBIP = USU_INDBIP;
        this.USU_OBSBIP = USU_OBSBIP;
    }
   
    

    public int getUSU_CODEMP() {
        return USU_CODEMP;
    }

    public void setUSU_CODEMP(int USU_CODEMP) {
        this.USU_CODEMP = USU_CODEMP;
    }

    public Date getUSU_DATINV() {
        return USU_DATINV;
    }

    public void setUSU_DATINV(Date USU_DATINV) {
        this.USU_DATINV = USU_DATINV;
    }

    public String getUSU_CODDEP() {
        return USU_CODDEP;
    }

    public void setUSU_CODDEP(String USU_CODDEP) {
        this.USU_CODDEP = USU_CODDEP;
    }

    public String getUSU_CODPRO() {
        return USU_CODPRO;
    }

    public void setUSU_CODPRO(String USU_CODPRO) {
        this.USU_CODPRO = USU_CODPRO;
    }

    public String getUSU_CODDER() {
        return USU_CODDER;
    }

    public void setUSU_CODDER(String USU_CODDER) {
        this.USU_CODDER = USU_CODDER;
    }

    public int getUSU_NUMCON() {
        return USU_NUMCON;
    }

    public void setUSU_NUMCON(int USU_NUMCON) {
        this.USU_NUMCON = USU_NUMCON;
    }

    public float getUSU_QTDCON() {
        return USU_QTDCON;
    }

    public void setUSU_QTDCON(float USU_QTDCON) {
        this.USU_QTDCON = USU_QTDCON;
    }

    public int getUSU_USUCON() {
        return USU_USUCON;
    }

    public void setUSU_USUCON(int USU_USUCON) {
        this.USU_USUCON = USU_USUCON;
    }

    public Date getUSU_DATCON() {
        return USU_DATCON;
    }

    public void setUSU_DATCON(Date USU_DATCON) {
        this.USU_DATCON = USU_DATCON;
    }

    public String getUSU_ETIQUETA() {
        return USU_ETIQUETA;
    }

    public void setUSU_ETIQUETA(String USU_ETIQUETA) {
        this.USU_ETIQUETA = USU_ETIQUETA;
    }

    public String getUSU_INDBIP() {
        return USU_INDBIP;
    }

    public void setUSU_INDBIP(String USU_INDBIP) {
        this.USU_INDBIP = USU_INDBIP;
    }

    public String getUSU_OBSBIP() {
        return USU_OBSBIP;
    }

    public void setUSU_OBSBIP(String USU_OBSBIP) {
        this.USU_OBSBIP = USU_OBSBIP;
    }
    
   @Override
    public String toString() {
        return this.getUSU_CODDEP();
    }
    
}
