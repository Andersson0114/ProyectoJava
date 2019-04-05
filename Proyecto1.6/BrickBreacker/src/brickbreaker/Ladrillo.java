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
    
    public Ladrillo(int x, int y, int poder){//constructor
        this.x=x; this.y=y; this.poder=poder;
    }
    
    //recibir la ubicacion de la base, para detectar colisiones
    public void RecibirBase(int bx, int by, int ancho){
        this.bx=bx; this.by=by; this.ancho=ancho;
        if(new Rectangle(bx, by, ancho, 10). intersects(new Rectangle(x, y,30, 10))){
            colisionBase=true; y=0; dy=-1;
        }else{colisionBase=false;}
    }
    
    //saber si no ha perdido vidas o el juego
    public void Enjuego(boolean fuera){
        this.fuera=fuera;
    }
    
    //dibujar
    public void dibujar(Graphics2D g){
        if(y<560 ){
            //g.setColor(new Color ((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1));
            if(poder==1){g.setColor(Color.orange); }
            if(poder==2){g.setColor(Color.red); }
            if(poder==3){g.setColor(Color.green); }
            
            g.fillRect(x, y, 30 , 10);
            g.setColor(Color.white);
            g.drawRect(x, y, 30, 10);
        }
        mover();
    }
    //mober el ladrillo
    public void mover(){
        
        if(fuera==false){
            y=0; x=-50;
            dy=0;
        }
        y+=dy;
    }

    //retornar el porder del ladrillo
    public int getPoder() {
        return poder;
    }

    //retornar estado de colision
    public boolean ColisionBase() {
        return colisionBase;
    }
    
    
}
