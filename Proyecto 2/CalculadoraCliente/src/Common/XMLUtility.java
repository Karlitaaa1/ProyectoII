/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Common;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kbren
 */
/**
 * Clase de utilidad para convertir objetos Tarea a/desde XML de forma manual.
 * No utiliza JAXB.
 */
public class XMLUtility {
    
    
    /* Convierte un objeto Tarea COMPLETO a una cadena de texto XML.
     * @param tarea El objeto Tarea a convertir.
     * @return Una cadena formateada en XML.
     */
    public static String toXML(Tarea tarea) {
        if (tarea == null) return "";

        StringBuilder xml = new StringBuilder();
        xml.append("<tarea>\n");
        
        // Añadir todos los campos de la Tarea
        if (tarea.getIdCliente()!= null) {
            xml.append("  <id>").append(tarea.getIdCliente()).append("</id>\n");
        }
        if (tarea.getIdCliente() != null) {
            xml.append("  <idCliente>").append(tarea.getIdCliente()).append("</idCliente>\n");
        }
        if (tarea.getEstado() != null) {
            xml.append("  <estado>").append(tarea.getEstado()).append("</estado>\n");
        }
        if (tarea.getTipoOperacion() != null) {
            xml.append("  <tipoOperacion>").append(tarea.getTipoOperacion()).append("</tipoOperacion>\n");
        }
        
        // Añadir parámetros
        if (tarea.getParametros() != null && !tarea.getParametros().isEmpty()) {
            xml.append("  <parametros>\n");
            for (Map.Entry<String, String> entry : tarea.getParametros().entrySet()) {
                xml.append("    <parametro>\n");
                xml.append("      <clave>").append(entry.getKey()).append("</clave>\n");
                xml.append("      <valor>").append(entry.getValue()).append("</valor>\n");
                xml.append("    </parametro>\n");
            }
            xml.append("  </parametros>\n");
        }
        
        // Añadir resultado si existe
        if (tarea.getResultado() != null) {
            // Reutilizamos el otro método toXML para no repetir código
            xml.append(toXML(tarea.getResultado())); 
        }

        xml.append("</tarea>");
        return xml.toString();
    }
    

    /**
     * Convierte un objeto Tarea a una cadena de texto XML.
     * @param tarea El objeto Tarea a convertir.
     * @return Una cadena formateada en XML.
     */
    public static String toXML(Resultado resultado) {
        if (resultado == null) return "";
        
        StringBuilder xml = new StringBuilder();
        xml.append("<resultado>\n");
        xml.append("  <exito>").append(resultado.isExito()).append("</exito>\n");
        xml.append("  <mensaje>").append(resultado.getMensaje()).append("</mensaje>\n");
        xml.append("  <datos><![CDATA[").append(resultado.getDatos()).append("]]></datos>\n");
        xml.append("</resultado>");
        return xml.toString();
    }

    /**
     * Parsea una cadena XML y la convierte en un objeto Tarea.
     * @param xmlString La cadena XML a procesar.
     * @return Un objeto Tarea, o null si el XML es inválido.
     */
    public static Tarea fromXML(String xmlString) {
        try {
            Tarea tarea = new Tarea();
            Map<String, String> parametros = new HashMap<>();

            // Extraer datos usando métodos de String. Es simple pero efectivo.
            tarea.setIdCliente(getTagValue(xmlString, "idCliente"));
            String tipoOpStr = getTagValue(xmlString, "tipoOperacion");
            if (tipoOpStr != null) {
                tarea.setTipoOperacion(TipoOperacion.valueOf(tipoOpStr));
            }

            // Extraer parámetros
            String paramsBlock = getTagValue(xmlString, "parametros");
            if (paramsBlock != null) {
                String[] params = paramsBlock.split("</parametro>");
                for (String p : params) {
                    if (p.contains("<clave>")) {
                        String clave = getTagValue(p, "clave");
                        String valor = getTagValue(p, "valor");
                        parametros.put(clave, valor);
                    }
                }
                tarea.setParametros(parametros);
            }

            // Extraer resultado si existe
            String resultadoBlock = getTagValue(xmlString, "resultado");
            if (resultadoBlock != null) {
                Resultado resultado = new Resultado();
                resultado.setExito(Boolean.parseBoolean(getTagValue(resultadoBlock, "exito")));
                resultado.setMensaje(getTagValue(resultadoBlock, "mensaje"));
                resultado.setDatos(getTagValue(resultadoBlock, "datos")); // getTagValue ya maneja CDATA
                tarea.setResultado(resultado);
            }
            
            return tarea;
        } catch (Exception e) {
            e.printStackTrace(); // Es importante ver el error si algo falla
            return null; // Devuelve null si el parsing falla
        }
    }

    /**
     * Un pequeño método ayudante para extraer el valor de una etiqueta XML.
     * @param xml La cadena completa.
     * @param tagName El nombre de la etiqueta (ej: "idCliente").
     * @return El contenido de la etiqueta.
     */
    private static String getTagValue(String xml, String tagName) {
        String startTag = "<" + tagName + ">";
        String endTag = "</" + tagName + ">";
        String cdataStart = "<![CDATA[";
        String cdataEnd = "]]>";
        
        // Manejo especial para CDATA
        if (xml.contains(startTag + cdataStart)) {
            startTag = startTag + cdataStart;
            endTag = cdataEnd + endTag;
        }
        
        int startIndex = xml.indexOf(startTag);
        int endIndex = xml.indexOf(endTag);
        
        if (startIndex != -1 && endIndex != -1) {
            return xml.substring(startIndex + startTag.length(), endIndex);
        }
        
        return null; // Devuelve null si no encuentra la etiqueta
    }
}

