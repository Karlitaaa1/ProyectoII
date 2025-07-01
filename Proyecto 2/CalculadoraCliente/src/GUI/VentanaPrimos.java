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
public class VentanaPrimos extends VentanaOperaciones {
    private String usuario;
    private ControladorCliente controlador; 

    public VentanaPrimos(String usuario, ControladorCliente controlador) {
        super("BUSQUEDA DE NUMEROS PRIMOS EN UN RANGO");
        this.usuario = usuario;
        this.controlador = controlador;

        JLabel lblInicio = new JLabel("Desde:");
        lblInicio.setBounds(30, 50, 100, 30);
        lblInicio.setFont(new Font("Malgun Gothic", Font.PLAIN, 15));
        lblInicio.setForeground(new Color(32, 35, 122));
        panelContenido.add(lblInicio);

        JTextField txtInicio = new JTextField();
        txtInicio.setBounds(100, 50, 150, 30);
        txtInicio.setFont(new Font("Malgun Gothic", Font.PLAIN, 12));
        panelContenido.add(txtInicio);

        JLabel lblFin = new JLabel("Hasta:");
        lblFin.setBounds(280, 50, 100, 30);
        lblFin.setFont(new Font("Malgun Gothic", Font.PLAIN, 15));
        lblFin.setForeground(new Color(32, 35, 122));
        panelContenido.add(lblFin);

        JTextField txtFin = new JTextField();
        txtFin.setBounds(350, 50, 150, 30);
        txtFin.setFont(new Font("Malgun Gothic", Font.PLAIN, 12));
        panelContenido.add(txtFin);

        JButton btnBuscar = new JButton("BUSCAR");
        btnBuscar.setBounds(520, 50, 120, 30);
        btnBuscar.setBackground(new Color(32, 35, 122));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
        panelContenido.add(btnBuscar);

        JTextArea txtResultado = new JTextArea();
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);
        txtResultado.setFont(new Font("Malgun Gothic", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(txtResultado);
        scroll.setBounds(30, 100, 740, 300);
        panelContenido.add(scroll);

        btnBuscar.addActionListener(e -> {
            String inicioTexto = txtInicio.getText().trim();
            String finTexto = txtFin.getText().trim();

            if (inicioTexto.isEmpty() || finTexto.isEmpty()) {
                txtResultado.setText("Por favor, ingrese ambos límites del rango.");
                return;
            }

            try {
                long inicio = Long.parseLong(inicioTexto);
                long fin = Long.parseLong(finTexto);

                if (inicio < 0 || fin < 0 || inicio > fin) {
                    txtResultado.setText("El rango debe ser válido y positivo (inicio <= fin).");
                    return;
                }

                Resultado resultado = controlador.solicitarBusquedaPrimos(usuario, inicio, fin);
                if (resultado != null && resultado.isExito()) {
                    txtResultado.setText("Solicitud enviada con éxito.\n"
                            + "Mensaje del servidor: " + resultado.getMensaje() + "\n\n"
                            + "ID de la Tarea: " + resultado.getDatos());
                } else {
                    String mensajeError = (resultado != null) ? resultado.getMensaje() : "Respuesta nula del servidor.";
                    txtResultado.setText("Error al enviar la solicitud:\n" + mensajeError);
                }

            } catch (NumberFormatException ex) {
                txtResultado.setText("Error: Ingrese solo numeros validos para los limites del rango.");
            } catch (Exception ex) {
                txtResultado.setText("Error inesperado en el cliente: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

//                Map<String, String> parametros = new HashMap<>();
//                parametros.put("rangoInicio", String.valueOf(inicio));
//                parametros.put("rangoFin", String.valueOf(fin));
//
//                Tarea tarea = new Tarea();
//                tarea.setIdCliente(usuario);
//                tarea.setTipoOperacion(TipoOperacion.BUSQUEDA_PRIMOS);
//                tarea.setParametros(parametros);
//
//                GestorConexion conexion = new GestorConexion("localhost", 9090);
//                conexion.conectar();
//                conexion.enviarTareaXML(tarea);
//                Resultado resultado = conexion.recibirConfirmacion();
//                conexion.desconectar();
//
//                if (resultado.isExito()) {
//                    txtResultado.setText("Números primos encontrados:\n" + resultado.getDatos());
//                } else {
//                    txtResultado.setText("Error del servidor:\n" + resultado.getMensaje());
//                }
//
//            } catch (NumberFormatException ex) {
//                txtResultado.setText("Error: Ingrese solo números válidos.");
//            } catch (Exception ex) {
//                txtResultado.setText("Error al conectar con el servidor:\n" + ex.getMessage());
//                ex.printStackTrace();
//            }
//        });
    }
}
