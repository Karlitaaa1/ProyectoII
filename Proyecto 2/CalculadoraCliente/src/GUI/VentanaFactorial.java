/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import javax.swing.*;

/**
 *
 * @author karla
 */

public class VentanaFactorial extends VentanaOperaciones {

    public VentanaFactorial() {
        super("CÁLCULO DEL FACTORIAL DE UN NÚMERO GRANDE (N!)");
        
        //ACA ESTA LA LOGICA DE LA OPERACION SOLO PARA VER EL FUNCIIONAMIENTO, PERO ESTO NO SE HACE ACA
        //DESPUES TENEMOS QUE CAMBIARLO PORQUE NO PUEDE QUEDAR ACA
        //ACA SOLO VA LA INTERFAZ GRAFICA

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
            try {
                int n = Integer.parseInt(txtNumero.getText());
                java.math.BigInteger resultado = factorial(n);
                txtResultado.setText(n + "! =\n" + resultado.toString());
            } catch (NumberFormatException ex) {
                txtResultado.setText("Por favor, ingrese un número válido.");
            }
        });
    }

    private java.math.BigInteger factorial(int n) {
        java.math.BigInteger result = java.math.BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(java.math.BigInteger.valueOf(i));
        }
        return result;
    }
}
