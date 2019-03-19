/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreacker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;



/**
 *
 * @author Andersson
 */
public class Tablero extends JPanel implements ActionListener{
    Base base = new Base();//base
    Timer timer = new Timer(5,this);//tiempo
    int score =0, nivel = 1;//acomodar
    int vidas =4;//vidas
    boolean play = false, jugar = true;//inicio de juego
    
    public Tablero(){
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
        //BORDES
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, 900, 3);
        g.fillRect(0, 28, 900, 3);
        g.fillRect(0, 562, 900, 2);
        //scores
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(""+ score, 10, 25);
        
        //nivel
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Nivel "+ nivel, 400, 25);
        
        //mostrar vidas
        g.setColor(Color.green);
        g.setFont(new Font("serif", Font.BOLD, 13));
        g.drawString("VIDAS",20, 575);
        for(int i=0; i< vidas; i ++){
            g.setColor(Color.green);
            g.fillRect(i*25+5,580, 20, 5);
        }
        
        
        
    }
    
    //mover
    @Override
    public void actionPerformed(ActionEvent e){
    //actualiza las posiciondes de los objeto y hace el repaint

        if(jugar ==true){
            if(play ==true){
                
                base.mover();
                if(vidas<0){
                    jugar=false;
                }
                repaint();
            }  
        }   
        
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
}

