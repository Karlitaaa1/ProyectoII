/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import Common.Resultado;
import Common.Tarea;
import Common.TipoOperacion;
import Gestor.GestorConexion;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kbren
 */
/**
 * Contiene la lógica de negocio del cliente.
 * Actúa como un intermediario entre la GUI y el GestorDeConexion.
 * La GUI solo debe hablar con esta clase.
 */
public class ControladorCliente { //Contiene la lógica de negocio del cliente.
//Actúa como un intermediario entre la GUI y el GestorDeConexion. La GUI solo debe hablar con esta clase.
    private final String hostServidor;
    private final int puertoServidor;
    
    public ControladorCliente(String host, int puerto) {
        this.hostServidor = host;
        this.puertoServidor = puerto;
    }
    
    private Resultado enviarTarea(Tarea tarea) { // GestorConexion se crea para cada transacción, simplificando la gestión de estado.
        GestorConexion gestor = new GestorConexion(hostServidor, puertoServidor);
        try {
            gestor.conectar();
            gestor.enviarTareaXML(tarea);
            Resultado confirmacion = gestor.recibirConfirmacion();
            return confirmacion;
        } catch (Exception e) {
            System.err.println("CONTROLADOR: Falló la operación de envío. " + e.getMessage());
            return new Resultado(false, "No se pudo comunicar con el servidor: " + e.getMessage(), null);
        } finally { // Asegurarse de que siempre nos desconectamos
            try {
                gestor.desconectar();
            } catch (Exception ex) {
                System.err.println("CONTROLADOR: Error menor al intentar desconectar. " + ex.getMessage());
            }
        }
    }
    
    // Métodos de alto nivel que la GUI usará. 
    public Resultado solicitarFactorial(String idCliente, long numero) { //Prepara y envía una tarea para calcular el factorial de un número.
        Tarea tarea = new Tarea();
        tarea.setIdCliente(idCliente);
        tarea.setTipoOperacion(TipoOperacion.FACTORIAL);
        Map<String, String> params = new HashMap<>();
        params.put("numero", String.valueOf(numero));
        tarea.setParametros(params);
        return enviarTarea(tarea);
    }
    
    public Resultado solicitarBusquedaPrimos(String idCliente, long rangoInicio, long rangoFin) { //Prepara y envía una tarea para buscar números primos en un rango.
        Tarea tarea = new Tarea();
        tarea.setIdCliente(idCliente);
        tarea.setTipoOperacion(TipoOperacion.BUSQUEDA_PRIMOS);
        Map<String, String> params = new HashMap<>();
        params.put("rangoInicio", String.valueOf(rangoInicio));
        params.put("rangoFin", String.valueOf(rangoFin));
        tarea.setParametros(params);
        return enviarTarea(tarea);
    }

    public Resultado solicitarFactorizacion(String idCliente, long numero) { //Prepara y envía una tarea para la descomposición factorial de un número.
        Tarea tarea = new Tarea();
        tarea.setIdCliente(idCliente);
        tarea.setTipoOperacion(TipoOperacion.DESCOMPOSICION_FACTORIAL);
        Map<String, String> params = new HashMap<>();
        params.put("numero", String.valueOf(numero));
        tarea.setParametros(params);
        return enviarTarea(tarea);
    }
    
    public Resultado solicitarPotencia(String idCliente, String base, int exponente) { //Prepara y envía una tarea para calcular una potencia.
        Tarea tarea = new Tarea();
        tarea.setIdCliente(idCliente);
        tarea.setTipoOperacion(TipoOperacion.POTENCIA);
        Map<String, String> params = new HashMap<>();
        params.put("base", base);
        params.put("exponente", String.valueOf(exponente));
        tarea.setParametros(params);
        return enviarTarea(tarea);
    }
    
    public Resultado solicitarFibonacci(String idCliente, long limiteTerminos) { //Prepara y envía una tarea para generar la serie de Fibonacci hasta un límite de términos.
        Tarea tarea = new Tarea();
        tarea.setIdCliente(idCliente);
        tarea.setTipoOperacion(TipoOperacion.FIBONACCI);
        Map<String, String> params = new HashMap<>();
        params.put("limite", String.valueOf(limiteTerminos)); // Usamos la clave "limite" como en tu GUI original
        tarea.setParametros(params);
        return enviarTarea(tarea);
    }
    
    public Resultado solicitarProductoMatrices(String idCliente, String matrizA, String matrizB) { //Prepara y envía una tarea para multiplicar dos matrices.
        Tarea tarea = new Tarea();
        tarea.setIdCliente(idCliente);
        tarea.setTipoOperacion(TipoOperacion.PRODUCTO_MATRICES);
        Map<String, String> params = new HashMap<>();
        params.put("matrizA", matrizA);
        params.put("matrizB", matrizB);
        tarea.setParametros(params);
        return enviarTarea(tarea);
    }
  
    public Resultado solicitarConsultaEstado(String idCliente) {
        Tarea tarea = new Tarea();
        tarea.setIdCliente(idCliente);
        tarea.setTipoOperacion(TipoOperacion.CONSULTA_ESTADO);
        Map<String, String> params = new HashMap<>();
        params.put("usuario", idCliente);
        tarea.setParametros(params);
        return enviarTarea(tarea);
    }
}
