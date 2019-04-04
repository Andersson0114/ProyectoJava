package brickbreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.Timer;

/**
 *
 * @author Andersson Cordoba
 *         Chirstian Medina
 *         Diego Toro
 */
public class Base {
    //variables
    private int x, y, dx,ancho=100, alto=10, poder=0;
    private boolean enter=false, reinicio;
    
    
    public Base (){
        x=400; //ubicacion en x de la base
        y=550; //ubicacion en y de la base
        
    }
    
   
    //recibir poder y reinicio(para quitar el poder por si se pierde una vida)
    public void RecibirPo(int poder, boolean reinicio){
        this.reinicio=reinicio;//reinicio representa el fin del juego o el perder una vida
        this.poder=poder;//representa el poder adquirido
        
        if( this.reinicio==true){//reiniciar valores
            ancho=100;//reiniciar tamaño de la base(poder perdido)
            this.poder=0;//reasignar el valor del poder
            x=400;
        }
        
        if(this.poder==1 && ancho<220){//agrandar base(poder)
            ancho+=40;
        }
    }
    
    //pintar en el panel
    public void dibujar(Graphics2D g){
        g.setColor(Color.orange);//darle el color a la base
        if(poder==1){//dar color aleatoria si el poder es 1 (crecer base)
            g.setColor(new Color ((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1));
        }
        g.fillRect( x, y, ancho, alto);//pintar la base
        g.setColor(Color.red);//darle el color a la base
        g.drawRect( x, y, ancho, alto);//pintar la base
        
    }
    
    //detectar teclas presionada
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT){//detecta si la tecla es derechas
            dx= 7;//direccion de la base
        }
        
        if(key == KeyEvent.VK_LEFT){//detecta si la tecla es izquierda
            dx= -7;//direccion de la base
        }
        
    }
    
    //detectar si se levantó la tecla
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT){
            dx= 0;
        }
        if(key == KeyEvent.VK_LEFT){
            dx= 0;
        }
    }
    
    //metodo para mover
    public void  mover(){
        //si la base colisiona con la pared, este no avanza
        if(x<0) {
           x=1;  
        }
        
        //si la base colisiona con la pared, este no avanza
        if( x>920-ancho)
        {
            x= 920-ancho-4;
        }
        //sumarle a x el valor de la direccion
        x+=dx; 
        
    }
    
    //retonar valores
      public boolean reiniciar(){
        return enter;
    }
    
    public int getBx(){
        return x;
    }
    public int getBy(){
        return y;
    }
    
 
    public int getAncho(){
        return ancho;
    }
    public int getAlto(){
        return alto;
    }
    
}

