/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author HP
 */
public class JuegoAhorcadoFijo extends JuegoAhorcadoBase {
    
    
   private String palabraSecreta;
    private String palabraActual;
    private JFrame frame;
    private JTextField letraInput;
    private JLabel palabraLabel;
    private JPanel panelSeleccion;
    private JLabel intentosLabel;
    private JButton intentarButton;
    private AdminPalabrasSecretas adminPalabras;

    // Constructor
    public JuegoAhorcadoFijo(String secretW, int intentos, AdminPalabrasSecretas adminPalabras) {
        super(intentos);
        this.palabraSecreta = secretW;
        this.palabraActual = "_".repeat(secretW.length());
        this.adminPalabras = adminPalabras;
        inicializarGUI();
    }

    @Override
    public void actualizarPalabraActual(char letra) {
        StringBuilder palabraT = new StringBuilder(palabraActual);
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                palabraT.setCharAt(i, letra);
            }
        }
        palabraActual = palabraT.toString();
        palabraLabel.setText("Palabra: " + palabraActual);
    }

    @Override
    public boolean verificarLetra(char letra) {
        boolean esCorrecta = palabraSecreta.indexOf(letra) != -1;
        if (esCorrecta) {
            actualizarPalabraActual(letra);
        } else {
            intentos--;
        }
        return esCorrecta;
    }

    @Override
    public boolean hasGanado() {
        return palabraActual.equals(palabraSecreta);
    }

    @Override
    public void InicializarPalabraSecreta() {
        // No se necesita implemetnar aqui
    }

    @Override
    public void jugar() {
        frame.setVisible(true);
    }

    private void inicializarGUI() {
        frame = new JFrame("Juego de Ahorcado");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        panelSeleccion = new JPanel();
        JLabel seleccionarPalabraLabel = new JLabel("Selecciona una palabra:");
        JComboBox<String> comboPalabras = new JComboBox<>(adminPalabras.getPalabras().toArray(new String[0]));
        JButton jugarButton = new JButton("Jugar");

        panelSeleccion.add(seleccionarPalabraLabel);
        panelSeleccion.add(comboPalabras);
        panelSeleccion.add(jugarButton);
        frame.add(panelSeleccion, BorderLayout.NORTH);

        JPanel panelJuego = new JPanel();
        panelJuego.setLayout(new GridLayout(3, 1));

        palabraLabel = new JLabel("Palabra: ");
        intentosLabel = new JLabel("Intentos restantes: " + intentos);
        panelJuego.add(palabraLabel);
        panelJuego.add(intentosLabel);

        JPanel panelInput = new JPanel();
        panelInput.setLayout(new FlowLayout());

        JLabel letraLabel = new JLabel("Ingresa una letra:");
        letraInput = new JTextField(5);
        intentarButton = new JButton("Intentar");

        letraInput.setEnabled(false);
        intentarButton.setEnabled(false);

        panelInput.add(letraLabel);
        panelInput.add(letraInput);
        panelInput.add(intentarButton);

        frame.add(panelJuego, BorderLayout.CENTER);
        frame.add(panelInput, BorderLayout.SOUTH);

        // Acción para el botón Jugar
        jugarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String palabraSeleccionada = (String) comboPalabras.getSelectedItem();
                if (palabraSeleccionada != null) {
                    iniciarJuego(palabraSeleccionada);
                    letraInput.setEnabled(true);
                    intentarButton.setEnabled(true);
                    panelSeleccion.setVisible(false); // Oculta el panel de selección
                } else {
                    JOptionPane.showMessageDialog(frame, "Por favor, selecciona una palabra.");
                }
            }
        });

        intentarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarIntento();
            }
        });

        frame.setVisible(true);
    }

    private void iniciarJuego(String palabraSeleccionada) {
        palabraSecreta = palabraSeleccionada;
        palabraActual = "_".repeat(palabraSecreta.length());
        intentos = 6;

        palabraLabel.setText("Palabra: " + palabraActual);
        intentosLabel.setText("Intentos restantes: " + intentos);
    }

    private void realizarIntento() {
        if (letraInput.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese una letra");
            return;
        }

        char letra = letraInput.getText().toLowerCase().charAt(0);
        letraInput.setText("");

        if (verificarLetra(letra)) {
            JOptionPane.showMessageDialog(null, "Correcto");
        } else {
            intentosLabel.setText("Intentos restantes: " + intentos);
            JOptionPane.showMessageDialog(null, "Incorrecto");
        }

        if (hasGanado()) {
            JOptionPane.showMessageDialog(null, "Felicidades, has ganado. La palabra era: " + palabraSecreta);
            preguntarVolverAlMenu();
        } else if (intentos <= 0) {
            JOptionPane.showMessageDialog(null, "Lo sentimos, has perdido todas las oportunidades. La palabra era: " + palabraSecreta);
            preguntarVolverAlMenu();
        }
    }

    private void preguntarVolverAlMenu() {
        int respuesta = JOptionPane.showConfirmDialog(frame, "¿Quieres volver al menú?", "Fin del juego", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            frame.dispose();
            main.mostrarMenu();
        } else {
            frame.dispose();
        }
    }
    
}
