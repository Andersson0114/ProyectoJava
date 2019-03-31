


import java.awt.Color;
import java.awt.Graphics2D;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Andersson Cordoba
 *            Chirstian Medina
 *            Diego Toro
 */
public class Pelota {
    int x, y, x1, y1, x2, y2, dx, dy, alto, ancho;//pelota
    int bx, by,bAlto, bAncho, lado;//bloques
    boolean pasar, fuera=false, colision=false;
    
    public Clip clip;
    public String ruta= "/Sonidos/";
    
    //sonido del juego
    public void sonido(String archivo){
        try{
            clip= AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream(ruta+archivo+".wav")));  
            clip.start();
        }catch(Exception e){
            
        }
    }
    
    
    public Pelota(){
        x = (int)(Math.random()*800)+1;//valor aleatorio en x para la pelota
        y = 358; dx=2; dy=2; alto=12; ancho=12;
        
        x1=x; y1=y;
        x2=x; y2=y;
        //350
    }
    
    public void dibujar(Graphics2D g){
        //dibujar pelota
        g.setColor(Color.red);
        g.fillOval(x, y, ancho, alto);
        g.setColor(Color.yellow);
        g.drawOval(x, y, ancho, alto);
        
    }
    //tener la posicion del bloque
    public void tenerBloque(int Bx, int By, int bancho, int balto){
        bx=Bx; by=By; bAlto=balto; bAncho=bancho;
    }
    public void next(boolean Pasar){
        pasar=Pasar;
        if(pasar==true){
            y=350;// si la pelota sale
            x = (int)(Math.random()*800)+1;
            dy=-dy;
        }
    }
    
    //mover la pelota
    public void moverP(boolean colisionBase, boolean colisionBloque,  int Lado){
        lado=Lado;
        x+=dx;
        y+=dy;
       
        if(colisionBase==true ){//colision con la base
            dy=-dy;
            y=550-alto;
            sonido("g3");
        }
        
        if(colisionBloque == true){//colision con los bloques
            colision=colisionBloque;
            switch (lado) {
                case 4://Arriba
                    y= y-13;
                    dy=-2;
                    //System.out.println("choca desde la abajo");
                    break;
                case 1://aa
                    y= y+14;
                    dy=2;
                    System.out.println("choca desde la arriba");
                    break;
                case 2://Izquierda
                    x= x-15;
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
           
        
        if(y>=580 ){
            sonido("g5");
            y=350;// si la pelota sale
            x = (int)(Math.random()*800)+1;
            dy=-dy;
            fuera=true;
        }else{fuera=false;}
        
        if(x>920-12 || x<0){
            sonido("g1");
            dx=-dx;
            
        }
        
        if(y-28<=0){
            sonido("g1");
            y=30;
            dy=-dy;
            
        }
        
        
    }
    
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

