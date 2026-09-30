/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * classe reponsavel por definir qual operacao o client esta solicitando
 * @author Wilson
 */
public class Operacoes implements Serializable{
       
    private String operacao;
    
    /* 
    chave : Object
    */
    
    Map<String, Object> params;
    /**
     * Parametro que define qual operacao deve ser executada
     * @param operacao 
     */
    
    public Operacoes(String operacao)
    {
       this.operacao = operacao;
       params = new HashMap<>();
    }
    
    public String getOperacao()
    {
        return operacao;
    }
    
   
    public void setParam( String chave, Object valor )
    {
        params.put( chave, valor );
    }
    
    public Object getParam( String chave )
    {
        return params.get(chave);
    }
    
    @Override
    public String toString()
    {
        String m = "Operacao: "+ operacao;
        
        m += "\nParâmetros:\n ";
        for (String p : params.keySet() ) { 
            m += p+": " + params.get(p)+"\n";  
        }
        return m;
    }
    
    
}
