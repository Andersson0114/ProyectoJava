/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreacker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Diego Toro
 */
// COLISIONES ARREGLADAS 
public class Bloques {
    
    public int bloques[][];
    public int duros[][];
    public int ladrilloAncho, LadrilloAlto;
    public int pelotaX, pelotaY, bX, bY, Xmax, Xmin, Ymax, Ymin;
    public int px, py;
    public boolean colisionBloque;
   public int cont=0, Lado=0;
    public  Bloques(int fil, int col, int nivel){//recibe las filas(fil) y columnas(col)
        
        bloques = new int[fil][col];
        duros = new int[fil][col];
        int n= col*fil;
        
        if(nivel==1){
            for (int i = 0; i<bloques.length; i++){
                for(int j = 0; j< bloques[0].length; j++){
                    bloques[i][j]=2;
                }
                
            }
        }
        
            
        ladrilloAncho= 770/col;
        LadrilloAlto= 240/fil;
    }
    
    public void recibirPelota(int Px, int Py){
        pelotaX=Px;
        pelotaY=Py;
    }
    
    public void  dibujar(Graphics2D g){
         colisionBloque=false;
        for (int i = 0; i<bloques.length; i++){
            for(int j = 0; j< bloques[0].length; j++){
                
                if(bloques[i][j]>0){
                    if(bloques[i][j]==1){
                        Xmin = j*ladrilloAncho+70;
                        Ymin = i*LadrilloAlto+50;
                        Xmax = Xmin+ladrilloAncho;
                        Ymax = Ymin+LadrilloAlto;
                       // g.setColor(Color.cyan); 
                        //g.fillRect(Xmin, Ymin, ladrilloAncho, LadrilloAlto);
                        g.setStroke(new BasicStroke(2));
                        g.setColor(Color.BLACK);
                        g.drawRect(Xmin, Ymin, ladrilloAncho, LadrilloAlto);
                        
                        g.setColor(Color.cyan); 
                        g.fillRect(Xmin, Ymin, ladrilloAncho, 2);
                        g.fillRect(Xmin, Ymin+3, 2, LadrilloAlto-3);
                        
                        g.fillRect(Xmax-3, Ymin+3, 2, LadrilloAlto-3);
                        g.fillRect(Xmin, Ymax-2, ladrilloAncho, 2);
                        evaluar(i, j);
                        
                    }

                    if(bloques[i][j]==2){
                        Xmin = j*ladrilloAncho+70;
                        Ymin = i*LadrilloAlto+50;
                        Xmax = Xmin+ladrilloAncho;
                        Ymax = Ymin+LadrilloAlto;
                        //g.setColor(Color.blue); 
                        //g.fillRect(Xmin, Ymin, ladrilloAncho, LadrilloAlto);
                        g.setStroke(new BasicStroke(2));
                        g.setColor(Color.BLACK);
                        g.drawRect(Xmin, Ymin, ladrilloAncho, LadrilloAlto);
                        
                        g.setColor(Color.blue);
                        g.fillRect(Xmin, Ymin, ladrilloAncho, 2);
                        g.setColor(Color.yellow);
                        g.fillRect(Xmin, Ymin+3, 2, LadrilloAlto-3);
                        
                        g.setColor(Color.white);
                        g.fillRect(Xmax-3, Ymin+3, 2, LadrilloAlto-3);
                        g.setColor(Color.red);
                        g.fillRect(Xmin, Ymax-2, ladrilloAncho, 2);
                        evaluar(i, j);
                    }
                    
                }
                
            }
        }
    }
    
    public void tenerPelota(int px, int py){
        this.px=px;
        this.py=py;
    }
    public void evaluar(int i, int j){
        Xmin = j*ladrilloAncho+60;
        Ymin = i*LadrilloAlto+50;
        Xmax = Xmin+ladrilloAncho;
        Ymax = Ymin+LadrilloAlto;
        if((new Rectangle(px, py+15, 13, 13).intersects(new Rectangle(Xmin, Ymin, ladrilloAncho, 4)))){
            Lado=4;//aarriba
            colisionBloque= true;
            bloques[i][j]--;
        }else if((new Rectangle(px, py, 13, 13).intersects(new Rectangle(Xmin, Ymax-2, ladrilloAncho, 2)))){
            Lado=1;//aa
            colisionBloque= true;
            bloques[i][j]--;
        }else if((new Rectangle(px, py, 12, 13).intersects(new Rectangle(Xmin, Ymin+3, 2, LadrilloAlto-3)))){
            Lado=2;//izquierda
            colisionBloque= true;
            bloques[i][j]--;
        }else if((new Rectangle(px-8, py, 13, 13).intersects(new Rectangle(Xmax, Ymin+3, 2, LadrilloAlto-3)))){
            Lado=3;//derecha
            colisionBloque= true;
            bloques[i][j]--;
        }
        
        
    }
    
   
    
    public int lado(){
        return Lado;
    }
    
    public boolean colision(){
        return colisionBloque;
    }
    
    public int getAncho(){
        return Xmax;
    }
    public int getAlto(){
        return Ymax;
    }
    public int getBx(){
        return Xmin;
    }
    public int getBy(){
        return Ymin;
    }

    
    
   
}
