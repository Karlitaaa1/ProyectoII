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
public class VentanaPrimos extends VentanaOperaciones {
    public VentanaPrimos() {
        super("BUSQUEDA DE NUMEROS PRIMOS EN UN RANGO");
        
        JLabel lblInstruccion = new JLabel("Ingrese el rango de números para buscar primos:");
        lblInstruccion.setBounds(30, 50, 400, 30);
        panelContenido.add(lblInstruccion);
        
        JTextField txtInicial = new JTextField();
        txtInicial.setBounds(430, 50, 100, 30);
        panelContenido.add(txtInicial);
        JLabel lblInicial = new JLabel("Inicial");
        lblInicial.setBounds(430, 80, 100, 20);
        panelContenido.add(lblInicial);

        JTextField txtFinal = new JTextField();
        txtFinal.setBounds(540, 50, 100, 30);
        panelContenido.add(txtFinal);
        JLabel lblFinal = new JLabel("Final");
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
            try {
                txtResultado.setText("aca va el resultado");
            } catch (NumberFormatException ex) {
                txtResultado.setText("Por favor, ingrese un número válido.");
            }
        });
    }   
}