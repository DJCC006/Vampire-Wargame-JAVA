/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame.Tablero;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
            screen.setSize(3000, 900);  //Tamaño standard para menus
            screen.setResizable(false);
            screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            screen.setLocationRelativeTo(null);
            screen.setLayout(null);

            //CONFIG DE RULETA
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
            
            JPanel panelAnuncios = new JPanel();
            panelAnuncios.setBounds(500, 650, 600, 100);
            panelAnuncios.setBackground(Color.GRAY);
            
            //DISPLAYERS DE PLAYERS
            JPanel cementerioPlayer = new JPanel();
            cementerioPlayer.setBounds(1140, 100, 350, 300);
            cementerioPlayer.setBackground(Color.GRAY);
            
            JLabel gravePlayerLB= new JLabel("CEMENTERIO PLAYER");
            gravePlayerLB.setBounds(1140, 30, 350, 80);
            gravePlayerLB.setFont(new Font("Serif", Font.BOLD, 30));

            
            JPanel cementerioContricante = new JPanel();
            cementerioContricante.setBounds(1140, 500, 350, 300);
            cementerioContricante.setBackground(Color.GRAY);
            
            JLabel graveContriLB= new JLabel("CEMENTERIO CONTRICANTE");
            graveContriLB.setBounds(1140, 440, 350, 80);
            graveContriLB.setFont(new Font("Serif", Font.BOLD, 30));
            
            
            
            JButton rendirseBT= new JButton("RENDIRSE");
            rendirseBT.setBounds(60, 500, 300, 80);
            
            screen.add(rendirseBT);
            screen.add(cementerioPlayer);
            screen.add(gravePlayerLB);
            screen.add(graveContriLB);
            screen.add(cementerioContricante);
            screen.add(panelAnuncios);
            screen.add(ruletaPanel);
            screen.add(panelTablero);
            screen.setVisible(true);
        });
        
    }
    
    public static void main(String[] args) {
        gamePlay game = new gamePlay();
    }
    
    
    
    
    
   
    
    
}
