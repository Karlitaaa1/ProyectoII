/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Domain.ControladorCliente;
import javax.swing.*;
import java.awt.*;

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
        System.out.println("CLIENTE: VentanaLogin abierta"); //QUITAR EN UN FUTURO, ES SOLO PRUEBA

        JPanel panelFondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelFondo.setLayout(null);

        JLabel lblTitulo = new JLabel("CALCULADORA", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Malgun Gothic", Font.BOLD, 30));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(0, 45, 900, 30);
        panelFondo.add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("COMPUTACIONAL DISTRIBUIDA", SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Malgun Gothic", Font.BOLD, 25));
        lblSubtitulo.setForeground(Color.WHITE);
        lblSubtitulo.setBounds(0, 80, 900, 20);
        panelFondo.add(lblSubtitulo);

        JPanel panelFondoLogin = new JPanel();
        panelFondoLogin.setLayout(null);
        panelFondoLogin.setBackground(Color.WHITE);
        panelFondoLogin.setBounds(280, 150, 340, 220);
        panelFondo.add(panelFondoLogin);

        JLabel lblBienvenido = new JLabel("BIENVENIDO!", SwingConstants.CENTER);
        lblBienvenido.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
        lblBienvenido.setForeground(new Color(32, 35, 122));
        lblBienvenido.setBounds(0, 20, 340, 30);
        panelFondoLogin.add(lblBienvenido);

        JLabel lblUser = new JLabel("INGRESE SU ID O USERNAME:");
        lblUser.setFont(new Font("Malgun Gothic", Font.PLAIN, 15));
        lblUser.setBounds(30, 70, 280, 20);
        panelFondoLogin.add(lblUser);

        JTextField txtUser = new JTextField();
        txtUser.setBounds(30, 95, 280, 30);
        panelFondoLogin.add(txtUser);

        JButton btnIngresar = new JButton("INGRESAR");
        btnIngresar.setBackground(new Color(32, 35, 122));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFocusPainted(false);
        btnIngresar.setFont(new Font ("Malgun Gothic", Font.BOLD,15));
        btnIngresar.setBounds(110, 145, 120, 35);
        panelFondoLogin.add(btnIngresar);

        btnIngresar.addActionListener(e -> {
            String usuario = txtUser.getText().trim();
            if (!usuario.isEmpty()) {
                try {
                    ControladorCliente controlador = new ControladorCliente("localhost", 9090); // Se crea un solo controlador que se reutilizará
                    // (Opcional) Puedes hacer un ping/handshake aquí para verificar la conexión antes de abrir la ventana principal.
                    VentanaPrincipal ventana = new VentanaPrincipal(usuario, controlador); // Pasas el controlador
                    ventana.setVisible(true);
                    dispose();
                } catch (Exception ex) { // Cambiado de IOException
                    JOptionPane.showMessageDialog(this, "No se pudo conectar con el servidor.\n"
                            + "Por favor, verifique que el servidor esté en línea.\n\n"
                            + "Error: " + ex.getMessage(),
                            "Error de Conexión", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Si el campo de usuario está vacío
                JOptionPane.showMessageDialog(this, "Por favor, ingrese su ID o username.");
            }
        });


        JLabel lblProyecto = new JLabel("II PROYECTO PROGRAMACION 2025 | KARLA BRENES KEVIN BRENES", SwingConstants.RIGHT);
        lblProyecto.setFont(new Font("Malgun Gothic", Font.PLAIN, 10));
        lblProyecto.setForeground(Color.LIGHT_GRAY);
        lblProyecto.setBounds(420, 445, 450, 15);
        panelFondo.add(lblProyecto);

        setContentPane(panelFondo);

    }
}
