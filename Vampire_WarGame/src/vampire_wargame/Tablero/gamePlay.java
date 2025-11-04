/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame.Tablero;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author David
 */
public class gamePlay {
    
    public gamePlay(){
        SwingUtilities.invokeLater(()-> {
            JFrame screen = new JFrame();
            screen.setSize(2000, 900);  //Tamaño standard para menus
            screen.setResizable(false);
            screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            screen.setLocationRelativeTo(null);
            screen.setLayout(null);

            JPanel ruletaPanel = new JPanel();
            ruletaGen generadorRuleta= new ruletaGen(ruletaPanel);
            
            generadorTablero tablero = new generadorTablero(100, generadorRuleta);
            generadorRuleta.setTablero(tablero);
            JPanel panelTablero = new JPanel();
            panelTablero.setBounds(400, 10, 800, 600);
            //panelTablero.setBackground(Color.red);
            panelTablero.add(tablero);
            
            
            
            
            
            ruletaPanel.setLayout(null);
            ruletaPanel.add(generadorRuleta);
            ruletaPanel.setBounds(60, 10, 350, 400);
            //ruletaPanel.setBackground(Color.red);
            


            screen.add(ruletaPanel);
            screen.add(panelTablero);
            screen.setVisible(true);
        });
        
    }
    
    public static void main(String[] args) {
        gamePlay game = new gamePlay();
    }
    
    
    
    
    
   
    
    
}
