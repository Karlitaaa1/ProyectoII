/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Common.Resultado;
import Common.Tarea;
import Common.TipoOperacion;
import Domain.ControladorCliente;
import Gestor.GestorConexion;
import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author karla
 */
public class VentanaMatrices extends VentanaOperaciones {
    private String usuario;
    private ControladorCliente controlador; 
    
    public VentanaMatrices(String usuario, ControladorCliente controlador) {
        super("PRODUCTO DE MATRICES");
        this.usuario = usuario;
        this.controlador = controlador;

        JLabel lblTitulo = new JLabel("INGRESE LAS MATRICES");
        lblTitulo.setFont(new Font("Malgun Gothic", Font.BOLD, 16));
        lblTitulo.setBounds(30, 40, 300, 30);
        lblTitulo.setForeground(new Color(32, 35, 122));
        panelContenido.add(lblTitulo);

        JTextArea txtMatriz1 = new JTextArea();
        txtMatriz1.setLineWrap(true);
        txtMatriz1.setWrapStyleWord(true);
        JScrollPane scrollMatriz1 = new JScrollPane(txtMatriz1);
        scrollMatriz1.setBounds(30, 80, 300, 100);
        panelContenido.add(scrollMatriz1);

        JTextArea txtMatriz2 = new JTextArea();
        txtMatriz2.setLineWrap(true);
        txtMatriz2.setWrapStyleWord(true);
        JScrollPane scrollMatriz2 = new JScrollPane(txtMatriz2);
        scrollMatriz2.setBounds(350, 80, 300, 100);
        panelContenido.add(scrollMatriz2);

        JButton btnMultiplicar = new JButton("MULTIPLICAR");
        btnMultiplicar.setBounds(680, 100, 120, 30);
        btnMultiplicar.setBackground(new Color(32, 35, 122));
        btnMultiplicar.setForeground(Color.WHITE);
        panelContenido.add(btnMultiplicar);

        JTextArea txtResultado = new JTextArea();
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Malgun Gothic", Font.PLAIN, 12));
        JScrollPane scrollResultado = new JScrollPane(txtResultado);
        scrollResultado.setBounds(30, 200, 770, 200);
        panelContenido.add(scrollResultado);

        btnMultiplicar.addActionListener(e -> {
            String matriz1Texto = txtMatriz1.getText().trim();
            String matriz2Texto = txtMatriz2.getText().trim();
            if (matriz1Texto.isEmpty() || matriz2Texto.isEmpty()) {
                txtResultado.setText("Por favor, ingrese ambas matrices.");
                return;
            }
            try {
                Resultado resultado = controlador.solicitarProductoMatrices(usuario, matriz1Texto, matriz2Texto);
                if (resultado != null && resultado.isExito()) {
                    txtResultado.setText("Solicitud enviada con éxito.\n"
                            + "Mensaje del servidor: " + resultado.getMensaje() + "\n\n"
                            + "ID de la Tarea: " + resultado.getDatos());
                } else {
                    String mensajeError = (resultado != null) ? resultado.getMensaje() : "Respuesta nula del servidor.";
                    txtResultado.setText("Error al enviar la solicitud:\n" + mensajeError);
                }

            } catch (Exception ex) {
                txtResultado.setText("Error inesperado en el cliente: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
//            try {
//                Map<String, String> parametros = new HashMap<>();
//                parametros.put("matrizA", matriz1Texto);
//                parametros.put("matrizB", matriz2Texto);
//
//                Tarea tarea = new Tarea();
//                tarea.setIdCliente(usuario);
//                tarea.setTipoOperacion(TipoOperacion.PRODUCTO_MATRICES);
//                tarea.setParametros(parametros);
//
//                GestorConexion conexion = new GestorConexion("localhost", 9090);
//                conexion.conectar();
//                conexion.enviarTareaXML(tarea);
//                Resultado resultado = conexion.recibirConfirmacion();
//                conexion.desconectar();
//
//                txtResultado.setText(resultado.isExito()
//                        ? resultado.getMensaje() + "\n\nResultado:\n" + resultado.getDatos()
//                        : "Error: " + resultado.getMensaje());
    }
}
