
package brickbreacker;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Diego Toro
 */
public class Pelota {
    int x, y, dx, dy, alto, ancho;//pelota
    int bx, by,bAlto, bAncho, lado;//bloques
    public Pelota(){
        x = (int)(Math.random()*800)+1;//valor aleatorio en x para la pelota
        y = 358; dx=2; dy=2; alto=12; ancho=12;
    }
    
    public void dibujar(Graphics2D g){
        g.setColor(Color.red);
        g.fillOval( x, y, ancho, alto);
        g.setColor(Color.yellow);
        g.drawOval( x, y, ancho, alto);
    }
    
    public void tenerBloque(int Bx, int By, int bancho, int balto){
        bx=Bx; by=By; bAlto=balto; bAncho=bancho;
    }
    
    public void moverP(boolean colisionBase, boolean colisionBloque, int Lado){
        lado=Lado;
        x+=dx;
        y+=dy;
        
        if(colisionBase==true){
            dy=-dy;
            y=550-alto;
        }
        
        if(colisionBloque == true){
            
            switch (lado) {
                case 4://Arriba
                    y= y-15;
                    dy=-2;
                    System.out.println("choca desde la abajo");
                    break;
                case 1://aa
                    y= y+15;
                    dy=2;
                    System.out.println("choca desde la arriba");
                    break;
                case 2://Izquierda
                    x= x-15;
                    dx=-dx;
                    System.out.println("choca desde la izquieda");
                    break;
                case 3: //derecha
                    x= x+15;
                    dx=-dx;
                    System.out.println("choca desde la derecha");
                    break;
            }
                
        }
           
        
        if(y>=580){
            y=350;// si la pelota sale
            x = (int)(Math.random()*800)+1;
            dy=-dy;
        }
        if(x>900-12 || x<0){
            dx=-dx;
        }
        
        if(y-28<=0){
            y=30;
            dy=-dy;
        }
        
        
    }
    
    public int getPx(){
        return x;
    }
    public int getPy(){
        return y;
    }
     
    
    
}

