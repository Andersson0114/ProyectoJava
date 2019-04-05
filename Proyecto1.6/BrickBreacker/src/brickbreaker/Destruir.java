package brickbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 *
 * @author Andersson Cordoba
 *         Chirstian Medina
 *         Diego Toro
 */
public class Destruir {

    int x, x1, x2, x3,y, y1, y2;
    boolean activo, colisionBase, espace;
    //constructor
    public Destruir (int x, int y){
        this.x=x; this.y=y;
        x1=x;
        x2=x1+20;
        x3=x2+20;
        y1=y;
        y2=y+20;
    }
    
//dibujar la animacion
    public void dibujar(Graphics2D g){
        g.setColor(new Color ((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1));
        if(x>0){
            if(x3<x+150){
                g.fillRect(x1, y2, 7, 7);
                g.fillRect(x2, y2, 7, 7);
                g.fillRect(x3, y2, 7, 7);
                g.fillRect(x1, y1, 7, 7);
                g.fillRect(x2, y1, 7, 7);
                g.fillRect(x3, y1, 7, 7); 
            }   
        } 
        mover();
    }
    
    public void mover(){
        //Movimiento
        x1-=2; x3+=2;
        y2+=1; y1-=1;
    }
    //retornar valores
    public int getBx(){
        return x;
    }
    
    public boolean colisio(){
        return colisionBase;
    }
    
    
            
}
