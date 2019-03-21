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
 * @author Andersson
 */
public class Bloques {
    public Clip audio;
    public String ruta="/Sonidos/";
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
                    bloques[i][j]=1;
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
        for (int i = 1; i<bloques.length; i++){
            for(int j = 0; j< bloques[0].length; j++){
                
                if(bloques[i][j]>0){
                    if(bloques[i][j]==1){
                        Xmin = j*ladrilloAncho+60;
                        Ymin = i*LadrilloAlto+50;
                        Xmax = Xmin+ladrilloAncho;
                        Ymax = Ymin+LadrilloAlto;
                        g.setColor(Color.cyan); 
                        g.fillRect(Xmin, Ymin, ladrilloAncho, LadrilloAlto);
                        g.setStroke(new BasicStroke(2));
                        g.setColor(Color.BLACK);
                        g.drawRect(Xmin, Ymin, ladrilloAncho, LadrilloAlto);
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
        if(new Rectangle(px, py, 12, 12).intersects(new Rectangle(Xmin, Ymin, ladrilloAncho, LadrilloAlto))){
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

    public void Sonido(String archivo){
        try {
            audio=AudioSystem.getClip();
            audio.open(AudioSystem.getAudioInputStream(getClass().getResource(ruta + archivo + ".mpeg")));
            audio.start();
        } catch (Exception e) {
        }
    }
    
   
}
