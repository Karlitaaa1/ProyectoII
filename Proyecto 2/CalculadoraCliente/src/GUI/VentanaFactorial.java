/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Common.Tarea;
import Common.TipoOperacion;
import Common.XMLUtility;
import Domain.ConexionClienteSocket;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

/**
 *
 * @author karla
 */
public class VentanaFactorial extends VentanaOperaciones {
    private String usuario;

    public VentanaFactorial(String usuario) {
        super("CÁLCULO DEL FACTORIAL DE UN NÚMERO GRANDE (N!)");
        this.usuario = usuario;

        JLabel lblNumero = new JLabel("Ingrese un número:");
        lblNumero.setBounds(30, 50, 200, 30);
        panelContenido.add(lblNumero);

        JTextField txtNumero = new JTextField();
        txtNumero.setBounds(180, 50, 150, 30);
        panelContenido.add(txtNumero);

        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(350, 50, 100, 30);
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
                // CREAR TAREA
                Tarea tarea = new Tarea();
                tarea.setIdCliente(usuario);
                tarea.setTipoOperacion(TipoOperacion.FACTORIAL);
                tarea.setParametros(parametros);

                // CONVERTIR A XML Y ENVIAR
                String xml = XMLUtility.toXML(tarea);
                ConexionClienteSocket cliente = new ConexionClienteSocket("localhost", 9090);
                cliente.enviarMensaje(xml);
                String respuesta = cliente.recibirRespuesta();
                cliente.cerrar();

                txtResultado.setText("Respuesta del servidor:\n" + respuesta);
            } catch (Exception ex) {
                txtResultado.setText("Error: " + ex.getMessage());
            }
        });     
    }
    
    
    //ACA ESTA LA LOGICA DE LA OPERACION SOLO PARA VER EL FUNCIIONAMIENTO, PERO ESTO NO SE HACE ACA
        //DESPUES TENEMOS QUE CAMBIARLO PORQUE NO PUEDE QUEDAR ACA
        //ACA SOLO VA LA INTERFAZ GRAFICA
    private java.math.BigInteger factorial(int n) {
        java.math.BigInteger result = java.math.BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(java.math.BigInteger.valueOf(i));
        }
        return result;
    }
}
