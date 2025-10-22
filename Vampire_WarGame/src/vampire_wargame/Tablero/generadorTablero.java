/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame.Tablero;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
//import javax.swing.*;
//import java.awt.*;
/**
 *
 * @author David
 */
public class generadorTablero extends JPanel {
    private int tamanioCeldas;
    private int filas =6;
    private int columnas=6;
    
    public generadorTablero(int tamanioCeldas){
        this.tamanioCeldas= tamanioCeldas;
        setPreferredSize(new Dimension(columnas * tamanioCeldas, filas*tamanioCeldas));
    }
    
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2= (Graphics2D) g;
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//Suavisado de bordes
       
        int anchoCelda =getWidth()/columnas;
        int alturaCelda= getHeight()/filas;
        
        for(int i=0; i<filas; i++){
            for(int j=0; j<columnas; j++){
                g2.drawRect(j*tamanioCeldas, i*tamanioCeldas, tamanioCeldas, tamanioCeldas);
                
                
               
                if((i+j)%2==0){
                    g2.setColor(Color.LIGHT_GRAY);
                }else{
                    g2.setColor(Color.DARK_GRAY);
                }
                
                //Obtencion de pixeles como tal
                int x= j *anchoCelda;
                int y = i*alturaCelda;
                
                g2.fillRect(x, y, anchoCelda, alturaCelda);

            }
        }
        System.out.println("Ya se ha pintado todo chaval");
    }
    

}
