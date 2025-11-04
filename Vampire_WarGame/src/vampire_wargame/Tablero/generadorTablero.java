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
    private Ficha fichaOnHold;//Almacena la ficha que se ha llecho click
    private boolean seleccion=false;
    private int previousX;
    private int previousY;
    
    //Controlador de turnos
    private boolean turnos=true;// true-JUGADOR    false-CONTRARIO
    
    
    
    //Tablero logico
    private Ficha[][] tableroLogico = new Ficha[6][6];
    private ArrayList<Point> casillasDisponibles = new ArrayList<>(); //Guarda las coordenadas de las casillas que se remarcaran
    
    
    //Creacion de objeto de RULETA
    //ruletaGen ruletaJUGADOR = new ruletaGen(this);
    //ruletaGen ruletaCONTRICANTE = new ruletaGen(this);
    private ruletaGen ruletaGeneral;
 
    private int typFichaActual=0;//variable que lleva control del tipo de ficha que salio de la ruleta
    
    
    
    public generadorTablero(int tamanioCeldas, ruletaGen ruleta){
        ruletaGeneral= ruleta;
        this.tamanioCeldas= tamanioCeldas;
        setPreferredSize(new Dimension(columnas * tamanioCeldas, filas*tamanioCeldas));
        addMouseListener(this);//agregar el listener de los clicks
        
        //Colocacuion de fichas
        //FICHAS CONTRICANTES
        tableroLogico[0][0]= new wolfMan();
        tableroLogico[0][0].setBando("CONTRARIO");
        tableroLogico[0][1] = new Vampire();
        tableroLogico[0][1].setBando("CONTRARIO");
        tableroLogico[0][2]= new NecroMancer();
        tableroLogico[0][2].setBando("CONTRARIO");
        tableroLogico[0][3]= new NecroMancer();
        tableroLogico[0][3].setBando("CONTRARIO");
        tableroLogico[0][4]= new Vampire();
        tableroLogico[0][4].setBando("CONTRARIO");
        tableroLogico[0][5]= new wolfMan();
        tableroLogico[0][5].setBando("CONTRARIO");
        
        
        //FICHAS DE JUGADOR
        tableroLogico[5][0]= new wolfMan();
        tableroLogico[5][0].setBando("JUGADOR");
        tableroLogico[5][1]= new Vampire();
        tableroLogico[5][1].setBando("JUGADOR");
        tableroLogico[5][2]= new NecroMancer();
        tableroLogico[5][2].setBando("JUGADOR");
        tableroLogico[5][3]= new NecroMancer();
        tableroLogico[5][3].setBando("JUGADOR");
        tableroLogico[5][4]= new Vampire();
        tableroLogico[5][4].setBando("JUGADOR");
        tableroLogico[5][5]= new wolfMan();
        tableroLogico[5][5].setBando("JUGADOR");
        
        
        System.out.println("TURNO JUGADOR");  
        //ruletaJUGADOR.genVisuals();
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
       
        
        
        Graphics2D g2= (Graphics2D) g;
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//Suavisado de bordes
       
        int anchoCelda =getWidth()/columnas;
        int alturaCelda= getHeight()/filas;
        
        
         if(turnos){
            typFichaActual=ruletaGeneral.getLastSelected();
        }else{
            typFichaActual=ruletaGeneral.getLastSelected();
        }

        
        
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
                
                
               
                
                
                System.out.println("Ficha actual:"+typFichaActual);
                
                if(tableroLogico[i][j]!= null && fichaOnHold== null  && tableroLogico[i][j].getTypeFicha()==typFichaActual ){
                    if(turnos==true){
                        if(tableroLogico[i][j].getBando().equals("JUGADOR")){
                            g2.setColor(Color.RED);
                            g2.fillRect(x, y, anchoCelda, alturaCelda);
                            //ruletaJUGADOR.genVisuals();
                        }
                    }else if(turnos==false){
                        if(tableroLogico[i][j].getBando().equals("CONTRARIO")){
                            g2.setColor(Color.RED);
                            g2.fillRect(x, y, anchoCelda, alturaCelda);
                            
                        }
                    }
                }
                
                
                
                //FIX THIS
                /*
                if(fichaOnHold!=null && fichaOnHold.getTypeFicha()==typFichaActual){
                    if(turnos==true){
                        if(tableroLogico[i][j].getBando().equals("JUGADOR")){
                            g2.setColor(Color.RED);
                            g2.fillRect(x, y, anchoCelda, alturaCelda);
                            //ruletaJUGADOR.genVisuals();
                        }
                    }else if(turnos==false){
                        if(tableroLogico[i][j].getBando().equals("CONTRARIO")){
                            g2.setColor(Color.RED);
                            g2.fillRect(x, y, anchoCelda, alturaCelda);
                            
                        }
                    }
                }
                
                */
                
                
                
                
                //REALIZAR UN BLOQUE DE CODE QUE PINTE LAS CASILLAS DONDE SE ENCUENTRE EL TIPO DE FICHA SELECCIONADA
                
                
                //Seleccion de casilla solo si tiene ficha 
                if(i==filaSeleccionada && j == columnaSeleccionada && fichaOnHold!=null && tableroLogico[filaSeleccionada][columnaSeleccionada]!=null && typFichaActual== fichaOnHold.getTypeFicha()){
                    
                    System.out.println("FICHA ON HOLD: "+fichaOnHold.getTypeFicha());
                    //Seleccion condicionada al turno actual
                    if(turnos==true){
                        if(tableroLogico[filaSeleccionada][columnaSeleccionada].getBando().equals("JUGADOR")){
                            g2.setColor(Color.RED);
                            g2.fillRect(x, y, anchoCelda, alturaCelda);
                            //ruletaJUGADOR.genVisuals();
                        }
                    }else if(turnos==false){
                        if(tableroLogico[filaSeleccionada][columnaSeleccionada].getBando().equals("CONTRARIO")){
                            g2.setColor(Color.RED);
                            g2.fillRect(x, y, anchoCelda, alturaCelda);
                            
                        }
                    }
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
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //OBtencion de coordenadas donde se ha hecho click
        int x= e.getX();
        int y= e.getY();
        
        //Obtenemos los valores de la teoricos fila y columna donde se ha hecho click
        int fila = y/tamanioCeldas;
        int columna = x/tamanioCeldas;
        
        
        //Obtencion de info de tipo de ficha seleccionada
        
        if(turnos){
            typFichaActual=ruletaGeneral.getLastSelected();
        }else{
            typFichaActual=ruletaGeneral.getLastSelected();
        }

        
        System.out.println("TIPO DE FICHA A JUGAR:"+typFichaActual);
        
        //Comprobacion que sean las filas y columnas del tablero como tal
        if(fila>=0 && fila<filas && columna>=0 && columna<columnas){
            //CASO 1 DE DESELECCION: CUANDO ES SU MISMA FICHA
            if(fila==filaSeleccionada && columna == columnaSeleccionada){
                filaSeleccionada=-1;
                columnaSeleccionada=-1;
                System.out.println("Casilla Deseleccionada");
                casillasDisponibles.clear();
                repaint();
            }else{
               
                filaSeleccionada=fila;
                columnaSeleccionada=columna;
                
               //Check up de casilla con tablero logico
               if(tableroLogico[filaSeleccionada][columnaSeleccionada]!=null){
                   //Seleccion dependiendo del turno
                   if(turnos==true){
                       if(tableroLogico[filaSeleccionada][columnaSeleccionada].getBando().equals("JUGADOR")){
                            System.out.println("Aqui hay una ficha!");
                            fichaOnHold=tableroLogico[filaSeleccionada][columnaSeleccionada];
                            previousX=filaSeleccionada;
                            previousY=columnaSeleccionada;
                            seleccion=true;

                            if(fichaOnHold!= null && typFichaActual== fichaOnHold.getTypeFicha()){
                                calcularMovimientos(filaSeleccionada, columnaSeleccionada);
                                
                            }else if(fichaOnHold!= null && typFichaActual!= fichaOnHold.getTypeFicha()){
                                filaSeleccionada=-1;
                                columnaSeleccionada=-1;
                                System.out.println("Casilla Deseleccionada");
                                casillasDisponibles.clear();
                                repaint();
                            }
                       }else if(tableroLogico[filaSeleccionada][columnaSeleccionada].getBando().equals("CONTRARIO")){
                            //CASE 2 DE DESELECCION: CUANDO SE CLICKEA FICHA DEL CONTRICANTE QUE NO TIENE TURNO
                            filaSeleccionada=-1;
                            columnaSeleccionada=-1;
                            
                            System.out.println("Casilla Deseleccionada");
                            casillasDisponibles.clear();
                            repaint();
                       }
                   }else if(turnos==false){
                       if(tableroLogico[filaSeleccionada][columnaSeleccionada].getBando().equals("CONTRARIO")){
                            System.out.println("Aqui hay una ficha!");
                            fichaOnHold=tableroLogico[filaSeleccionada][columnaSeleccionada];
                            previousX=filaSeleccionada;
                            previousY=columnaSeleccionada;
                            seleccion=true;

                            if(fichaOnHold!= null && typFichaActual== fichaOnHold.getTypeFicha()){
                                calcularMovimientos(filaSeleccionada, columnaSeleccionada);
                            }else if(fichaOnHold!= null && typFichaActual!= fichaOnHold.getTypeFicha()){
                                filaSeleccionada=-1;
                                columnaSeleccionada=-1;
                                System.out.println("Casilla Deseleccionada");
                                casillasDisponibles.clear();
                                repaint();
                            }
                       }else if(tableroLogico[filaSeleccionada][columnaSeleccionada].getBando().equals("JUGADOR")){
                           //CASE 2 DE DESELECCION: CUANDO SE CLICKEA FICHA DEL CONTRICANTE QUE NO TIENE TURNO
                            filaSeleccionada=-1;
                            columnaSeleccionada=-1;
                            
                            System.out.println("Casilla Deseleccionada");
                            casillasDisponibles.clear();
                            repaint();
                       }
                       
                   }
                   
               }else{
                   
                   for(Point p:casillasDisponibles){
                       int pX= p.x;
                       int pY= p.y;
                       
                       if(filaSeleccionada==pX && columnaSeleccionada==pY){
                            //Movimiento
                            tableroLogico[filaSeleccionada][columnaSeleccionada]=fichaOnHold;
                            tableroLogico[previousX][previousY]= null;
                            fichaOnHold=null;
                            seleccion=false;
                            //reseteo de seleccion
                            filaSeleccionada=-1;
                            columnaSeleccionada=-1;
                            
                            ruletaGeneral.cleanLastSelected();
                            ruletaGeneral.cleanLastSelected();
                            
                            casillasDisponibles.clear();
                            repaint();
                            
                            //CAMBIO DE BANDOS
                            if(turnos==true){
                                turnos=false;
                                ruletaGeneral.setTurnos(turnos);
                                System.out.println("TURNO CONTRICANTE");
                                
                            }else{
                                turnos=true;
                                ruletaGeneral.setTurnos(turnos);
                                System.out.println("TURNO JUGADOR");
                                
                            }
                            break;
                       }
                   }
                   
                   //CASO 3  DE DESELECCION: CUANDO SE CLICKEA EN UNA FICHA SIN NADA
                   //Buffer de en caso que se clickee en casilla donde no hay nada
                    filaSeleccionada=-1;
                    columnaSeleccionada=-1;
                    
                    System.out.println("Casilla Deseleccionada");
                    casillasDisponibles.clear();
                    repaint();
                   
               }
            }
            repaint(); 
        }  
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
    
    private void calcularMovimientos(int fila, int columna){
        casillasDisponibles.clear();
        
        int[][] direcciones=fichaOnHold.getdirecciones();
        
        for(int[] cords: direcciones){
            
            int filaNueva= fila +cords[0];
            int columnaNueva = columna +cords[1];
            
            //Verificacion que este dentro de los parametros del tablero
            if(filaNueva>=0 && filaNueva< tableroLogico.length && columnaNueva>=0 && columnaNueva< tableroLogico[0].length){
                
                //Verificar que la casilla este vacia de verdad
                if(tableroLogico[filaNueva][columnaNueva]==null ){
                    casillasDisponibles.add(new Point(filaNueva, columnaNueva));
                } 
            }
        }
        
        repaint();
    }
    
    
    private void cargarRuleta(){
        if(turnos){
            //ruletaJUGADOR.genVisuals();
            System.out.println("GENERANDO RULETA JUGADOR");
        }else{
            //ruletaCONTRICANTE.genVisuals();
            System.out.println("GENERANDO RUELTA CONTRICANTE");
        }
    }
    
   
    public void setTypFicha(int typ){
        typFichaActual=typ;
    }
    
    
    
}
