/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Common;

import java.io.Serializable;

import java.util.Map; // Usaremos un mapa para los parámetros
/**
 *
 * @author kbren
 */
/**
 * Representa una tarea que un cliente envía al servidor.
 * Anotada con JAXB para poder convertirla fácilmente a XML.
 */




public class Tarea implements Serializable {
    // CAMPOS AÑADIDOS
    private String idCliente;
    private String estado;
    
    // Campos que ya tenías
    private TipoOperacion tipoOperacion;
    private Map<String, String> parametros;
    private Resultado resultado;
    
    public Tarea() {}

    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    public TipoOperacion getTipoOperacion() { return tipoOperacion; }
    public void setTipoOperacion(TipoOperacion tipoOperacion) { this.tipoOperacion = tipoOperacion; }
    
    public Map<String, String> getParametros() { return parametros; }
    public void setParametros(Map<String, String> parametros) { this.parametros = parametros; }
    
    public Resultado getResultado() { return resultado; }
    public void setResultado(Resultado resultado) { this.resultado = resultado; }
}

