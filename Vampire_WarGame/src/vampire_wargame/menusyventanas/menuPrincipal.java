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
import vampire_wargame.menusyventanas.menuInicial;

/**
 *
 * @author David
 */
public class menuPrincipal {
      //Creacion de JFrame y demas elementos
    public menuPrincipal(){
    
        JFrame screen = new JFrame();
        screen.setSize(800, 600);  //Tamaño standard para menus
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setLayout(null);
        
        
        JLabel titulo = new JLabel("MENU PRINCIPAL");
        titulo.setBounds(150, 50, 700, 100);
        titulo.setFont(new Font("Serif", Font.BOLD, 50));
        
        JButton btPlay = new JButton("Jugar VAMPIRE WARGAME");
        btPlay.setBounds(300, 180, 200, 50);
        
        btPlay.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              screen.dispose();
             
          }
                    
        });
        
        
        
        JButton btAccount = new JButton("MI Perfil");
        btAccount.setBounds(300, 250, 200, 50);
        
        btAccount.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              screen.dispose();
              ventanaPerfil ventana = new ventanaPerfil();
          }
                    
        });
        
        
        
        JButton btReportes = new JButton("Reportes");
        btReportes.setBounds(300, 320, 200, 50);
        
        btReportes.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              screen.dispose();
             
          }
                    
        });
        
        JButton btSalir = new JButton("Cerrar Sesion");
        btSalir.setBounds(300, 390, 200, 50);
        
        btSalir.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              screen.dispose();
             menuInicial ventana = new menuInicial();
          }
                    
        });
        
        
        screen.add(btPlay);
        screen.add(btAccount);
        screen.add(btReportes);
        screen.add(btSalir);
        screen.add(titulo);
        
        screen.setVisible(true);
        
    }
    
    
    public static void main(String[] args) {
        menuPrincipal ventana = new menuPrincipal();
    }
}
