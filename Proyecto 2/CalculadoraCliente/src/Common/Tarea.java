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

    private String idCliente;
    private TipoOperacion tipoOperacion;
    private Map<String, String> parametros;
    private Resultado resultado; // Referencia a la clase separada

    // Constructor vacío 
    public Tarea() {}

    // --- Getters y Setters ---
    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }
    public TipoOperacion getTipoOperacion() { return tipoOperacion; }
    public void setTipoOperacion(TipoOperacion tipoOperacion) { this.tipoOperacion = tipoOperacion; }
    public Map<String, String> getParametros() { return parametros; }
    public void setParametros(Map<String, String> parametros) { this.parametros = parametros; }
    public Resultado getResultado() { return resultado; }
    public void setResultado(Resultado resultado) { this.resultado = resultado; }
}

