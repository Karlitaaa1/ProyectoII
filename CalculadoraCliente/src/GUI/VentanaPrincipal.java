/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author karla
 */
public class VentanaPrincipal extends JFrame {

    private Image backgroundImage;

    public VentanaPrincipal() {
        setTitle("Calculadora Computacional Distribuida");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        backgroundImage = new ImageIcon(getClass().getResource("/GUI/Fondo.png")).getImage();
        JPanel panelFondo = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelFondo.setLayout(null);

        Font tituloFont = new Font("Verdana", Font.BOLD, 24);
        Font botonFont = new Font("Verdana", Font.PLAIN, 14);

        JLabel lblTitulo = new JLabel("CALCULADORA", SwingConstants.CENTER);
        lblTitulo.setFont(tituloFont);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(0, 30, 900, 30);
        panelFondo.add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("COMPUTACIONAL DISTRIBUIDA", SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Verdana", Font.BOLD, 16));
        lblSubtitulo.setForeground(Color.WHITE);
        lblSubtitulo.setBounds(0, 60, 900, 20);
        panelFondo.add(lblSubtitulo);

        String[] textos = {
            "CÁLCULO DEL\nFACTORIAL DE UN\nNÚMERO GRANDE (N!)",
            "BÚSQUEDA DE NÚMEROS\nPRIMOS EN UN RANGO",
            "DESCOMPOSICIÓN\nFACTORIAL",
            "CÁLCULO DE POTENCIAS\nDE NÚMEROS GRANDES",
            "SERIE DE FIBONACCI\nHASTA NÚMERO N",
            "PRODUCTO DE\nMATRICES GRANDES"
        };

        int x1 = 180, x2 = 480;
        int y = 120;
        int width = 240, height = 70;
        int spacingY = 85;

        for (int i = 0; i < textos.length; i++) {
            JButton boton = new JButton("<html><center>" + textos[i].replace("\n", "<br>") + "</center></html>");
            boton.setFont(botonFont);
            boton.setFocusPainted(false);
            boton.setBackground(Color.WHITE);
            boton.setBounds((i % 2 == 0 ? x1 : x2), y + (i / 2) * spacingY, width, height);
            int finalI = i;
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (finalI) {
                        case 0 ->
                            new GUI.VentanaFactorial().setVisible(true);
                        case 1 ->
                            new GUI.VentanaPrimos().setVisible(true);
                        case 2 ->
                            new GUI.VentanaFactorizacion().setVisible(true);
                        case 3 ->
                            new GUI.VentanaPotencias().setVisible(true);
                        case 4 ->
                            new GUI.VentanaFibonacci().setVisible(true);
                        case 5 ->
                            new GUI.VentanaMatrices().setVisible(true);
                    }
                }
            });
            panelFondo.add(boton);
        }

        JLabel lblCreditos = new JLabel("II PROYECTO PROGRAMACION 2025 | KARLA BRENES KEVIN BRENES", SwingConstants.RIGHT);
        lblCreditos.setFont(new Font("Verdana", Font.PLAIN, 10));
        lblCreditos.setForeground(Color.LIGHT_GRAY);
        lblCreditos.setBounds(420, 420, 450, 15);
        panelFondo.add(lblCreditos);

        setContentPane(panelFondo);
    }

}
