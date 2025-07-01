/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Common.Tarea;
import Common.TipoOperacion;
import Common.XMLUtility;
import Domain.ConexionClienteSocket;
import Domain.ControladorCliente;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author karla
 */
public class VentanaConsultaEstado extends JFrame {
    private String usuario;
    private ControladorCliente controlador;

    public VentanaConsultaEstado(String usuario, ControladorCliente controlador) {
        this.usuario = usuario;
        this.controlador = controlador;

        setTitle("Estado de solicitudes");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea txtResultados = new JTextArea();
        txtResultados.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtResultados);

        JButton btnConsultar = new JButton("CONSULTAR ESTADO");
        btnConsultar.setBackground(new Color(32, 35, 122));
        btnConsultar.setForeground(Color.WHITE);
        btnConsultar.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
        add(btnConsultar, BorderLayout.NORTH);

        btnConsultar.addActionListener(e -> {
            try {
                Tarea tarea = new Tarea();
                tarea.setIdCliente(usuario);
                tarea.setTipoOperacion(TipoOperacion.CONSULTA_ESTADO);
                Map<String, String> parametros = new HashMap<>();
                parametros.put("usuario", usuario);
                tarea.setParametros(parametros);
                String xml = XMLUtility.toXML(tarea);

                // Enviar al servidor
                ConexionClienteSocket cliente = new ConexionClienteSocket("localhost", 9090); //CAMBIAR ACA
                cliente.enviarMensaje(xml);
                String respuesta = cliente.recibirRespuesta();
                cliente.cerrar();

                txtResultados.setText("Estado de tus solicitudes:\n\n" + respuesta);
            } catch (Exception ex) {
                txtResultados.setText("Error: " + ex.getMessage());
            }
        });
        add(scroll, BorderLayout.CENTER);
    }

}
