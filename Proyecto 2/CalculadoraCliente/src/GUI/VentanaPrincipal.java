/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Domain.ControladorCliente;
import Gestor.GestorConexion;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author karla
 */
public class VentanaPrincipal extends JFrame {
    private String usuario;
    private ControladorCliente controlador;
    private Image backgroundImage;

    public VentanaPrincipal(String usuario, ControladorCliente controlador) {
        this.usuario = usuario;
        this.controlador = controlador;
        setTitle("Calculadora Computacional Distribuida");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        backgroundImage = new ImageIcon(getClass().getResource("/GUI/Fondo.png")).getImage();
        JPanel panelFondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelFondo.setLayout(null);

        JLabel lblTitulo = new JLabel("CALCULADORA", SwingConstants.CENTER);
        lblTitulo.setFont (new Font("Malgun Gothic", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(0, 30, 900, 30);
        panelFondo.add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("COMPUTACIONAL DISTRIBUIDA", SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Malgun Gothic", Font.BOLD, 16));
        lblSubtitulo.setForeground(Color.WHITE);
        lblSubtitulo.setBounds(0, 60, 900, 20);
        panelFondo.add(lblSubtitulo);

        String[] textos = {
            "CÁLCULO DEL FACTORIAL \n DE UN NÚMERO GRANDE (N!)",
            "BÚSQUEDA DE NÚMEROS\nPRIMOS EN UN RANGO",
            "DESCOMPOSICIÓN\nFACTORIAL",
            "CÁLCULO DE POTENCIAS\nDE NÚMEROS GRANDES",
            "SERIE DE FIBONACCI\nHASTA NÚMERO N",
            "PRODUCTO DE\nMATRICES GRANDES"
        };
        
        for (int i = 0; i < textos.length; i++) {
            JButton boton = new JButton("<html><center>" + textos[i].replace("\n", "<br>") + "</center></html>");
            boton.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
            boton.setForeground(new Color(32, 35, 122));
            boton.setFocusPainted(false);
            boton.setBackground(Color.WHITE);
            boton.setBounds((i % 2 == 0 ? 180 : 480), 120 + (i / 2) * 85, 240, 70);
            int finalI = i;
            
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (finalI) {
                        case 0: new GUI.VentanaFactorial(usuario, controlador).setVisible(true); break;
                        case 1: new GUI.VentanaPrimos(usuario, controlador).setVisible(true);break;
                        case 2:new GUI.VentanaFactorizacion(usuario, controlador).setVisible(true);break;
                        case 3:new GUI.VentanaPotencias(usuario, controlador).setVisible(true);break;
                        case 4:new GUI.VentanaFibonacci(usuario, controlador).setVisible(true); break;
                        case 5:new GUI.VentanaMatrices(usuario, controlador).setVisible(true);break;
                    }
                }
            });
            panelFondo.add(boton);
        }

        JButton btnEstado = new JButton("VER ESTADO DE LAS SOLICITUDES");
        btnEstado.setForeground(new Color(32, 35, 122));
        btnEstado.setBackground(Color.WHITE);
        btnEstado.setFocusPainted(false);
        btnEstado.setBounds(350, 400, 250, 30);
        btnEstado.addActionListener(e -> new VentanaConsultaEstado(usuario, controlador).setVisible(true));
        panelFondo.add(btnEstado);

        JLabel lblProyecto = new JLabel("II PROYECTO PROGRAMACION 2025 | KARLA BRENES KEVIN BRENES", SwingConstants.RIGHT);
        lblProyecto.setFont(new Font("Malgun Gothic", Font.PLAIN, 10));
        lblProyecto.setForeground(Color.LIGHT_GRAY);
        lblProyecto.setBounds(420, 445, 450, 15);
        panelFondo.add(lblProyecto);

        setContentPane(panelFondo);
    }

}
