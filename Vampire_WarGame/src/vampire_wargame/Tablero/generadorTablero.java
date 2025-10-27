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
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import vampire_wargame.UsersYFichas.Ficha;
import vampire_wargame.UsersYFichas.*;
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
    
    
    private Ficha fichaOnHold;
    private int previousX;
    private int previousY;
    
    
    //Tablero logico
    Ficha[][] tableroLogico = new Ficha[6][6];
    
    
    
    
    
    
    /*
    0 1 2 3 4 5 
    x x x x x x
    x x x x x x
    x x x x x x 
    x x x x x x 
    x x x x x x 
    x x x x x x 
    */
    
    
    
    
    
    public generadorTablero(int tamanioCeldas){
        this.tamanioCeldas= tamanioCeldas;
        setPreferredSize(new Dimension(columnas * tamanioCeldas, filas*tamanioCeldas));
        addMouseListener(this);//agregar el listener de los clicks
        
        //Colocacuion de fichas
        tableroLogico[0][0]= new wolfMan(); 
        tableroLogico[0][1] = new Vampire();
        tableroLogico[0][2]= new NecroMancer();
        tableroLogico[0][3]= new NecroMancer();
        tableroLogico[0][4]= new Vampire();
        tableroLogico[0][5]= new wolfMan();
        
        
        tableroLogico[5][0]= new wolfMan();
        tableroLogico[5][1]= new Vampire();
        tableroLogico[5][2]= new NecroMancer();
        tableroLogico[5][3]= new NecroMancer();
        tableroLogico[5][4]= new Vampire();
        tableroLogico[5][5]= new wolfMan();
    }
    
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2= (Graphics2D) g;
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//Suavisado de bordes
       
        int anchoCelda =getWidth()/columnas;
        int alturaCelda= getHeight()/filas;
        
        
        
        System.out.println("Ancho de celda: "+anchoCelda+"px  | Altura de Celda: "+alturaCelda+"px");
        System.out.println("Medida total de tablero: "+getWidth()+"x"+getHeight()+"pxs");
        
        
        
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
                
               
                //Print de fichas
                if(tableroLogico[i][j]!=null){
                    ImageIcon iconoFicha = tableroLogico[i][j].getImageIcon();
                    iconoFicha.paintIcon(this,g,x,y);
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
               
               //Check up de casilla con tablero logico
               if(tableroLogico[filaSeleccionada][columnaSeleccionada]!=null){
                   System.out.println("Aqui hay una ficha!");
                   fichaOnHold=tableroLogico[filaSeleccionada][columnaSeleccionada];
                   previousX=filaSeleccionada;
                   previousY=columnaSeleccionada;
                   
               }else{
                   System.out.println("Aqui no hay nada");
                   tableroLogico[filaSeleccionada][columnaSeleccionada]=fichaOnHold;
                   tableroLogico[previousX][previousY]= null;
                   fichaOnHold=null;
                   repaint();
               }
               
               
                
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
    
    
    
    private void paintFichas(int x, int y){
        ImageIcon iconoFicha = tableroLogico[x][y].getImageIcon();
        //iconoFicha.paintIcon(this,g,x,y);
    }
    

}
