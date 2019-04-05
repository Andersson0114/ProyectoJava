package brickbreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Locale;
import java.util.Vector;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Andersson Cordoba
 *         Chirstian Medina
 *         Diego Toro
 */
public class Tablero extends JPanel implements ActionListener{
    //objetos
    Menú menu = new Menú();
    Base base = new Base();//base
    Pelota pelota = new Pelota();//pelota
    Bloques bloques;//acomodar por niveles  
    Poderes poderAct = new Poderes();//poder
    Destruir destruir = new Destruir(0, 0);//animacion de destruccion
    Destruir destruir2 = new Destruir(0,0);//animacion de destruccion
    Destruir destruir3 = new Destruir(0,0);//animacion de destruccion
   
    //Variables
    //               puntaje   nivel     tiempo    contador  #bloques  #vidas
    private int x, y,score =0, nivel=1 , tiempo=400, cont=0, Bloque=0,vidas =4, PuntajeFinal, municion=1;
    private boolean colisionBase=false,play = true, jugar, salir=true;//inicio de juego
    String nombre="Player";
    //    tiempo 
    Timer timer = new Timer(10,this);//tiempo
    //audio
    private Clip clip;
    private String ruta= "/Sonidos/";
    
    private Vector<Destruir> listaDestruir = null;//------------
    private Vector<Ladrillo> listaLadrillo = null;
    
    public Tablero(){//constructor -----------------------------------
        
        bloques = new Bloques(6, 11);//asignamos el valor de la matriz
        bloques.asignarNivel(nivel);//asignamos el nivel
        setBackground(Color.BLACK);//color de fondo
        setFocusable(true);//
        addKeyListener(new teclado());//se agrega el oyente del teclado
        //
        this.listaDestruir = new Vector<>();
        this.listaLadrillo = new Vector<>();
        //
        timer.start();//inicia el 
        
        nombre = JOptionPane.showInputDialog("Ingresa tu Nombre");//ingresar el nombre del juegador
        if(nombre== null){
            salir=false;
        }
        
        menu.Recibir(nombre, score);
        menu.Leer();
        
        sonido("melodiaa");
        jugar = true;
    } 
    
    //este metodo se encarga de dibujar sobre el panel
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
       
        base.dibujar(g2);//dibujar la base
        pelota.dibujar(g2);//dibujar la pelota
        bloques.dibujar(g2);//dibujar los bloques
        poderAct.dibujar(g2);//dibujar disparo
        menu.dibujar((Graphics2D) g);
        //animacion de destruccion
        for(Destruir des : listaDestruir){
            
            des.dibujar(g2);//dibujar
            des.mover();
        }
        
        //animasion de ladrillo poder que cae
        for(Ladrillo poder : listaLadrillo){
            poder.Enjuego(play);//preguntar si esrá en juego
            poder.dibujar(g2);
            poder.RecibirBase(base.getBx(), base.getBy(), base.getAncho());//dectectar colision
            if(poder.ColisionBase()){
                score+=200;
                if(poder.getPoder()==1){ base.RecibirPo(1, false);  }
                if(poder.getPoder()==2){
                    municion+=1;
                   // poderAct.recibir(base.getBx(), base.getBy(), base.getAncho());poderAct.Enjuego(play);
                }
                if(poder.getPoder()==3){vidas++;}
            }
            
        }
        
        //BORDES
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, 920, 3);
        g.fillRect(0, 28, 920, 3);
        g.fillRect(0, 562, 920, 2);
        g.fillRect(0, 0, 1, 600);
        g.fillRect(920, 0, 1, 600);
        
        /*
        g.setColor(Color.red);
        g.fillRect(1165, 8, 20, 20);
        g.setColor(Color.white);
        g2.setStroke(new BasicStroke(1));//el grueso de la linea
        g2.drawLine(1167, 10, 1183, 26);
        g2.drawLine(1167, 26, 1183, 10);*/
        
        //Puntaje
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(""+ score, 10, 25);
         
        //nivel
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Nivel "+ nivel, 400, 25);
        
        
        //tiempo
        g.setColor(new Color ((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1));
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Tme: "+ tiempo, 720, 25);
        
        //cambio de color 
        if(vidas>2){
            g.setColor(Color.green);
        }else{g.setColor(Color.red);}
        //mostrar vidas//250, 320
        g.setFont(new Font("serif", Font.BOLD, 13));
        g.drawString("VIDAS:",460, 580);
        for(int i=0; i< vidas; i ++){
            g.fillRect(i*25+530,575, 20, 5);
        }
        
        //mostrar nombre
        g.setFont(new Font("serif", Font.BOLD, 13));
        g.drawString("JUGADOR: ",260, 580);
        g.setFont(new Font("serif", Font.BOLD, 18));
        g.drawString(nombre,330, 580);
        
        //cuando se pierde el juego
        if(vidas<0 || tiempo<=0){
            PuntajeFinal= (nivel*500)+(vidas*100)+score;
            
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 100));
            g.drawString("Game Over " , 250, 300);
            
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Score: "+ PuntajeFinal, 270, 330);
            g.drawString("PRESIONE ESPACIO PARA REINICIAR ", 260, 350);
            
            //System.out.println(PuntajeFinal);
            reinicio();
            jugar=false;
            timer.stop();
        }
        //cuando se gana el juego
        if(nivel>3){
            PuntajeFinal= (nivel*500)+(tiempo*20)+(vidas*100)+score;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 70));
            g.drawString("¡¡FELICITACIONES!! " , 100, 300);
            
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Score: "+ PuntajeFinal, 270, 330);
            g.drawString("PRESIONE ESPACIO PARA REINICIAR ", 260, 350);
            System.out.println(PuntajeFinal);
            reinicio();
            jugar=false;
            timer.stop();
        }
        
    }
    
    //mover
    @Override
    public void actionPerformed(ActionEvent e){
        if(jugar ==true){//inicio
            if(play ==true){//play - pausa
                
                menu.Recibir(nombre, score);
                Bloque=bloques.Bloque();//numero de bloques
                //Colision de la pelota con la base
                if(new Rectangle(pelota.getPx(), pelota.getPy(), 12, 12).intersects(new Rectangle(base.getBx(), 550, base.getAncho(), base.getAlto()))){
                    colisionBase= true;
                }else{colisionBase= false;}
                 
                base.mover();//movee la base
                //colision de la pelota con los bloques
                pelota.tenerBloque( bloques.getBx(), bloques.getBy(),  bloques.getAncho(),  bloques.getAlto());
                pelota.colisiones(colisionBase, bloques.colision(),bloques.lado());//colision de la pelota
                //dar la posicion de la pelota y bala al bloque(para detectar colisiones)
                bloques.tenerPelota(pelota.getPx(), pelota.getPy(),poderAct.getPx(), poderAct.getPy(), 5, 15);
                //validacion para destruir el bloque
                if(bloques.colision()==true || bloques.colisionBalas()==true){
                    this.listaDestruir.add(new Destruir(bloques.getBx(), bloques.getBy()));//activat el metodo destruir(animacion)
                    if(bloques.Poderes()>0){//si el bloque posee un poder, este soltará un pequeño ladrillo
                                                            //da la posicion y poder del ladrillo que se soltará
                        this.listaLadrillo.add(new Ladrillo(bloques.getBx(), bloques.getBy(), bloques.Poderes()));
                    }
                }
               
                //puntaje
                if(bloques.colision()==true || bloques.colisionBalas()==true){
                    score+=500;
                }
                
                //restar vidas
                if(pelota.menos()==true){
                    base.RecibirPo(0, true);
                    vidas--; 
                    score-=100;
                    play=false;
                    municion=0;
                    poderAct.recibir(0, 0, 0);//bala desaparece
                }
                
                //tiempo
                cont +=2;
                if(cont%100==0){
                    tiempo--;
                }
                //pasar nivel
               if(nivel>0){ 
                    if(Bloque==0){//pasar nivel
                        play=false;
                        nivel=bloques.nivel();
                        bloques.asignarNivel(nivel);
                        pelota.next(bloques.Pasar());
                        tiempo=400; vidas++;
                        sonido("melodiaa");
                        
                        if(nivel>3){//reiniciar desde cero
                            play=false;
                            jugar=false;
                            PuntajeFinal= (nivel*500)+(tiempo*10)+(vidas*100)+score;
                            System.out.println(PuntajeFinal);
                        } 
                    }
                }
                timer.start(); repaint();
            }  
        }
        
    }
    
    //reinicio de juego(arreglasr)------------------------------------------
   public void reinicio(){
       //si se reinicia el juego, todos sus valores se reinician
       if(base.reiniciar()!=true){
            play=true;
            timer.start();
            nivel=1; tiempo=400; vidas=4; score=0; 
            bloques.reinicio(true);
            bloques.asignarNivel(nivel);nivel=bloques.nivel();
            play=false;
        }
   }
   
    //detectar si una tecla es presionada
    private class teclado extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT){
                timer.start();//inicia el timer
                play = true;
                base.keyPressed(e);
            }
            if(jugar==false){//reiniciar el juego
                menu.Recibir(nombre, PuntajeFinal);
                menu.Llenar();
                if(key == KeyEvent.VK_SPACE){
                    nombre = JOptionPane.showInputDialog("ingresa tu nombre");//ingresar el nombre del juegador
                    if(nombre == null){
                        System.exit(0);
                    }
                    menu.Recibir(nombre, score);
                    jugar=true;
                    nivel=0;sonido("melodiaa");
                    bloques.reinicio(true);
                    bloques.asignarNivel(nivel);
                    poderAct.recibir(0, 0, 0);
                    municion=0;
                    reinicio();
                }
               
            }
            if(municion>0){
                if(key == KeyEvent.VK_SPACE){
                    poderAct.recibir(base.getBx(), base.getBy(), base.getAncho());poderAct.Enjuego(play);
                    municion--;
                }
            }
            
        }
        @Override
        public void keyReleased(KeyEvent e){
            base.keyReleased(e);
        }
    }
    
    public boolean Salir(){//salir del juego
        return salir;
    }
    //sonido bien perrón
    public void sonido(String archivo){
        try{
            clip= AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream(ruta+archivo+".wav")));  
            clip.start();
        }catch(Exception e){
            
        }
    }
}

