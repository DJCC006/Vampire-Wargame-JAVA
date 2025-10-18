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
public class ventanaOponentes {
    public ventanaOponentes(){
        JFrame screen = new JFrame();
        screen.setSize(800, 600);  //Tamaño standard para menus
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setLayout(null);
        
        
        JLabel titulo = new JLabel("SELECCIONE SU OPONENTE");
        titulo.setBounds(100, 50, 700, 100);
        titulo.setFont(new Font("Serif", Font.BOLD, 50));
        
  
        
        JButton btSalir = new JButton("Volver");
        btSalir.setBounds(500, 390, 200, 50);
        
        btSalir.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              screen.dispose();
             menuPrincipal ventana = new menuPrincipal();
          }
                    
        });
        
     
        screen.add(btSalir);
        screen.add(titulo);
        
        screen.setVisible(true);
    }
    
    
    
    public static void main(String[] args) {
        ventanaOponentes ventana = new ventanaOponentes();
    }
}
