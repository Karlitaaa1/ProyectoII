/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gestor;

import Common.Resultado;
import Common.Tarea;
import Common.XMLUtility;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kbren
 */
public class GestorConexion { //Gestiona la comunicación de bajo nivel (sockets y streams) con el servidor.
    //Es el único que sabe cómo enviar y recibir datos por la red.

    private final String host;
    private final int puerto;
    private Socket socket;
    private DataOutputStream out; // Usaremos DataOutputStream para enviar longitud y datos
    private DataInputStream in;   // Usaremos DataInputStream para leer longitud y datos
//    private PrintWriter out;
//    private BufferedReader in;

    public GestorConexion(String host, int puerto) {
        this.host = host;
        this.puerto = puerto;
    }

    public void conectar() throws IOException, UnknownHostException { //Intenta establecer una conexión con el servidor y realiza el handshake.
        socket = new Socket(host, puerto);
        // Envolvemos los streams del socket
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
//        out = new PrintWriter(socket.getOutputStream(), true);
//        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        out.println("<handshake><tipo>CLIENTE</tipo></handshake>"); // El cliente se presenta al servidor.
        String handshake = "<handshake><tipo>CLIENTE</tipo></handshake>";
        enviarMensaje(handshake); // Usamos un nuevo método helper
        System.out.println("GESTOR: Conexion establecida y handshake enviado."); //QUITAR EN UN FUTURO, ES SOLO PRUEBA    }
    }

    public void enviarTareaXML(Tarea tarea) { //Envía una Tarea serializada como XML al servidor.
        String xmlTarea = XMLUtility.toXML(tarea); //Tarea=La tarea a enviar.
        if (xmlTarea == null || xmlTarea.trim().isEmpty()) {
            System.err.println("[CLIENTE] Error: XML generado es nulo o vacio. No se enviara.");
            return;
        }
        try {
            enviarMensaje(xmlTarea);
        } catch (IOException ex) {
            Logger.getLogger(GestorConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("GESTOR: Tarea XML enviada al servidor.");
    }

    public Resultado recibirConfirmacion() throws IOException {
        String xmlRespuesta = recibirMensaje();
        System.out.println("[DEBUG] XML recibido completo:\n" + xmlRespuesta);
        
        if (xmlRespuesta == null || xmlRespuesta.trim().isEmpty()) {
            System.err.println("[CLIENTE] Error: respuesta vacía del servidor.");
            return new Resultado(false, "Respuesta vacía del servidor.", null);
        }

        System.out.println("GESTOR: Confirmacion XML recibida:\n" + xmlRespuesta);
        Resultado resultado = XMLUtility.resultadoFromXML(xmlRespuesta);
        
        if (resultado == null) {
            System.err.println("[CLIENTE] Error: No se pudo convertir XML en Resultado.");
            return new Resultado(false, "Error al convertir XML en resultado.", null);
        }
        return resultado;
    }
     
    private void enviarMensaje(String mensaje) throws IOException {
        byte[] mensajeBytes = mensaje.getBytes(StandardCharsets.UTF_8);
        out.writeInt(mensajeBytes.length); // 1. Enviar longitud
        out.write(mensajeBytes);           // 2. Enviar datos
        out.flush();
    } 
     
    private String recibirMensaje() throws IOException {
        int length = in.readInt(); // 1. Leer longitud
        if (length > 0) {
            byte[] mensajeBytes = new byte[length];
            in.readFully(mensajeBytes, 0, length); // 2. Leer exactamente esa cantidad de bytes
            return new String(mensajeBytes, StandardCharsets.UTF_8);
        }
        return null;
    }

    public void desconectar() throws IOException {
        if (in != null) in.close();
        if (out != null) out.close();
        if (socket != null && !socket.isClosed()) socket.close();
        System.out.println("GESTOR: Conexión cerrada.");
    }
}

