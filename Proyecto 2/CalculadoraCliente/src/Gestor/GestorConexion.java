/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gestor;

import Common.Resultado;
import Common.Tarea;
import Common.XMLUtility;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author kbren
 */
/**
 * Gestiona la comunicación de bajo nivel (sockets y streams) con el servidor.
 * Es el único que sabe cómo enviar y recibir datos por la red.
 */
public class GestorConexion {

    private final String host;
    private final int puerto;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public GestorConexion(String host, int puerto) {
        this.host = host;
        this.puerto = puerto;
    }

    /**
     * Intenta establecer una conexión con el servidor y realiza el handshake.
     * @throws IOException si ocurre un error de red.
     * @throws UnknownHostException si no se puede encontrar el host.
     */
    public void conectar() throws IOException, UnknownHostException {
        socket = new Socket(host, puerto);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        // El cliente se presenta al servidor.
        out.println("<handshake><tipo>CLIENTE</tipo></handshake>");
        System.out.println("GESTOR: Conexión establecida y handshake enviado.");
    }

    /**
     * Envía una Tarea serializada como XML al servidor.
     * @param tarea La tarea a enviar.
     */
    public void enviarTareaXML(Tarea tarea) {
        String xmlTarea = XMLUtility.tareaToXML(tarea);
        out.println(xmlTarea);
        System.out.println("GESTOR: Tarea XML enviada al servidor.");
    }

    /**
     * Espera y lee la respuesta del servidor (confirmación).
     * @return El objeto Resultado deserializado desde el XML de respuesta.
     * @throws IOException si la conexión se pierde mientras se espera.
     */
    public Resultado recibirConfirmacion() throws IOException {
        String xmlRespuesta = in.readLine();
        if (xmlRespuesta == null) {
            throw new IOException("La conexión fue cerrada por el servidor antes de recibir respuesta.");
        }
        System.out.println("GESTOR: Confirmación XML recibida.");
        return XMLUtility.fromXML(xmlRespuesta, Resultado.class); // Usamos el método de utilidad
    }

    /**
     * Cierra todos los recursos de red de forma segura.
     */
    public void desconectar() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
            System.out.println("GESTOR: Conexión cerrada.");
        } catch (IOException e) {
            System.err.println("GESTOR: Error al cerrar la conexión: " + e.getMessage());
        }
    }
}

