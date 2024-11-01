/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab4;

/**
 *
 * @author HP
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
public class main {
    public static void main(String[] args) {
        mostrarMenu();
    }

    public static void mostrarMenu() {

        JFrame frame = new JFrame("Seleccionar Juego de Ahorcado");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);

        AdminPalabrasSecretas adminPalabras = new AdminPalabrasSecretas();

        JLabel labelSeleccion = new JLabel("Seleccione el tipo de juego:");
        JButton botonFijo = new JButton("Juego con Palabra Fija");
        JButton botonAzar = new JButton("Juego con Palabra Aleatoria");
        JButton botonAdminPalabras = new JButton("Administrar Palabras");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 5, 5));
        panel.add(labelSeleccion);
        panel.add(botonFijo);
        panel.add(botonAzar);
        panel.add(botonAdminPalabras);
        frame.add(panel);

        botonFijo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String palabraFija = "programacion";
                int intentos = 6;
                JuegoAhorcadoFijo juegoFijo = new JuegoAhorcadoFijo(palabraFija, intentos, adminPalabras);
                juegoFijo.jugar();
                frame.dispose();
            }
        });

        botonAzar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int intentos = 6;
                JuegoAhorcadoAzar juegoAzar = new JuegoAhorcadoAzar(adminPalabras, intentos);
                juegoAzar.jugar();
                frame.dispose();
            }
        });

        botonAdminPalabras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaAdminPalabras(adminPalabras);
            }
        });

        frame.setVisible(true);
    }

    private static void abrirVentanaAdminPalabras(AdminPalabrasSecretas adminPalabras) {
        JFrame frameAdmin = new JFrame("Administrar Palabras Secretas");
        frameAdmin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameAdmin.setSize(500, 450);
        frameAdmin.setLocationRelativeTo(null);

        JLabel labelPalabras = new JLabel("Palabras actuales: " + adminPalabras.getPalabras());
        JTextField inputNuevaPalabra = new JTextField(15);
        JButton botonAgregar = new JButton("Agregar Palabra");
        JButton botonEliminar = new JButton("Eliminar Palabra");

        JButton volver = new JButton("volver");

        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevaPalabra = inputNuevaPalabra.getText().trim().toLowerCase();
                if (nuevaPalabra.isEmpty()) {
                    JOptionPane.showMessageDialog(frameAdmin, "La palabra no puede estar vacía.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (adminPalabras.getPalabras().contains(nuevaPalabra)) {
                    JOptionPane.showMessageDialog(frameAdmin, "La palabra ya existe en la lista.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    adminPalabras.agregarPalabra(nuevaPalabra);
                    labelPalabras.setText("Palabras actuales: " + adminPalabras.getPalabras());
                    inputNuevaPalabra.setText("");
                }
            }
        });

        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String palabraAEliminar = inputNuevaPalabra.getText().trim().toLowerCase();
                if (palabraAEliminar.isEmpty()) {
                    JOptionPane.showMessageDialog(frameAdmin, "Ingrese una palabra para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!adminPalabras.getPalabras().contains(palabraAEliminar)) {
                    JOptionPane.showMessageDialog(frameAdmin, "La palabra no se encuentra en la lista.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    int confirm = JOptionPane.showConfirmDialog(frameAdmin, "¿Está seguro de que desea eliminar esta palabra?", "Confirmación", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        adminPalabras.eliminarPalabra(palabraAEliminar);
                        labelPalabras.setText("Palabras actuales: " + adminPalabras.getPalabras());
                        inputNuevaPalabra.setText("");
                    }
                }
            }
        });

        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameAdmin.dispose();
            }
        });

        JPanel panelAdmin = new JPanel();
        panelAdmin.setLayout(new GridLayout(5, 1, 5, 5));
        panelAdmin.add(labelPalabras);
        panelAdmin.add(inputNuevaPalabra);
        panelAdmin.add(botonAgregar);
        panelAdmin.add(botonEliminar);
        panelAdmin.add(volver);

        frameAdmin.add(panelAdmin);
        frameAdmin.setVisible(true);
    }
}
