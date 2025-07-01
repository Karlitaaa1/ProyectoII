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
public class VentanaFibonacci extends VentanaOperaciones {
    private String usuario;
    private ControladorCliente controlador; 
    
    public VentanaFibonacci(String usuario, ControladorCliente controlador) {
        super("SERIE DE FIBONACCI");
        this.usuario = usuario;
        this.controlador = controlador;

        this.usuario = usuario;

        JLabel lblCantidad = new JLabel("Cantidad de términos:");
        lblCantidad.setBounds(30, 50, 200, 30);
        lblCantidad.setFont(new Font("Malgun Gothic", Font.PLAIN, 15));
        lblCantidad.setForeground(new Color(32, 35, 122));
        panelContenido.add(lblCantidad);

        JTextField txtFibonna = new JTextField();
        txtFibonna.setBounds(200, 50, 150, 30);
        txtFibonna.setFont(new Font("Malgun Gothic", Font.PLAIN, 12));
        panelContenido.add(txtFibonna);

        JButton btnCalcular = new JButton("CALCULAR");
        btnCalcular.setBounds(370, 50, 100, 30);
        btnCalcular.setBackground(new Color(32, 35, 122));
        btnCalcular.setForeground(Color.WHITE);
        btnCalcular.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
        panelContenido.add(btnCalcular);

        JTextArea txtResultado = new JTextArea();
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);
        txtResultado.setFont(new Font("Malgun Gothic", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(txtResultado);
        scroll.setBounds(30, 100, 740, 300);
        panelContenido.add(scroll);

        btnCalcular.addActionListener(e -> {
            String numeroTexto = txtFibonna.getText().trim();
            if (numeroTexto.isEmpty()) {
                txtResultado.setText("Por favor, ingrese una cantidad válida.");
                return;
            }
            try {
                long numero = Long.parseLong(numeroTexto);
                if (numero < 0) {
                    txtResultado.setText("El número debe ser positivo.");
                    return;
                }
                Resultado resultado = controlador.solicitarFibonacci(usuario, numero);

                if (resultado != null && resultado.isExito()) {
                    txtResultado.setText("Solicitud enviada con éxito.\n"
                            + "Mensaje del servidor: " + resultado.getMensaje() + "\n\n"
                            + "ID de la Tarea: " + resultado.getDatos()); // Asumiendo que el servidor devuelve el ID en 'datos'
                } else {
                    txtResultado.setText("Error del servidor:\n" + (resultado != null ? resultado.getMensaje() : "Respuesta nula."));
                }

//                Map<String, String> parametros = new HashMap<>();
//                parametros.put("limite", String.valueOf(cantidad));
//
//                Tarea tarea = new Tarea();
//                tarea.setIdCliente(usuario);
//                tarea.setTipoOperacion(TipoOperacion.FIBONACCI);
//                tarea.setParametros(parametros);
//
//                GestorConexion conexion = new GestorConexion("localhost", 9090);
//                conexion.conectar();
//                conexion.enviarTareaXML(tarea);
//                Resultado resultado = conexion.recibirConfirmacion();
//                conexion.desconectar();
//
//                if (resultado.isExito()) {
//                    txtResultado.setText("Serie de Fibonacci:\n" + resultado.getDatos());
//                } else {
//                    txtResultado.setText("Error del servidor:\n" + resultado.getMensaje());
//                }

            } catch (NumberFormatException ex) {
                txtResultado.setText("Error: Ingrese solo números válidos.");
//            } catch (Exception ex) {
//                txtResultado.setText("Error al conectar con el servidor:\n" + ex.getMessage());
//                ex.printStackTrace();
            }
        });
    }
}

