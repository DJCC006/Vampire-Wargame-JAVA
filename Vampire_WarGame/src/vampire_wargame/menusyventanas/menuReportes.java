/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame.menusyventanas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author David
 */
public class menuReportes {
    
    public menuReportes(){
        JFrame screen = new JFrame();
        screen.setSize(800, 600);  //Tamaño standard para menus
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setLayout(null);
        
        
        JLabel titulo = new JLabel("MENU REPORTES");
        titulo.setBounds(150, 50, 700, 100);
        titulo.setFont(new Font("Serif", Font.BOLD, 50));
        
        
        JButton btRanking = new JButton("Ranking de Jugadores");
        btRanking.setBounds(300, 180, 200, 50);
        
        btRanking.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              screen.dispose();
              ventanaRanking ventana = new ventanaRanking();
             
          }
                    
        });
        
        
        
        JButton btLogs = new JButton("Logs de Partidas");
        btLogs.setBounds(300, 250, 200, 50);
        
        btLogs.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              screen.dispose();
              ventanaLogs ventana = new ventanaLogs();
          }
                    
        });
        
        
        
        
        JButton btSalir = new JButton("Volver");
        btSalir.setBounds(300, 390, 200, 50);
        
        btSalir.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              screen.dispose();
             menuPrincipal ventana = new menuPrincipal();
          }
                    
        });
        
        
        screen.add(btRanking);
        screen.add(btLogs);
        screen.add(btSalir);
        screen.add(titulo);
        
        screen.setVisible(true);
    }
    
    
    public static void main(String[] args) {
        menuReportes ventana= new menuReportes();
    }
}
