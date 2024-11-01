/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab4;

/**
 *
 * @author HP
 */
import java.util.List;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JuegoAhorcadoAzar extends JuegoAhorcadoBase {

   private AdminPalabrasSecretas adminPalabras;
    private JFrame frame;
    private JLabel labelPalabraActual;
    private JLabel labelIntentos;
    private JTextField inputLetra;
    private JButton botonIntentar;

    public JuegoAhorcadoAzar(AdminPalabrasSecretas adminPalabras, int intentos) {
        super(intentos);
        this.adminPalabras = adminPalabras;
        InicializarPalabraSecreta();
        inicializarPalabraActual();
        configurarInterfaz();
    }

    @Override
    public void InicializarPalabraSecreta() {
        palabraSecreta = adminPalabras.seleccionarPalabraAleatoria();
    }

    @Override
    protected boolean verificarLetra(char letra) {
        boolean esCorrecta = palabraSecreta.indexOf(letra) != -1;
        if (esCorrecta) {
            actualizarPalabraActual(letra);
        } else {
            intentos--;
        }
        return esCorrecta;
    }

    @Override
    protected boolean hasGanado() {
        return palabraActual.equals(palabraSecreta);
    }

    @Override
    protected void actualizarPalabraActual(char letra) {
        StringBuilder nuevaPalabraActual = new StringBuilder(palabraActual);
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                nuevaPalabraActual.setCharAt(i, letra);
            }
        }
        palabraActual = nuevaPalabraActual.toString();
    }

    private void configurarInterfaz() {
        frame = new JFrame("Juego de Ahorcado");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);

        labelPalabraActual = new JLabel("Palabra: " + palabraActual);
        labelPalabraActual.setFont(new Font("Arial", Font.PLAIN, 24));
        labelIntentos = new JLabel("Intentos restantes: " + intentos);
        labelIntentos.setFont(new Font("Arial", Font.PLAIN, 16));

        inputLetra = new JTextField(1);
        botonIntentar = new JButton("Intentar");

        botonIntentar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = inputLetra.getText().trim();
                if (!texto.isEmpty()) {
                    char letra = texto.charAt(0);
                    procesarIntento(letra);
                }
                inputLetra.setText("");
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(labelPalabraActual);
        panel.add(labelIntentos);
        panel.add(inputLetra);
        panel.add(botonIntentar);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void procesarIntento(char letra) {
        boolean esCorrecta = verificarLetra(letra);

        if (hasGanado()) {
            JOptionPane.showMessageDialog(frame, "¡Felicidades! Has ganado. La palabra era: " + palabraSecreta);
            preguntarVolverAlMenu();
        } else if (intentos <= 0) {
            JOptionPane.showMessageDialog(frame, "Lo siento, has perdido. La palabra era: " + palabraSecreta);
            preguntarVolverAlMenu();
        } else {
            labelPalabraActual.setText("Palabra: " + palabraActual);
            labelIntentos.setText("Intentos restantes: " + intentos);
            JOptionPane.showMessageDialog(frame, esCorrecta ? "¡Letra correcta!" : "Letra incorrecta.");
        }
    }

    public void jugar() {
        // El juego ya comienza al configurar la interfaz en el constructor
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
