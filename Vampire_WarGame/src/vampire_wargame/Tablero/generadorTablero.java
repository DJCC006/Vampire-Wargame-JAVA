/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame.Tablero;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
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
    
    
    //Almacenadores de demas GUI con la que interactua el tablero
    //POSIBLEMENTE PODER VOLVER ESTO A UNA INTERFAZ PARA MOSTRAR ANUNCIOS DE MANERA MAS FACIL
    private JPanel pAnuncios;
    private JPanel cJugador;
    private JPanel cContricante;
    private JPanel pTurnos;
    
    
    
    public generadorTablero(int tamanioCeldas, ruletaGen ruleta, JPanel pAnuncios, JPanel cJugador, JPanel cContricantes, JPanel pTurnos){
        ruletaGeneral= ruleta;
        this.tamanioCeldas= tamanioCeldas;
        this.pAnuncios=pAnuncios;
        this.cJugador=cJugador;
        this.cContricante=cContricantes;
        this.pTurnos=pTurnos;
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
        
        
        //System.out.println("TURNO JUGADOR");  
        msgCambioTurnos(turnos, pTurnos);
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
                
                
               
                
                
                //System.out.println("Ficha actual:"+typFichaActual);
                
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
                
                //REALIZAR UN BLOQUE DE CODE QUE PINTE LAS CASILLAS DONDE SE ENCUENTRE EL TIPO DE FICHA SELECCIONADA
                
                
                //Seleccion de casilla solo si tiene ficha 
                if(i==filaSeleccionada && j == columnaSeleccionada && fichaOnHold!=null && tableroLogico[filaSeleccionada][columnaSeleccionada]!=null && typFichaActual== fichaOnHold.getTypeFicha()){
                    
                    //System.out.println("FICHA ON HOLD: "+fichaOnHold.getTypeFicha());
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
                System.out.println("Casilla Deseleccionada: MISMA FICHA");
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
                                System.out.println("Casilla Deseleccionada: FICHA NO DEL TIPO OBTENIDO");
                                casillasDisponibles.clear();
                                repaint();
                            }
                       }else if(tableroLogico[filaSeleccionada][columnaSeleccionada].getBando().equals("CONTRARIO")){
                            //CASE 2 DE DESELECCION: CUANDO SE CLICKEA FICHA DEL CONTRICANTE QUE NO TIENE TURNO
                            
                             if(revYAtaque()){
                                turnos=false;
                                ruletaGeneral.setTurnos(turnos);
                                msgCambioTurnos(turnos, pTurnos);    
                                fichaOnHold=null;
                                seleccion=false;
                                ruletaGeneral.cleanLastSelected();
                                casillasDisponibles.clear();
                                repaint();
                                 
                                 
                                 
                             }else{
                                filaSeleccionada=-1;
                                columnaSeleccionada=-1;

                                System.out.println("Casilla Deseleccionada: FICHA DE CONTRICANTE");
                                casillasDisponibles.clear();
                                repaint();
                             }
                             
                            
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
                                System.out.println("Casilla Deseleccionada: FICHA NO DEL TIPO OBTENIDO");
                                casillasDisponibles.clear();
                                repaint();
                            }
                       }else if(tableroLogico[filaSeleccionada][columnaSeleccionada].getBando().equals("JUGADOR")){
                           //CASE 2 DE DESELECCION: CUANDO SE CLICKEA FICHA DEL CONTRICANTE QUE NO TIENE TURNO
                            if(revYAtaque()){
                                turnos=true;
                                ruletaGeneral.setTurnos(turnos);
                                msgCambioTurnos(turnos, pTurnos);    
                                fichaOnHold=null;
                                seleccion=false;
                                ruletaGeneral.cleanLastSelected();
                                casillasDisponibles.clear();
                                repaint();
                                 
                                 
                                 
                             }else{
                                filaSeleccionada=-1;
                                columnaSeleccionada=-1;

                                System.out.println("Casilla Deseleccionada: FICHA DE CONTRICANTE");
                                casillasDisponibles.clear();
                                repaint();
                             }
                       }
                       
                   }
                   
               }else{
                   
                   //Seleccion de movimiento
                   for(Point p:casillasDisponibles){
                       int pX= p.x;
                       int pY= p.y;
                       
                       if(filaSeleccionada==pX && columnaSeleccionada==pY){ //CONSIDERANDO SI YA ESTA EN EL CAMPO DE SELECCION
                            //Movimiento General
                             tableroLogico[filaSeleccionada][columnaSeleccionada]=fichaOnHold;
                                msgMovimiento(filaSeleccionada, columnaSeleccionada, fichaOnHold.getTypeFicha(),pAnuncios);
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
                                    msgCambioTurnos(turnos, pTurnos);
                                    //System.out.println("TURNO CONTRICANTE");

                                }else{
                                    turnos=true;
                                    ruletaGeneral.setTurnos(turnos);
                                    msgCambioTurnos(turnos, pTurnos);
                                    //System.out.println("TURNO JUGADOR");

                                }
                                break;
                       }
                   }
                   
                   //CASO 3  DE DESELECCION: CUANDO SE CLICKEA EN UNA FICHA SIN NADA
                   //Buffer de en caso que se clickee en casilla donde no hay nada
                    filaSeleccionada=-1;
                    columnaSeleccionada=-1;
                    
                    //System.out.println("Casilla Deseleccionada");
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
                }else{
                    if(turnos){
                        if(tableroLogico[filaNueva][columnaNueva]!= null && tableroLogico[filaNueva][columnaNueva].getBando().equals("CONTRARIO")){
                            casillasDisponibles.add(new Point(filaNueva, columnaNueva));
                        }
                    }else{
                       if(tableroLogico[filaNueva][columnaNueva]!= null && tableroLogico[filaNueva][columnaNueva].getBando().equals("JUGADOR")){
                            casillasDisponibles.add(new Point(filaNueva, columnaNueva));
                        } 
                    }
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
    
    
    
    //Metodos para mostrar de anuncios
    private void msgMovimiento(int fila, int columna, int TipoFicha, JPanel panel){
        JLabel texto= new JLabel();
        //System.out.println("CAMBIANDO TEXTO");
        switch(TipoFicha){
            case 1:
                panel.removeAll();
                texto.setText("se ha movido HOMBRE LOBO a casilla ["+(fila+1)+","+(columna+1)+"]");
                panel.add(texto);
                texto.setBounds(100, 5, 600, 100);
                texto.setFont(new Font("Serif", Font.BOLD, 20));
                break;
            case 2:
                panel.removeAll();
                texto.setText("se ha movido VAMPIRO a casilla ["+(fila+1)+","+(columna+1)+"]");
                panel.add(texto);
                texto.setBounds(100, 5, 600, 100);
                texto.setFont(new Font("Serif", Font.BOLD, 20));
                break;
                
            case 3:
                panel.removeAll();
                texto.setText("se ha movido NECROMANCER a casilla ["+(fila+1)+","+(columna+1)+"]");
                panel.add(texto);
                texto.setBounds(100, 5, 600, 100);
                texto.setFont(new Font("Serif", Font.BOLD, 18));
                break;
        }
        
    }
    
    
    
    private void msgCambioTurnos(boolean turnos, JPanel panel){//USAR UN JPANEL APARTE PARA TURNOS
        JLabel texto= new JLabel();
        if(turnos){
            panel.removeAll();
            texto.setText("TURNO DE JUGADOR");
            panel.add(texto);
            texto.setBounds(100, 5, 600, 100);
            texto.setFont(new Font("Serif", Font.BOLD, 18));
        }else{
            panel.removeAll();
            texto.setText("TURNO DE CONTRICANTE");
            panel.add(texto);
            texto.setBounds(100, 5, 600, 100);
            texto.setFont(new Font("Serif", Font.BOLD, 18));
        }
        
    }
    
    
    
    private boolean revYAtaque(){
        for(Point p:casillasDisponibles){
            int pX= p.x;
            int pY= p.y;
                       
            if(filaSeleccionada==pX && columnaSeleccionada==pY){ //CONSIDERANDO SI YA ESTA EN EL CAMPO DE SELECCION
                //Movimiento
                            
                //Check up de si hay una ficha enemiga
                if(tableroLogico[filaSeleccionada][columnaSeleccionada]!=null){
                   System.out.println("Detecto que hay una ficha aqui");
                    if(turnos){
                        if(fichaOnHold!=null && tableroLogico[filaSeleccionada][columnaSeleccionada].getBando().equals("CONTRARIO")){
                            fichaOnHold.ataque(tableroLogico[filaSeleccionada][columnaSeleccionada]);
                            System.out.println("Se ha realizado un ataque");
                            System.out.println("STATS DE CONTRICANTE:"
                                + "\n Escudo: "+tableroLogico[filaSeleccionada][columnaSeleccionada].getEscudo()
                                +"\nVida: "+tableroLogico[filaSeleccionada][columnaSeleccionada].getVida());
                            return true;
                            
                        }
                    }else{
                        if(fichaOnHold!= null && tableroLogico[filaSeleccionada][columnaSeleccionada].getBando().equals("JUGADOR")){
                            fichaOnHold.ataque(tableroLogico[filaSeleccionada][columnaSeleccionada]);
                            System.out.println("Se ha realizado un ataque");
                            System.out.println("STATS DE CONTRICANTE:"
                                + "\n Escudo: "+tableroLogico[filaSeleccionada][columnaSeleccionada].getEscudo()
                                +"\nVida: "+tableroLogico[filaSeleccionada][columnaSeleccionada].getVida());
                            return true;
                        }
                    }
                                
                }
            }
        }
        
        return false;
    }
    
}
