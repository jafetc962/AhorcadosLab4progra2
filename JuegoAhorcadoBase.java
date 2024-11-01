/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab4;

/**
 *
 * @author HP
 */


public abstract class JuegoAhorcadoBase implements JuegoAhorcado{
    protected String palabraSecreta;
    protected String palabraActual;
    protected int intentos;
    
    public JuegoAhorcadoBase(int intentos){
        this.intentos=intentos;
    }
    
    protected void inicializarPalabraActual(){
        
        palabraActual = "_".repeat(palabraSecreta.length());
        
    }
    
    protected abstract boolean verificarLetra(char letra);
    
    protected abstract boolean hasGanado();
    
    protected abstract void actualizarPalabraActual(char letra);
}
