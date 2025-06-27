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
public class VentanaFibonacci extends VentanaOperaciones {
    public VentanaFibonacci() {
        super("SERIE DE FIBONACCI");
        JLabel lblNumero = new JLabel("Ingrese un número limite de la serie Fibonacci:");
        lblNumero.setBounds(30, 50, 350, 30);
        panelContenido.add(lblNumero);
        
        JTextField txtNumero = new JTextField();
        txtNumero.setBounds(300, 50, 150, 30);
        panelContenido.add(txtNumero);
        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(460, 50, 100, 30);
        panelContenido.add(btnCalcular);

        JTextArea txtResultado = new JTextArea();
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(txtResultado);
        scroll.setBounds(30, 100, 740, 300);
        panelContenido.add(scroll);
        
        btnCalcular.addActionListener(e -> {
            try {
                txtResultado.setText("aca va el resultado");
            } catch (NumberFormatException ex) {
                txtResultado.setText("Por favor, ingrese un número válido.");
            }
        });
    }
}

