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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
//import javax.swing.*;
//import java.awt.*;
/**
 *
 * @author David
 */
public class generadorTablero extends JPanel implements MouseListener {
    private int tamanioCeldas;
    private int filas =6;
    private int columnas=6;
    private boolean doubleClick=false;
    
    private int filaSeleccionada=-1;
    private int columnaSeleccionada=-1;
    
    
    public generadorTablero(int tamanioCeldas){
        this.tamanioCeldas= tamanioCeldas;
        setPreferredSize(new Dimension(columnas * tamanioCeldas, filas*tamanioCeldas));
        addMouseListener(this);//agregar el listener de los clicks
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

                
                //Seleccion de casilla
                if(i==filaSeleccionada && j == columnaSeleccionada){
                    g2.setColor(Color.RED);
                    g2.fillRect(x, y, anchoCelda, alturaCelda);
                }
                
               
                
                
            }
        }
        System.out.println("Ya se ha pintado todo chaval");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //OBtencion de coordenadas donde se ha hecho click
        int x= e.getX();
        int y= e.getY();
        
        //Obtenemos los valores de la teoricos fila y columna donde se ha hecho click
        int fila = y/tamanioCeldas;
        int columna = x/tamanioCeldas;
        
        //Comprobacion que sean las filas y columnas del tablero como tal
        if(fila>=0 && fila<filas && columna>=0 && columna<columnas){
            if(fila==filaSeleccionada && columna == columnaSeleccionada){
                filaSeleccionada=-1;
                columnaSeleccionada=-1;
                System.out.println("Casilla Deseleccionada");
            }else{
               filaSeleccionada=fila;
               columnaSeleccionada=columna;
               System.out.println("Fila: "+filaSeleccionada+" Columna: "+columnaSeleccionada);
                
            }
            repaint(); 
        }
        
        
        
        //Verificador en case que clickee en la misma casilla, para desactivar seleccion
        //Aunque creo que solo habria seleccion unica.....si es asi, no es necesario esto
       
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    

}
