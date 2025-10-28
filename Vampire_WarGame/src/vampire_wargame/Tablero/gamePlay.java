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
            screen.setSize(1000, 800);  //Tamaño standard para menus
            screen.setResizable(false);
            screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            screen.setLocationRelativeTo(null);
            //screen.setLayout(null);


            generadorTablero tablero = new generadorTablero(110);
            JPanel panelTablero = new JPanel();
            panelTablero.add(tablero);
            panelTablero.setBounds(200, 100, 800, 600);
            
            
            



            screen.add(panelTablero);
            screen.setVisible(true);
        });
        
    }
    
    public static void main(String[] args) {
        gamePlay game = new gamePlay();
    }
    
    
    
    
    
   
    
    
}
