/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Andersson
 */
public class Menú extends JFrame{
    private int  puntaje, cont;
    private String nombre="", textico="";//guardar los Sting
    
    //vector de Strings para guardar posiciones
    String Jugadores[] = new String[10];
   //ubicacion del archivo
    File archivo = new File("Puntajes.txt");
    
    //recibir el nombre y puntaje actual
    public void Recibir(String nombre, int puntaje){
        this.nombre = nombre; this.puntaje = puntaje;
    } 
    
    ///leer el txt donde se tiene guardados los jugadores
    public void Leer(){
        cont=0;
        try {
            BufferedReader leer=  new BufferedReader(new FileReader(archivo));
            String linea = leer.readLine();//lector
            while(linea != null){//leer lineas
                //System.out.print(linea+"\n");
                textico+=linea+"\n ";
                linea = leer.readLine();
                Jugadores[cont]=linea;cont++;
            }
            
        } catch (Exception ex) {
            Logger.getLogger("ha sucedido un error en: "+ ex);
        } 
    } 
    
    //dibujar los puntajes
   public void dibujar(Graphics g){
       //recuadro grande
        g.setColor(Color.cyan);
        g.fillRect(926, 3, 264, 3);
        g.fillRect(926, 580, 264, 3);
        g.fillRect(926, 3, 3, 580);
        g.fillRect(1187, 3, 3, 580);
        g.fillRect(920, 0, 1, 600);
        //titulo
        g.setColor(Color.cyan);
        g.setFont(new Font("serif", Font.BOLD, 50));
        g.drawString("Brick", 970, 50);
        g.drawString("Breaker ", 950, 100);
        //nombre y puntaje del jugador actual
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 30));
        g.drawString("PUNTAJE", 950, 160);
        g.setColor(new Color ((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1));
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(nombre, 950, 200);
        g.drawString(""+puntaje, 970, 240);
        
        //recuadro en el que se muestran los ultimos jugadores
        g.setColor(Color.red);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Jugadores", 950, 290);
        g.setColor(Color.cyan);
        g.fillRect(933, 300, 250, 250);
        g.setColor(Color.red);
        g.setFont(new Font("serif", Font.BOLD, 30));
        
        g.drawString(Jugadores[0], 933, 350);
        g.drawString(Jugadores[2], 933, 400);
        g.drawString(Jugadores[4], 933, 450);
        g.drawString(Jugadores[6], 933, 500);
        
        
        
   }
  
   
   public void Llenar(){
      
        FileWriter w;
        BufferedWriter bw;
        PrintWriter wr;
        
        try {
           
            w= new FileWriter(archivo);
            bw= new BufferedWriter(w);
            wr = new PrintWriter(bw);
            
           textico = nombre+": "+puntaje+"\n "+textico;
            wr.println(textico);
            wr.close();
            bw.close();
            
        } catch (Exception ex) {
            Logger.getLogger("ha sucedido un"+ ex);
        }   
       //
       Leer();
    }
    
}
