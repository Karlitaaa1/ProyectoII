/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.io.*;
import java.net.Socket;

/**
 *
 * @author karla
 */
public class ConexionClienteSocket {

    private Socket socket;
    private PrintWriter salida;
    private BufferedReader entrada;

    public ConexionClienteSocket(String host, int puerto) throws IOException {
        socket = new Socket(host, puerto);
        salida = new PrintWriter(socket.getOutputStream(), true);
        entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void enviarMensaje(String mensajeXML) {
        salida.println(mensajeXML);
    }

    public String recibirRespuesta() throws IOException {
        return entrada.readLine(); // puede cambiarse a leer más si el servidor lo necesita
    }

    public void cerrar() throws IOException {
        entrada.close();
        salida.close();
        socket.close();
    }

}
