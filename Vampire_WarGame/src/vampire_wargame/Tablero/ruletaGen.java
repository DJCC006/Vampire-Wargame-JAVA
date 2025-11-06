/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame.Tablero;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author David
 */
public class ruletaGen extends JPanel {
    private Map<Integer, Integer> favorabilidadActiva;
    private Map<Integer, Integer> fContricanteActiva;
    private Random rand;
    private static final int espacioMuestralFijo= 6;
    private JFrame screen = new JFrame();
    private ImageIcon ruletaImag;
    private ImageIcon wolfImag;
    private ImageIcon vampImag;
    private ImageIcon necroImag;
    private ImageIcon nullIcon;
    
    private int lastSelected=0;
    private JPanel panel;
    private generadorTablero tableroController;
    
    private boolean turnosControlador=true;
    
    public ruletaGen(JPanel panel ){
        this.panel=panel;
      //this.panel=panel;
      rand = new Random();
      //Creacion de mapa y datos para el jugador
      favorabilidadActiva= new HashMap<>();
      favorabilidadActiva.put(1, 2);//Wolf      (TIPO FICHA, CANTIDAD DE ESA FICHA)
      favorabilidadActiva.put(2, 2);//Vamp
      favorabilidadActiva.put(3, 2);//Necro 
      
      //Creacion de mapa y datos para el contricante
      fContricanteActiva= new HashMap<>();
      fContricanteActiva.put(1, 2);
      fContricanteActiva.put(2, 2);
      fContricanteActiva.put(3, 2);
      
      
      
      ruletaImag= new ImageIcon("src\\resources\\ruletaGIF.gif");
      wolfImag = new ImageIcon("src\\resources\\wolfIcon.png");
      vampImag = new ImageIcon("src\\resources\\vampIcon.png");
      necroImag = new ImageIcon("src\\resources\\deathIcon.png");
      nullIcon = new ImageIcon("src\\resources\\nuledaIcon.png");
      
      //this.setLayout(null);
      
      JLabel texto = new JLabel("");
      texto.setBounds(0, 0, 300, 200);
      texto.setIcon(ruletaImag);
      
      JButton btGirar = new JButton("GIRAR RULETA");
      btGirar.setBounds(100, 290, 200, 50);
      
       btGirar.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              ImprimirProbabilidades();
              
              System.out.println("");
              int resultado = girarRuleta();
              
              lastSelected=resultado;//guardar el tipo de ficha seleccionada por cada tirada;
             texto.setIcon(nullIcon);
            Timer timer = new Timer(2000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){ //Ya la estructura del actionPerfomed ejecuta la logica de cerrarse despues del delay
                
                ((Timer)e.getSource()).stop();
                
                switch(resultado){
                  case 1:
                      texto.setIcon(wolfImag);
                      System.out.println("RESULTADO: HOMBRE LOBO");
                      break;
                      
                      
                  case 2:
                      texto.setIcon(vampImag);
                      System.out.println("RESULTADO: VAMPIRO");
                      
                      break;
                      
                  case 3:
                      texto.setIcon(necroImag);
                      System.out.println("RESULTADO: NECROMANCER");
                      
                      break;
                      
                  case 0:
                      texto.setIcon(nullIcon);
                      System.out.println("RESULTADO: ESPACIO VACIO");
                      int extra= manejarResultadoNulo();
                      switch(extra){
                          case 1:
                              texto.setIcon(wolfImag);
                              System.out.println("RESULTADO EXTRA: HOMBRE LOBO");
                              break;
                              
                          case 2:
                              texto.setIcon(vampImag);
                              System.out.println("RESULTADO EXTRA: VAMPIRO");
                              break;
                          case 3:
                              texto.setIcon(necroImag);
                              System.out.println("RESULTADO EXTRA: NECROMANCER");
                              break;
                      }
                      break;
              }
                  
                  
                 
                 tableroController.setTypFicha(lastSelected);
                 tableroController.repaint();
//              iniciarTempoCierre();
                
                
                
                }         
            });

            timer.setRepeats(false);
            timer.start();
          }
                    
        });
       
      panel.add(texto);
      panel.add(btGirar);
    }
    
    
    
    public int girarRuleta() {
        
        int resultado = rand.nextInt(espacioMuestralFijo)+1;
        
        int favAcumulada=0;
        
        //calculos en base al turno
        if(turnosControlador){
            System.out.println("CALCULANDO DESDE EL CONTROLADOR DE JUGADOR");
           for(Map.Entry<Integer, Integer> entrada: favorabilidadActiva.entrySet()){
                int tipoFicha = entrada.getKey();
                int favorabilidad = entrada.getValue();


                favAcumulada+=favorabilidad;

                if(resultado<= favAcumulada){
                    return tipoFicha;
                }
            } 
        }else{
            System.out.println("CALCULANDO DESDE EL CONTROLADOR DE CONTRICANTE");
            for(Map.Entry<Integer, Integer> entrada: fContricanteActiva.entrySet()){
                int tipoFicha = entrada.getKey();
                int favorabilidad = entrada.getValue();


                favAcumulada+=favorabilidad;

                if(resultado<= favAcumulada){
                    return tipoFicha;
                }
            }
        }
        
        
        return 0; //0 representando a un espacio vacio
    }
    
    
    
    public boolean reducirPesoFicha(int tipoFicha){//probablemente modificar para cambiar al del tipo del enemigo
        
        //Condicionado a turnos
        if(turnosControlador){
            int favActual = favorabilidadActiva.get(tipoFicha);

            if(favActual >0){
                favorabilidadActiva.put(tipoFicha, favActual-1);
                return true;
            }     
        }else{
            int favActual = fContricanteActiva.get(tipoFicha);

            if(favActual >0){
                fContricanteActiva.put(tipoFicha, favActual-1);
                return true;
            }  
        }
        return false;
    }
    
    
    
    public int manejarResultadoNulo(){
        int pesoMinimo=6;
        
        //Condicionada a turnos
        if(turnosControlador){
            //Proceso para buscar el peso / cantidad minima de fichas que hay
            for(int peso: favorabilidadActiva.keySet()){
                if(peso>0 && peso< pesoMinimo){
                    pesoMinimo=peso;
                }
            }
            //Encontrar los tipo de ficha dentro de tal rango
        }else{
            for(int peso: fContricanteActiva.keySet()){
                if(peso>0 && peso< pesoMinimo){
                    pesoMinimo=peso;
                }
            }
        }
        
        ArrayList<Integer> candidatos = new ArrayList<>();
        
        //Condicionada a turnos
        if(turnosControlador){
            for(Map.Entry<Integer, Integer> ficha : favorabilidadActiva.entrySet()){
                if(ficha.getValue()==pesoMinimo){
                    candidatos.add(ficha.getKey());
                }
            }
        }else{
            for(Map.Entry<Integer, Integer> ficha : fContricanteActiva.entrySet()){
                if(ficha.getValue()==pesoMinimo){
                    candidatos.add(ficha.getKey());
                }
            }
        }
        
        
        
        //determinar de manera random a quien le correspondera la nulidad
        if(candidatos.size()==1){
            int fichaSelecc = candidatos.get(0);
            System.out.println("TIPO FICHA SELECCIONADA: "+ fichaSelecc);
            return fichaSelecc;
        }else if(candidatos.size()>1){
            int seleccionRand = rand.nextInt(candidatos.size());//revisar bien esta linea, no vaya ser que obvie algun dato en particular
            int fichaSelecc = candidatos.get(seleccionRand);
            System.out.println("TIPO FICHA SELECCIONADA: "+fichaSelecc);
            return fichaSelecc;
        }
        return 0;//en caso que no haya espaicos nulos
    }
    
    
    
    public void ImprimirProbabilidades(){//testing only
        System.out.println("----PROBABLIDADES ACTUALES-----");
        int pesoTotalActivo =0;
        
        //condicionado a turnos
        if(turnosControlador){
             for(int peso: favorabilidadActiva.values()){
                pesoTotalActivo+=peso;//cuantas fichas hay en total
            }

            for(Map.Entry<Integer, Integer> entry:  favorabilidadActiva.entrySet()){
                int tipo = entry.getKey();
                int peso = entry.getValue();

                double probabilidad = (double) peso/espacioMuestralFijo;
                System.out.printf("Tipo %d: %d/%d (%.2f%%)\n", tipo, peso, espacioMuestralFijo, probabilidad*100);
            }
        }else{
             for(int peso: fContricanteActiva.values()){
                pesoTotalActivo+=peso;//cuantas fichas hay en total
            }

            for(Map.Entry<Integer, Integer> entry:  fContricanteActiva.entrySet()){
                int tipo = entry.getKey();
                int peso = entry.getValue();

                double probabilidad = (double) peso/espacioMuestralFijo;
                System.out.printf("Tipo %d: %d/%d (%.2f%%)\n", tipo, peso, espacioMuestralFijo, probabilidad*100);
            }
            
            
            
            
        }
        
        int pesoNulo = espacioMuestralFijo -pesoTotalActivo;
        double probNula = (double) pesoNulo / espacioMuestralFijo;
        System.out.printf("Nulo (0): %d/%d (%.2f%%) [Espacio vacio]\n", pesoNulo, espacioMuestralFijo, probNula*100);
        System.out.println("Suma Total: 100%");
        
    }
    
    
    private void iniciarTempoCierre(){
        //Timer para que se cierre automaticamente la ventana una vez se obtiene el intento
        Timer timer = new Timer(2000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){ //Ya la estructura del actionPerfomed ejecuta la logica de cerrarse despues del delay
                screen.dispose();
                ((Timer)e.getSource()).stop();
            }         
        });
              
        timer.setRepeats(false);
        timer.start();
    }
            
    public int getLastSelected(){
        return lastSelected;
    }
    
    
    public void cleanLastSelected(){
        lastSelected=5;
    }
    
    
    
    //ELEMENTOS DE GENERACION VISUAL
    public void genVisuals(){
       screen.setSize(800, 600);  //Tamaño standard para menus
       screen.setResizable(false);
       screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       screen.setLocationRelativeTo(null);
       screen.setLayout(null);
       
       
       JPanel ruletaPanel = new JPanel();
       ruletaPanel.setBounds(200, 200, 400, 500);
       JLabel texto = new JLabel("");
       texto.setBounds(0, 0, 400, 500);
       texto.setIcon(ruletaImag);
       ruletaPanel.add(texto);
       
       
       
       
       JButton btGirar = new JButton("GIRAR RULETA");
        btGirar.setBounds(200, 100, 200, 50);
        
        btGirar.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              ImprimirProbabilidades();
              
              System.out.println("");
              int resultado = girarRuleta();
              
              lastSelected=resultado;//guardar el tipo de ficha seleccionada por cada tirada;
              
              switch(resultado){
                  case 1:
                      texto.setIcon(wolfImag);
                      System.out.println("RESULTADO: HOMBRE LOBO");
                      break;
                      
                      
                  case 2:
                      texto.setIcon(vampImag);
                      System.out.println("RESULTADO: VAMPIRO");
                      
                      break;
                      
                  case 3:
                      texto.setIcon(necroImag);
                      System.out.println("RESULTADO: NECROMANCER");
                      
                      break;
                      
                  case 0:
                      texto.setIcon(nullIcon);
                      System.out.println("RESULTADO: ESPACIO VACIO");
                      break;
              }
              
              panel.repaint();
              //tableroController.
              //iniciarTempoCierre();
          }
                    
        });
       screen.add(btGirar);
       screen.add(ruletaPanel);
       screen.setVisible(true);
    }
    
    public void closeVisual(){
        screen.dispose();
    }
    
    
    public void setTablero(generadorTablero tablero){
        tableroController=tablero;
    }
    
    public void setTurnos(boolean cambio){
        turnosControlador=cambio;
    }
    
}
