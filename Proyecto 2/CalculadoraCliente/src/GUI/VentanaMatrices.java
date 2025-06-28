/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Common.Tarea;
import Common.TipoOperacion;
import Common.XMLUtility;
import Domain.ConexionClienteSocket;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

/**
 *
 * @author karla
 */
public class VentanaMatrices extends VentanaOperaciones {

    private String usuario;

    public VentanaMatrices(String usuario) {
        super("PRODUCTO DE MATRICES");
        this.usuario = usuario;

        JLabel lblInstruccion = new JLabel("Ingrese las matrices:");
        lblInstruccion.setBounds(30, 80, 400, 30);
        panelContenido.add(lblInstruccion);

        JTextArea txtMatriz1 = new JTextArea();
        txtMatriz1.setBounds(200, 50, 170, 100);
        txtMatriz1.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        panelContenido.add(txtMatriz1);
        JLabel lblMat1 = new JLabel("Matriz 1");
        lblMat1.setBounds(250, 100, 100, 110);
        panelContenido.add(lblMat1);

        JTextArea txtMatriz2 = new JTextArea();
        txtMatriz2.setBounds(400, 50, 170, 100);
        txtMatriz2.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        panelContenido.add(txtMatriz2);
        JLabel lblMat2 = new JLabel("Matriz 2");
        lblMat2.setBounds(450, 100, 100, 110);
        panelContenido.add(lblMat2);

        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(600, 80, 100, 30);
        panelContenido.add(btnCalcular);

        JTextArea txtResultado = new JTextArea();
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(txtResultado);
        scroll.setBounds(30, 210, 740, 150);
        panelContenido.add(scroll);

        btnCalcular.addActionListener(e -> {
            String matriz1 = txtMatriz1.getText().trim();
            String matriz2 = txtMatriz2.getText().trim();

            if (matriz1.isEmpty() || matriz2.isEmpty()) {
                txtResultado.setText("Por favor, ingrese ambas matrices.");
                return;
            }

            try {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("matriz1", matriz1);
                parametros.put("matriz2", matriz2);

                Tarea tarea = new Tarea();
                tarea.setIdCliente(usuario);
                tarea.setTipoOperacion(TipoOperacion.PRODUCTO_MATRICES);
                tarea.setParametros(parametros);

                String xml = XMLUtility.toXML(tarea);
                ConexionClienteSocket cliente = new ConexionClienteSocket("localhost", 9090); // Cambiar aca
                cliente.enviarMensaje(xml);
                String respuesta = cliente.recibirRespuesta();
                cliente.cerrar();

                txtResultado.setText("Respuesta del servidor:\n" + respuesta);

            } catch (Exception ex) {
                txtResultado.setText("Error al conectar con el servidor:\n" + ex.getMessage());
            }
        });

    }
}
