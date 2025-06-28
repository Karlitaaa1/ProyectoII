/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Gestor.GestorConexion;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 *
 * @author karla
 */
public class VentanaLogin extends JFrame {

    private Image backgroundImage;

    public VentanaLogin() {
        setTitle("Login - Calculadora Distribuida");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 28));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(0, 40, 900, 30);
        panelFondo.add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("COMPUTACIONAL DISTRIBUIDA", SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Verdana", Font.BOLD, 16));
        lblSubtitulo.setForeground(Color.WHITE);
        lblSubtitulo.setBounds(0, 75, 900, 20);
        panelFondo.add(lblSubtitulo);

        JPanel fondoLogin = new JPanel();
        fondoLogin.setLayout(null);
        fondoLogin.setBackground(Color.WHITE);
        fondoLogin.setBounds(280, 150, 340, 220);
        fondoLogin.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        panelFondo.add(fondoLogin);

        JLabel lblBienvenido = new JLabel("BIENVENIDO!", SwingConstants.CENTER);
        lblBienvenido.setFont(new Font("Verdana", Font.BOLD, 20));
        lblBienvenido.setForeground(new Color(32, 35, 122));
        lblBienvenido.setBounds(0, 20, 340, 30);
        fondoLogin.add(lblBienvenido);

        JLabel lblUser = new JLabel("INGRESE SU ID O USERNAME:");
        lblUser.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblUser.setBounds(30, 70, 280, 20);
        fondoLogin.add(lblUser);

        JTextField txtUser = new JTextField();
        txtUser.setBounds(30, 95, 280, 30);
        fondoLogin.add(txtUser);

        JButton btnIngresar = new JButton("INGRESAR");
        btnIngresar.setBackground(new Color(32, 35, 122));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFocusPainted(false);
        btnIngresar.setBounds(110, 145, 120, 35);
        fondoLogin.add(btnIngresar);

        btnIngresar.addActionListener(e -> {
            String usuario = txtUser.getText().trim();
            if (!usuario.isEmpty()) {
                try {
                    // Crear y conectar gestor
                    GestorConexion gestor = new GestorConexion("localhost", 9090);
                    gestor.conectar();
                    VentanaPrincipal ventana = new VentanaPrincipal(usuario, gestor); // Pasar el usuario y el gestor a la ventana principal
                    ventana.setVisible(true);
                    dispose();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "No se pudo conectar con el servidor.\n" + ex.getMessage(),
                            "Error de conexion", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese su ID o username.");
            }
        });


        JLabel lblProyecto = new JLabel("II PROYECTO PROGRAMACION 2025 | KARLA BRENES KEVIN BRENES", SwingConstants.RIGHT);
        lblProyecto.setFont(new Font("Verdana", Font.PLAIN, 10));
        lblProyecto.setForeground(Color.LIGHT_GRAY);
        lblProyecto.setBounds(420, 445, 450, 15);
        panelFondo.add(lblProyecto);

        setContentPane(panelFondo);

    }
}
