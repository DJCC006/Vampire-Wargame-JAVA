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
import javax.swing.JOptionPane;
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
    
    private int typNecromancer=3;
    private int typZombie=4;
    
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
                
                //Caso especifico para zombie
                if(tableroLogico[i][j]!= null && fichaOnHold== null  && tableroLogico[i][j].getTypeFicha()==4 && typFichaActual==3){
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
        
        for(Point p: casillasDisponibles){
            g2.setColor(new Color(0,255,0,100));
            int x = p.y*anchoCelda;
            int y = p.x*alturaCelda;
            g2.fillRect(x, y, anchoCelda, alturaCelda);
            
            if(tableroLogico[p.x][p.y]!=null){
                if(turnos){
                    if(fichaOnHold!=null && tableroLogico[p.x][p.y].getBando().equals("CONTRARIO")){
                            g2.setColor(new Color(255,102,102,100));
                            g2.fillRect(x, y, anchoCelda, alturaCelda);
                        }
                }else{
                    if(fichaOnHold!= null && tableroLogico[p.x][p.y].getBando().equals("JUGADOR")){ 
                        g2.setColor(new Color(255,102,102,100));
                        g2.fillRect(x, y, anchoCelda, alturaCelda);
                    }
                }
            } 
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
                            
                            
                            
                            
                            boolean esTipoCorrecto = fichaOnHold!= null && typFichaActual == fichaOnHold.getTypeFicha();
                            
                            if(typFichaActual == typNecromancer && fichaOnHold!= null && fichaOnHold.getTypeFicha()== typZombie){
                                esTipoCorrecto=true;
                            }
                         
                            
                            //fichaOnHold!= null && typFichaActual== fichaOnHold.getTypeFicha()
                            if(esTipoCorrecto){
                                
                                //Consideracion de que sea necromancer
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
                                if(tableroLogico[filaSeleccionada][columnaSeleccionada]!=null){
                                    msgAtaqueBasic(pAnuncios,fichaOnHold, tableroLogico[filaSeleccionada][columnaSeleccionada]);
                                }
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
                            
                             boolean esTipoCorrecto = fichaOnHold!= null && typFichaActual == fichaOnHold.getTypeFicha();
                            
                            if(typFichaActual == typNecromancer && fichaOnHold!= null && fichaOnHold.getTypeFicha()== typZombie){
                                esTipoCorrecto=true;
                            }
                         
                            
                            //fichaOnHold!= null && typFichaActual== fichaOnHold.getTypeFicha()
                            if(esTipoCorrecto){
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
                                if(tableroLogico[filaSeleccionada][columnaSeleccionada]!=null){
                                    msgAtaqueBasic(pAnuncios,fichaOnHold, tableroLogico[filaSeleccionada][columnaSeleccionada]);
                                }
                                
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
                       
                       
                       //Spawner de zombie 
                        if(fichaOnHold.getName().equals("NECROMANCER")){
                            NecroMancer tempo = (NecroMancer) fichaOnHold;
                            Object[] options= {"Si", "No"};
                            if(tableroLogico[filaSeleccionada][columnaSeleccionada]==null){
                                int choice= JOptionPane.showOptionDialog(this, "Generar un Zombie", "Accion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                                
                                if(choice==JOptionPane.YES_OPTION){
                                    tempo.spawnearZombie(tableroLogico, filaSeleccionada, columnaSeleccionada,turnos);
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
                                    
                                }else if(choice==JOptionPane.NO_OPTION){
                                    System.out.println("XD");
                                    
                                }
                                
                            }
                        }
                       
                       
                       if(filaSeleccionada==pX && columnaSeleccionada==pY){ //CONSIDERANDO SI YA ESTA EN EL CAMPO DE SELECCION

                           boolean movimientoValido=true;
                           
                           if(fichaOnHold!= null && fichaOnHold.getName().equals("NECROMANCER")){
                               int fOrigen = previousX;
                               int cOrigen = previousY;
                               
                               int fDestino = filaSeleccionada;
                               int cDestino = columnaSeleccionada;
                               
                               if(tableroLogico[fDestino][cDestino]==null){
                                   if(Math.abs(fDestino-fOrigen)>1 || Math.abs(cDestino -cOrigen)>1){
                                       movimientoValido=false;
                                       System.out.println("CASILLA FUERA DEL RANGO DE MOVIMIENTO VALIDO DEL NECROMANCER");
                                   }
                               }
                           }
                           
                           
                           if(fichaOnHold!=null && fichaOnHold.getName().equals("ZOMBIE")){
                               movimientoValido=false;
                               System.out.println("NO SE PUEDE MOVER EL ZOMBIE");
                           }
                           
                           
                           System.out.println("Movimeinto valido: "+movimientoValido);
                           if(movimientoValido){
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
        
        
        //Calculo normal de posiciones validas
        for(int[] cords: direcciones){
            
            int filaNueva= fila +cords[0];
            int columnaNueva = columna +cords[1];
            
            //Verificacion que este dentro de los parametros del tablero
            if(filaNueva>=0 && filaNueva< tableroLogico.length && columnaNueva>=0 && columnaNueva< tableroLogico[0].length){
                
                //Verificar que la casilla este vacia de verdad
                if(tableroLogico[filaNueva][columnaNueva]==null ){
                    casillasDisponibles.add(new Point(filaNueva, columnaNueva));
                }else{
                    
                    if(fichaOnHold.getName().equals("HOMBRE LOBO")){
                        int diffFila = Math.abs(filaNueva-fila);
                        int diffColumna = Math.abs(columnaNueva-columna);
                        
                        int distanciaMaxima =Math.max(diffFila, diffColumna);
                        
                        if(distanciaMaxima==1){
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
        }
        
        
        //logica 
        if(fichaOnHold.getName().equals("NECROMANCER")){
            for(int i=0; i< tableroLogico.length; i++){
                for(int j=0; j<tableroLogico[0].length; j++){
                    
                    if(i==fila && j== columna){
                        continue;
                    }
                    if(tableroLogico[i][j]==null){
                        casillasDisponibles.add(new Point(i,j));
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
    
    
    private void msgAtaqueBasic(JPanel panel, Ficha atacante, Ficha atacado){
        JLabel texto= new JLabel();
        if(turnos){
            panel.removeAll();
            texto.setText(atacante.getName()+" ha atacado a "+atacado.getName()
                            +"\nStats de "+atacado.getName()+": HP-"+atacado.getVida()+" SHD: "+atacado.getEscudo());
            panel.add(texto);
            texto.setBounds(100, 5, 600, 100);
            texto.setFont(new Font("Serif", Font.BOLD, 18));
        }else{
            panel.removeAll();
            texto.setText(atacante.getName()+" ha atacado a "+atacado.getName()
                            +"\nStats de "+atacado.getName()+": HP-"+atacado.getVida()+" SHD: "+atacado.getEscudo());
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
                
                            
                //Revision previo a ataque
                int filaOrigen = previousX;
                int columnaOrigen = previousY;
                
                int diffFila = Math.abs(filaSeleccionada-filaOrigen);
                int diffColumna = Math.abs(columnaSeleccionada-columnaOrigen);
                
                boolean ataqueValido=false;
                
                if(fichaOnHold!= null && fichaOnHold.getName().equals("NECROMANCER")){
                    if((diffFila<=2 && diffColumna<=2)&& (diffFila+diffColumna>0)){
                        ataqueValido=true;
                    }else{
                        System.out.println("NOS SALIMOS DEL RANGO DE ATAQUE");
                        return false;
                    }
                }else if(fichaOnHold!= null && fichaOnHold.getName().equals("HOMBRE LOBO")){
                    if((diffFila<=1 && diffColumna<=1)&& (diffFila+diffColumna>0)){
                        ataqueValido=true;
                    }else{
                        System.out.println("NOS SALIMOS DEL RANGO DE ATAQUE");
                        return false;
                    }
                }else{
                    ataqueValido=true;
                }
                
                if(ataqueValido){
                    
                    
                    //Check-Up de ficha a seleccionar
                    if(tableroLogico[filaSeleccionada][columnaSeleccionada]!=null){
                       System.out.println("Detecto que hay una ficha aqui");
                        if(turnos){
                            if(fichaOnHold!=null && tableroLogico[filaSeleccionada][columnaSeleccionada].getBando().equals("CONTRARIO")){
                                //Revision de necromancer
                                if(fichaOnHold.getName().equals("NECROMANCER")){
                                    //Evaluacion de dsitancia aqui
                                    int distanciaMax= Math.max(diffFila, diffColumna);
                                    
                                    if(distanciaMax==2){
                                        fichaOnHold.ataqueEspecial(tableroLogico[filaSeleccionada][columnaSeleccionada]);
                                        System.out.println("NEGROMANCER Y Se ha realizado EL ATAQUE ESPECIAL LANZA");
                                    }else if(distanciaMax==1){
                                       fichaOnHold.ataque(tableroLogico[filaSeleccionada][columnaSeleccionada]);
                                        System.out.println("NEGROMANCER Y SE HA REALIZADO ATAQUE NORMAL");
                                        
                                    }
                                }else if(fichaOnHold.getName().equals("HOMBRE LOBO")){
                                    int distanciaMax= Math.max(diffFila, diffColumna);
                                    
                                    if(distanciaMax==1){
                                       fichaOnHold.ataque(tableroLogico[filaSeleccionada][columnaSeleccionada]);
                                        System.out.println("HOMBRE LOBO  HA REALIZADO ATAQUE NORMAL");
                                    }else{
                                        System.out.println("ATAQUE NO VALIDO PARA HOMBRE LOBO");
                                        return false;
                                    }
                                    
                                }else{
                                    fichaOnHold.ataque(tableroLogico[filaSeleccionada][columnaSeleccionada]);
                                    System.out.println("ATAQUE NORMAL NO NEGROMANCER");
                                }
                                
                                //REVISION DE ELIMINACION 
                                if(tableroLogico[filaSeleccionada][columnaSeleccionada].getVida()<=0){
                                    System.out.println(tableroLogico[filaSeleccionada][columnaSeleccionada].getName()+" ha sido eliminada");
                                    if(ruletaGeneral.reducirPesoFicha(tableroLogico[filaSeleccionada][columnaSeleccionada].getTypeFicha())){
                                        System.out.println("SE HA ELIMINADO UNA FICHA DE LA RULETA");
                                    }else{
                                        System.out.println("YA NO QUEDAN FICHAS DE ESTE TIPO EN LA RULETA");
                                    }
                                    tableroLogico[filaSeleccionada][columnaSeleccionada]=null;
                                    return true;
                                }else{
                                    System.out.println("STATS DE CONTRICANTE:"
                                    + "\n Escudo: "+tableroLogico[filaSeleccionada][columnaSeleccionada].getEscudo()
                                    +"\nVida: "+tableroLogico[filaSeleccionada][columnaSeleccionada].getVida());
                                    return true;
                                }


                            }
                        }else{
                            if(fichaOnHold!= null && tableroLogico[filaSeleccionada][columnaSeleccionada].getBando().equals("JUGADOR")){
                                 if(fichaOnHold.getName().equals("NECROMANCER")){
                                    int distanciaMax= Math.max(diffFila, diffColumna);
                                    
                                    if(distanciaMax==2){
                                        fichaOnHold.ataqueEspecial(tableroLogico[filaSeleccionada][columnaSeleccionada]);
                                        System.out.println("NEGROMANCER Y Se ha realizado EL ATAQUE ESPECIAL LANZA");
                                    }else if(distanciaMax==1){
                                       fichaOnHold.ataque(tableroLogico[filaSeleccionada][columnaSeleccionada]);
                                        System.out.println("NEGROMANCER Y SE HA REALIZADO ATAQUE NORMAL");
                                        
                                    }
                                }else if(fichaOnHold.getName().equals("HOMBRE LOBO")){
                                    int distanciaMax= Math.max(diffFila, diffColumna);
                                    
                                    if(distanciaMax==1){
                                       fichaOnHold.ataque(tableroLogico[filaSeleccionada][columnaSeleccionada]);
                                        System.out.println("HOMBRE LOBO  HA REALIZADO ATAQUE NORMAL");
                                    }else{
                                        System.out.println("ATAQUE NO VALIDO PARA HOMBRE LOBO");
                                        return false;
                                    }
                                    
                                }else{
                                    fichaOnHold.ataque(tableroLogico[filaSeleccionada][columnaSeleccionada]);
                                    System.out.println("ATAQUE NORMAL NO NEGROMANCER");
                                }
                                
                                //REVISION DE ELIMINACION 
                                if(tableroLogico[filaSeleccionada][columnaSeleccionada].getVida()<=0){
                                    System.out.println(tableroLogico[filaSeleccionada][columnaSeleccionada].getName()+" ha sido eliminada");
                                    if(ruletaGeneral.reducirPesoFicha(tableroLogico[filaSeleccionada][columnaSeleccionada].getTypeFicha())){
                                        System.out.println("SE HA ELIMINADO UNA FICHA DE LA RULETA");
                                    }else{
                                        System.out.println("YA NO QUEDAN FICHAS DE ESTE TIPO EN LA RULETA");
                                    }
                                    tableroLogico[filaSeleccionada][columnaSeleccionada]=null;
                                    return true;
                                }else{
                                    
                                    System.out.println("STATS DE CONTRICANTE:"
                                    + "\n Escudo: "+tableroLogico[filaSeleccionada][columnaSeleccionada].getEscudo()
                                    +"\nVida: "+tableroLogico[filaSeleccionada][columnaSeleccionada].getVida());
                                    return true;
                                }
                            }
                        }//cierre if de turnos
                    }
                }//cierre ataque valido
                
               
            }
        }    
       return false;  
    }
    
    
    
}
