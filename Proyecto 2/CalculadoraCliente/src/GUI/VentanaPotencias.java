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
import java.math.BigDecimal;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author karla
 */
public class VentanaPotencias extends VentanaOperaciones {
    private String usuario;
    private ControladorCliente controlador; 
    
    public VentanaPotencias(String usuario, ControladorCliente controlador) {
        super("CALCULO DE POTENCIAS");
        this.usuario = usuario;
        this.controlador = controlador; // Guardar la referencia

        JLabel lblBase = new JLabel("Base:");
        lblBase.setBounds(30, 50, 100, 30);
        lblBase.setFont(new Font("Malgun Gothic", Font.PLAIN, 15));
        lblBase.setForeground(new Color(32, 35, 122));
        panelContenido.add(lblBase);

        JTextField txtBase = new JTextField();
        txtBase.setBounds(90, 50, 150, 30);
        txtBase.setFont(new Font("Malgun Gothic", Font.PLAIN, 12));
        panelContenido.add(txtBase);

        JLabel lblExponente = new JLabel("Exponente:");
        lblExponente.setBounds(260, 50, 100, 30);
        lblExponente.setFont(new Font("Malgun Gothic", Font.PLAIN, 15));
        lblExponente.setForeground(new Color(32, 35, 122));
        panelContenido.add(lblExponente);

        JTextField txtExp = new JTextField();
        txtExp.setBounds(360, 50, 150, 30);
        txtExp.setFont(new Font("Malgun Gothic", Font.PLAIN, 12));
        panelContenido.add(txtExp);

        JButton btnCalcular = new JButton("CALCULAR");
        btnCalcular.setBounds(530, 50, 120, 30);
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
            String baseTexto = txtBase.getText().trim();
            String expTexto = txtExp.getText().trim();

            if (baseTexto.isEmpty() || expTexto.isEmpty()) {
                txtResultado.setText("Por favor, ingrese ambos valores.");
                return;
            }
            try {
                int exponente = Integer.parseInt(expTexto);
                Resultado resultado = controlador.solicitarPotencia(usuario, baseTexto, exponente);
                if (resultado != null && resultado.isExito()) {
                    txtResultado.setText("Solicitud enviada con éxito.\n"
                            + "Mensaje del servidor: " + resultado.getMensaje() + "\n\n"
                            + "ID de la Tarea: " + resultado.getDatos());
                } else {
                    String mensajeError = (resultado != null) ? resultado.getMensaje() : "Respuesta nula del servidor.";
                    txtResultado.setText("Error al enviar la solicitud:\n" + mensajeError);
                }

            } catch (NumberFormatException ex) {
                txtResultado.setText("Error: El exponente debe ser un número entero válido.");
            } catch (Exception ex) {
                txtResultado.setText("Error inesperado en el cliente: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

    }
}
