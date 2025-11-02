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

/**
 *
 * @author David
 */
public class ruletaGen {
    private Map<Integer, Integer> favorabilidadActiva;
    private Random rand;
    private static final int espacioMuestralFijo= 6;
    private JFrame screen = new JFrame();
    private ImageIcon ruletaImag;
    private ImageIcon wolfImag;
    private ImageIcon vampImag;
    private ImageIcon necroImag;
    private ImageIcon nullIcon;
    public ruletaGen(){
      rand = new Random();
      favorabilidadActiva= new HashMap<>();
      favorabilidadActiva.put(1, 2);//(TIPO FICHA, CANTIDAD DE ESA FICHA)
      favorabilidadActiva.put(2, 2);
      favorabilidadActiva.put(3, 2); 
      //Esto me dice que los tipo de ficha deben que manejarse con ints
      
      ruletaImag= new ImageIcon("src\\resources\\ruletaGIF.gif");
      wolfImag = new ImageIcon("src\\resources\\wolfIcon.png");
      vampImag = new ImageIcon("src\\resources\\vampIcon.png");
      necroImag = new ImageIcon("src\\resources\\deathIcon.png");
      nullIcon = new ImageIcon("src\\resources\\nuledaIcon.png");
      genVisuals();
    }
    
    
    
    public int girarRuleta(){
        int resultado = rand.nextInt(espacioMuestralFijo)+1;
        
        int favAcumulada=0;
        
        for(Map.Entry<Integer, Integer> entrada: favorabilidadActiva.entrySet()){
            int tipoFicha = entrada.getKey();
            int favorabilidad = entrada.getValue();
            
            
            favAcumulada+=favorabilidad;
            
            if(resultado<= favAcumulada){
                return tipoFicha;
            }
        }
        return 0; //0 representando a un espacio vacio
    }
    
    
    
    public boolean reducirPesoFicha(int tipoFicha){
        int favActual = favorabilidadActiva.get(tipoFicha);
        
        if(favActual >0){
            favorabilidadActiva.put(tipoFicha, favActual-1);
            return true;
        }    
        return false;
    }
    
    
    
    public int manejarResultadoNulo(){
        int pesoMinimo=6;
        
        
        //Proceso para buscar el peso / cantidad minima de fichas que hay
        for(int peso: favorabilidadActiva.keySet()){
            if(peso>0 && peso< pesoMinimo){
                pesoMinimo=peso;
            }
        }
        //Encontrar los tipo de ficha dentro de tal rango
        
        ArrayList<Integer> candidatos = new ArrayList<>();
        
        
        for(Map.Entry<Integer, Integer> ficha : favorabilidadActiva.entrySet()){
            if(ficha.getValue()==pesoMinimo){
                candidatos.add(ficha.getKey());
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
        for(int peso: favorabilidadActiva.values()){
            pesoTotalActivo+=peso;//cuantas fichas hay en total
        }
        
        for(Map.Entry<Integer, Integer> entry:  favorabilidadActiva.entrySet()){
            int tipo = entry.getKey();
            int peso = entry.getValue();
            
            double probabilidad = (double) peso/espacioMuestralFijo;
            System.out.printf("Tipo %d: %d/%d (%.2f%%)\n", tipo, peso, espacioMuestralFijo, probabilidad*100);
        }
        
        int pesoNulo = espacioMuestralFijo -pesoTotalActivo;
        double probNula = (double) pesoNulo / espacioMuestralFijo;
        System.out.printf("Nulo (0): %d/%d (%.2f%%) [Espacio vacio]\n", pesoNulo, espacioMuestralFijo, probNula*100);
        System.out.println("Suma Total: 100%");
        
    }
    
    
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
              
              switch(resultado){
                  case 1:
                      System.out.println("RESULTADO: HOMBRE LOBO");
                      texto.setIcon(wolfImag);
                      break;
                      
                  case 2:
                      System.out.println("RESULTADO: VAMPIRO");
                      texto.setIcon(vampImag);
                      break;
                      
                  case 3:
                      System.out.println("RESULTADO: NECROMANCER");
                      texto.setIcon(necroImag);
                      break;
                      
                  case 0:
                      System.out.println("RESULTADO: ESPACIO VACIO");
                      texto.setIcon(nullIcon);
                      break;
              }
             
          }
                    
        });
       
        
        //Se deberia de agregar un timer para que se cierre automaticamente esta ventana 
        
        JButton btVGirar = new JButton("REINICIAR RULETA");//NO DEBERIA IR, SOLO TESTEO
        btVGirar.setBounds(400, 100, 200, 50);
       
        btVGirar.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
             texto.setIcon(ruletaImag);
             reducirPesoFicha(1);
          }
                    
        });
        
        
       
        screen.add(btVGirar);
       screen.add(btGirar);
       screen.add(ruletaPanel);
       screen.setVisible(true);
    }
    
    
    
}
