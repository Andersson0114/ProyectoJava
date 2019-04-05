package brickbreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Andersson Cordoba
 *         Chirstian Medina
 *         Diego Toro
 */
public class Bloques {
    //variables
    //audio
    public Clip audio;
    public String ruta="/Sonidos/";
    //matriz                //vector
    public int bloques[][],bloqueP[][];
    //dimensiones
    public int ladrilloAncho, LadrilloAlto;
    public int pelotaX, pelotaY, bX, bY, Xmax, Xmin, Ymax, Ymin, poder=0, alt, anch;
    public int x, y, px, py, dx, dy;               //no se
    public boolean colisionBloque, pasar=false,colisionPre,  colisionBala=false;
   public int Lado=0, bloque=0, nivel=0;
   
   
    public  Bloques(int fil, int col){//recibe las filas(fil) y columnas(col)
        bloques = new int[fil][col];//asignar el tamaño a la matriz
        bloqueP = new int[fil][col];//asignar el tamaño a la matriz
       
        ladrilloAncho= 770/col;
        LadrilloAlto= 240/fil;
    }
    
    //creamos los niveles
    public void asignarNivel(int nivel){
        this.nivel=nivel; //recibimos el nivel
        colisionBloque=false;
        if(this.nivel==1){//nivel 1
            bloque=0;
            //vacear(reinicio)
            for (int i = 0; i<bloques.length; i++){
                for(int j = 0; j< bloques[0].length; j++){
                    bloques[i][j]=0;//vacio
                }
            }
            //asignar
            for (int i = 1; i<bloques.length; i++){
                for(int j = 0; j< bloques[0].length; j++){
                    bloques[i][j]= 1;//bloque fragil
                    bloque++;
                }
                int  a=(int)(Math.random()*8)+1;
                int b=(int)(Math.random()*2)+1;
                
                if(a%b==0){
                    bloques[i][a]=3;//bloque poder
                   bloqueP[i][a]=1;//poder 1: crecer base
                }
                if(b%a==0){
                    bloques[i][a]=3;//bloque poder
                   bloqueP[i][a]= 2;//poder 2; disparo
                }
                if(i%b==0){
                    bloques[i][a]=3;//bloque poder
                   bloqueP[i][a]= 3;//poder 3: vida
                }
                
            }
        }
        
        ///////////////////////////////////////////////////////////////////////////////
        if(this.nivel==2){//nivel 2
            bloque=0;
            //vacear
            for (int i = 0; i<bloques.length; i++){
                for(int j = 0; j< bloques[0].length; j++){
                    bloques[i][j]=0;//vacio
                }
            }
            //asignar
            for (int i = 0; i<bloques.length; i++){
                for(int j = 0; j< bloques[0].length; j++){
                    bloques[i][j]=0;
                }
            }
            for (int i = 0; i<3; i++){
                for(int j = i; j< bloques[0].length-i; j++){
                    bloques[i][j]=1;
                    bloque++;
                    int a=(int)(Math.random()*8)+1;
                    int b=(int)(Math.random()*3)+1;

                    if(a%b!=0){
                        bloques[i][a]=3;
                        bloqueP[i][a]= 1;
                    }
                    if(b%a==0){
                        bloques[i][a]=3;
                        bloqueP[i][a]= 2;
                    }
                    if(i%b==0){
                        bloques[i][a]=3;
                        bloqueP[i][a]= 3;
                    }
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
        
        ///////////////////////////////////////////////////////////////////////////////
        if(this.nivel==3){//nivel 3
            //vacear
            for (int i = 0; i<bloques.length; i++){
                for(int j = 0; j< bloques[0].length; j++){
                    bloques[i][j]=0;//vacio
                }
            }
            //asignar
            for (int i = 5; i<bloques.length; i++){
                    for(int j = 0; j< bloques[0].length; j++){//bloque indestructible
                        if(j%2==0){
                            bloques[i][j]=4;
                        }
                    }
             }
            bloque=0;
            for (int i = 2; i<3; i++){
                    for(int j = 0; j< bloques[0].length; j++){//bloque indestructible
                        if(j%2!=0){
                            bloques[i][j]=4;
                        }
                    }
                }
            
            for (int i = 0; i<bloques.length; i++){
                for(int j = i; j< bloques[0].length-i; j++){
                    if(bloques[i][j]<4){
                        if(i%2==0){
                            bloques[i][j]=1;//bloque fragil
                            bloque++;
                        }else{
                            bloques[i][j]=2;//bloque duro
                            bloque+=2;
                        } 
                        
                        if(i==0 || i==4 ){
                            int a=(int)(Math.random()*8)+1;
                            int b=(int)(Math.random()*6)+1;

                            if(a%b==0){
                                bloques[i][a]=3;
                                bloqueP[i][a]= 1;
                            }
                            if(b%a==0){
                                bloques[i][a]=3;
                                bloqueP[i][a]= 2;
                            }
                            if(j%b==0){
                                bloques[i][a]=3;
                                bloqueP[i][a]= 3;
                            }
                        }
                    } 
                } 
            }
        }
        
        ///////////////////////////////////////////////////////////////////////////////
                   
                    
    }
    
    
    ///recibir la dimension de la pelotas
    public void recibirPelota(int pelotaX, int pelotaY){
        this.pelotaX=pelotaX;
        this.pelotaY=pelotaY;
    }
    
    //dibujar los bloques
    public void  dibujar(Graphics2D g){
         colisionBloque=false;
        for (int i = 0; i<bloques.length; i++){
            for(int j = 0; j< bloques[0].length; j++){
                
                if(bloques[i][j]>0){//se pinta si en la posicion i, j hay un bloque
                    //si el bloque es frajil se hace lo siguiente
                    if(bloques[i][j]==1){
                        Xmin = j*ladrilloAncho+70;//la ponsicion en x
                        Ymin = i*LadrilloAlto+50;//la posicion en y
                        Xmax = Xmin+ladrilloAncho;// ancho
                        Ymax = Ymin+LadrilloAlto;// alto
                        g.setColor(Color.cyan); //se asigna color
                        g.fillRect(Xmin, Ymin, ladrilloAncho, LadrilloAlto);//se dibuja el bloque
                        //dibujar bordes
                        g.setColor(Color.BLACK);//se asigna el color
                        g.setStroke(new BasicStroke(2));//el grueso de la linea
                        g.drawRect(Xmin, Ymin, ladrilloAncho, LadrilloAlto);//se dibuja el borde
                        
                        //pueba de colision
                        /*
                        g.setColor(Color.cyan); 
                        g.fillRect(Xmin, Ymin, ladrilloAncho, 2);
                        g.fillRect(Xmin, Ymin+3, 2, LadrilloAlto-3);
                        
                        g.fillRect(Xmax-3, Ymin+3, 2, LadrilloAlto-3);
                        g.fillRect(Xmin, Ymax-2, ladrilloAncho, 2); */
                        
                        evaluar(i, j);//se evalua la colision
                       
                    }
                    //si el bloque es de poder se hace lo siguiente
                    if(bloques[i][j]==3){
                        Xmin = j*ladrilloAncho+70;
                        Ymin = i*LadrilloAlto+50;
                        Xmax = Xmin+ladrilloAncho;
                        Ymax = Ymin+LadrilloAlto;
                        
                        if(bloqueP[i][j]==1){
                            g.setColor(Color.orange); 
                        }
                        if(bloqueP[i][j]==3){
                            g.setColor(Color.green); 
                        }
                        if(bloqueP[i][j]==2){
                            g.setColor(Color.red); 
                        }
                        
                        g.fillRect(Xmin, Ymin, ladrilloAncho, LadrilloAlto);
                        g.setStroke(new BasicStroke(2));
                        g.setColor(Color.BLACK);
                        g.drawRect(Xmin, Ymin, ladrilloAncho, LadrilloAlto);
                        evaluar(i, j);
                    }
                        
                    //si el bloque es duro se hace lo siguiente
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
                    //si el bloque es indestructible se hace lo siguiente
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
    //tener la posicion de la pelota
    public void tenerPelota(int px, int py, int dx, int dy, int al, int anc){
       //pelota
        this.px=px;
        this.py=py;   
        //bala
        this.dx=dx;
        this.dy=dy;
        alt=al;
        anch=anc;
    }
    
    public void evaluar(int i, int j){//evaluar si hay colision
        Xmin = j*ladrilloAncho+60;//posicion en x del bloque
        Ymin = i*LadrilloAlto+50;//posicion en y del bloque
        Xmax = Xmin+ladrilloAncho;//Ancho del bloque
        Ymax = Ymin+LadrilloAlto;//alto del bloque
        
        
        //colisiones
        if((new Rectangle(px, py+13, 13, 13).intersects(new Rectangle(Xmin, Ymin, ladrilloAncho, 4)))){//si pega desde arriba
            Lado=4;//arriba
            colisionBloque= true;
            //evaluar con que tipo de bloque colisiona
            if(bloques[i][j]==3){ bloque--; colisionPre =true; poder = bloqueP[i][j];bloques[i][j]=0;}//si el bloque es de poder
                else if(bloques[i][j]<4){
                    if(bloques[i][j]==2){Sonido("g7");}//si el bloque es duro
                    else{Sonido("g6");}//si el bloque es fragil
                     bloque--;bloques[i][j]--;poder=0; 
                }else{Sonido("g8");poder=0;}//si el bloque es indestructible
            
            
            
        }else if((new Rectangle(px, py, 13, 13).intersects(new Rectangle(Xmin, Ymax-2, ladrilloAncho, 2)))){//si pega desde abajo
            Lado=1;//abajo
            colisionBloque= true;
            //evaluar con que tipo de bloque colisiona
            if(bloques[i][j]==3){ bloque--; colisionPre =true; poder = bloqueP[i][j];bloques[i][j]=0;}
                else if(bloques[i][j]<4){
                    if(bloques[i][j]==2){Sonido("g7");}
                    else{Sonido("g6");}
                     bloque--;bloques[i][j]--;poder=0; 
                }else{Sonido("g8");poder=0;}
            
        }else if((new Rectangle(px, py, 12, 13).intersects(new Rectangle(Xmin, Ymin+3, 2, LadrilloAlto-3)))){//si pega desde la izquierda
            Lado=2;//izquierda
            colisionBloque= true;
            //evaluar con que tipo de bloque colisiona
            if(bloques[i][j]==3){bloque--; colisionPre =true; poder = bloqueP[i][j] ;bloques[i][j]=0;}
                else if(bloques[i][j]<4){
                    if(bloques[i][j]==2){Sonido("g7");}
                    else{Sonido("g6");}
                     bloque--;bloques[i][j]--;poder=0; 
                }else{Sonido("g8");poder=0;}
            
        }else if((new Rectangle(px-8, py, 13, 13).intersects(new Rectangle(Xmax, Ymin+3, 2, LadrilloAlto-3)))){//si pega desde la derecha
            Lado=3;//derecha
            colisionBloque= true;
            //evaluar con que tipo de bloque colisiona
            if(bloques[i][j]==3){bloque--; colisionPre =true; poder = bloqueP[i][j];bloques[i][j]=0;}
                else if(bloques[i][j]<4){
                    if(bloques[i][j]==2){Sonido("g7");}
                    else{Sonido("g6");}
                     bloque--;bloques[i][j]--;poder=0; 
                }else{Sonido("g8");poder=0;}
        }
        if(colisionBloque==true){
            x=px; y=py;
        }
        
        //colision con la bala >:V
        if((new Rectangle(dx, dy, anch, alt).intersects(new Rectangle(Xmin, Ymax-2, ladrilloAncho, 2)))){
            Lado=1;//abajo
            colisionBala=true;
            //evaluar con que tipo de bloque colisiona
            if(bloques[i][j]==3){ bloque--; colisionPre =true; poder = bloqueP[i][j];bloques[i][j]=0; Sonido("g7");}
                else if(bloques[i][j]<4){
                    if(bloques[i][j]==2){Sonido("g7");bloque-=2;bloques[i][j]=0;}
                    else{Sonido("g6");}
                     bloque--;bloques[i][j]--;poder=0; 
                }else{Sonido("g8");poder=0;}
            if(colisionBala ==true){
                x=dx; y=dy;
            }
        }else{colisionBala=false;}
         
    }
    
    //reiniciar nivel
    public void reinicio(boolean reini){
        if(reini==true){
            bloque=0; colisionBloque=false;
            for (int i = 1; i<bloques.length; i++){
                for(int j = 0; j< bloques[0].length; j++){
                    bloques[i][j]=1;//bloque fragil
                    bloque++;
                }
            }
        }
    }
    
    //retornar nivel
    public int nivel(){
        if(bloque<=0){ 
            nivel++;
            pasar=true;
        }else{
            pasar=false;
        }
        return nivel;
    }
    
    //sonido
    public void Sonido(String archivo){
        try {
            audio=AudioSystem.getClip();
            audio.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream(ruta+archivo+".wav")));  
            audio.start();
        } catch (Exception e) {
        }
    }
    
  
    //retornar valores
    public boolean colisionPremi(){
        return colisionPre;
    }
    
    public boolean colisionBalas(){
        return colisionBala;
    }
    
    public int Bloque(){
        return bloque;
    }
    
    public int Poderes(){
        return poder;
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
        return x;
    }
    public int getBy(){
        return y;
    }

    public boolean Pasar(){
        return pasar;
        
    }

    
   
}
