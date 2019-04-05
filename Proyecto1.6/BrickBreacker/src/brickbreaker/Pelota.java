package brickbreaker;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Andersson Cordoba
 *         Chirstian Medina
 *         Diego Toro
 */ 
public class Pelota {
    //variables
    private int x, y, dx, dy, alto, ancho;//pelota
    private int bx, by,bAlto, bAncho, lado;//bloques
    private boolean pasar, fuera=false, colision=false, colisionBase, colisionBloque;
    //variables de audio
    private  Clip clip;
    private  String ruta= "/Sonidos/";
    
    public Pelota(){
        x = (int)(Math.random()*800)+1;//valor aleatorio en x para la pelota
        y = 340; 
        dx=4; dy=-4; //variacion de direccion
        alto=12; ancho=12;//dimensiones
    }
    
    public void dibujar(Graphics2D g){
        //dibujar pelota
        g.setColor(Color.red);
        g.fillOval(x, y, ancho, alto);
        g.setColor(Color.yellow);
        g.drawOval(x, y, ancho, alto);
        
    }
    //tener la posicion del bloque
    public void tenerBloque(int bx, int by, int bAncho, int bAlto){
        this.bx=bx; this.by=by; this.bAlto=bAlto; this.bAncho=bAncho;
    }
    //acomodar la pelota para un nuevo nivel o reinicio
    public void next(boolean pasar){
        this.pasar=pasar;
        if(this.pasar==true){
            y=350;// si la pelota sale
            x = (int)(Math.random()*800)+1;
            dy=4;
        }
    }
    //recibir las colisiones y el lado donde golpea al bloque
    public void colisiones(boolean colisionBase, boolean colisionBloque,  int lado){
        this.lado = lado;
        this.colisionBase = colisionBase;
        this.colisionBloque = colisionBloque;
        moverP();
    }
    //mover la pelota
    public void moverP(){
        
        x+=dx;//aumentar en x
        y+=dy;//aumentar en y
       
        //colision con la base
        if(colisionBase==true ){
            dy=-dy;
            y=550-alto;
            sonido("g3");
        }
        
        if(colisionBloque == true){//evaluar colision con los bloques
            colision=colisionBloque;
            
            switch (lado) {
                case 4://Arriba
                    y= y-13;
                    dy=-dy;
                    //System.out.println("choca desde la arriba");
                    break;
                case 1://abajo
                    y= y+14;
                    dy=-dy;
                    //System.out.println("choca desde la abajo");
                    break;
                case 2://Izquierda
                    x= x-13;
                    dx=-dx;
                    //System.out.println("choca desde la izquieda");
                    break;
                case 3: //derecha
                    x= x+15;
                    dx=-dx;
                   // System.out.println("choca desde la derecha");
                    break;
            }
        }else{ colision=false;}
        
        // comprobar si la pelota golpea los bordes 
        if(y>=579 ){//golpea abajo y sigue derecho
            sonido("g5");
            y=350;
            x = (int)(Math.random()*800)+1;
            dy=-dy;
            fuera=true;
        }else{fuera=false;}
        
        if(y-28<=0){//golpea arriba y rebota
            sonido("g1");
            y=30;
            dy=-dy;
        }
        
        if(x>920-12 || x<0){//golpea a los lados y rebota
            sonido("g1");
            dx=-dx; 
        } 
    }
    
    //audio
    public void sonido(String archivo){
        try{
            clip= AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream(ruta+archivo+".wav")));  
            clip.start();
        }catch(Exception e){
            
        }
    }
    
    //retornar valores
    public boolean colision(){
        return colision;
    }
    
    public boolean menos(){
        return fuera;
    }
    
    public int getPx(){
        return x;
    }
    public int getPy(){
        return y;
    }
     
    
    
}

