/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelDominio;

/**
 *
 * @author wilson.simoes
 */
import java.io.Serializable;

public class Opcao  implements Serializable{
    
    private String rotina;
    private String data;
    private String deposito;
    private String codigo;
    private String tipo;
       
 
    public Opcao(String rotina, String data, String deposito, String codigo, String tipo) {
        this.rotina = rotina;
        this.data = data;
        this.deposito = deposito;
        this.codigo = codigo;
        this.tipo = tipo;
    }
    
    public void setRotina(String rotina) { this.rotina = rotina; }
    public void setData(String data) { this.data = data; }
    public void setDeposito(String deposito) { this.deposito = deposito; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    public String getRotina() { return this.rotina; }
    public String getData() { return this.data; }
    public String getDeposito() { return this.deposito; }
    public String getCodigo() { return this.codigo; }
    public String getTipo() { return this.tipo; }
}