
package brickbreacker;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Andersson
 */
public class Pelota {
    int x, y, dx, dy, alto, ancho;//pelota
    int bx, by,bAlto, bAncho;//bloques
    public Pelota(){
        x = (int)(Math.random()*800)+1;//valor aleatorio en x para la pelota
        y = 350; dx=2; dy=-2; alto=12; ancho=12;
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
    
    public void moverP(boolean colisionBase, boolean colisionBloque){
        x+=dx;
        y+=dy;
        
        if(colisionBase==true){
            dy=-dy;
            y=550-alto;
        }
        
        if(colisionBloque== true){
            if( (x+13>=bx && x<bx) || (x+1<=bx+bAncho && x+12 > bx+bAncho )){
                
                dx=-dx;
                int bxx= bx+bAncho;
                int pxx = x+ancho;
                System.out.println("X: " + x + " PxM: " +pxx+ " Bx: " + bx + " AnchoBlo: " +bxx);
                
            }else{
                dy=-dy;
                int pyy= y+alto;
                int byy= by+bAlto;
                System.out.println("Y: " + y + " PYM: " +pyy+ " By: " + by + " AltoBlo: " +byy);

                
            }
            /*
            if( y+13>=by && y>by){//golpe desde arriba
                y=by-alto-3;
                dy=-dy;
                System.out.println("golpeado desde arriba: " +dy+ " y: "+ y);
            }else if(y<=bAlto ){//golpe desde abajo
               y=bAlto+1;
                dy=-dy;
                System.out.println("golpeado desde abajo: " +dy+ " y: "+ y);
            }else if(x+13>=bx){
                x= bx-ancho-3;
                dx=-dx;
                System.out.println("golpeado desde derecha: " +dx+ " x: "+ x);
                
            }else if(x<=bAncho){
                x=bAncho+1;
                dx=-dx;
                System.out.println("golpeado desde izquierda: " +dx+ " x: "+ x);

            }
            */
            
            /*
            
            if(lado==1){//arriba
                y=by-13;
                dy=-dy;
                
            }
            if(lado==2){
                dy=-dy;
                y=y+ancho;
                
            }*/
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

