/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame.menusyventanas;

import java.awt.Font;
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
        
        JButton botonLogIn = new JButton("Log In");
        botonLogIn.setBounds(300, 180, 200, 50);
        
        JButton botonCreate = new JButton("Crear Player");
        botonCreate.setBounds(300, 250, 200, 50);
        
        JButton botonSalir = new JButton("Salir");
        botonSalir.setBounds(300, 320, 200, 50);
        
        
        screen.add(botonLogIn);
        screen.add(botonCreate);
        screen.add(botonSalir);
        screen.add(titulo);
        
        screen.setVisible(true);
        
    }
    
    
    public static void main(String[] args) {
        menuPrincipal ventana = new menuPrincipal();
    }
}
