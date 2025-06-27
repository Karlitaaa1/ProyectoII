/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Common;

import java.io.Serializable;


/**
 *
 * @author kbren
 */
    
public class Resultado implements Serializable {
    
    private boolean exito;
    private String mensaje;
    private String datos;

    // Constructor vacío 
    public Resultado() {}

    public Resultado(boolean exito, String mensaje, String datos) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.datos = datos;
    }

    // --- Getters y Setters ---
    public boolean isExito() { return exito; }
    public void setExito(boolean exito) { this.exito = exito; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public String getDatos() { return datos; }
    public void setDatos(String datos) { this.datos = datos; }
}

