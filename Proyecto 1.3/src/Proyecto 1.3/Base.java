


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Andersson Cordoba
 *            Chirstian Medina
 *            Diego Toro
 */
public class Base {
    public int x, y, dx, ancho=100, alto=10, enter=0;
    
    public Base (){
        x=400; //ubicacion en x de la base
        y=550; //ubicacion en y de la base
    }
    
    //pintar en el panel
    public void dibujar(Graphics2D g){
        g.setColor(Color.orange);//darle el color a la base
        g.fillRect( x, y, ancho, alto);//pintar la base
        g.setColor(Color.red);//darle el color a la base
        g.drawRect( x, y, ancho, alto);//pintar la base
       
    }
    
    //detectar teclas presionada
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT){//detecta si la tecla es derechas
            dx= 5;//direccion de la base
            enter=0;
        }
        if(key == KeyEvent.VK_LEFT){//detecta si la tecla es izquierda
            dx= -5;//direccion de la base
            enter=0;
        }
        if(key == KeyEvent.VK_LEFT){//detecta si la tecla es izquierda
            enter=1;
        }
    }
    
    public int Enter(){
        return enter;
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
    public int getBx(){
        return x;
    }
    public int getBy(){
        return x;
    }
    public int getAncho(){
        return ancho;
    }
    public int getAlto(){
        return alto;
    }
    
}

