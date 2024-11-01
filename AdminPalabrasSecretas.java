/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab4;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdminPalabrasSecretas {
    private List<String> palabras;
    private Random random;

    public AdminPalabrasSecretas() {
        palabras = new ArrayList<>();

        palabras = new ArrayList<>();
        palabras.add("hipotenusa");
        palabras.add("barcelona");
        palabras.add("computadora");
        palabras.add("ingeniero");
        palabras.add("firulais");
        palabras.add("manzana");
        palabras.add("banana");
        palabras.add("cereza");
        palabras.add("durazno");
        this.random = new Random();

    }

    public void agregarPalabra(String palabra) {
        if (!palabras.contains(palabra)) {
            palabras.add(palabra);
        }
    }

    public String seleccionarPalabraAleatoria() {

        if (palabras.isEmpty()) {
            return null;
        }

        Random random = new Random();
        return palabras.get(random.nextInt(palabras.size()));

    }

    public List<String> getPalabras() {
        return new ArrayList<>(palabras);
    }

    public List<String> getListaPalabras() {
        return palabras;
    }

    public void eliminarPalabra(String palabra) {
        palabras.remove(palabra);
    }

}