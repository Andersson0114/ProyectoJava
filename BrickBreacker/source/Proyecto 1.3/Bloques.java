/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Andersson Cordoba
 *             Chirstian Medina
 *             Diego Toro
 */
public class Bloques {
    public Clip audio;
    public String ruta="/Sonidos/";
    public int bloques[][], elPoder[];
    public int ladrilloAncho, LadrilloAlto;
    public int pelotaX, pelotaY, bX, bY, Xmax, Xmin, Ymax, Ymin, a, b,c, poder=0; 
    public int px, py;
    public boolean colisionBloque, pasar=false,colisionPre;
   public int Lado=0, bloque=0, nivel=0;
   Destruir destruir = new Destruir();
   
   
    public  Bloques(int fil, int col){//recibe las filas(fil) y columnas(col)
        bloques = new int[fil][col];
        elPoder = new int[3];
        ladrilloAncho= 770/col;
        LadrilloAlto= 240/fil;
    }
    
    
    
    public void asignarNivel(int Nivel){
        nivel=Nivel; elPoder[0]=1;  elPoder[1]=2; elPoder[2]=3;
        if(nivel==1){
            bloque=0;
            for (int i = 1; i<bloques.length; i++){
                for(int j = 0; j< bloques[0].length; j++){
                    bloques[i][j]=1;//bloque fragil
                    bloque++;
                }
            }
            
            
        }
        
        if(nivel==2){
            
            for (int i = 5; i<bloques.length; i++){
                    for(int j = 0; j< bloques[0].length; j++){
                        if(j%2==0){
                            bloques[i][j]=4;
                        }
                    }
             }
            bloque=0;
            for (int i = 2; i<3; i++){
                    for(int j = 0; j< bloques[0].length; j++){
                        if(j%2!=0){
                            bloques[i][j]=4;
                        }
                    }
                }
            for (int i = 0; i<bloques.length; i++){
                for(int j = i; j< bloques[0].length-i; j++){
                    if(bloques[i][j]<4){
                        if(i%2==0){
                            bloques[i][j]=1;//bloque duro
                            bloque++;
                        }else{
                            bloques[i][j]=2;//bloque fragil
                            bloque+=2;
                        } 
                    } 
                } 
            }
            
            
        }
        
        if(nivel==3){
            bloque=0;
            for (int i = 0; i<bloques.length; i++){
                for(int j = 0; j< bloques[0].length; j++){
                    bloques[i][j]=0;
                }
            }
            for (int i = 0; i<3; i++){
                for(int j = i; j< bloques[0].length-i; j++){
                    bloques[i][j]=1;
                    bloque++;
                } 
            }
            for (int i = 3; i<4; i++){
                    for(int j = 0; j< bloques[0].length; j++){
                        if(j%2==0){
                            bloques[i][j]=4;
                        }
                    }
                }
            for (int i =4 ; i<bloques.length; i++){
                for(int j = 0; j<bloques[0].length; j++){
                    bloques[i][j]=2;  
                    bloque+=2;
                } 
            }
            bloques[4][10]=0; bloques[4][0]=0;bloque-=4;
        }
         
        //poderes
        
                    a=(int)(Math.random()*4)+1;
                    b=(int)(Math.random()*8)+1;
                    bloques[a][b]=3;
                    
                        
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
                        g.setColor(Color.cyan); 
                        g.fillRect(Xmin, Ymin, ladrilloAncho, LadrilloAlto);
                        g.setStroke(new BasicStroke(2));
                        g.setColor(Color.BLACK);
                        g.drawRect(Xmin, Ymin, ladrilloAncho, LadrilloAlto);
                        
                        /*
                        g.setColor(Color.cyan); 
                        g.fillRect(Xmin, Ymin, ladrilloAncho, 2);
                        g.fillRect(Xmin, Ymin+3, 2, LadrilloAlto-3);
                        
                        g.fillRect(Xmax-3, Ymin+3, 2, LadrilloAlto-3);
                        g.fillRect(Xmin, Ymax-2, ladrilloAncho, 2);*/
                        evaluar(i, j);
                        
                    }
                    
                    if(bloques[i][j]==3){
                        Xmin = j*ladrilloAncho+70;
                        Ymin = i*LadrilloAlto+50;
                        Xmax = Xmin+ladrilloAncho;
                        Ymax = Ymin+LadrilloAlto;
                        g.setColor(Color.red); 
                        g.fillRect(Xmin, Ymin, ladrilloAncho, LadrilloAlto);
                        g.setStroke(new BasicStroke(2));
                        g.setColor(Color.BLACK);
                        g.drawRect(Xmin, Ymin, ladrilloAncho, LadrilloAlto);
                       
                        evaluar(i, j);
                        
                    }

                    if(bloques[i][j]==2){
                        Xmin = j*ladrilloAncho+70;
                        Ymin = i*LadrilloAlto+50;
                        Xmax = Xmin+ladrilloAncho;
                        Ymax = Ymin+LadrilloAlto;
                        g.setColor(Color.blue); 
                        g.fillRect(Xmin, Ymin, ladrilloAncho, LadrilloAlto);
                        g.setStroke(new BasicStroke(2));
                        g.setColor(Color.BLACK);
                        g.drawRect(Xmin, Ymin, ladrilloAncho, LadrilloAlto);
                        
                        evaluar(i, j);
                    }
                    
                    if(bloques[i][j]==4){
                        Xmin = j*ladrilloAncho+70;
                        Ymin = i*LadrilloAlto+50;
                        Xmax = Xmin+ladrilloAncho;
                        Ymax = Ymin+LadrilloAlto;
                        g.setColor(Color.gray); 
                        g.fillRect(Xmin+2, Ymin+2, ladrilloAncho-2, LadrilloAlto-2);
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
        
        
        
        if((new Rectangle(px, py+13, 13, 13).intersects(new Rectangle(Xmin, Ymin, ladrilloAncho, 4)))){
            Lado=4;//arriba
            colisionBloque= true;
            if(bloques[i][j]==3){ colisionPre =true; poder = 1;bloques[i][j]=0;}else{ colisionPre =false; }
            if(bloques[i][j]<4){
                if(bloques[i][j]==2){Sonido("g7");}
                else{Sonido("g6");}
                 bloque--;bloques[i][j]--;poder=0;
                 
            }
            else{Sonido("g8");poder=0;}
            
        }else if((new Rectangle(px, py, 13, 13).intersects(new Rectangle(Xmin, Ymax-2, ladrilloAncho, 2)))){
            Lado=1;//abajo
            colisionBloque= true;
            if(bloques[i][j]==3){ colisionPre =true; poder = 1;bloques[i][j]=0;}
                else if(bloques[i][j]<4){
                    if(bloques[i][j]==2){Sonido("g7");}
                    else{Sonido("g6");}
                     bloque--;bloques[i][j]--;poder=0; 
                }
            else{Sonido("g8");poder=0;}
            
        }else if((new Rectangle(px, py, 12, 13).intersects(new Rectangle(Xmin, Ymin+3, 2, LadrilloAlto-3)))){
            Lado=2;//izquierda
            colisionBloque= true;
            if(bloques[i][j]==3){ colisionPre =true; poder = 1;bloques[i][j]=0;}
                else if(bloques[i][j]<4){
                    if(bloques[i][j]==2){Sonido("g7");}
                    else{Sonido("g6");}
                     bloque--;bloques[i][j]--;poder=0; 
                }
            else{Sonido("g8");poder=0;}
            
        }else if((new Rectangle(px-8, py, 13, 13).intersects(new Rectangle(Xmax, Ymin+3, 2, LadrilloAlto-3)))){
            Lado=3;//derecha
            colisionBloque= true;
            if(bloques[i][j]==3){ colisionPre =true; poder = 1;bloques[i][j]=0;}
                else if(bloques[i][j]<4){
                    if(bloques[i][j]==2){Sonido("g7");}
                    else{Sonido("g6");}
                     bloque--;bloques[i][j]--;poder=0; 
                }
            else{Sonido("g8");poder=0;}
            
        }
        
        
        
        
    }
    
    public boolean colisionPremi(){
        return colisionPre;
    }
    
    public int Bloque(){
        return bloque;
    }
    
    public int Poderes(){
        return poder;
    }
    
    public void reinicio(boolean reini){
        if(reini==true){
            bloque=0;
            for (int i = 1; i<bloques.length; i++){
                for(int j = 0; j< bloques[0].length; j++){
                    bloques[i][j]=1;//bloque fragil
                    bloque++;
                }
            }
        }
    }
    public int nivel(){
        if(bloque<=0){ 
            nivel++;
            pasar=true;
        }else{
            pasar=false;
        }
        return nivel;
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

    public boolean Pasar(){
        return pasar;
        
    }
    //soniso del juego
    public void Sonido(String archivo){
        try {
            audio=AudioSystem.getClip();
            audio.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream(ruta+archivo+".wav")));  
            audio.start();
        } catch (Exception e) {
        }
    }
    
   
}
