/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Common.Resultado;
import Domain.ControladorCliente;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author karla
 */
public class VentanaFactorizacion extends VentanaOperaciones {
    private String usuario;
    private ControladorCliente controlador;

    public VentanaFactorizacion(String usuario, ControladorCliente controlador) {
        super("DESCOMPOSICION FACTORIAL");
        this.usuario = usuario;
        this.controlador = controlador; // Guardar la referencia

        JLabel lblNumero = new JLabel("Ingrese un número:");
        lblNumero.setBounds(30, 50, 200, 30);
        lblNumero.setFont(new Font("Malgun Gothic", Font.PLAIN, 15));
        lblNumero.setForeground(new Color(32, 35, 122));
        panelContenido.add(lblNumero);

        JTextField txtNumero = new JTextField();
        txtNumero.setBounds(180, 50, 150, 30);
        txtNumero.setFont(new Font("Malgun Gothic", Font.PLAIN, 12));
        panelContenido.add(txtNumero);

        JButton btnCalcular = new JButton("CALCULAR");
        btnCalcular.setBounds(350, 50, 120, 30);
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
            String numeroTexto = txtNumero.getText().trim();
            if (numeroTexto.isEmpty()) {
                txtResultado.setText("Por favor, ingrese un número valido.");
                return;
            }
            try {
                long numero = Long.parseLong(numeroTexto);
                if (numero < 0) {
                    txtResultado.setText("El número debe ser positivo.");
                    return;
                }
                Resultado resultado = controlador.solicitarFactorizacion(usuario, numero);

                if (resultado != null && resultado.isExito()) {
                    txtResultado.setText("Solicitud enviada con éxito.\n"
                            + "Mensaje del servidor: " + resultado.getMensaje() + "\n\n"
                            + "ID de la Tarea: " + resultado.getDatos()); // Asumiendo que el servidor devuelve el ID en 'datos'
                } else {
                    txtResultado.setText("Error del servidor:\n" + (resultado != null ? resultado.getMensaje() : "Respuesta nula."));
                }

//                Map<String, String> parametros = new HashMap<>();
//                parametros.put("numero", numeroTexto);
//
//                Tarea tarea = new Tarea();
//                tarea.setIdCliente(usuario);
//                tarea.setTipoOperacion(TipoOperacion.DESCOMPOSICION_FACTORIAL);
//                tarea.setParametros(parametros);
//
//                GestorConexion conexion = new GestorConexion("localhost", 9090);
//                conexion.conectar();
//                conexion.enviarTareaXML(tarea);
//                Resultado resultado = conexion.recibirConfirmacion();
//                conexion.desconectar();
//
//                if (resultado.isExito()) {
//                    txtResultado.setText("Resultado de la factorización:\n" + resultado.getDatos());
//                } else {
//                    txtResultado.setText("Error del servidor:\n" + resultado.getMensaje());
//                }

            } catch (NumberFormatException ex) {
                txtResultado.setText("Error: El número debe ser válido (entero largo).");
//            } catch (Exception ex) {
//                txtResultado.setText("Error al conectar con el servidor:\n" + ex.getMessage());
//                ex.printStackTrace();
            }
        });
    }
}

