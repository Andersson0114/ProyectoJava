/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Andersson Cordoba
 *         Chirstian Medina
 *         Diego Toro
 */
public class Ladrillo {
    private int x, y, dy=1, bx, by, ancho, poder=0;
    private boolean colisionBase=false, fuera;
    
    public Ladrillo(int x, int y, int poder){
        this.x=x; this.y=y; this.poder=poder;
    }
    
    public void RecibirBase(int bx, int by, int ancho){
        this.bx=bx; this.by=by; this.ancho=ancho;
        if(new Rectangle(bx, by, ancho, 10). intersects(new Rectangle(x, y,30, 10))){
            colisionBase=true; y=0; dy=-1;
        }else{colisionBase=false;}
    }
    
    public void Enjuego(boolean fuera){
        this.fuera=fuera;
    }
    
    public void dibujar(Graphics2D g){
        if(y<560 ){
            g.setColor(new Color ((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1));
            g.fillRect(x, y, 30 , 10);
        }
        mover();
    }
    public void mover(){
        
        if(fuera==false){
            y=0; x=-50;
            dy=0;
        }
        y+=dy;
    }

    public int getPoder() {
        return poder;
    }

    public boolean ColisionBase() {
        return colisionBase;
    }
    
    
}
