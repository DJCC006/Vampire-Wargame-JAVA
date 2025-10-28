/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame.Tablero;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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
    
    
    //Elementos para el manejo de movimiento de fichas
    private Ficha fichaOnHold;
    private boolean seleccion=false;
    private int previousX;
    private int previousY;
    
    
    //Tablero logico
    Ficha[][] tableroLogico = new Ficha[6][6];
    private ArrayList<Point> casillasDisponibles = new ArrayList<>(); //Guarda las coordenadas de las casillas que se remarcaran
 
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

                
                //Seleccion de casilla solo si tiene ficha 
                if(i==filaSeleccionada && j == columnaSeleccionada && fichaOnHold!=null && tableroLogico[filaSeleccionada][columnaSeleccionada]!=null){
                    g2.setColor(Color.RED);
                    g2.fillRect(x, y, anchoCelda, alturaCelda);
                    System.out.println("COORDS DE RED PINTADA: "+x+","+y);
                }
                
               
                //Print de fichas
                if(tableroLogico[i][j]!=null){
                    ImageIcon iconoFicha = tableroLogico[i][j].getImageIcon();
                    iconoFicha.paintIcon(this,g,x,y);
                }
            }
        }
        
        
        //Pintar las casillas de seleccion
        g2.setColor(new Color(0,255,0,100));
        for(Point p: casillasDisponibles){
            int x = p.y*anchoCelda;
            int y = p.x*alturaCelda;
            g2.fillRect(x, y, anchoCelda, alturaCelda);
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
                casillasDisponibles.clear();
                repaint();
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
                   seleccion=true;
                   
                   if(fichaOnHold!= null){
                       calcularMovimientos(filaSeleccionada, columnaSeleccionada);
                   }
                   
                   
               }else{
                   
                   for(Point p:casillasDisponibles){
                       int pX= p.x;
                       int pY= p.y;
                       
                       if(filaSeleccionada==pX && columnaSeleccionada==pY){
                            System.out.println("Si esta en rango");
                            //Movimiento
                            tableroLogico[filaSeleccionada][columnaSeleccionada]=fichaOnHold;
                            tableroLogico[previousX][previousY]= null;
                            fichaOnHold=null;
                            seleccion=false;
                            //reseteo de seleccion
                            filaSeleccionada=-1;
                            columnaSeleccionada=-1;
                            casillasDisponibles.clear();
                            repaint();
                            break;
                       }
                   }
                   
                   
                    filaSeleccionada=-1;
                    columnaSeleccionada=-1;
                    System.out.println("Casilla Deseleccionada");
                    casillasDisponibles.clear();
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
    
    
    //Por revisar
    private void paintCampoSeleccion(int x, int y,  Graphics2D g2){//x y de la posicion actual
        //Medidas de celdas
        int anchoCelda =getWidth()/columnas;
        int alturaCelda= getHeight()/filas;
        
        //posicionamiento de array
        int fila = y/tamanioCeldas;
        int columna = x/tamanioCeldas;
        
        
        //     * * *     
        //     * F *
        //     * * *
        
        
        //F-1/C-1  F-1  F-1/C+1
        //    C-1  A  C+1
        //F+1/C-1  F+1  F+1/C+1
        
        
        if(tableroLogico[fila][columna]==null){
            g2.setColor(Color.RED);
            g2.fillRect(x, y, anchoCelda, alturaCelda);
            repaint();
        }
        
    }
    
   
    private void calcularMovimientos(int fila, int columna){
        casillasDisponibles.clear();
        
        int[][] direcciones={
            {-1,0}, //arriba
            {1,0},//abajo
            {0,-1},//izquierda
            {0,1},//derecha
            {-1,-1}, //esquina superior izquierda
            {-1,1},//esquina superior derecha
            {1,-1},//esquina inferior izquierda
            {1,1}//esquina inferiro derecha
        };
        
        for(int[] cords: direcciones){
            int filaNueva= fila +cords[0];
            int columnaNueva = columna +cords[1];
            
            //Verificacion que este dentro de los parametros del tablero
            if(filaNueva>=0 && filaNueva< tableroLogico.length && columnaNueva>=0 && columnaNueva< tableroLogico[0].length){
                
                //Verificar que la casilla este vacia de verdad
                if(tableroLogico[filaNueva][columnaNueva]==null ){
                    System.out.println("New Cords: "+filaNueva+","+columnaNueva);
                    casillasDisponibles.add(new Point(filaNueva, columnaNueva));
                } 
            }
        }
        
        repaint();
    }
    
    
    

}
