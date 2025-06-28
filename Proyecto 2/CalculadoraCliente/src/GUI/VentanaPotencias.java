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
public class VentanaPotencias extends VentanaOperaciones {

    private String usuario;

    public VentanaPotencias(String usuario) {
        super("CALCULO DE POTENCIAS");
        this.usuario = usuario;

        JLabel lblInstruccion = new JLabel("Ingrese la base y el exponente para calcular la potencia:");
        lblInstruccion.setBounds(30, 50, 400, 30);
        panelContenido.add(lblInstruccion);

        JTextField txtbase = new JTextField();
        txtbase.setBounds(430, 50, 100, 30);
        panelContenido.add(txtbase);
        JLabel lblInicial = new JLabel("base");
        lblInicial.setBounds(430, 80, 100, 20);
        panelContenido.add(lblInicial);

        JTextField txtExp = new JTextField();
        txtExp.setBounds(540, 50, 100, 30);
        panelContenido.add(txtExp);
        JLabel lblFinal = new JLabel("exponente");
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
            String base = txtbase.getText().trim();
            String exp = txtExp.getText().trim();
            if (base.isEmpty() || exp.isEmpty()) {
                txtResultado.setText("Por favor, ingrese ambos valores.");
                return;
            }

            try {
                Integer.parseInt(base);
                Integer.parseInt(exp);
                Map<String, String> parametros = new HashMap<>();
                parametros.put("base", base);
                parametros.put("exponente", exp);

                Tarea tarea = new Tarea();
                tarea.setIdCliente(usuario);
                tarea.setTipoOperacion(TipoOperacion.POTENCIA);
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
