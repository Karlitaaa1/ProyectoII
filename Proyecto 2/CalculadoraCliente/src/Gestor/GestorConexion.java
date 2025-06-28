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
public class GestorConexion { //Gestiona la comunicación de bajo nivel (sockets y streams) con el servidor.
    //Es el único que sabe cómo enviar y recibir datos por la red.
    private final String host;
    private final int puerto;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public GestorConexion(String host, int puerto) {
        this.host = host;
        this.puerto = puerto;
    }

    public void conectar() throws IOException, UnknownHostException { //Intenta establecer una conexión con el servidor y realiza el handshake.
        socket = new Socket(host, puerto);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out.println("<handshake><tipo>CLIENTE</tipo></handshake>"); // El cliente se presenta al servidor.
//        System.out.println("GESTOR: Conexion establecida y handshake enviado.");
    }

    public void enviarTareaXML(Tarea tarea) { //Envía una Tarea serializada como XML al servidor.
        String xmlTarea = XMLUtility.toXML(tarea); //Tarea=La tarea a enviar.
        out.println(xmlTarea);
        System.out.println("GESTOR: Tarea XML enviada al servidor.");
    }

    public Resultado recibirConfirmacion() throws IOException { //Espera y lee la respuesta del servidor (confirmación).
        String xmlRespuesta = in.readLine();
        if (xmlRespuesta == null) {
            throw new IOException("La conexion fue cerrada por el servidor antes de recibir respuesta.");
        }
        System.out.println("GESTOR: Confirmación XML recibida.");
        return XMLUtility.resultadoFromXML(xmlRespuesta); //El objeto Resultado deserializado desde el XML de respuesta.
    }

    public void desconectar() { //Cierra todos los recursos de red de forma segura.
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
            System.out.println("GESTOR: Conexion cerrada.");
        } catch (IOException e) {
            System.err.println("GESTOR: Error al cerrar la conexion: " + e.getMessage());
        }
    }
}

