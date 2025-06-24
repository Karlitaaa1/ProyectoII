/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author karla
 */
public class VentanaOperaciones extends JFrame {

    private Image backgroundImage;
    protected JPanel panelContenido;

    public VentanaOperaciones(String tituloContenido) {
        setTitle("Calculadora Computacional Distribuida");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // No cierra la app
        setResizable(false);
        backgroundImage = new ImageIcon(getClass().getResource("/gui/fondo.png")).getImage();

        JPanel panelFondo = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelFondo.setLayout(null);

        JLabel lblTitulo = new JLabel("CALCULADORA", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 26));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(0, 20, 900, 30);
        panelFondo.add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("COMPUTACIONAL DISTRIBUIDA", SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Verdana", Font.BOLD, 16));
        lblSubtitulo.setForeground(Color.WHITE);
        lblSubtitulo.setBounds(0, 50, 900, 20);
        panelFondo.add(lblSubtitulo);

        panelContenido = new JPanel();
        panelContenido.setLayout(null);
        panelContenido.setBackground(Color.WHITE);
        panelContenido.setBounds(50, 100, 800, 430);
        panelFondo.add(panelContenido);

        JLabel lblOperacion = new JLabel(tituloContenido);
        lblOperacion.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblOperacion.setForeground(new Color(32, 35, 122));
        lblOperacion.setBounds(20, 10, 760, 25);
        panelContenido.add(lblOperacion);

        setContentPane(panelFondo);
    }

}
