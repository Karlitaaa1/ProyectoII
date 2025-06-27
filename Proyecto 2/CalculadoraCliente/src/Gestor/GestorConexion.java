/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gestor;

import Domain.Tarea;
import Domain.Tarea.Resultado;
import java.net.Socket;

/**
 *
 * @author kbren
 */
public class GestorConexion {
    private Socket socket;

    public void conectarServidor() {
        // implementación de conexión
    }

    public void enviarTarea(Tarea tarea) {
        // enviar tarea al servidor
    }

    public Resultado recibirResultado() {
        // recibir y retornar resultado
        return null;
    }

    public void desconectar() {
        // cerrar conexión
    }
}

