/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import Common.Resultado;
import Common.Tarea;
import Gestor.GestorConexion;

/**
 *
 * @author kbren
 */
/**
 * Contiene la lógica de negocio del cliente.
 * Actúa como un intermediario entre la GUI y el GestorDeConexion.
 * La GUI solo debe hablar con esta clase.
 */
public class ControladorCliente {

    private final String hostServidor;
    private final int puertoServidor;

    public ControladorCliente(String host, int puerto) {
        this.hostServidor = host;
        this.puertoServidor = puerto;
    }

    /**
     * Método de alto nivel para enviar una tarea de búsqueda de primos.
     * @param rangoInicio El inicio del rango.
     * @param rangoFin El final del rango.
     * @return El resultado de la operación de envío (confirmación o error).
     */
    public Resultado solicitarBusquedaPrimos(long rangoInicio, long rangoFin) {
        // 1. Construir el objeto Tarea
        Map<String, String> parametros = new HashMap<>();
        parametros.put("rangoInicio", String.valueOf(rangoInicio));
        parametros.put("rangoFin", String.valueOf(rangoFin));

        Tarea tarea = new Tarea();
        tarea.setTipoOperacion(TipoOperacion.BUSQUEDA_PRIMOS);
        tarea.setParametros(parametros);

        // 2. Llamar al método genérico para enviar la tarea
        return enviarTareaAlServidor(tarea);
    }
    
    /**
     * Aquí añadirías otros métodos similares para las demás operaciones.
     * public Resultado solicitarFactorial(long numero) { ... }
     */

    /**
     * Orquesta el proceso completo de conexión, envío y desconexión.
     * @param tarea La tarea a enviar.
     * @return El resultado de la confirmación del servidor.
     */
    private Resultado enviarTareaAlServidor(Tarea tarea) {
        GestorConexion gestor = new GestorConexion(hostServidor, puertoServidor);
        try {
            // Conectar
            gestor.conectar();
            
            // Enviar
            gestor.enviarTareaXML(tarea);
            
            // Recibir confirmación
            Resultado confirmacion = gestor.recibirConfirmacion();
            
            return confirmacion;
            
        } catch (Exception e) {
            System.err.println("CONTROLADOR: Falló la operación de envío. " + e.getMessage());
            return new Resultado(false, "No se pudo comunicar con el servidor: " + e.getMessage(), null);
        } finally {
            // Asegurarse de que siempre nos desconectamos
            gestor.desconectar();
        }
    }
}
