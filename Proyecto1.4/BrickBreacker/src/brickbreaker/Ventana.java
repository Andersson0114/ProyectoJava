package brickbreaker;

import java.awt.event.KeyListener;
import javax.swing.JFrame;
/**
 *
 * @author Andersson Cordoba
 *         Chirstian Medina
 *         Diego Toro
 */

//ventana principal del juego
public class Ventana extends JFrame {
    
    Tablero tablero = new Tablero(); //se crea el objeto del tablero
    
    //Ventaba es un JFramme
    public Ventana() throws InterruptedException{
        setTitle("BrickBreaker");//titulo
        add(tablero); //se agrega el tablero
        setSize(1200, 620);//se crea una ventana con estas dimensiones
        setLocationRelativeTo(null);//centrar la ventana
        setResizable(false);//no ajustable el tamaño
        
    }
    
}
