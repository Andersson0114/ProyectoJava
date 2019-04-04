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
    private int x , y, puntaje, cont;
    private String nombre="";
    private boolean inicio=false;
    
    String Jugadores[] = new String[10];
    String textico="";
    File archivo = new File("Puntajes.txt");
    
    
    public void Recibir(String nombre, int puntaje){
        this.nombre = nombre; this.puntaje = puntaje;
    } 
    
    public void Leer(){
        cont=0;
        try {
            BufferedReader leer=  new BufferedReader(new FileReader(archivo));
            String linea = leer.readLine();
            while(linea != null){
                System.out.print(linea+"\n");
                textico+=linea+"\n";
                linea = leer.readLine();
                Jugadores[cont]=linea;cont++;
            } 
        } catch (Exception ex) {
            Logger.getLogger("ha sucedido un"+ ex);
        } 
    } 
    
    
   public void dibujar(Graphics g){
        g.setColor(Color.cyan);
        g.fillRect(926, 3, 264, 3);
        g.fillRect(926, 580, 264, 3);
        g.fillRect(926, 3, 3, 580);
        g.fillRect(1187, 3, 3, 580);
        g.fillRect(920, 0, 1, 600);
        
        g.setColor(Color.cyan);
        g.setFont(new Font("serif", Font.BOLD, 50));
        g.drawString("Brick", 970, 50);
        g.drawString("Breaker ", 950, 100);
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 30));
        g.drawString("PUNTAJE", 950, 160);
        g.setColor(new Color ((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1));
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(nombre, 950, 200);
        g.drawString(""+puntaje, 970, 240);
        
        //recuadro
        g.setColor(Color.red);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Jugadores", 950, 290);
        g.setColor(Color.cyan);
        g.fillRect(933, 300, 250, 250);
        g.setColor(Color.red);
        g.setFont(new Font("serif", Font.BOLD, 30));
        
        g.drawString(Jugadores[0], 930, 350);
        g.drawString(Jugadores[1], 930, 400);
        g.drawString(Jugadores[2], 930, 450);
        g.drawString(Jugadores[3], 930, 500);
        Leer();
         
   }
  
   
   public void Llenan(){
        File f;
        FileWriter w;
        BufferedWriter bw;
        PrintWriter wr;
        
        try {
           
            w= new FileWriter(archivo);
            bw= new BufferedWriter(w);
            wr = new PrintWriter(bw);
            
             wr.append(nombre+": "+puntaje+ "\n "+ textico);
            
            wr.close();
            bw.close();
            
            
        } catch (Exception ex) {
            Logger.getLogger("ha sucedido un"+ ex);
        }    
    }
    
}
