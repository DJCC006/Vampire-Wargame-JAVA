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
public class menuInicial {
    
    
    public menuInicial(){
        //Creacion de JFrame y demas elementos
        JFrame screen = new JFrame();
        screen.setSize(800, 600);  //Tamaño standard para menus
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setLayout(null);
        
        
        JLabel titulo = new JLabel("VAMPIRE WARGAME");
        titulo.setBounds(150, 50, 700, 100);
        titulo.setFont(new Font("Serif", Font.BOLD, 50));
        
        JButton botonLogIn = new JButton("Log In");
        botonLogIn.setBounds(300, 180, 200, 50);
        
        
        botonLogIn.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              screen.dispose();
              ventanaLogIn ventana = new ventanaLogIn();
          }
                    
        });
        
        
        JButton botonCreate = new JButton("Crear Player");
        botonCreate.setBounds(300, 250, 200, 50);
        
        
        
        botonCreate.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              screen.dispose();
              ventanaNewAcc ventana = new ventanaNewAcc();
          }
                    
        });
        
        
        JButton botonSalir = new JButton("Salir");
        botonSalir.setBounds(300, 320, 200, 50);
        
        
        botonSalir.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              screen.dispose();
          }
                    
        });
        
        
        
        screen.add(botonLogIn);
        screen.add(botonCreate);
        screen.add(botonSalir);
        screen.add(titulo);
        
        screen.setVisible(true);
        
    }
    
    
    public static void main(String[] args) {
        menuInicial ventana = new menuInicial();
    }
    
    
}
