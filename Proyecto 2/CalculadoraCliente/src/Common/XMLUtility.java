/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Common;

import Common.Resultado;
import Common.Tarea;
import Common.TipoOperacion;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author kbren
 */
public class XMLUtility { //Clase de utilidad para XML usando JDOM.

    public static String toXML(Tarea tarea) {
        if (tarea == null) {
            System.err.println("[XMLUtility] Error: Tarea es nula.");
            return null;
        }

        boolean estaVacia = tarea.getIdCliente() == null && tarea.getEstado() == null
                && tarea.getTipoOperacion() == null && (tarea.getParametros() == null || tarea.getParametros().isEmpty())
                && tarea.getResultado() == null; // Verifica si no tiene campos útiles

        if (estaVacia) {
            System.err.println("[XMLUtility] Advertencia: Tarea sin contenido útil.");
            return null;
        }

        try {
            Element root = new Element("tarea");
            if (tarea.getId() != 0) {
            root.addContent(new Element("id").setText(String.valueOf(tarea.getId())));
        }
            if (tarea.getIdCliente() != null) {
                root.addContent(new Element("idCliente").setText(String.valueOf(tarea.getIdCliente())));
            }
            if (tarea.getEstado() != null) {
                root.addContent(new Element("estado").setText(tarea.getEstado()));
            }
            if (tarea.getTipoOperacion() != null) {
                root.addContent(new Element("tipoOperacion").setText(tarea.getTipoOperacion().toString()));
            }
            if (tarea.getParametros() != null && !tarea.getParametros().isEmpty()) {
                Element parametrosElement = new Element("parametros");
                for (Map.Entry<String, String> entry : tarea.getParametros().entrySet()) {
                    Element paramElement = new Element("parametro");
                    paramElement.addContent(new Element("clave").setText(entry.getKey()));
                    paramElement.addContent(new Element("valor").setText(entry.getValue()));
                    parametrosElement.addContent(paramElement);
                }
                root.addContent(parametrosElement);
            }
            if (tarea.getResultado() != null) {
                root.addContent(resultadoToElement(tarea.getResultado()));
            }
            Document doc = new Document(root);
            return new XMLOutputter(Format.getPrettyFormat()).outputString(doc);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String toXML(Resultado resultado) {
        try {
            Document doc = new Document(resultadoToElement(resultado));
            return new XMLOutputter(Format.getPrettyFormat()).outputString(doc);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Tarea fromXML(String xmlString) {
        try {
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(new StringReader(xmlString));
            Element root = doc.getRootElement();
            Tarea tarea = new Tarea();
            String idTexto = root.getChildText("id");
if (idTexto != null) {
    try {
        tarea.setId(Integer.parseInt(idTexto));
    } catch (NumberFormatException e) {
        System.err.println("[XMLUtility] Advertencia: id no es un numero valido: " + idTexto);
    }
}


            String idCliente = root.getChildText("idCliente");
            if (idCliente != null) {
                tarea.setIdCliente(idCliente);
            }
            tarea.setEstado(root.getChildText("estado"));
            String tipoOp = root.getChildText("tipoOperacion");
            if (tipoOp != null) {
                tarea.setTipoOperacion(TipoOperacion.valueOf(tipoOp));
            }

            Element parametrosElement = root.getChild("parametros");
            if (parametrosElement != null) {
                Map<String, String> parametros = new HashMap<>();
                List<Element> listaParametros = parametrosElement.getChildren("parametro");
                for (Element param : listaParametros) {
                    String clave = param.getChildText("clave");
                    String valor = param.getChildText("valor");
                    parametros.put(clave, valor);
                }
                tarea.setParametros(parametros);
            }

            Element resultadoElement = root.getChild("resultado");
            if (resultadoElement != null) {
                tarea.setResultado(elementToResultado(resultadoElement));
            }

            return tarea;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Resultado resultadoFromXML(String xml) {
        if (xml == null || xml.trim().isEmpty()) {
            System.err.println("[XMLUtility] Error: XML vacío o nulo.");
            return null;
        }

        try {
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(new StringReader(xml));
            return elementToResultado(document.getRootElement());
        } catch (Exception e) {
            System.err.println("[XMLUtility] Error al parsear XML:");
            e.printStackTrace();
            return null;
        }
    }


    private static Element resultadoToElement(Resultado resultado) {
        Element resultadoElement = new Element("resultado");
        resultadoElement.addContent(new Element("exito").setText(String.valueOf(resultado.isExito())));
        resultadoElement.addContent(new Element("mensaje").setText(resultado.getMensaje() != null ? resultado.getMensaje() : ""));
        resultadoElement.addContent(new Element("datos").setText(resultado.getDatos() != null ? resultado.getDatos() : ""));
        return resultadoElement;
    }

    private static Resultado elementToResultado(Element resultadoElement) {
        Resultado resultado = new Resultado();
        resultado.setExito(Boolean.parseBoolean(resultadoElement.getChildText("exito")));
        resultado.setMensaje(resultadoElement.getChildText("mensaje"));
        resultado.setDatos(resultadoElement.getChildText("datos"));
        return resultado;
    }
}
