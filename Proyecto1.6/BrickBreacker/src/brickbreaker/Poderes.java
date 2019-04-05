package brickbreaker;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
/**
 *
 * @author Andersson Cordoba
 *         Chirstian Medina
 *         Diego Toro
 */
public class Poderes {
    int x, y, Bx=70, By=100, poder=0;
    int bx[]= new int[10];
    int by[]= new int[10];
    boolean bala[]= new boolean[10];
    boolean colision, espace, fuera;
    //recibir la posicion de la base
    public void recibir(int x, int y, int ancho){
        //donde seldrá la bala
        this.x=x+(ancho/2);
         this.y=y;
    }
    //dibujar la bala
    public void dibujar(Graphics2D g){
        
        if(x>0){
             g.setColor(Color.yellow);
             g.fillRect(x, y, 5, 15);
        }
       mover();
    }
    //mover
    public void Enjuego(boolean fuera){
        this.fuera=fuera;
    }
    
    public void mover(){
        y-=2;
        if(fuera==false){
            y=620;
        }
    }
    //retornar los valores
    public int getPx(){
        return x;
    }
    
    public int getPy(){
        return y;
    }
    
}
