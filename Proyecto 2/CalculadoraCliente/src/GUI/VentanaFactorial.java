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
public class VentanaFactorial extends VentanaOperaciones {
    private String usuario;
    private ControladorCliente controlador;

    public VentanaFactorial(String usuario, ControladorCliente controlador) {
        super("CÁLCULO DEL FACTORIAL DE UN NÚMERO GRANDE (N!)");
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
        btnCalcular.setBounds(350, 50, 100, 30);
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
                
                Resultado resultado = controlador.solicitarFactorial(usuario, numero);
                if (resultado != null && resultado.isExito()) {
                    txtResultado.setText("Solicitud enviada con éxito.\n" +
                                       "Mensaje del servidor: " + resultado.getMensaje() + "\n\n" +
                                       "ID de la Tarea: " + resultado.getDatos()); // Asumiendo que el servidor devuelve el ID en 'datos'
                } else {
                    txtResultado.setText("Error del servidor:\n" + (resultado != null ? resultado.getMensaje() : "Respuesta nula."));
                }

//                Map<String, String> parametros = new HashMap<>();
//                parametros.put("numero", String.valueOf(numero));
//
//                Tarea tarea = new Tarea();
//                tarea.setIdCliente(usuario);
//                tarea.setTipoOperacion(TipoOperacion.FACTORIAL);
//                tarea.setParametros(parametros);
//
//                GestorConexion conexion = new GestorConexion("localhost", 9090);
//                conexion.conectar();
//                conexion.enviarTareaXML(tarea);
//                Resultado resultado = conexion.recibirConfirmacion();
//                conexion.desconectar();
//
//                if (resultado.isExito()) {
//                    txtResultado.setText("Resultado:\n" + resultado.getDatos());
//                } else {
//                    txtResultado.setText("Error del servidor:\n" + resultado.getMensaje());
//                }

            } catch (NumberFormatException ex) {
                txtResultado.setText("Error: Debe ingresar un número valido.");
            } 
        });
    }
}

//            //ACA ESTA LA LOGICA DE LA OPERACION SOLO PARA VER EL FUNCIIONAMIENTO, PERO ESTO NO SE HACE ACA
//            //DESPUES TENEMOS QUE CAMBIARLO PORQUE NO PUEDE QUEDAR ACA
//            //ACA SOLO VA LA INTERFAZ GRAFICA
//    private java.math.BigInteger factorial(int n) {
//        java.math.BigInteger result = java.math.BigInteger.ONE;
//        for (int i = 2; i <= n; i++) {
//            result = result.multiply(java.math.BigInteger.valueOf(i));
//        }
//        return result;
//    }
