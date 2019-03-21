/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreacker;

import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author Andersson
 */
public class Ventana extends JFrame {
    
    Tablero tablero = new Tablero(); //se crea el objeto del tablero
    
    //se crea una ventana con estas dimensiones
    //Ventaba es un JFramme
    public Ventana() throws InterruptedException{
        setTitle("BrickBreaker");//titulo
        add(tablero); //se agrega el tablero
        setSize(900, 620);//dimensiones
        setLocationRelativeTo(null);//centrar la ventana
        setResizable(false);//no ajustable el tamaño
        
        
    }
    
}
