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
public class VentanaFactorizacion extends VentanaOperaciones {

    private String usuario;

    public VentanaFactorizacion(String usuario) {
        super("DESCOMPOSICION FACTORIAL");
        this.usuario = usuario;

        JLabel lblNumero = new JLabel("Ingrese un número para su descomposicion factorial:");
        lblNumero.setBounds(30, 50, 350, 30);
        panelContenido.add(lblNumero);

        JTextField txtNumero = new JTextField();
        txtNumero.setBounds(340, 50, 150, 30);
        panelContenido.add(txtNumero);
        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(500, 50, 100, 30);
        panelContenido.add(btnCalcular);

        JTextArea txtResultado = new JTextArea();
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(txtResultado);
        scroll.setBounds(30, 100, 740, 300);
        panelContenido.add(scroll);

        btnCalcular.addActionListener(e -> {
            String numeroTexto = txtNumero.getText().trim();
            if (numeroTexto.isEmpty()) {
                txtResultado.setText("Por favor, ingrese un número válido.");
                return;
            }

            try {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("numero", numeroTexto);

                Tarea tarea = new Tarea();
                tarea.setIdCliente(usuario);
                tarea.setTipoOperacion(TipoOperacion.DESCOMPOSICION_FACTORIAL);
                tarea.setParametros(parametros);
                String xml = XMLUtility.toXML(tarea);
                ConexionClienteSocket cliente = new ConexionClienteSocket("localhost", 9090); // CAMBIAR ACA
                cliente.enviarMensaje(xml);
                String respuesta = cliente.recibirRespuesta();
                cliente.cerrar();

                txtResultado.setText("Respuesta del servidor:\n" + respuesta);
            } catch (Exception ex) {
                txtResultado.setText("Error: " + ex.getMessage());
            }
        });

    }
}
