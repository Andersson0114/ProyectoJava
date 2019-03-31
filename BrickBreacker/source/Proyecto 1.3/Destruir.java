/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Andersson Cordoba
 *             Chirstian Medina
 *             Diego Toro
 */
public class Destruir {

    int x, x1, x2, x3,y, y1, y2,xp, yp, cont=0, poder;
    boolean activo;
    public Destruir() {
       
    }
    
    public void recibir(int i, int j){
        x=i; y=j;
        xp=i; yp=j;
        x1=x+20;
        x2=x1+20;
        x3=x;
        
        y1=y;
        y2=y+20;
    }
    
    public void activar(boolean activate, int poderes){
        activo=activate;
        poder=poderes;
    }
    
    public void dibujar(Graphics2D g){
        
            if(poder==1 || poder==2 || poder==3){
                g.setColor(Color.white);
                g.fillRect(xp, yp, 50, 10);
            }    
        
            if(cont<20){
                g.setColor(Color.green);
                
            }else{
                g.setColor(Color.yellow);
            }
            
            
            if(x>0){
            if(x2<x+160){
                g.fillRect(x1, y2, 10, 10);
                g.fillRect(x2, y2, 10, 10);
                g.fillRect(x3, y2, 10, 10);
                g.fillRect(x1, y1, 10, 10);
                g.fillRect(x2, y1, 10, 10);
                g.fillRect(x3, y1, 10, 10);
            }}
                
       

        System.out.println(cont);
        cont++;mover();
    }
    
    public void mover(){
        if(cont<30)
        {
            x1-=1; x2+=1;
                y2+=1;
                y1-=1;
        }else{
            cont=0;
        }
        
        
        yp+=1;
        
    }
}
