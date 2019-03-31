/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;
import javax.swing.Timer;



/**
 *
 * @author Andersson Cordoba
 *             Chirstian Medina
 *             Diego Toro
 */
public class Tablero extends JPanel implements ActionListener{
    Base base = new Base();//base
    Pelota pelota = new Pelota();//pelota
    Pelota pelota2 = new Pelota();//pelota
    public Clip clip;
    public String ruta= "/Sonidos/";
    int score =0, nivel=2 , tiempo=400, cont=0, Bloque=0;//acomodar
    Bloques bloques;//acomodar por niveles
    Destruir destruir = new Destruir();
    Timer timer = new Timer(5,this);//tiempo
    int vidas =4;//vidas
    boolean play = false, jugar = true;//inicio de juego
    public boolean colisionBase=false;
    
    public Tablero(){
        bloques = new Bloques(6, 11);
        setBackground(Color.BLACK);//color de fondo
        setFocusable(true);//
        addKeyListener(new teclado());//se agrega el oyente del teclado
        timer.start();//inicia el juego
        
    }
    
    
    @Override
    public void paint(Graphics g){
    //este metodo se encarga de dibujar sobre el panel
       super.paint(g);
       Graphics2D g2 = (Graphics2D)g;
       
        base.dibujar((Graphics2D)g);//dibujar la base
        pelota.dibujar((Graphics2D)g);//dibujar la pelota
        
        bloques.dibujar((Graphics2D) g);//dibujar los bloques 
        destruir.dibujar((Graphics2D) g);//accionar destruccion :V
       
        
        //BORDES
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, 920, 3);
        g.fillRect(0, 28, 920, 3);
        g.fillRect(0, 562, 920, 2);
        //Puntaje
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(""+ score, 10, 25);
        
        
        //nivel
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Nivel "+ nivel, 400, 25);
        //tiempo
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Tiempo  "+ tiempo+"sg", 720, 25);
        
        //mostrar vidas
        g.setColor(Color.green);
        g.setFont(new Font("serif", Font.BOLD, 13));
        g.drawString("VIDAS",20, 575);
        for(int i=0; i< vidas; i ++){
            g.setColor(Color.green);
            g.fillRect(i*25+5,580, 20, 5);
        }
        
        //Borrar(prueba)---------------------------
        g.setColor(Color.green);
        g.setFont(new Font("serif", Font.BOLD, 13));
        g.drawString("Bloques"+ Bloque,500, 575);
        
        //restar vidas
        if(pelota.menos()==true){
            vidas--;
            play=false;
        }
        
        if(vidas<0 || tiempo<=0){
            jugar=false;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 100));
            g.drawString("Game Over " , 250, 300);
            
            
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Score: "+ score, 270, 330);
            g.drawString("PRESIONE ENTER PARA REINICIAR ", 260, 350);
            
            reinicio();
            timer.stop();
        }
        
    }
    
    //mover
    @Override
    public void actionPerformed(ActionEvent e){
    
        
        if(jugar ==true){//inicio
            if(play ==true){//play - pausa
                
                Bloque=bloques.Bloque();//numero de bloques
                //Colision con la base
                if(new Rectangle(pelota.getPx(), pelota.getPy(), 12, 12).intersects(new Rectangle(base.getBx(), 550, base.getAncho(), base.getAlto()))){
                    colisionBase= true;
                }else{colisionBase= false;}
                //mover
                destruir.mover();
                base.mover();
                
                //colision con los bloques
                pelota.tenerBloque( bloques.getBx(), bloques.getBy(),  bloques.getAncho(),  bloques.getAlto());
                if(bloques.colision()==true){
                    destruir.recibir(pelota.getPx(), pelota.getPy());//activat el metodo destruir
                    destruir.activar(bloques.colisionPremi(), bloques.Poderes());//recibir posicion del bloque
                }
                pelota.moverP(colisionBase, bloques.colision(),bloques.lado());//colision de la pelota
                
                bloques.tenerPelota(pelota.getPx(), pelota.getPy());//posicion de la pelota
                
                
                //puntos
                if(bloques.colision()==true){
                    score+=500;
                    
                }
                
                
                //tiempo
                cont +=2;
                if(cont%100==0){
                    tiempo--;
                }
                //pasar nivel(arreglar)-----------------------
               if(nivel>0){
                   
                    if(Bloque==0){
                        play=false;
                        nivel=bloques.nivel();
                        bloques.asignarNivel(nivel);
                        pelota.next(bloques.Pasar());
                        tiempo=400;
                        //sonido("melodiaa");
                        if(nivel>3){
                            reinicio();
                            nivel=0;
                            bloques.reinicio(true);
                            bloques.asignarNivel(nivel);
                        }
                        
                    }
               }
               
                timer.start();
                repaint();
            }  
        }   
        
    }
    
    //reinicio de juego(arreglasr)------------------------------------------
   public void reinicio(){
       nivel=1; tiempo=400; vidas=4; jugar =true; play=true; score=0; bloques.reinicio(true);
       bloques.asignarNivel(nivel);nivel=bloques.nivel();
   }
    //detectar si una tecla es presionada
    private class teclado extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            timer.start();//inicia el timer
            play = true;
            base.keyPressed(e);
            
            
        }
        @Override
        public void keyReleased(KeyEvent e){
            base.keyReleased(e);
        }
    }
    
    
    //sonido del juego
    public void sonido(String archivo){
        try{
            clip= AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream(ruta+archivo+".wav")));  
            clip.start();
        }catch(Exception e){
            
        }
    }
}

