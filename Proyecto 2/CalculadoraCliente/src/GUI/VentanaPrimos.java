/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Common.Tarea;
import Common.TipoOperacion;
import Common.XMLUtility;
import Domain.ConexionClienteSocket;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

/**
 *
 * @author karla
 */
public class VentanaPrimos extends VentanaOperaciones {

    private String usuario;

    public VentanaPrimos(String usuario) {
        super("BUSQUEDA DE NUMEROS PRIMOS EN UN RANGO");
        this.usuario = usuario;

        JLabel lblInstruccion = new JLabel("Ingrese el rango de números para buscar primos:");
        lblInstruccion.setBounds(30, 50, 400, 30);
        panelContenido.add(lblInstruccion);

        JTextField txtInicial = new JTextField();
        txtInicial.setBounds(430, 50, 100, 30);
        panelContenido.add(txtInicial);
        JLabel lblInicial = new JLabel("Inicial");
        lblInicial.setBounds(430, 80, 100, 20);
        panelContenido.add(lblInicial);

        JTextField txtFinal = new JTextField();
        txtFinal.setBounds(540, 50, 100, 30);
        panelContenido.add(txtFinal);
        JLabel lblFinal = new JLabel("Final");
        lblFinal.setBounds(540, 80, 100, 20);
        panelContenido.add(lblFinal);

        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(660, 50, 100, 30);
        panelContenido.add(btnCalcular);

        JTextArea txtResultado = new JTextArea();
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(txtResultado);
        scroll.setBounds(30, 110, 740, 290);
        panelContenido.add(scroll);

        btnCalcular.addActionListener(e -> {
            String inicio = txtInicial.getText().trim();
            String fin = txtFinal.getText().trim();
            if (inicio.isEmpty() || fin.isEmpty()) {
                txtResultado.setText("Por favor, ingrese ambos valores del rango.");
                return;
            }

            try {
                Integer.parseInt(inicio);
                Integer.parseInt(fin);

                Map<String, String> parametros = new HashMap<>();
                parametros.put("inicio", inicio);
                parametros.put("fin", fin);

                Tarea tarea = new Tarea();
                tarea.setIdCliente(usuario);
                tarea.setTipoOperacion(TipoOperacion.BUSQUEDA_PRIMOS);
                tarea.setParametros(parametros);

                String xml = XMLUtility.toXML(tarea);
                ConexionClienteSocket cliente = new ConexionClienteSocket("localhost", 9090); // CAMBIAR ACA
                cliente.enviarMensaje(xml);
                String respuesta = cliente.recibirRespuesta();
                cliente.cerrar();
                txtResultado.setText("Respuesta del servidor:\n" + respuesta);

            } catch (NumberFormatException ex) {
                txtResultado.setText("Por favor, ingrese números válidos.");
            } catch (Exception ex) {
                txtResultado.setText("Error al conectar con el servidor:\n" + ex.getMessage());
            }
        });

    }
}
