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
public class ventanaPerfil {
    public ventanaPerfil(){
        JFrame screen = new JFrame();
        screen.setSize(800, 600);  //Tamaño standard para menus
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setLayout(null);
        
        
        JLabel titulo = new JLabel("MI PERFIL");
        titulo.setBounds(100, 50, 700, 100);
        titulo.setFont(new Font("Serif", Font.BOLD, 50));
        
        //Labels Mostrando informacion de jugador
        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(100, 150, 500, 70);
        usernameLabel.setFont(new Font("Serif", Font.BOLD, 25));
        
        
        JLabel puntosLabel = new JLabel("Puntos: ");
        puntosLabel.setBounds(100, 200, 500, 70);
        puntosLabel.setFont(new Font("Serif", Font.BOLD, 25));
        
        JLabel dateLabel = new JLabel("Fecha de Ingreso: ");
        dateLabel.setBounds(100, 250, 500, 70);
        dateLabel.setFont(new Font("Serif", Font.BOLD, 25));
        
        JLabel statusLabel = new JLabel("Estado de Cuenta: ");
        statusLabel.setBounds(100, 300, 500, 70);
        statusLabel.setFont(new Font("Serif", Font.BOLD, 25));
        
        
        
        //Configuracion de botones
        JButton btChangePssw = new JButton("Cambiar Contraseña");
        btChangePssw.setBounds(500, 250, 200, 50);
        
        btChangePssw.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              screen.dispose();
             
          }
                    
        });
        
        
        
        JButton btBorrar = new JButton("Borrar Cuenta");
        btBorrar.setBounds(500, 320, 200, 50);
        
        btBorrar.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              screen.dispose();
             
          }
                    
        });
        
        JButton btSalir = new JButton("Volver");
        btSalir.setBounds(500, 390, 200, 50);
        
        btSalir.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              screen.dispose();
             menuPrincipal ventana = new menuPrincipal();
          }
                    
        });
        
        screen.add(usernameLabel);
        screen.add(puntosLabel);
        screen.add(dateLabel);
        screen.add(statusLabel);
        screen.add(btChangePssw);
        screen.add(btBorrar);
        screen.add(btSalir);
        screen.add(titulo);
        
        screen.setVisible(true);
    }
    
    
    
    public static void main(String[] args) {
        ventanaPerfil ventana = new ventanaPerfil();
    }
}
